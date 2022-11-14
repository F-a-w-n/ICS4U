import java.util.*;

public class PracTest12 {
  public static void main(String []args) {
    //String []names = {"sam", "sally", "debra", "ann", "nick"};
    Scanner stdin = new Scanner(System.in);
    try {
      String []names = new String[5];
      for (int i = 0; i<5; i++) {
        names[i] = stdin.nextLine();
        names[i] = names[i].toLowerCase();
      }

      for (String name: names) {
        for (String match: names) {
          int points = 0;
          if (name != match) {
            if (name.charAt(0) == match.charAt(0)) {
              points +=4;
            } 
            for (char c:name.toCharArray()) {
              for (char d:match.toCharArray()) {
                if (c==d) {
                  points++;
                }
              }
            }
            System.out.println(name+","+match+" "+ points);
          }
        }
      }
      /*
      sam,sally6
      sam,debra1
      sam,ann1
      sam,nick0
      sally,sam6
      sally,debra1
      sally,ann1
      sally,nick0
      debra,sam1
      debra,sally1
      debra,ann1
      debra,nick0
      ann,sam1
      ann,sally1
      ann,debra1
      ann,nick2
      nick,sam0
      nick,sally0
      nick,debra0
      nick,ann2
      */

    } finally {
      stdin.close();
    }
  }
}
