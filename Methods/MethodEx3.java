import static java.lang.System.out;
//import static java.lang.Math.*;
import java.util.*;

public class MethodEx3 {

  public static int []odds(int []all) {
    int []o = new int[all.length];
    int opos = 0;
    for (int n = 0; n<all.length; n++) {
      if (all[n] % 2 == 1) {
        o[opos] = all[n];
        opos++;
      }
    }
    int []fin = new int[opos];
    for (int i=0; i<opos; i++) {
      fin[i] = o[i];
    }
    return fin;
  }
  public static void main(String []args) {
    Scanner kb = new Scanner(System.in);
    try {

      int n = kb.nextInt();
      int []nums = new int[n];
      for (int i=0; i<n; i++) {
        nums[i] = kb.nextInt();
      }
      nums = odds(nums);
      out.println(Arrays.toString(nums));

    } finally {
      kb.close();
    }

  }
}
