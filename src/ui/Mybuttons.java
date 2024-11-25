package ui;

import java.awt.*;

public class Mybuttons {

    private  boolean mouseover,mousepressed;
    public int x;
    public int y;
    public int width;
    public int height;
    public int id;
    private String text;
    private Rectangle bounds;

    public Mybuttons(String text , int x , int y , int width , int height ){

        this.text=text;
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        this.id=-1;

        initbounds();
    }
    public Mybuttons(String text, int x, int y, int width, int height, int id) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.id = id;

        initbounds();
    }

    private void initbounds() {

        this.bounds=new Rectangle(x,y,width,height);
    }

    public void draw(Graphics g){
        //body
        if(mouseover) {
            g.setColor(Color.GRAY);
        }else{
            g.setColor(Color.white);}
            g.fillRect(x,y,width,height);

        //border
        g.setColor(Color.black);
        g.drawRect(x,y,width,height);
        if(mousepressed){
            g.drawRect(x+1,y+1,width-2,height-2);
            g.drawRect(x+2,y+2,width-4,height-4);
        }

        //text
        int w = g.getFontMetrics().stringWidth(text);
        int h = g.getFontMetrics().getHeight();
        g.drawString(text,x-w/2+width/2,y+h/3+height/2);
    }

    public void resetbooleans(){
        this.mouseover=false;
        this.mousepressed=false;
    }

    public void setMousepressed(boolean mousepressed){
        this.mousepressed=mousepressed;
    }

    public void setmouseover(boolean mouseover){

        this.mouseover=mouseover;
    }
    public Rectangle getBounds(){
        return bounds;
    }
    public int getId() {
        return id;
    }

    public boolean isMouseOver() {
        return mouseover;
    }

    public boolean isMousePressed() {
        return mousepressed;
    }
}
