//Frog.java - original code by Fawn Barisic - makes a nice little froggy sprite that runs around and gets killed many times

import java.awt.*;

//controls movement, collisions, and painting of the frog sprite
public class Frog extends Sprite{

  private int up, down, left, right; //stores button values for controls
  private boolean []queue = new boolean[4]; //queues button inputs
  private int last = 0; //last time the frog jumped, 

  public Frog(String path, int x, int y, int w, int h, int speed, int []controls) { //makes a sprite and turns the controls array to independent variables
    super(path, x, y, w, h, speed);
    this.up = controls[0];
    this.down = controls[1];
    this.left = controls[2];
    this.right = controls[3];
  }

  public void Move(int []keys, int dt) { //move run periodically so it takes deltaT
    
    if (dt - last > 2) { //queues moves starting 2 time units after the last move
      if(keys[up]>0) { //if up is pressed queues an up move
        queue[0] = true;
        keys[up]--; //then ticks down up
      }
      if (keys[down]>0) { //if down is pressed queues a down move
        queue[1] = true;
        keys[down]--; //then ticks down down
      }
      if (keys[left]>0) { //if left is pressed queues a left move
        queue[2] = true;
        keys[left]--; //then ticks down left
      }
      if (keys[right]>0) { //if right is pressed queues a right move
        queue[3] = true;
        keys[right]--; //then ticks down right
      }
    }

    
    if (dt % 10 == 0) { //every 1/5 of a second the queued moves are done
      last = dt; //updates last move time
      if (queue[0]) { //if up != 0
        setY(-50); //frog moves in units of 50, and up is negative y so it subtracts 50 from y
        queue[0] = false; //removes up from queue
      }
      if (queue[1]) { //if down != 0
        setY(50); //moves 50 in positive y to go down
        queue[1] = false; //removes down from queue
      }
      if (queue[2]) { //if left != 0
        setX(-50); //left is negative x, so it subtracts 50 from x
        queue[2] = false; //removes left from queue
      }
      if (queue[3]) { //if right != 0
        setX(50); //moves 50 in positive x to go right
        queue[3] = false; //removes right from queue
      }
    }
    if (getX() < 0) { //if you're too far left 
      setX(50); //pushes you right
    } else if (getX() > 950) { //if you're too far right
      setX(-50); //pushes you left
    }
  }
  @Override
  public Rectangle getRect() { //returns rectangle, overridden to 10px off of each side so the very tip of a car doesn't kill you and you can't survive on the very edge of a log
    return new Rectangle(this.getX()+10, this.getY()+10, this.getWidth()-20, this.getHeight()-20);
  }
  public boolean collide(Rectangle r) { //quicker way to check if it collides with another sprite's rect
    return getRect().intersects(r);
  }
}
