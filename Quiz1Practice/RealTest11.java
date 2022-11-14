import java.util.*;

public class RealTest11 {
  public static void main(String []args) {
    Scanner stdin = new Scanner(System.in);
    try {

      int SIZE = 50;

      int tCount = stdin.nextInt();

      Random rand = new Random();

      for (int i=0; i<tCount; i++) {
        //a^2 + b^2 = c^2
        int a = rand.nextInt(SIZE-1)+1;
        int b = 1;
        while (Math.floor(Math.sqrt(a*a + b*b)) != Math.sqrt(a*a + b*b) || Math.sqrt(a*a + b*b) > 50) {
          if (b<SIZE) {
            b++;
          } else {
            a=rand.nextInt(SIZE-1)+1;
            b=1;
          }
        }
        System.out.println(a + ", " + b + ", " + (int)Math.sqrt(a*a + b*b));
        
      }

    } finally {
      stdin.close();
    }
  }
}
