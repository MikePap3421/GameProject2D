package enemies;
import managers.enemymanager;

import static helpz.constants.Enemies.*;

public class MiniOrc extends Enemy{

    public MiniOrc(float x, float y, int ID, enemymanager em){
        super(x,y,ID,MiniOrc,em);
        setStartingHealth();
    }
}
