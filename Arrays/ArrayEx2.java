import java.util.*;

public class ArrayEx2 {
  public static void main(String []args) {
    Scanner stdin = new Scanner(System.in);
    //singleton means named lowercase of class name; only uses one
    //any other name is for in case you use multiple, dont use scanner1, scanner2, etc.
    try {

      String in = stdin.nextLine();
      String []days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

      for (int i=days.length-2; i>=0; i--) {
        if (i>0) {
          in=in.replaceAll(days[i], days[i+1]);
        } else {
          in=in.replaceAll(days[i], days[days.length-1]);
        }
      }

      System.out.println(in);

    } finally {
      stdin.close();
    }
  }
}
