package Main;

import inputs.Keyboardlistener;
import inputs.Mouselistener;
import inputs.SoundHandler;
import javax.swing.*;
import java.awt.*;

public class Gamescreen extends JPanel {

    private Main.game game;
    private  Dimension size;

    private Mouselistener mouselistener;
    private Keyboardlistener keyboardlistener;
    SoundHandler player = new SoundHandler();


    public Gamescreen(game game) {
        this.game= game;
        setpanelsize();
    }

    public void initInputs(){
        mouselistener=new Mouselistener(game);
        keyboardlistener=new Keyboardlistener(game);

        addMouseListener(mouselistener);
        addMouseMotionListener(mouselistener);
        addKeyListener(keyboardlistener);

        requestFocus();

    }

    private void setpanelsize() {
        size = new Dimension(1280, 960);
        setMinimumSize(size);
        setMaximumSize(size);
        setPreferredSize(size);

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.getRender().render(g);
        Font f =  g.getFont().deriveFont( 20.0f );
        g.setFont(f);

    }

    public SoundHandler getPlayer() {
        return player;
    }

}





