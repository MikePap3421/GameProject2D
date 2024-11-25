package Main;

import inputs.SoundHandler;

public enum Gamestates {

    PLAYING,MENU,SETTINGS,ABOUT,Game_Over,Game_Win;

    public  static Gamestates gamestate = MENU;

    public static void Setgamestate(Gamestates state){
        gamestate =state;
    }



}
