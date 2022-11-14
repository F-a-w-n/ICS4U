import java.util.*;

public class Test12 {
  public static void main(String []args) {
    Scanner stdin = new Scanner(System.in);
    try {
      String d1 = stdin.nextLine();
      String d2 = stdin.nextLine();

      int []lengths = {0,31,28,31,30,31,30,31,31,30,31,30,31};

      int d1day = Integer.parseInt(d1.split("/")[0]);
      int d1month = Integer.parseInt(d1.split("/")[1]);
      int d2day = Integer.parseInt(d2.split("/")[0]);
      int d2month = Integer.parseInt(d2.split("/")[1]);

      for (int i=0; i<d1month-1; i++) {
        d1day+=lengths[i];
      }
      for (int j=0; j<d2month-1; j++) {
        d2day+=lengths[j];
      }
      int diff = (d1day>d2day)? d1day-d2day: d2day-d1day;
      System.out.printf("Day %d is %d days after day %d", ((d1day>d2day)? 1 : 2), diff, ((d1day<d2day)? 1 : 2));
    } finally {
      stdin.close();
    }
  }
}
