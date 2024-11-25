package enemies;
import managers.enemymanager;

import static helpz.constants.Enemies.*;

public class BigOrc extends Enemy{

    public BigOrc(float x, float y, int ID, enemymanager em){
        super(x,y,ID,BigOrc,em);
        setStartingHealth();
    }

}