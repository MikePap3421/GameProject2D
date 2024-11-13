package scenes;

import Main.game;
import ui.Mybuttons;

import java.awt.*;
import static Main.Gamestates.*;


public class ABOUT extends gamescene implements scenemethods{
    private Mybuttons bMenu;
    public ABOUT(Main.game game) {
        super(game);
        initbuttons();
    }

    private void initbuttons() {
        bMenu = new Mybuttons("Menu", 2, 2, 100, 30);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, 960, 960);

        drawButtons(g);
    }
    private void drawButtons(Graphics g) {
        bMenu.draw(g);
    }


    @Override
    public void mouseclicked(int x, int y) {
        if (bMenu.getBounds().contains(x, y))
            Setgamestate(MENU);
    }

    @Override
    public void mousemoved(int x, int y) {
        bMenu.setmouseover(false);
        if (bMenu.getBounds().contains(x, y))
            bMenu.setmouseover(true);
    }

    @Override
    public void mousepressed(int x, int y) {
        if (bMenu.getBounds().contains(x, y))
            bMenu.setMousepressed(true);
    }

    @Override
    public void mousereleased(int x, int y) {
        bMenu.resetbooleans();
    }
}

