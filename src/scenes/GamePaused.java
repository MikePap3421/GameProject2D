package scenes;

import Main.Gamestates;
import ui.Mybuttons;

import static Main.Gamestates.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;


public class GamePaused extends gamescene implements scenemethods {

    private Mybuttons bReplay, bMenu,bSettings;
    private SETTINGS settings;

    public GamePaused(Main.game Game,SETTINGS settings) {
        super(Game);
        initButtons();
        this.settings=settings;
    }

    private void initButtons() {

        int w = 200;
        int h = w / 3;
        int x = 1280 / 2 - w / 2;
        int y = 350;
        int yOffset = 150;

        bMenu = new Mybuttons("Menu", x, y, w, h);
        bReplay = new Mybuttons("Replay", x, y + yOffset, w, h);
        bSettings = new Mybuttons("Settings", x,y + yOffset*2, w, h);

    }

    @Override
    public void render(Graphics g) {
        Color specialBlue = new Color(92, 175, 241);
        g.setColor(specialBlue);
        g.fillRect(0, 0, 1280,960 );
        // game over text
        g.setFont(new Font("LucidaSans", Font.BOLD, 80));
        g.setColor(Color.red);
        g.drawString("PAUSED!", 450, 200);
        g.setFont(new Font("LucidaSans", Font.BOLD, 45));
        g.setColor(Color.BLACK);
        g.drawString("Press 'SPACE' to continue", 360, 260);

        // buttons
        g.setFont(new Font("LucidaSans", Font.BOLD, 20));
        bMenu.draw(g);
        bReplay.draw(g);
        bSettings.draw(g);
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
    public void keypressed(KeyEvent e){
        if(e.getKeyCode()==KeyEvent.VK_SPACE || e.getKeyCode()==KeyEvent.VK_ESCAPE ){
            getGame().getPlaying().setPause();
            getGame().changeState("PLAYING");
            Setgamestate(PLAYING);
        }
    }

    @Override
    public void mouseclicked(int x, int y) {
        if (bMenu.getBounds().contains(x, y)) {
            Setgamestate(MENU);
            resetAll();
        } else if (bReplay.getBounds().contains(x, y)) {
            replayGame();
            getGame().changeState("PLAYING");
        }else if(bSettings.getBounds().contains(x,y)) {
            settings.setPreviousScene(1);
            Gamestates.Setgamestate(SETTINGS);
        }
    }

    @Override
    public void mousemoved(int x, int y) {
        bMenu.setmouseover(false);
        bReplay.setmouseover(false);
        bSettings.setmouseover(false);

        if (bMenu.getBounds().contains(x, y)) {
            bMenu.setmouseover(true);
        }else if (bReplay.getBounds().contains(x, y)) {
            bReplay.setmouseover(true);
        }else if (bSettings.getBounds().contains(x,y)){
            bSettings.setmouseover(true);
        }
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
        bSettings.resetbooleans();
    }


}

