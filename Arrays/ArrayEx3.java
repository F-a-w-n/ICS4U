import java.util.*;
import java.awt.Point;

public class ArrayEx3 {
  
  static double distance(int x, int y, int tx, int ty) {
    double d = Math.sqrt((tx-x)*(tx-x) + (ty-y)*(ty-y));

    return d;
  }
  public static void main(String []args) {
    Random rand = new Random();

    int length = 30;

    Point []coords = new Point[length];

    int planted=0;
    int loops =0;
    do{
      int x = rand.nextInt(30);
      int y = rand.nextInt(30);

      boolean safe = true;

      for (Point p : coords) {
        if (p==null) {
          break;
        }
        if (distance(x,y, (int)p.getX(), (int)p.getY()) < 3) {
          safe = false;
          break;
        }
      }

      if (loops > 50) {
        coords = new Point[length];
        planted=0;
        loops=0;
      }
      loops++;

      if (safe) {
        coords[planted] = new Point(x,y);
        planted++;
      }
      
    }while (planted<coords.length);

    for (int i=0; i<coords.length; i++) {
      System.out.printf("Tree %d: x-%d y-%d \n", i+1, (int)coords[i].getX(), (int)coords[i].getY());
    }
  }

}
