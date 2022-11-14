import java.util.*;
import static java.lang.System.out;

public class RealTest11b {
  public static void main(String []args) {
    Scanner kb = new Scanner(System.in);
    try {

      int N = kb.nextInt();

      int []fib = new int[N+1];

      for (int i=0; i<N+1; i++) {
        if (i<=1) {
          fib[i] = i;
        } else {
          fib[i] = fib[i-1]+ fib[i-2];
        }
      }

      for (int i=0; i<N+1; i++) {
        out.print(fib[i]+" ");
      }


    } finally {
      kb.close();
    }
  }
}
