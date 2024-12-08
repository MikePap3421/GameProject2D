package managers;

import java.util.ArrayList;
import java.util.Arrays;
import Main.Gamestates;
import events.Wave;
import scenes.PLAYING;

public class WaveManager {

    private PLAYING playing;
    private ArrayList<Wave> waves = new ArrayList<>();
    private int enemySpawnTickLimit = 40 * 1;
    private int enemySpawnTick = enemySpawnTickLimit;
    private int enemyIndex, waveIndex;
    private int waveTickLimit=60*5;
    private int waveTick=0;
    private boolean waveStarTimer,waveTickTimerOver;

    public WaveManager(PLAYING playing) {
        this.playing = playing;
        createWaves();
    }

    public void update() {
        if (enemySpawnTick < enemySpawnTickLimit)
            enemySpawnTick++;
        if(waveStarTimer){
            waveTick++;
            if(waveTick>= waveTickLimit){
                waveTickTimerOver=true;
            }
        }gameWon();
    }
    public void increaseWaveindex(){
        waveIndex++;
        waveTick=0;
        waveStarTimer=false;
        waveTickTimerOver=false;
    }

    public int getNextEnemy() {
        enemySpawnTick = 0;
        return waves.get(waveIndex).getEnemyList().get(enemyIndex++);
    }

    private void createWaves() {
        waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0,0,0))));
        waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0,2,1,0,2,0,1))));
        waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0,1,0,1,0,0,2,1,2))));
        waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1,1,1,1,0,1,1,0,2,2))));
        waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1,1,1,1,0,1,1,1,0,1,1,0,1,2,2,2,2))));
    }

    public ArrayList<Wave> getWaves() {
        return waves;
    }

    public boolean isTimeForNewEnemy() {
        return enemySpawnTick >= enemySpawnTickLimit;
    }

    public boolean isThereMoreEnemiesInWave() {
        return enemyIndex < waves.get(waveIndex).getEnemyList().size();
    }

    public boolean isThereMoreWaves() {
        return waveIndex +1<waves.size();
    }

    public void startWaveTimer() {
        waveStarTimer = true;
    }

    public boolean isWaveTimerOver() {
        return waveTickTimerOver;
    }

    public void resetEnemyIndex() {
        enemyIndex=0;
    }

    public int getWaveIndex() {
        return waveIndex;
    }
    public float getTimeLeft(){
        float secondsleft=waveTickLimit-waveTick;
        return secondsleft/60.0f;
    }

    public boolean isWaveTimerStarted() {
        return  waveStarTimer;
    }
    public void reset(){
        waves.clear();
        createWaves();

        enemyIndex=0;
        waveIndex=0;

        waveStarTimer=false;
        waveTickTimerOver=false;

        waveTick=0;
        enemySpawnTick=enemySpawnTickLimit;
    }
    public void gameWon(){
        if(!isThereMoreWaves())
            if(playing.isAllEnemiesDead()) {
                playing.getGame().changeState("Game_Win");
                Gamestates.Setgamestate(Gamestates.Game_Win);
            }
    }
}
