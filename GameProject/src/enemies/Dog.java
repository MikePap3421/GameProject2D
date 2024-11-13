package enemies;
import managers.enemymanager;

import static helpz.constants.Enemies.*;

public class Dog extends Enemy{

    public Dog(float x, float y, int ID, enemymanager em){
        super(x,y,ID,Dog,em);
        setStartingHealth();
    }
}