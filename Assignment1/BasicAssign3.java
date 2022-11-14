//BasicAssign3.java - Original code by Fawn Barisic - Program to determine whether or not a point is on a line in two dimensions using java's built-in Line2D class

//java.util for the input and because I love it with all my heart
import java.util.*;
//line2D and Point classes to use the helpful built-in methods for lines
import java.awt.geom.Line2D;
import java.awt.Point;


public class BasicAssign3 {
  public static void main(String []args) {
    //Scanner created for console input
    Scanner kb = new Scanner(System.in);
    //obligatory try{}finally{} for vs code's problems
    try {

      //takes input for the point's x and y coordinates
      System.out.println("input point x");
      int px = kb.nextInt();
      System.out.println("input point y");
      int py = kb.nextInt();
      //creates new point object at those coordinates
      Point p = new Point(px, py);

      //takes input for the two points that make the line (x1, y1) (x2,y2)
      System.out.println("input line x1");
      int x1 = kb.nextInt();
      System.out.println("input line y1");
      int y1 = kb.nextInt();

      System.out.println("input line x2");
      int x2 = kb.nextInt();
      System.out.println("input line y2");
      int y2 = kb.nextInt();
      //creates line2d object at the input coordinates
      Line2D ln = new Line2D.Float((float)x1, (float)y1, (float)x2, (float)y2);

      //checks if point distance to line is 0 (on the line)
      if (ln.ptLineDist(p)==0) {
        System.out.println("On the line!");
      } else {
        System.out.println("Not on the line!");
      }
      //closing kb to get rid of squigglies
    } finally {
      kb.close();
    }
  }
}
