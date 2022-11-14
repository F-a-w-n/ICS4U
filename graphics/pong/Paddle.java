package pong;

import java.awt.*;

class Paddle {
  private int x, y, up, down;
  private int speed = 10, width = 10, height = 100;

  public Paddle(int x, int y, int up, int down) {
    this.x=x;
    this.y=y;
    this.up=up;
    this.down=down;
  }

  public void move(boolean []keys) {
    if (keys[up] && y>=speed) {
      y-=speed;
    } else if (keys[down] && y<=600-speed-height) {
      y+=speed;
    }
  }
  public void draw(Graphics g) {
    g.setColor(Color.WHITE);
    g.fillRect(x, y, width, height);
  }
  public Rectangle getRect() {
    return new Rectangle(x, y, width, height);
  }
}
