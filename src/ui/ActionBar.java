package ui;

import java.awt.*;
import java.text.DecimalFormat;
import helpz.constants;
import helpz.constants.Towers;
import objects.Tower;
import scenes.PLAYING;
import static Main.Gamestates.*;


public class ActionBar extends Bar {

    private PLAYING playing;
    private Mybuttons bMenu;

    private Mybuttons[] towerButtons;
    private Tower selectedTower;
    private Tower displayedTower;
    private boolean draggingTower = false;

    private DecimalFormat formatter;

    private int gold=50;
    private boolean showTowerCost;
    private int towerCostType;


    private int lives=10;
    private int difficulty;

    public ActionBar(int x, int y, int width, int height, PLAYING playing) {
        super(x, y, width, height);
        this.playing = playing;
        formatter=new DecimalFormat("0.0");
        initButtons();
    }
    public void resetEverything() {
        lives=setLives(difficulty);
        towerCostType=0;
        showTowerCost=false;
        gold=50;
        selectedTower=null;
        displayedTower=null;
    }

    private void initButtons() {

        bMenu = new Mybuttons("Menu", 5, 835, 150, 50);

        towerButtons = new Mybuttons[3];
        int w = 70;
        int h = 70;
        int xStart = 200;
        int yStart = 850;
        int xOffset = (int) (w * 1.5f);

        for (int i = 0; i < towerButtons.length; i++)
            towerButtons[i] = new Mybuttons("", xStart + xOffset * i, yStart, w, h, i);

    }

    private void drawButtons(Graphics g) {
        bMenu.draw(g);

        for (Mybuttons b : towerButtons) {
            g.setColor(Color.gray);
            g.fillRect(b.x, b.y, b.width, b.height);
            g.drawImage(playing.getTowerManager().getTowerImgs()[b.getId()], b.x, b.y, b.width, b.height, null);
            drawButtonFeedback(g, b);
        }
    }

    public void draw(Graphics g) {

        // Background
        g.setColor(new Color(220, 123, 15));
        g.fillRect(x, y, width, height);

        // Buttons
        drawButtons(g);
        drawDisplayedTower(g);
        drawWaveInfo(g);
        drawGoldAmount(g);
        if(showTowerCost)
            drawTowerCost(g);

        if (draggingTower == true && selectedTower != null) {
            g.drawImage(playing.getTowerManager().getTowerImgs()[selectedTower.getTowertype()],
                    selectedTower.getX(), selectedTower.getY(), null);
        }

        g.setFont(new Font("LucidasSans",Font.BOLD,30));
        g.setColor(Color.black);
        g.drawString("Lives :"+ lives, 5,950);

    }

    private void drawTowerCost(Graphics g) {
        g.setFont(new Font("LucidasSans",Font.BOLD,20));
        g.setColor(Color.gray);
        g.fillRect(500,850,160,70);
        g.setColor(Color.black);
        g.drawRect(500,850,160,70);
        g.drawString(""+ getTowerCostName(),505,870);
        g.drawString("Cost: "+getTowerCost()+"g ",505,891 );

        if(isTowerCostMoreThanGold()){
            g.setColor(Color.red);
            g.drawString("Cant Afford",505,913);
        }
    }
    public void removeOneLife(){
        lives--;
        if(lives<=0)
            Setgamestate(Game_Over);
    }

    private boolean isTowerCostMoreThanGold() {
        return getTowerCost()>gold;
    }

    private String getTowerCostName() {
        return constants.Towers.GetName(towerCostType);
    }

    private int getTowerCost() {
        return constants.Towers.getTowerCost(towerCostType);

    }

    private void drawGoldAmount(Graphics g) {
        g.drawString("Gold: "+gold+"g",5,920);
    }

    private void drawWaveInfo(Graphics g) {
        g.setColor(Color.black);
        g.setFont(new Font("LucidasSans",Font.BOLD,30));
        drawWaveTimerInfo(g);
        drawEnemiesLeftInfo(g);
        drawWavesLeftInfo(g);

    }

    private void drawWavesLeftInfo(Graphics g) {
        int current=playing.getWaveManager().getWaveIndex();
        int size = playing.getWaveManager().getWaves().size();
        g.drawString("Wave " + (current +1)+ " / " + size ,990 ,900);
    }

    private void drawEnemiesLeftInfo(Graphics g) {
        int remaining=playing.getEnemyManager().getAliveEnemies();
        g.drawString("Enemies Left: "+ remaining ,990,940);
    }

