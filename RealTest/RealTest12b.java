import static java.lang.System.out;
import java.util.*;

public class RealTest12b {
  public static void main(String []args) {
    Scanner kb = new Scanner(System.in);
    try {

      out.print("Enter a Line:");
      String in = kb.nextLine();
      char []inlist = in.toCharArray();

      Random rand = new Random();

      char []vowels = "aeiou".toCharArray();

      while (!(in.equals("end"))) {
        String output = "";
        for (int c=0; c<inlist.length; c++ /*the better language*/) {
          for (char v : vowels) {
            if (inlist[c] == v) {
              inlist[c] = vowels[rand.nextInt(5)];
            }
          }
        }
        for (char c : inlist) {
          output+=""+c;
        }
        out.println(output);
        out.print("Enter a Line:");
        in = kb.nextLine();
        inlist = in.toCharArray();
      }

    } finally {
      kb.close();
    }

  }
}