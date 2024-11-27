package managers;

import helpz.ImgFix;
import objects.tile;
import helpz.LoadSave;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import static helpz.constants.Tiles.*;

public class tilemanager {

    public tile GRASS,WATER,ROAD,ROADcorner,ROADcorner2,ROADcorner3,ROADcorner4;
    public BufferedImage atlas;
    public ArrayList<tile> tiles=new ArrayList<>();

    public tilemanager(){
        loadatlas();
        createtiles();
    }

    private void createtiles(){
        int id=0;
        tiles.add(GRASS= new tile(getsprite(8,1),id++,GRASS_TILE)); //0
        tiles.add(WATER= new tile(getsprite(0,6),id++,WATER_TILE)); //1
        tiles.add(ROAD= new tile(getsprite(9,0),id++,ROAD_TILE));  //2
        tiles.add(ROAD= new tile(ImgFix.getRotImg((getsprite(9,0)),90,1),id++,ROAD_TILE));  //3
        tiles.add(ROADcorner= new tile(getsprite(8,0),id++,ROAD_TILE)); //4
        tiles.add(ROADcorner2=new tile(ImgFix.getRotImg((getsprite(8,0)),90,1),id++,ROAD_TILE)); //5
        tiles.add(ROADcorner3=new tile(ImgFix.getRotImg((getsprite(8,0)),90,2),id++,ROAD_TILE)); //6
        tiles.add(ROADcorner4=new tile(ImgFix.getRotImg((getsprite(8,0)),90,3),id++,ROAD_TILE)); //7
        tiles.add(ROAD= new tile(getsprite(5,2),id++,WATER_TILE));  //8
        tiles.add(ROAD= new tile(getsprite(7,1),id++,WATER_TILE));  //9
        tiles.add(ROAD= new tile(getsprite(5,3),id++,WATER_TILE));  //10
        tiles.add(ROAD= new tile(getsprite(0,3),id++,WATER_TILE));  //11
        tiles.add(ROAD= new tile(getsprite(4,3),id++,WATER_TILE));  //12
        tiles.add(ROAD= new tile(getsprite(4,2),id++,WATER_TILE));  //13
        tiles.add(ROAD= new tile(getsprite(3,2),id++,WATER_TILE));  //14
        tiles.add(ROAD= new tile(getsprite(4,0),id++,WATER_TILE));  //15
        tiles.add(ROAD= new tile(getsprite(0,2),id++,WATER_TILE));  //16
        tiles.add(ROAD= new tile(getsprite(0,3),id++,WATER_TILE));  //17
        tiles.add(ROAD= new tile(getsprite(5,4),id++,WATER_TILE));  //18
        tiles.add(ROAD= new tile(getsprite(0,5),id++,WATER_TILE));  //19
        tiles.add(ROAD= new tile(getsprite(0,0),id++,WATER_TILE));  //20
        tiles.add(ROAD= new tile(getsprite(0,1),id++,WATER_TILE));  //21
        tiles.add(ROAD= new tile(getsprite(0,4),id++,WATER_TILE));  //22
        tiles.add(ROAD= new tile(getsprite(0,0),id++,WATER_TILE));  //23




    }

    private void loadatlas(){
        atlas= LoadSave.getspriteatlas2();
    }
    public tile getTile(int id) {
        return tiles.get(id);
    }

    public BufferedImage getsprite(int id) {
        return tiles.get(id).getSprite();
    }

    public BufferedImage getsprite(int x,int y) {
        return atlas.getSubimage(x*64,y*64,64,64);
    }

}
