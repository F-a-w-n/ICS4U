import static java.lang.System.out;
//import static java.lang.Math.*;
import java.util.*;

public class RecursiveEx1 {

  public static void ana(String og) {
    ana("", og);
  }
  public static void ana (String cur, String left) {
    if (left.length()== 1) {
      out.println(cur+left);
    } else {
      for (int i=0; i<left.length(); i++) {
        char let = left.charAt(i);
        String newLeft = left.substring(0, i) + left.substring(i+1);
        ana(cur+let, newLeft);
      }
    }
  }
  public static void main(String []args) {
    Scanner kb = new Scanner(System.in);
    try {

      ana("satin");

    } finally {
      kb.close();
    }

  }
}
