package Main;

import inputs.SoundHandler;
import managers.tilemanager;
import scenes.*;
import ui.Bar;

import javax.swing.*;

public class game extends JFrame implements Runnable {
    private Gamescreen gamescreen;

    private final double fps_set = 200.0;
    private final double ups_set = 80.0;

    private int updates;
    private Thread gamethread;
    private Render render;
    private MENU menu;
    private SETTINGS settings;
    private PLAYING playing;
    private ABOUT about;
    private tilemanager tilemanager;
    private GameOver gameOver;
    private GameWin gameWin;
    private GamePaused gamePaused;
    private int Audio=1;
    private Bar b;

    private String gameState = "MENU"; // Default state
    private SoundHandler soundManager;

    public game() {
        setTitle("Tower Defense Game");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocation(350, 30);
        setTitle("The Last Empire");
        initClasses();
        add(gamescreen);
        pack();
        if(Audio==1)
            soundManager.playSound("res/GameMenuMusic.wav");
        else
            soundManager.stopSound();
    }

    private void initClasses() {
        render = new Render(this);
        gamescreen = new Gamescreen(this);
        menu = new MENU(this);
        playing = new PLAYING(this);
        settings = new SETTINGS(this);
        tilemanager = new tilemanager();
        about = new ABOUT(this);
        gameOver = new GameOver(this);
        gameWin=new GameWin(this);
        gamePaused=new GamePaused(this,settings);
        soundManager = new SoundHandler();

    }

    public void changeState(String newState) {
        if (gameState.equals(newState)) {
            return;
        }

        soundManager.stopSound();

        gameState = newState;

        switch (gameState) {
            case "MENU":
                if(Audio==1)
                    soundManager.playSound("res/GameMenuMusic.wav");
                break;
            case "PLAYING":
                if(Audio==1)
                    soundManager.playSound("res/GameMusic.wav");
                break;
            case "Game_Over":
                if(Audio==1)
                    soundManager.playSound("res/lossMusic.wav");
                break;
            case "Game_Win":
                if(Audio==1)
                    soundManager.playSound("res/WinMusic.wav");
                break;
        }
    }

    private void start() {
        gamethread = new Thread(this);
        gamethread.start();
    }

    private void update() {
        updates++;
        switch (Gamestates.gamestate) {
            case PLAYING:
                playing.update();
                break;
            case MENU:

                break;
            case SETTINGS:

                break;
            case ABOUT:

                break;
        }
    }

    public static void main(String[] args) {
        game game = new game();
        game.gamescreen.initInputs();
        game.start();
    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / fps_set;
        double timePerUpdate = 1000000000.0 / ups_set;
        long lastFrame = System.nanoTime();
        long lastUpdate = System.nanoTime();
        long lastTimeCheck = System.currentTimeMillis();

        int frames = 0;
        int updates = 0;

        while (true) {
            if (System.nanoTime() - lastFrame >= timePerFrame) {
                lastFrame = System.nanoTime();
                repaint();
                frames++;
            }

            if (System.nanoTime() - lastUpdate >= timePerUpdate) {
                update();
                lastUpdate = System.nanoTime();
                updates++;
            }

            if (System.currentTimeMillis() - lastTimeCheck >= 1000) {
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
                lastTimeCheck = System.currentTimeMillis();
            }
        }
    }

    public Render getRender() {
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

    public GameWin getGameWin() {
        return gameWin;
    }

    public GamePaused getGamePaused() {
        return gamePaused;
    }

    public SoundHandler getSoundManager() {
        return soundManager;
    }
    public SETTINGS GetSettings(){
        return settings;
    }
    public void setAudio(int x){
        Audio=x;
        if(Audio==0)
            soundManager.stopSound();
        else
            soundManager.playSound("res/GameMenuMusic.wav");
    }
}
