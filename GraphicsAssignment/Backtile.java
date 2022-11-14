//Backtile.java - original code by Fawn Barisic - handles painting and directions and types for background tiles

import javax.swing.*;
import java.awt.*;

//paints and handles basic functions of the backgrounds of each level
public class Backtile {
  private int y, w, h = 50;
  Image image;

  //types, used to tell water from grass and such
  public static final int GRASS = 1;
  public static final int PAVEMENT = 2;
  public static final int WATER = 3;
  public static final int ENDZONE = 6;

  //direction stored for logs and cars so they all go the same way
  public static final int LEFT = 4;
  public static final int RIGHT = 5;

  private int type, dir;

  public Backtile(String path,int y, int w, int h, int type, int dir) { //just sets all the variables for it
    this.y=y;
    this.w=w;
    image = new ImageIcon(getClass().getResource("/images/"+path)).getImage();
    this.type = type;
    this.dir = dir;
  }
  
  public int getY() {
    return y;
  }

  public Rectangle getRect() { //used mostly to drown the frog
    return new Rectangle(0, y, w, h);
  }

  public void SetImage(String path) { //sets image to new path
    image = new ImageIcon(getClass().getResource("/images/"+path)).getImage();
  }
  public int getType() {
    return type;
  }

  public int getDir() {
    return dir;
  }

  public void Paint(Graphics g, JPanel panel) { //just draws the image 
    g.drawImage(image, 0, y, w, h, panel);
  }
}