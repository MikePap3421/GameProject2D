package objects;

import helpz.constants;

public class Tower {

    private int x,y,id,towertype,cdTick,dmg;
    private float range,cooldown;

    public Tower(int x,int y,int id,int towertype){
        this.x=x;
        this.y=y;
        this.id=id;
        this.towertype=towertype;
        setDefaultDmg();
        setDefaultRange();
        setDefaultCooldown();
    }

    public void update(){
        cdTick++;
    }

    private void setDefaultRange() {
        range= constants.Towers.GetDefaultRange(towertype);
    }

    private void setDefaultCooldown() {
        cooldown= constants.Towers.GetDefaultCooldown(towertype);
    }

    private void setDefaultDmg() {
        dmg= constants.Towers.GetStartDmg(towertype);
    }


    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getTowertype() {
        return towertype;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setTowertype(int towertype) {
        this.towertype = towertype;
    }

    public float getCooldown() {
        return cooldown;
    }

    public int getDmg() {
        return dmg;
    }

    public float getRange() {
        return range;
    }

    public boolean isCooldowmOver() {
        return cdTick>=cooldown;
    }

    public void resetCooldown() {
        cdTick=0;
    }
}
