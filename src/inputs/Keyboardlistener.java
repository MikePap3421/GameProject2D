package inputs;
import static Main.Gamestates.*;
import Main.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboardlistener implements KeyListener {
    private game game;

    public Keyboardlistener(game game){
        this.game=game;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(gamestate==PLAYING)
            game.getPlaying().keypressed(e);
        else if(gamestate==Game_Paused)
            game.getGamePaused().keypressed(e);

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
