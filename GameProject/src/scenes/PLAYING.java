package scenes;
import Main.game;
import enemies.Enemy;
import helpz.LoadSave;
import helpz.constants;
import helpz.levelbuilder;
import managers.*;
import objects.PathPoint;
import objects.Tower;
import ui.ActionBar;
import java.awt.*;
import java.awt.event.KeyEvent;
import static helpz.constants.Tiles.GRASS_TILE;


public class PLAYING extends gamescene implements scenemethods {

    private int[][] lvl;
    private ActionBar actionBar;
    private tilemanager tilemanager;
    private enemymanager enemyManager;
    private game game;
    PathPoint start;
    private towermanager towermanager;
    private Tower selectedTower;
    private LoadSave LoadSave;
    private int mouseX, mouseY;
    private ProjectileManager projManager;
    private WaveManager waveManager;
    private int goldTick;
    private boolean Pause;



    public PLAYING(game game) {
        super(game);
        PathPoint end=new PathPoint(19,8);
        this.game = game;
        lvl = levelbuilder.getleveldata();
        tilemanager = new tilemanager();
        actionBar = new ActionBar(0, 832, 1289, 130, this);
        enemyManager = new enemymanager(this,start,end);
        towermanager= new towermanager(this);
        projManager=new ProjectileManager(this);
        waveManager=new WaveManager(this);

    }

    public void update() {
        if(!Pause) {
            goldTick++;
            if (goldTick % (60 * 3) == 0)
                actionBar.addGold(5);

            if (isAllEnemiesDead()) {
                if (isThereMoreWaves()) {
                    waveManager.startWaveTimer();
                    if (isWaveTimerOver()) {
                        waveManager.increaseWaveindex();
                        enemyManager.getEnemies().clear();
                        waveManager.resetEnemyIndex();
                    }
                }
            }
            LoadSave.updateintex();
            enemyManager.update();
            towermanager.update();
            projManager.update();
        }
    }

    private boolean isWaveTimerOver() {
        return waveManager.isWaveTimerOver();

    }

    private boolean isThereMoreWaves() {
        return waveManager.isThereMoreWaves();
    }

    private boolean isAllEnemiesDead() {
        if(waveManager.isThereMoreEnemiesInWave()){
            return false;
        }
        for (Enemy e: enemyManager.getEnemies()){
            if(e.isAlive())
                return false;
        }
        return true;
    }

    public void setSelectedTower(Tower selectedTower) {
        this.selectedTower=selectedTower;
    }

    @Override
    public void render(Graphics g) {
        Font f = new Font("Comic Sans MS", Font.PLAIN, 25);
        g.setFont(f);
        for (int y = 0; y < 13; y++) {
            for (int x = 0; x < 20; x++) {
                int id = lvl[y][x];
                g.drawImage(tilemanager.getsprite(id), x * 64, y * 64, null);
            }
        }
        enemyManager.draw(g);
        towermanager.draw(g);
        projManager.draw(g);
        drawSelectedTower(g);
        actionBar.draw(g);
        drawHighlight(g);
        drawWaveInfos(g);
        if(Pause)
            drawPause(g);
    }

    private void drawPause(Graphics g) {
        g.setFont(new Font("LucidaSans",Font.BOLD,100));
        g.drawString("PAUSED",400,265);
        g.setFont(new Font("LucidaSans",Font.BOLD,40));
        g.drawString("Press 'Space' to Continue",360,310);
    }

    private void drawWaveInfos(Graphics g) {

    }

    private void drawHighlight(Graphics g) {
            g.setColor(Color.WHITE);
            g.drawRect(mouseX, mouseY, 64, 64);
    }

    private void drawSelectedTower(Graphics g) {
        if (selectedTower != null)
            g.drawImage(towermanager.getTowerImgs()[selectedTower.getTowertype()], mouseX, mouseY, null);
    }



    public int getTileType(int x, int y) {
        int xCord = x / 64;
        int yCord = y / 64;

        if(xCord < 0 || xCord >= 20)
            return 0;
        if(yCord < 0 || yCord >= 13)
            return 0;



        int id = lvl[y / 64][x / 64];
        return game.getTileManager().getTile(id).getTileType();
    }

    @Override
    public void mouseclicked(int x, int y) {
        actionBar.mouseClicked(x,y);
    }

    private void removeGold(int towertype) {
        actionBar.payForTower(towertype);
    }

    private Tower getTowerAt(int x, int y) {
        return towermanager.getowerat(x,y);
    }

    public WaveManager getWaveManager() {
        return waveManager;
    }
    public void keypressed(KeyEvent e){
        if(e.getKeyCode()==KeyEvent.VK_SPACE){
            Pause=!isPause();
        }
    }

    @Override
    public void mousemoved(int x, int y) {
        if (y >= 830 && !Pause)
            actionBar.mouseMoved(x, y);
        else {
            mouseX = (x / 64) * 64;
            mouseY = (y / 64) * 64;
        }
    }


    private boolean isTileGrass(int x, int y) {
        int id = lvl[y / 64][x / 64];
        int tileType = game.getTileManager().getTile(id).getTileType();
        return tileType == GRASS_TILE;
    }

    @Override
    public void mousepressed(int x, int y) {
        if (y >= 830 && !Pause)
            actionBar.mousePressed(x, y);

    }

    public void mouseDragged(int x, int y) {
            if (y <= 830 && !Pause)
                actionBar.mouseDragged(x, y);
            if  (y<830) {
                mouseX = (x / 64) * 64;
                mouseY = (y / 64) * 64;
            }
    }

    @Override
    public void mousereleased(int x, int y) {
        if(!Pause) {
            if (y > 830) {
                actionBar.setSelectedTower(null);
                actionBar.mousereleased(mouseX, mouseY);
            }
            if (actionBar.getSelectedTower() != null) {
                if (isTileGrass(mouseX, mouseY)) {
                    if (getTowerAt(mouseX, mouseY) == null) {
                        towermanager.addTower(actionBar.getSelectedTower(), mouseX, mouseY);
                        removeGold(actionBar.getSelectedTower().getTowertype());
                        actionBar.mousereleased(mouseX, mouseY);
                        actionBar.setSelectedTower(null);
                    }
                }
            } else {
                Tower t = getTowerAt(mouseX, mouseY);
                actionBar.displayTower(t);
            }
            actionBar.mousereleased(mouseX, mouseY);
        }
    }



    public towermanager getTowerManager() {
        return towermanager;
    }
    public enemymanager getEnemyManager(){
        return enemyManager;
    }
    public void shootEnemy(Tower t, Enemy e) {
        projManager.newProjectile(t,e);
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public void rewardPlayer(int enemyType) {
        actionBar.addGold(constants.Enemies.getReward(enemyType));
    }

    public boolean isPause() {
        return Pause;
    }

    public void removeOneLife() {
        actionBar.removeOneLife();
    }
    public void resetEverything(){
        actionBar.resetEverything();

        enemyManager.reset();
        towermanager.reset();
        waveManager.reset();
        projManager.reset();

        mouseX=0;
        mouseY=0;
        selectedTower=null;
        goldTick=0;
        Pause=false;
    }
}
