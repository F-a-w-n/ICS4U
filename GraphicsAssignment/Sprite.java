//Sprite.java - original code by Fawn Barisic - does most of the work for cars, logs, frogs, and if I ever add some dogs I know where to start

import javax.swing.*;
import java.awt.*;

//paints, moves, collides, and generally forms a basis for every moving object in game
public class Sprite {
  //stores position and size for painting and collisions
  private int x, y, w, h;
  //stores image to paint of the sprite
  Image image;
  //speed controls how fast each move moves the sprite
  private int speed;

  //sets all the above variables with the only fun one being the image from path
  public Sprite(String path, int x, int y, int w, int h, int speed) {
    this.x=x;
    this.y=y;
    this.w=w;
    this.h=h;
    this.speed = speed;
    //getClass().getResource() grabs the file for image which stores the picture in a paint-able form
    image = new ImageIcon(getClass().getResource("/images/"+path)).getImage(); 
  }

  public void roundX(int mod) { //rounds to the nearest multiple of the modifier
    x=(x%mod < mod/2)? x-(x%mod) : x-(x%mod)+mod; //I only use this for the frog hopping off logs onto solid ground so that the endzone can still register it as a good position, doesn't affect gameplay much though
  }

  public void setX(int dx) { //adds/subtracts the speed of the sprite to the x value to move it
    x+=dx*speed;
  }
  public void setY(int dy) { //adds/subtracts the speed of the sprite to the y value to move it
    y+=dy*speed;
  }
  public int getX() { return x;} //getter methods for all the important values
  public int getY() {return y;}
  public int getWidth() {return w;}
  public int getHeight() {return h;}

  public Rectangle getRect() { //makes a nice neat lil' rectangle of it for collisions and such
    return new Rectangle(x, y, w, h); //new one each time since it's always movin'
  }

  public void Paint(Graphics g, JPanel panel) {
    //just draws the image where the sprite is
    g.drawImage(image, x, y, w, h, panel);
  }
}
