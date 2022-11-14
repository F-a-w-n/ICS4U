import java.util.*;
public class RealTest12 {
  public static void main(String []args) {
    Scanner stdin = new Scanner(System.in);
    try {

      String in = stdin.nextLine();
      String []spIn = in.split(" ");

      int HALF = spIn.length/2;

      String end = "";
      String start = "";

      for (int i=0; i<spIn.length; i++) {
        if ((i <= HALF && spIn.length%2==1) || (i < HALF && spIn.length%2==0)) {
          end += " "+spIn[i];
        } else {
          start += " "+spIn[i];
        }
      }

      String startCapital = ""+start.charAt(1);
      startCapital = startCapital.toUpperCase();
      start = startCapital + start.substring(2);

      String endCapital = ""+end.charAt(1);
      endCapital = endCapital.toLowerCase();
      end = endCapital + end.substring(2);

      String out = start + ", "+ end;

      System.out.println(out);


    } finally {
      stdin.close();
    }
  }
}
