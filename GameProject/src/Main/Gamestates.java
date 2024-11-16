package Main;

import scenes.GameOver;

public enum Gamestates {

    PLAYING,MENU,SETTINGS,ABOUT,Game_Over;

    public  static Gamestates gamestate = MENU;

    public  static void Setgamestate(Gamestates state){
        gamestate =state;
    }
}
