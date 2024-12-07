package helpz;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;


public class LoadSave  {
    private static int  imgTicks=0,imgIndex=0;

    public static BufferedImage getspriteatlas2() {
        InputStream[] IS=new InputStream[2];
        BufferedImage img = null;
        IS[0] = LoadSave.class.getClassLoader().getResourceAsStream("pixil-frame-0.png");
        IS[1] = LoadSave.class.getClassLoader().getResourceAsStream("pixil-frame-1.png");


        try {
            img = ImageIO.read(IS[imgIndex]);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return img;
    }

    public static void updateintex(){
        imgTicks++;
        if(imgTicks>=12){
            imgTicks=0;
            imgIndex++;
            if(imgIndex>=2){
                imgIndex=0;
            }
        }
    }





    public static int GetHypoDistance(float x1, float y1, float x2, float y2) {

        float xDiff = Math.abs(x1 - x2);
        float yDiff = Math.abs(y1 - y2);

        return (int) Math.hypot(xDiff, yDiff);

    }

}