package Main;

public enum Gamestates {

    PLAYING,MENU,SETTINGS,ABOUT;

    public  static Gamestates gamestate = MENU;

    public  static void Setgamestate(Gamestates state){
        gamestate =state;
    }
}
