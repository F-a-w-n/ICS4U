import java.util.*;

public class StringEx1 {
  public static void main(String []args) {
    Scanner kb = new Scanner(System.in);
    try {
      String input = kb.nextLine();
      String []wordlist = input.split(" ");

      String output="";
      for (String word : wordlist) {
        if (word.length()!=4) {
          output+=word+" ";
        }
      }

      System.out.println(output);
    } finally {
      kb.close();
    }

  }
}
