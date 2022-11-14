//Log.java - original code by Fawn Barisic - controls log-specific things for log sprites

//adds log movement functionality and other fun stuff so it pulls the frog with it
public class Log extends Sprite {
  
  //directions so my main looks nice
  public static final int LEFT = 1;
  public static final int RIGHT = 2;

  private int dir, speed;

  public Log(String path, int x, int y, int w, int h, int dir, int speed) { //just passes all the variables in, most of the stuff is just sprite work
    super(path, x, y, w, h, speed);
    this.dir = dir;
    this.speed = speed;
  }

  public int getDir() {
    return ((dir == Log.LEFT)? -speed : speed); //gets the direction of it (- for left, + for right)
  }

  public int Move(int dt) {
    setX((dir == Log.LEFT)? -1 : 1); //only moves in the x, depends on tile direction
    return getDir(); //returns the direction of it for niceness
  }
}
