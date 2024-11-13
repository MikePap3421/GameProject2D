package scenes;

import Main.game;
import ui.Mybuttons;

import java.awt.*;
import static Main.Gamestates.*;


public class SETTINGS extends gamescene implements scenemethods{
    private Mybuttons bMenu,bDifficulty,bEasy,bMedium,bHard,bSoundOn,bSoundOff;
    public SETTINGS(Main.game game) {
        super(game);
        initbuttons();
    }

    private void initbuttons() {
        int w = 200;
        int h = w / 3;
        int x = 1280 / 2 - w / 2;
        int y = 150;
        int yOffset = 150;
        bMenu = new Mybuttons("Back", 2, 2, 140, 50);
        bEasy = new Mybuttons("Easy", 370 , 280, 140, 50);
        bMedium = new Mybuttons("Medium",570 , 280, 140, 50);
        bHard = new Mybuttons("Hard", 770, 280 , 140, 50);

        bSoundOn = new Mybuttons("ON", 470, 580 , 140, 50);
        bSoundOff = new Mybuttons("OFF", 670, 580 , 140, 50);


    }

    @Override
    public void render(Graphics g) {
        drawButtons(g);
    }
    private void drawButtons(Graphics g) {
        Font x = new Font("Comic Sans MS", Font.PLAIN, 25);
        g.setFont(x);
        bEasy.draw(g);
        bMedium.draw(g);
        bHard.draw(g);
        bSoundOn.draw(g);
        bSoundOff.draw(g);
        Font f = new Font("Comic Sans MS", Font.PLAIN, 35);
        g.setFont(f);
        bMenu.draw(g);
        g.drawString("Difficulty",570,180);
        g.drawString("Audio",600,480);

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

