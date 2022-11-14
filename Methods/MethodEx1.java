import static java.lang.System.out;
//import static java.lang.Math.*;
import java.util.*;

public class MethodEx1 {

  public static int range(int []list) {
    int []list2 = Arrays.copyOf(list, list.length);
    boolean sorted = false;
    while (!sorted) {
      sorted = true;
      for (int i = 0; i<list2.length-1; i++) {
        if (list2[i] > list2[i+1]) {
          int p = list2[i];
          list2[i] = list2[i+1];
          list2[i+1] = p;
          sorted=false;
        }
      }
    }

    int r=list2[list2.length-1]-list2[0];
    return r;
  }
  public static void main(String []args) {
    Scanner kb = new Scanner(System.in);
    try {

      int n = kb.nextInt();
      int []list = new int[n];
      for (int i=0; i<n; i++) {
        list[i] = kb.nextInt();
      }

      int r = range(list);
      out.println(r);


    } finally {
      kb.close();
    }

  }
}
