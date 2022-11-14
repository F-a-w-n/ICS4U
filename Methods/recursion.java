import static java.lang.System.out;
//import static java.lang.Math.*;
import java.util.*;

public class recursion{

  public static int fib(int n) {
    if (n < 2) {
      return n;
    } else {
      return fib(n-1) + fib(n-2);
    }
  } 

  public static void bits(int n) {
    bits("", n);
  }

  public static void bits(String sub, int n) {
    if (n==0) {
      out.println(sub);
    } else {
      for (int i = 0; i<2; i++) {
        bits("" + sub + i, n-1);
      }
    }
  }
  public static void main(String []args) {
    Scanner kb = new Scanner(System.in);
    try {

      out.println(fib(9));
      bits(5);

    } finally {
      kb.close();
    }

  }
}
