package scenes;

import helpz.constants;
import ui.Mybuttons;
import java.awt.*;
import static Main.Gamestates.*;

public class SETTINGS extends gamescene implements scenemethods{
    private Mybuttons bBack,bEasy,bMedium,bHard,bSoundOn,bSoundOff;
    private int Difficulty=0;
    private int Audio=1;
    int previousScene=0;

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
        bBack = new Mybuttons("Back", 2, 2, 140, 50);
        bEasy = new Mybuttons("Easy", 370 , 280, 140, 50);
        bMedium = new Mybuttons("Medium",570 , 280, 140, 50);
        bHard = new Mybuttons("Hard", 770, 280 , 140, 50);

        bSoundOn = new Mybuttons("ON", 470, 580 , 140, 50);
        bSoundOff = new Mybuttons("OFF", 670, 580 , 140, 50);


    }

    @Override
    public void render(Graphics g) {
        Color specialBlue = new Color(92, 175, 241);
        g.setColor(specialBlue);
        g.fillRect(0, 0, 1280,960 );
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
        bBack.draw(g);
        g.drawString("Difficulty",570,180);
        g.drawString("Audio",600,480);
        drawSelectedButtons(g);
        Font b = new Font("Comic Sans MS", Font.PLAIN, 25);
        g.setFont(b);
        drawNotPermited(g);
    }
    private void drawNotPermited(Graphics g){
        if (previousScene==1)
            g.drawString("Cannot change Settings while you are in the middle of the Game!",320,240);

    }

    private void drawSelectedButtons(Graphics g) {
        g.setColor(Color.red);
        if(getDifficulty()==0){
            g.drawRect(bEasy.x, bEasy.y, bEasy.width, bEasy.height);
            g.drawRect(bEasy.x+1,bEasy.y+1,bEasy.width-2,bEasy.height-2);
            g.drawRect(bEasy.x+2,bEasy.y+2,bEasy.width-4,bEasy.height-4);
        }else if (getDifficulty()==1){
            g.drawRect(bMedium.x, bMedium.y, bMedium.width, bMedium.height);
            g.drawRect(bMedium.x+1,bMedium.y+1,bMedium.width-2,bMedium.height-2);
            g.drawRect(bMedium.x+2,bMedium.y+2,bMedium.width-4,bMedium.height-4);
        }else {
            g.drawRect(bHard.x, bHard.y, bHard.width, bHard.height);
            g.drawRect(bHard.x+1,bHard.y+1,bHard.width-2,bHard.height-2);
            g.drawRect(bHard.x+2,bHard.y+2,bHard.width-4,bHard.height-4);
        }
        if(getAudio()==1){
            g.drawRect(bSoundOn.x, bSoundOn.y, bSoundOn.width, bSoundOn.height);
            g.drawRect(bSoundOn.x+1,bSoundOn.y+1,bSoundOn.width-2,bSoundOn.height-2);
            g.drawRect(bSoundOn.x+2,bSoundOn.y+2,bSoundOn.width-4,bSoundOn.height-4);
        }else if (getAudio()==0){
            g.drawRect(bSoundOff.x, bSoundOff.y, bSoundOff.width, bMedium.height);
            g.drawRect(bSoundOff.x+1,bSoundOff.y+1,bSoundOff.width-2,bSoundOff.height-2);
            g.drawRect(bSoundOff.x+2,bSoundOff.y+2,bSoundOff.width-4,bSoundOff.height-4);
        }
    }


    @Override
    public void mouseclicked(int x, int y) {
        if (bBack.getBounds().contains(x, y)) {
            if(getPreviousScene()==0)
                Setgamestate(MENU);
            else if(getPreviousScene()==1)
                Setgamestate(Game_Paused);
        } else if (bEasy.getBounds().contains(x, y)){
            if(getPreviousScene()==0) {
                Difficulty = 0;
                constants.setDifficulty(Difficulty);
                getGame().getPlaying().getActionBar().SetDifficulty(Difficulty);
            }
        } else if (bMedium.getBounds().contains(x, y)){
            if(getPreviousScene()==0) {
                Difficulty = 1;
                constants.setDifficulty(Difficulty);
                getGame().getPlaying().getActionBar().SetDifficulty(Difficulty);
            }
        } else if (bHard.getBounds().contains(x, y)){
            if(getPreviousScene()==0){
            Difficulty = 2;
            constants.setDifficulty(Difficulty);
            getGame().getPlaying().getActionBar().SetDifficulty(Difficulty);
            }
        } else if (bSoundOn.getBounds().contains(x, y)){
            Audio = 1;
            getGame().setAudio(Audio);
        } else if (bSoundOff.getBounds().contains(x, y)){
            Audio = 0;
            getGame().setAudio(Audio);
        }
    }


    @Override
    public void mousemoved(int x, int y) {
        bBack.setmouseover(false);
        bEasy.setmouseover(false);
        bMedium.setmouseover(false);
        bHard.setmouseover(false);
        bSoundOn.setmouseover(false);
        bSoundOff.setmouseover(false);
        
        if (bBack.getBounds().contains(x, y)) {
            bBack.setmouseover(true);
        } else if (bEasy.getBounds().contains(x,y)) {
            bEasy.setmouseover(true);
        }else if (bMedium.getBounds().contains(x,y)) {
            bMedium.setmouseover(true);
        }else if (bHard.getBounds().contains(x,y)) {
            bHard.setmouseover(true);
        }else if (bSoundOn.getBounds().contains(x,y)) {
            bSoundOn.setmouseover(true);
        }else if (bSoundOff.getBounds().contains(x,y)) {
            bSoundOff.setmouseover(true);
        }
    }

    @Override
    public void mousepressed(int x, int y) {
        if (bBack.getBounds().contains(x, y))
            bBack.setMousepressed(true);
    }

    @Override
    public void mousereleased(int x, int y) {
        bBack.resetbooleans();
    }

    public int getAudio() {
        return Audio;
    }

    public int getDifficulty() {
        return Difficulty;
    }

    public void setPreviousScene(int x) {
        previousScene=x;
    }

    public int getPreviousScene() {
        return previousScene;
    }
}

