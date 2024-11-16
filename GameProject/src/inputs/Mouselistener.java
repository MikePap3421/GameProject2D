package inputs;
import Main.Gamestates;
import Main.game;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouselistener implements MouseListener, MouseMotionListener {

    private game game;

    public Mouselistener(game game){
        this.game=game;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        switch (Gamestates.gamestate) {
            case MENU:
                game.getMenu().mouseclicked(e.getX(), e.getY());
                break;
            case PLAYING:
                game.getPlaying().mouseclicked(e.getX(), e.getY());
                break;
            case SETTINGS:
                game.getSettings().mouseclicked(e.getX(), e.getY());
                break;
            case ABOUT:
                game.getAbout().mouseclicked(e.getX(), e.getY());
                break;
            case Game_Over:
                game.getGameOver().mouseclicked(e.getX(), e.getY());
                break;
            default:
                break;

        }
        }


    @Override
    public void mousePressed(MouseEvent e) {
        switch (Gamestates.gamestate) {
            case MENU:
                game.getMenu().mousepressed(e.getX(), e.getY());
                break;
            case PLAYING:
                game.getPlaying().mousepressed(e.getX(), e.getY());
                break;
            case SETTINGS:
                game.getSettings().mousepressed(e.getX(), e.getY());
                break;
            case ABOUT:
                game.getAbout().mousepressed(e.getX(), e.getY());
                break;
            case Game_Over:
                game.getGameOver().mousepressed(e.getX(), e.getY());
                break;
            default:
                break;

        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch (Gamestates.gamestate) {
            case MENU:
                game.getMenu().mousereleased(e.getX(), e.getY());
                break;
            case PLAYING:
                game.getPlaying().mousereleased(e.getX(), e.getY());
                break;
            case SETTINGS:
                game.getSettings().mousereleased(e.getX(), e.getY());
                break;
            case ABOUT:
                game.getAbout().mousereleased(e.getX(), e.getY());
                break;
            case Game_Over:
                game.getGameOver().mousereleased(e.getX(), e.getY());
                break;
            default:
                break;

        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        switch (Gamestates.gamestate) {
            case MENU:
                game.getMenu().mousereleased(e.getX(), e.getY());
                break;
            case PLAYING:
                game.getPlaying().mouseDragged(e.getX(), e.getY());
                break;
            case SETTINGS:
                game.getSettings().mousereleased(e.getX(), e.getY());
                break;
            case ABOUT:
                game.getAbout().mousereleased(e.getX(), e.getY());
                break;
            default:
                break;

        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        switch (Gamestates.gamestate) {
            case MENU:
                game.getMenu().mousemoved(e.getX(), e.getY());
                break;
            case PLAYING:
                game.getPlaying().mousemoved(e.getX(), e.getY());
                break;
            case SETTINGS:
                game.getSettings().mousemoved(e.getX(), e.getY());
                break;
            case ABOUT:
                game.getAbout().mousemoved(e.getX(), e.getY());
                break;
            case Game_Over:
                game.getGameOver().mousemoved(e.getX(), e.getY());
                break;
            default:
                break;
            }
        }
    }
