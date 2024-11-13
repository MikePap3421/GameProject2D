package Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

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
        }

    }


}
