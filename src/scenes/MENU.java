package scenes;


import ui.Mybuttons;
import static Main.Gamestates.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static Main.Gamestates.Setgamestate;

public class MENU extends gamescene implements scenemethods{

    private BufferedImage img;
    private ArrayList<BufferedImage> sprites = new ArrayList<>();
    private SETTINGS settings=new SETTINGS(getGame());

    private Mybuttons bplaying,bsettings,bquit,babout;

    public MENU(Main.game game) {
        super(game);
        importImg();
        loadsprites();
        inbuttons();
    }

    public void inbuttons(){
        int w = 200;
        int h = w / 3;
        int x = 1280 / 2 - w / 2;
        int y = 350;
        int yOffset = 150;


        bplaying = new Mybuttons("Play", x, y, w, h);
        bsettings = new Mybuttons("Settings", x,y + yOffset, w, h);
        babout = new Mybuttons("About", x,y + yOffset* 2, w, h);
        bquit = new Mybuttons("Quit", x, y + yOffset * 3, w, h);
    }

    @Override
    public void render(Graphics g) {
        Color specialBlue = new Color(92, 175, 241);
        g.setColor(specialBlue);
        g.fillRect(0, 0, 1280,960 );
        Font f = new Font("Comic Sans MS", Font.BOLD, 25);
        g.setFont(f);
        drawButtons(g);


    }
    private void drawButtons(Graphics g) {
        bplaying.draw(g);
        bsettings.draw(g);
        bquit.draw(g);
        babout.draw(g);
        g.setColor(Color.black);
        Font y = new Font("Comic Sans MS", Font.BOLD, 90);
        g.setFont(y);
        g.drawString("Game Name",400,200);


    }

    @Override
    public void mouseclicked(int x, int y) {
        if (bplaying.getBounds().contains(x, y)) {
            Setgamestate(PLAYING);
            getGame().changeState("PLAYING");
        } else if (bsettings.getBounds().contains(x, y)) {
            getGame().getSettings().setPreviousScene(0);
            Setgamestate(SETTINGS);
        } else if (bquit.getBounds().contains(x, y)) {
            System.exit(0);
        }else if(babout.getBounds().contains(x, y))
            Setgamestate(ABOUT);
    }

    @Override
    public void mousemoved(int x, int y) {
        bplaying.setmouseover(false);
        bsettings.setmouseover(false);
        bquit.setmouseover(false);
        babout.setmouseover(false);

        if (bplaying.getBounds().contains(x, y)) {
            bplaying.setmouseover(true);
        } else if (bsettings.getBounds().contains(x, y)) {
            bsettings.setmouseover(true);
        } else if (bquit.getBounds().contains(x, y)) {
            bquit.setmouseover(true);
        }else if (babout.getBounds().contains(x, y)) {
            babout.setmouseover(true);
        }
    }

    @Override
    public void mousepressed(int x, int y) {
        if (bplaying.getBounds().contains(x, y)) {
            bplaying.setMousepressed(true);
        } else if (bsettings.getBounds().contains(x, y)) {
            bsettings.setMousepressed(true);
        } else if (bquit.getBounds().contains(x, y)) {
            bquit.setMousepressed(true);
        } else if (bquit.getBounds().contains(x, y)) {
            bquit.setMousepressed(true);
        }
    }

    @Override
    public void mousereleased(int x, int y) { resetButtons();}

        private void resetButtons() {
            bplaying.resetbooleans();
            bsettings.resetbooleans();
            bquit.resetbooleans();
            babout.resetbooleans();
        }


    private void importImg() {

        InputStream is = getClass().getResourceAsStream("/pixil-frame-0.png");
        try {
            img= ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadsprites() {

        for (int i = 0; i < 10; i++) {
            for (int x = 0; x < 10; x++) {
                sprites.add(img.getSubimage(x * 64, i * 64, 64, 64));
            }
        }
    }
}
