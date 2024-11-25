package helpz;


public class constants {
    private static int currentDifficulty = 0;

    public static void setDifficulty(int difficulty) {
        currentDifficulty = difficulty;
    }

    public static class Projectiles {
        public static final int ARROW = 0;
        public static final int BOMB = 1;
        public static final int CHAINS = 2;

        public static float GetSpeed(int type) {
            switch (type) {
                case ARROW:
                    return 14f;
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

    public static class Towers {


        public static final int CANNON = 0;
        public static final int ARCHER= 1;
        public static final int WIZARD = 2;

        public static String getTowerDmgType(int towerType){
            switch (towerType) {
                case CANNON: return "High";
                case ARCHER: return "Low";
                case WIZARD: return "Very Low";
            }
            return "none";
        }

        public static String getTowerAbilityType(int towerType){
            switch (towerType) {
                case CANNON: return "DMG";
                case ARCHER: return "DMG";
                case WIZARD: return "SLOW";
            }
            return "none";
        }

        public static int getTowerCost(int towerType) {
            switch (currentDifficulty) {
                case 0: // Easy
                    switch (towerType) {
                        case CANNON: return 50;
                        case ARCHER: return 35;
                        case WIZARD: return 50;
                    }
                    break;
                case 1: // Medium
                    switch (towerType) {
                        case CANNON: return 55;
                        case ARCHER: return 35;
                        case WIZARD: return 55;
                    }
                    break;
                case 2: // Hard
                    switch (towerType) {
                        case CANNON: return 60;
                        case ARCHER: return 40;
                        case WIZARD: return 60;
                    }
                    break;
            }
            return 0;
        }


    public static int GetStartDmg(int towerType) {
            switch (towerType) {
                case CANNON:
                    return 65;
                case ARCHER:
                    return 20;
                case WIZARD:
                    return 5;
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
            switch (currentDifficulty) {
                case 0:
                    switch (towerType) {
                        case CANNON:
                            return 120;
                        case ARCHER:
                            return 150;
                        case WIZARD:
                            return 170;
                    }break;
                case 1:
                    switch (towerType) {
                        case CANNON:
                            return 110;
                        case ARCHER:
                            return 140;
                        case WIZARD:
                            return 160;
                    }break;
                case 2:
                    switch (towerType) {
                        case CANNON:
                            return 110;
                        case ARCHER:
                            return 120;
                        case WIZARD:
                            return 150;
                    }break;
            }return 0;
        }

        public static float GetDefaultCooldown(int towerType) {
            switch (towerType) {
                case CANNON:
                    return 55;
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
            switch (currentDifficulty) {
                case 0:
                    switch (enemyType) {
                        case MiniOrc:
                            return 2;
                        case BigOrc:
                            return 4;
                        case Dog:
                            return 2;
                    }break;
                case 1:
                    switch (enemyType) {
                        case MiniOrc:
                            return 1;
                        case BigOrc:
                            return 3;
                        case Dog:
                            return 1;
                    }break;
                case 2:
                    switch (enemyType) {
                        case MiniOrc:
                            return 1;
                        case BigOrc:
                            return 2;
                        case Dog:
                            return 1;
                    }break;
            }
            return 0;
        }

        public static float GetSpeed(int enemyType) {
            switch (enemyType) {
                case MiniOrc:
                    return 1.1f;
                case BigOrc:
                    return 1.0f;
                case Dog:
                    return 1.4f;
            }
            return 0;
        }

        public static int GetStartHealth(int enemyType) {
            switch (currentDifficulty) {
                case 0:
                    switch (enemyType) {
                        case MiniOrc:
                            return 120;
                        case BigOrc:
                            return 220;
                        case Dog:
                            return 120;
                    }break;
                case 1:
                    switch (enemyType) {
                        case MiniOrc:
                            return 130;
                        case BigOrc:
                            return 270;
                        case Dog:
                            return 130;
                    }break;
                case 2:
                    switch (enemyType) {
                        case MiniOrc:
                            return 140;
                        case BigOrc:
                            return 280;
                        case Dog:
                            return 140;
                    }break;
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