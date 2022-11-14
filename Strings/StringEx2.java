import java.util.*;

public class StringEx2 {
  public static void main(String []args) {
    Scanner stdin = new Scanner(System.in);

    try {
      String input = stdin.nextLine();

      int half = input.length()/2;
      
      String []split = input.split("");

      String out ="";

      for (int i=0; i<half; i++) {
          out += split[i] + split[i+half];
      }
      if (2*half < input.length()) {
        out+= split[input.length()-1];
      }

      System.out.println(out);

    } finally {
      stdin.close();
    }
  }
}
