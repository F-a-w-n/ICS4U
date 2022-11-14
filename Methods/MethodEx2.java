import static java.lang.System.out;
//import static java.lang.Math.*;
import java.util.*;

public class MethodEx2 {
  public static String novowel(String vowels) {
    String novow = vowels.replaceAll("[AEIOUaeiou]", "");
    return novow;
  }
  public static void main(String []args) {
    Scanner kb = new Scanner(System.in);
    try {

      String v = kb.nextLine();
      String nv=novowel(v);
      out.println(nv);

    } finally {
      kb.close();
    }

  }
}
