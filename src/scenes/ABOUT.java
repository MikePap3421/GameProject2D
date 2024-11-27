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
        bMenu = new Mybuttons("Back", 2, 2, 140, 50);
    }

    @Override
    public void render(Graphics g) {
        Color specialBlue = new Color(92, 175, 241);
        g.setColor(specialBlue);
        g.fillRect(0, 0, 1280,960 );
        Font f = new Font("Comic Sans MS", Font.PLAIN, 35);
        g.setFont(f);
        drawButtons(g);
        Font x = new Font("Comic Sans MS", Font.PLAIN, 32);
        g.setFont(x);

        g.drawString("This is a 2D Tower Defence Game developed as a project for  ",190,200);
        g.drawString("the course \"Human-Computer Interaction\" in the third year",190,240);
        g.drawString("at the dept. of Computer Science and Electronic Systems ",190,280);
        g.drawString("Engineering at International Hellenic University. In this ",190,320);
        g.drawString("Game the player is supposed to defend his \"Castle\" in order",190,360);
        g.drawString("to Win. The Enemies are Constantly coming in waves and when",190,400);
        g.drawString("they reach the end of the Road the player loses one life.",190,440);
        g.drawString("The only way to win is by dragging the towers in the map ",190,480);
        g.drawString("for them to shoot and kill the Enemies! This program has been  ",190,520);
        g.drawString("written by Papageorgiou Mixail who was born on July 3th, 2004, ",190,560);
        g.drawString("in Athens , Greece. The software used for the development of ",190,600);
        g.drawString("this program are IntelliJ IDEA as the IDE and pixilart.com ",190,640);
        g.drawString("for the Sprites. The soundtracks were taken from StockTune ",190,680);
        g.drawString("and Chosic.com. ",190,720);
        g.drawString("Thanks For Playing! ",190,780);

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

