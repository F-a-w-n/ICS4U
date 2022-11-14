package pong;

import java.awt.*;

class Ball {
  private int x, y, vx, vy, h, w;
  
  public int randint(int high, int low) {
    return (int)Math.random() * (high-low+1)+low;
  }
  
  public Ball() {
    this.x=400;
    this.y=300;
    h=20;
    w=20;
    vx=randint(-1, 1)*10;
    vy=randint(-10, 10);
  }

  public void draw(Graphics g) {
    g.setColor(Color.WHITE);
    g.fillOval(x-w/2, y-h/2, w, h);
  }
  public void move(Paddle p1, Paddle p2) {
    if (y+vy > 600 || y+vy < 0) {
      vy*=-1;
    }
    Rectangle rec = getRect();
    if (rec.intersects(p1.getRect()) || rec.intersects(p2.getRect())) {
      vx*=-1;
    }
    
    x+=vx;
    y+=vy;
  }
  public Rectangle getRect() {
    return new Rectangle(x-w/2, y-h/2, w, h);
  }

  public boolean getOutOfBound() {
    return (x < 0 || x > 800)? true : false; 
  }
  public int getX() {
    return x;
  }
}
