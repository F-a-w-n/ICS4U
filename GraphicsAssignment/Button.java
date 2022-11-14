//Button.java - original code by Fawn Barisic - very necessary never before done button class since I DEFINITELY didn't have access to another useful button class

import java.awt.*;

//paints and handles collisions for clicks
public class Button {

  private int x,y,w,h;
  String text;

  public Button(int x, int y, int w, int h, String text) { //just sets all the variables nothing special
    this.x=x;
    this.y=y;
    this.w=w;
    this.h=h;
    this.text=text;
  }

  public void paint(Graphics g) { //paints a black rectangle and white text fitted to the rectangle
    g.setColor(Color.BLACK);
    g.fillRect(x, y, w, h); //love me a good black rectangle
    g.setColor(Color.WHITE);
    g.setFont(new Font("Comic Sans MS", Font.BOLD, h));
    g.drawString(text, x+5, y+h-5);
  }

  public boolean detectClick(Point mouse) { //probably not the best way to handle clicks but it works, only run when mouse clicked so I just check for position
    if (new Rectangle(x, y, w, h).contains(mouse)) { //if the mouse is over the button returns true
      return true;
    } else { //otherwise returns false
      return false;
    }
  }
  
}
