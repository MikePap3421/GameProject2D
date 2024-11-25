package objects;

import java.awt.image.BufferedImage;

public class tile {
    private BufferedImage[] sprite;
    private int id, tileType;

    public tile(BufferedImage sprite, int id, int tileType) {
        this.sprite = new BufferedImage[1];
        this.sprite[0] = sprite;
        this.id = id;
        this.tileType = tileType;

    }


    public int getTileType() {
        return tileType;
    }

    public BufferedImage getSprite() {
        return sprite[0];
    }

    public int getId() {
        return id;
    }

    public BufferedImage getsprite(){
       return  sprite[0];
   }
}
