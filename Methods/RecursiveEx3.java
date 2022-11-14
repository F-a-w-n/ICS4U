import static java.lang.System.out;
//import static java.lang.Math.*;
import java.util.*;

public class RecursiveEx3 {
/*
  public static String toBin(int startNum) {
    String back = toBin("", startNum);
    String ret = "";
    for (int i=back.length()-1; i>=0; i--) {
      ret += "" + back.charAt(i);
    }
    return ret;
  }
  public static String toBin(String bin, int rem) {
    if (rem == 0) {
      return bin;
    }
    bin += "" + rem%2;
    rem/=2;
    return toBin(bin, rem);
  }*/
  public static String toBin(int inp) {
    if (inp > 0) {
      return toBin(inp/2) + inp%2;
    } else {
      return "";
    }
  }
  public static void main(String []args) {
    Scanner kb = new Scanner(System.in);
    try {

      out.println(toBin(24));

    } finally {
      kb.close();
    }

  }
}
