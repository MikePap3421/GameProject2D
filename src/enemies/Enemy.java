package enemies;

import helpz.constants;
import managers.enemymanager;

import java.awt.*;

import static helpz.constants.Direction.*;

public abstract class Enemy {

    float x,y;
    private Rectangle bounds;
    private int health;
    private int maxHealth;
    private int id;
    private int enemytype;
    private int lastDir;
    private boolean alive=true;
    private int slowTickLimit=120;
    private int slowTick=slowTickLimit;
    private enemymanager enemyManager;

    public Enemy(float x,float y,int id,int enemytype,enemymanager enemyManager){
        this.x=x;
        this.y=y;
        this.id=id;
        this.enemytype=enemytype;
        bounds=new Rectangle((int)x,(int)y,64,64);
        lastDir=RIGHT;
        this.enemyManager=enemyManager;
    }

    protected void setStartingHealth(){
        health= constants.Enemies.GetStartHealth(enemytype);
        maxHealth=health;
    }

    public void move(float speed, int dir) {
        lastDir = dir;

        if(slowTick<slowTickLimit){
            slowTick++;
            speed *=0.6f;
        }

        switch (dir) {
            case LEFT:
                this.x -= speed;
                break;
            case UP:
                this.y -= speed;
                break;
            case RIGHT:
                this.x += speed;
                break;
            case DOWN:
                this.y += speed;
                break;
        }
        updateHitbox();
    }

    private void updateHitbox() {
        bounds.x=(int)x-5;
        bounds.y=(int)y-5;
    }


    public void setPos(int x, int y) {

        this.x = x;
        this.y = y;
    }
    public float gethealthBar(){
        return health/(float)maxHealth;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public int getId() {
        return id;
    }

    public int getEnemytype() {
        return enemytype;
    }

    public int getHealth() {
        return health;
    }

    public int getLastDir() {
        return lastDir;
    }

    public void hurt(int dmg) {
        this.health-=dmg;
        if(health<=0) {
            alive = false;
            enemyManager.rewardPlayer(enemytype);
        }
    }

    public boolean isAlive() {
        return alive;
    }

    public  void slow(){
        slowTick=0;
    }

    public void kill(){
        alive=false;
        health=0;
    }
}
