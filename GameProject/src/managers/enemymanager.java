package managers;

import enemies.*;
import enemies.Enemy;
import helpz.LoadSave;
import helpz.constants.*;
import objects.PathPoint;
import scenes.PLAYING;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static helpz.constants.Direction.*;
import static helpz.constants.Enemies.*;
import static helpz.constants.Tiles.*;

public class enemymanager {

    private PLAYING playing;
    private BufferedImage[] enemyimgs;
    private Enemy testenemy;
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private PathPoint start,end;
    private int hpBarWidth=20;

    public enemymanager(PLAYING playing, PathPoint start, PathPoint end){
        this.playing=playing;
        enemyimgs=new BufferedImage[6];
        this.end=end;
        this.start = start;

        loadenemyimg();

    }


    private void loadenemyimg() {
        BufferedImage atlas= LoadSave.getspriteatlas2();
        enemyimgs[0]=atlas.getSubimage(9*64,2*64,64,64);
        enemyimgs[1]=atlas.getSubimage(9*64,1*64,64,64);
        enemyimgs[2]=atlas.getSubimage(7*64,3*64,64,64);
        enemyimgs[3]=atlas.getSubimage(6*64,6*64,64,64);
        enemyimgs[4]=atlas.getSubimage(4*64,6*64,64,64);
        enemyimgs[5]=atlas.getSubimage(9*64,3*64,64,64);

    }

    public void update() {
        updatewavemanager();
        if(isTimeForNewEnemy()){
            spawnEnemy();
        }

        loadenemyimg();
        for (Enemy e : enemies) {
            if(e.isAlive()){
            updateenemymove(e);

            }
            }
        }

    private void updatewavemanager() {
        playing.getWaveManager().update();
    }

    private void spawnEnemy() {
        addEnemy(playing.getWaveManager().getNextEnemy());
    }

    private boolean isTimeForNewEnemy() {
        if(playing.getWaveManager().isTimeForNewEnemy()){
            if(playing.getWaveManager().isThereMoreEnemiesInWave())
                return true;
        }
        return false;
    }


    public void updateenemymove(Enemy e) {
        if (e.getLastDir() == -1)
            setNewDirectionAndMove(e);

        int newX = (int) (e.getX() + getSpeedAndWidth(e.getLastDir(),e.getEnemytype()));
        int newY = (int) (e.getY() + getSpeedAndHeight(e.getLastDir(),e.getEnemytype()));

        if (getTileType(newX, newY) == ROAD_TILE) {
            e.move(GetSpeed(e.getEnemytype()), e.getLastDir());
        } else if (isAtEnd(e)) {
            e.kill();
            playing.removeOneLife();
        } else {
            setNewDirectionAndMove(e);
        }
    }

    private void setNewDirectionAndMove(Enemy e) {
        int dir = e.getLastDir();

        int xCord = (int) (e.getX() / 64);
        int yCord = (int) (e.getY() / 64);

        fixEnemyOffsetTile(e, dir, xCord, yCord);

        if (isAtEnd(e)){
            System.out.println("telos");
            e.kill();
            playing.removeOneLife();
            return;}

        if (dir == LEFT || dir == RIGHT) {
            int newY = (int) (e.getY() + getSpeedAndHeight(UP,e.getEnemytype()));
            if (getTileType((int) e.getX(), newY) == ROAD_TILE)
                e.move(GetSpeed(e.getEnemytype()), UP);
            else
                e.move(GetSpeed(e.getEnemytype()), DOWN);
        } else {
            int newX = (int) (e.getX() + getSpeedAndWidth(RIGHT,e.getEnemytype()));
            if (getTileType(newX, (int) e.getY()) == ROAD_TILE)
                e.move(GetSpeed(e.getEnemytype()), RIGHT);
            else
                e.move(GetSpeed(e.getEnemytype()), LEFT);

        }

    }

    private void fixEnemyOffsetTile(Enemy e, int dir, int xCord, int yCord) {
        switch (dir) {
            case RIGHT:
                if (xCord < 19)
                    xCord++;
                break;
            case DOWN:
                if (yCord < 13)
                    yCord++;
                break;
        }

        e.setPos(xCord * 64, yCord * 64);

    }

    private boolean isAtEnd(Enemy e) {

        if (e.getX() == end.getxCord() * 64) {
            if (e.getY() == end.getyCord() * 64)
                return true;
            }
            return false;
    }

    private int getTileType(int x, int y) {
        return playing.getTileType(x, y);
    }

    private float getSpeedAndHeight(int dir,int enemytype) {
        if (dir == UP)
            return -GetSpeed(enemytype);
        else if (dir == DOWN)
            return GetSpeed(enemytype) + 64;

        return 0;
    }

    private float getSpeedAndWidth(int dir,int enemytype) {
        if (dir == LEFT)
            return -GetSpeed(enemytype);
        else if (dir == RIGHT)
            return GetSpeed(enemytype)+64;

        return 0;
    }

    public void addEnemy(int enemytype) {
        int x=0;
        int y=10*64;

       switch (enemytype) {
           case MiniOrc:
               enemies.add(new MiniOrc(x, y, 0,this));
               break;
           case BigOrc:
               enemies.add(new BigOrc(x, y, 0,this));
               break;
           case Dog:
               enemies.add(new Dog(x, y, 0,this));
               break;
       }
    }

    public void draw(Graphics g) {
       for (Enemy e : enemies) {
           if (e.isAlive()) {
               drawenemy(e, g);
               drawHealthBar(e, g);
           }
       }

    }

    private void drawHealthBar(Enemy e, Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int)e.getX()+32-(getNewBarWidth(e)/2),(int)e.getY()-6,getNewBarWidth(e),3);
    }
    private int getNewBarWidth(Enemy e){
        return (int)(hpBarWidth*e.gethealthBar()+10);
    }


    private void drawenemy(Enemy e, Graphics g) {
        if(e.getLastDir()==LEFT) {
            g.drawImage(enemyimgs[e.getEnemytype()+3], (int) e.getX(), (int) e.getY(), null);
        }else if(e.getLastDir()==RIGHT) {
            g.drawImage(enemyimgs[e.getEnemytype()], (int) e.getX(), (int) e.getY(), null);
        }else
            g.drawImage(enemyimgs[e.getEnemytype()], (int) e.getX(), (int) e.getY(), null);

    }
    public ArrayList<Enemy> getEnemies(){
        return enemies;
    }

    public int getAliveEnemies() {
        int size=0;
        for(Enemy e :enemies){
            if(e.isAlive())
                size++;
        }
        return size;
    }

    public void rewardPlayer(int enemytype) {
        playing.rewardPlayer(enemytype);
    }
    public void reset(){
        enemies.clear();
    }
}
