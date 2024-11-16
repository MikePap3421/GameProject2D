package helpz;

public class constants {

    public static class Projectiles {
        public static final int ARROW = 0;
        public static final int BOMB = 1;
        public static final int CHAINS = 2;

        public static float GetSpeed(int type) {
            switch (type) {
                case ARROW:
                    return 10f;
                case BOMB:
                    return 9f;
                case CHAINS:
                    return 12f;
            }
            return 0f;
        }
    }


    public static class Direction {
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }

    public static class Towers{
        public static final int CANNON = 0;
        public static final int ARCHER= 1;
        public static final int WIZARD = 2;

        public static int getTowerCost(int towerType){
            switch (towerType) {
                case CANNON:
                    return 65;
                case ARCHER:
                    return 30;
                case WIZARD:
                    return 45;
            }
            return 0;
        }

        public static int GetStartDmg(int towerType) {
            switch (towerType) {
                case CANNON:
                    return 25;
                case ARCHER:
                    return 20;
                case WIZARD:
                    return 10;
            }
            return 0;
        }
        public static String GetName(int towerType) {
            switch (towerType) {
                case CANNON:
                    return "Cannon";
                case ARCHER:
                    return "Archer";
                case WIZARD:
                    return "Wizard";
            }
            return "";
        }

        public static float GetDefaultRange(int towerType) {
            switch (towerType) {
                case CANNON:
                    return 100;
                case ARCHER:
                    return 120;
                case WIZARD:
                    return 150;
            }

            return 0;
        }

        public static float GetDefaultCooldown(int towerType) {
            switch (towerType) {
                case CANNON:
                    return 40;
                case ARCHER:
                    return 30;
                case WIZARD:
                    return 20;
            }

            return 0;
        }
    }

    public static class Enemies{
        public static final int MiniOrc=0;
        public static final int BigOrc=1;
        public static final int Dog=2;

        public static int getReward(int enemyType){
            switch (enemyType) {
                case MiniOrc:
                    return 3;
                case BigOrc:
                    return 5;
                case Dog:
                    return 4;
            }
            return 0;
        }

        public static float GetSpeed(int enemyType) {
            switch (enemyType) {
                case MiniOrc:
                    return 0.8f;
                case BigOrc:
                    return 0.7f;
                case Dog:
                    return 0.9f;
            }
            return 0;
        }

        public static int GetStartHealth(int enemyType) {
            switch (enemyType) {
                case MiniOrc:
                    return 150;
                case BigOrc:
                    return 200;
                case Dog:
                    return 130;
            }
            return 0;
        }
    }


    public static class Tiles{
        public static final int WATER_TILE = 0;
        public static final int GRASS_TILE = 1;
        public static final int ROAD_TILE = 2;
    }

}