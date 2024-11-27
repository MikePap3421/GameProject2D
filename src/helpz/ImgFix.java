package helpz;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImgFix {

    public static BufferedImage getRotImg(BufferedImage img, int rotAngle,int times){

        int w=img.getWidth();
        int h=img.getHeight();

        BufferedImage newImg=new BufferedImage(w,h,img.getType());
        Graphics2D g2=newImg.createGraphics();

        switch (times){
            case 1:
                g2.rotate(Math.toRadians(rotAngle),w/2,h/2);
                g2.drawImage(img,0,0,null);
                g2.dispose();
            case 2:
                g2.rotate(Math.toRadians(rotAngle*2),w/2,h/2);
                g2.drawImage(img,0,0,null);
                g2.dispose();
            case 3:
                g2.rotate(Math.toRadians(rotAngle*3),w/2,h/2);
                g2.drawImage(img,0,0,null);
                g2.dispose();

        }


        return newImg;
    }
}
