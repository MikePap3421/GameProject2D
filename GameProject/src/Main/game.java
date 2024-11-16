package Main;

import managers.tilemanager;
import scenes.*;

import javax.swing.*;

public class game extends JFrame implements Runnable {
    private Gamescreen gamescreen;

    private  final double fps_set=200.0;
    private  final double ups_set=80.0;

    private  int updates;
    private long lastimeups;
    private Thread gamethread;
    private  Render render;
    private MENU menu;
    private SETTINGS settings;
    private PLAYING playing;
    private ABOUT about;
    private tilemanager tilemanager;
    private GameOver gameOver;


    public game(){

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    
        initclasses();
        add(gamescreen);
        pack();
    }

    private void initclasses() {
        render = new Render(this);
        gamescreen = new Gamescreen(this);
        menu= new MENU(this);
        playing=new PLAYING(this);
        settings=new SETTINGS(this);
        tilemanager = new tilemanager();
        about=new ABOUT(this);
        gameOver=new GameOver(this);
    }


    private void start(){
        gamethread= new Thread(this){};
        gamethread.start();
    }


    private void callups() {

        if(System.currentTimeMillis() - lastimeups >= 1000){
            System.out.println("UPS: " + updates);
            updates=0;
            lastimeups=System.currentTimeMillis();
        }
    }

    private void update() {

        updates++;
        switch (Gamestates.gamestate){
            case PLAYING:{
                playing.update();
                break;
            }
            case MENU:{
                break;
            }
            case SETTINGS:{
                break;
            }
            case ABOUT:{
                break;
            }
        }


    }


    public static void main(String[] args) {
            game game=new game();
            game.gamescreen.initInputs();
            game.start();
        }

    @Override
    public void run() {
        double timeperframe=1000000000.0 / fps_set;
        double timeperupdate =1000000000.0 / ups_set;
        long lastframe=System.nanoTime();
        long lastimecheck = System.currentTimeMillis();
        long lastupdate = System.nanoTime();

        int frames=0;
        int updates=0;


        while (true) {
            if (System.nanoTime() - lastframe >= timeperframe) {
                lastframe = System.nanoTime();
                repaint();
                frames++;
            }

            if(System.nanoTime() - lastupdate >= timeperupdate){
                update();
                lastupdate=System.nanoTime();
                updates++;
            }
            if(System.currentTimeMillis() - lastimecheck >= 1000){
                System.out.println("FPS: " + frames + "| UPS: " + updates);
                frames=0;
                updates=0;
                lastimecheck=System.currentTimeMillis();
            }
        }
    }

    public Render getRender(){
        return render;
    }

    public SETTINGS getSettings() {
        return settings;
    }

    public MENU getMenu() {
        return menu;
    }

    public PLAYING getPlaying() {
        return playing;
    }
    public tilemanager getTileManager() {
        return tilemanager;
    }

    public ABOUT getAbout() {
        return about;
    }

    public GameOver getGameOver() {
        return gameOver;
    }
}