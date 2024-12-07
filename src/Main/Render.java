package Main;

import java.awt.*;

public class Render {

    private game game;


    public Render(game game){
        this.game=game;

    }

    public void render(Graphics g){

        switch(Gamestates.gamestate){

            case MENU:
                game.getMenu().render(g);
                break;
            case SETTINGS:
                game.getSettings().render(g);
                break;
            case PLAYING:
                game.getPlaying().render(g);
                break;
            case ABOUT:
                game.getAbout().render(g);
                break;
            case Game_Over:
                game.getGameOver().render(g);
                break;
            case Game_Win:
                game.getGameWin().render(g);
                break;
            case Game_Paused:
                game.getGamePaused().render(g);
                break;
        }

    }


}
