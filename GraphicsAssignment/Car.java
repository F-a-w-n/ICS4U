//Car.java - original code by Fawn Barisic - controls the movement of cars, besides that just makes a standard sprite

//controls movement of the car sprites
public class Car extends Sprite {
  public static final int LEFT = 1; //love me a public static final int
  public static final int RIGHT = 2; //Car.RIGHT is more fun than writing 2 for sure

  private int dir;//stores direction of the background tile the car is on

  public Car(String path, int x, int y, int w, int h, int dir, int speed) { //just makes a sprite and sets dir
    super(path, x, y, w, h, speed);
    this.dir = dir;
  }

  public void Move(int dt) { //moves at the car's speed every time interval, based on the speed set by the level
    setX((dir == Car.LEFT)? -1 : 1);
  }

}
