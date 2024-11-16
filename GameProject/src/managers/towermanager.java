package managers;

import enemies.Enemy;
import helpz.LoadSave;
import objects.Tower;
import scenes.PLAYING;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class towermanager {

    private PLAYING playing;
    private BufferedImage[] towerimgs;
    private int towerAmount = 0;
    private ArrayList<Tower> towers = new ArrayList<>();

    public towermanager(PLAYING playing){
        this.playing=playing;

        loadtowerimg();
    }

    private void loadtowerimg() {
        BufferedImage atlas= LoadSave.getspriteatlas2();
        towerimgs=new  BufferedImage[3];
        towerimgs[0]=atlas.getSubimage(8*64,3*64,64,64);
        towerimgs[1]=atlas.getSubimage(9*64,3*64,64,64);
        towerimgs[2]=atlas.getSubimage(8*64,4*64,64,64);
    }
    public void addTower(Tower selectedTower, int xPos, int yPos) {
        towers.add(new Tower(xPos, yPos, towerAmount++, selectedTower.getTowertype()));
    }
    public void update(){
        for (Tower t : towers) {
            t.update();
            attackEnemyIfClose(t);
        }
    }

    private void attackEnemyIfClose(Tower t) {
        for (Enemy e : playing.getEnemyManager().getEnemies()) {
            if (e.isAlive())
                if (isEnemyInRange(t, e)) {
                    if(t.isCooldowmOver()) {
                        playing.shootEnemy(t, e);
                        t.resetCooldown();
                    }
                } else {
                    // we do nothing
                }
        }

    }

    private boolean isEnemyInRange(Tower t, Enemy e) {
        float range=helpz.LoadSave.GetHypoDistance(t.getX(),t.getY(),e.getX(),e.getY());
        return range<t.getRange();
    }

    public void draw(Graphics g){
        for (Tower t : towers)
            g.drawImage(towerimgs[t.getTowertype()], t.getX(), t.getY(), null);
    }
    public BufferedImage[] getTowerImgs() {
        return towerimgs;
    }


    public Tower getowerat(int x, int y) {
        for (Tower t : towers) {
            if (t.getX() == x)
                if (t.getY() == y)
                    return t;

        }
        return null;
    }
    public void reset(){
        towers.clear();
        towerAmount=0;
    }
}

