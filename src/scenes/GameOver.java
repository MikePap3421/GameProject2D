package scenes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import Main.game;
import ui.Mybuttons;
import static Main.Gamestates.*;

public class GameOver extends gamescene implements scenemethods {

    private Mybuttons bReplay, bMenu;

    public GameOver(game Game) {
        super(Game);
        initButtons();
    }

    private void initButtons() {

        int w = 200;
        int h = w / 3;
        int x = 1280 / 2 - w / 2;
        int y = 350;
        int yOffset = 150;

        bMenu = new Mybuttons("Menu", x, y, w, h);
        bReplay = new Mybuttons("Replay", x, y + yOffset, w, h);

    }

    @Override
    public void render(Graphics g) {
        Color specialBlue = new Color(92, 175, 241);
        g.setColor(specialBlue);
        g.fillRect(0, 0, 1280,960 );
        // game over text
        g.setFont(new Font("LucidaSans", Font.BOLD, 80));
        g.setColor(Color.red);
        g.drawString("Game Over!", 410, 200);

        // buttons
        g.setFont(new Font("LucidaSans", Font.BOLD, 20));
        bMenu.draw(g);
        bReplay.draw(g);
    }

    private void replayGame() {
        // reset everything
        resetAll();

        // change state to playing
        Setgamestate(PLAYING);

    }

    private void resetAll() {
        getGame().getPlaying().resetEverything();
    }

    @Override
    public void mouseclicked(int x, int y) {
        if (bMenu.getBounds().contains(x, y)) {
            Setgamestate(MENU);
            resetAll();
        } else if (bReplay.getBounds().contains(x, y))
            replayGame();
    }

    @Override
    public void mousemoved(int x, int y) {
        bMenu.setmouseover(false);
        bReplay.setmouseover(false);

        if (bMenu.getBounds().contains(x, y))
            bMenu.setmouseover(true);
        else if (bReplay.getBounds().contains(x, y))
            bReplay.setmouseover(true);
    }

    @Override
    public void mousepressed(int x, int y) {
        if (bMenu.getBounds().contains(x, y))
            bMenu.setMousepressed(true);
        else if (bReplay.getBounds().contains(x, y))
            bReplay.setMousepressed(true);

    }

    @Override
    public void mousereleased(int x, int y) {
        bMenu.resetbooleans();
        bReplay.resetbooleans();

    }


}
