//Method2.java - Original code by Fawn Barisic - outputs a string n times

//static lang.System.out so I dont have to type as much
import static java.lang.System.out;
//util for scanner
import java.util.*;

public class Method2 {
  //repeat method, takes the string to repeat and the amount of times to repeat as parameters
  public static void repeat(String s, int n) {
    //loops n times, outputting the string each time
    for (int i=0; i<n; i++) {
      out.println(s);
    }
  }
  //main takes user input and runs repeat
  public static void main(String []args) {
    //constructs scanner object for console input
    Scanner kb = new Scanner(System.in);
    //the beloved try{}finally{} so I can have less squigglies
    try {
      //n is amount of repeats, s is the string being output
      int n=Integer.parseInt(kb.nextLine());
      String s=kb.nextLine();

      //runs repeat with s and n from user
      repeat(s,n);

    } finally {
      kb.close();
    }

  }
}