    public void drawWaveTimerInfo(Graphics g){
        if(playing.getWaveManager().isWaveTimerStarted()){
            g.setColor(Color.black);
            float timeLeft= playing.getWaveManager().getTimeLeft();
            String formatedText=formatter.format(timeLeft);
            g.drawString("Time Left : " + formatedText ,990,860);
        }
    }

    private void drawDisplayedTower(Graphics g) {
        if (displayedTower != null) {
            g.setColor(Color.gray);
            g.fillRect(760, 845, 220, 85);
            g.setColor(Color.black);
            g.drawRect(760, 845, 220, 85);
            g.drawRect(765, 850, 50, 50);
            g.drawImage(playing.getTowerManager().getTowerImgs()[displayedTower.getTowertype()], 765, 850, 50, 50, null);
            g.setFont(new Font("LucidaSans", Font.BOLD, 16));
            g.drawString("" + Towers.GetName(displayedTower.getTowertype()), 820, 860);
            g.drawString("ID: " + displayedTower.getId(), 820, 875);
            g.drawString("DMG: " + constants.Towers.getTowerDmgType(displayedTower.getTowertype()), 820, 891);
            g.drawString("ABILITY: " + constants.Towers.getTowerAbilityType(displayedTower.getTowertype()), 820, 908);
            drawDisplayedTowerRange(g);
            drawDisplayedTowerBorder(g);
        }

    }
    private void drawDisplayedTowerBorder(Graphics g) {

        g.setColor(Color.CYAN);
        g.drawRect(displayedTower.getX(), displayedTower.getY(), 64, 64);

    }

    private void drawDisplayedTowerRange(Graphics g) {
        g.setColor(Color.white);
        g.drawOval(displayedTower.getX() + 32 - (int) (displayedTower.getRange())*2 / 2, displayedTower.getY() + 32 - (int) (displayedTower.getRange())*2 / 2, (int) displayedTower.getRange()*2,
                (int) displayedTower.getRange()*2);

    }

    public void mouseClicked(int x, int y) {
        if (bMenu.getBounds().contains(x, y)) {
            Setgamestate(MENU);
            playing.resetEverything();
            playing.getGame().changeState("MENU");
        }
    }

    public void mouseMoved(int x, int y) {
        bMenu.setmouseover(false);
        showTowerCost=false;
        for (Mybuttons b : towerButtons)
            b.setmouseover(false);

        if (bMenu.getBounds().contains(x, y))
            bMenu.setmouseover(true);
        else {
            for (Mybuttons b : towerButtons)
                if (b.getBounds().contains(x, y)) {
                    b.setmouseover(true);
                    showTowerCost=true;
                    towerCostType=b.getId();
                    return;
                }
        }
    }

    public void mousePressed(int x, int y) {
        if (bMenu.getBounds().contains(x, y)) {
            bMenu.setMousepressed(true);
        } else {
            for (Mybuttons b : towerButtons) {
                if (b.getBounds().contains(x, y)) {
                    b.setMousepressed(true);
                    if(!isGoldEnoughForTower(b.getId()))
                        return;
                    selectedTower = new Tower(playing.getMouseX(), playing.getMouseY(), -1, b.getId());
                    draggingTower = true;
                    return;
                }
            }
        }
    }

    private boolean isGoldEnoughForTower(int towertype) {
        return gold>=helpz.constants.Towers.getTowerCost(towertype);
    }

    public void mouseDragged(int x, int y) {
        if (draggingTower==true && selectedTower != null) {
            selectedTower.setX(playing.getMouseX());
            selectedTower.setY(playing.getMouseY());
        }
    }



    public void mousereleased(int x, int y) {
        bMenu.resetbooleans();
        for (Mybuttons b : towerButtons) {
            selectedTower = null;
            draggingTower = false;
            b.resetbooleans();
        }

    }


    public void displayTower(Tower t) {
        displayedTower = t;
    }

    public void payForTower(int towertype) {
        this.gold-=constants.Towers.getTowerCost(towertype);
    }

    public Tower getSelectedTower() {
        return selectedTower;
    }

    public void setSelectedTower(Tower selectedTower) {
        this.selectedTower = selectedTower;
    }

    public void addGold(int reward) {
        this.gold+=reward;
    }

    public int setLives(int difficulty) {
        if(difficulty==0)
            lives=10;
        else if (difficulty==1)
            lives=5;
        else
            lives=3;

        return  lives;
    }

    public void SetDifficulty(int diff){
        difficulty=diff;
        lives=setLives(difficulty);
    }

}
