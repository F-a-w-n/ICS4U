//BasicAssign2.java - Original code by Fawn Barisic - Basic run-length encoder for a string of characters

//my love for java.util is immeasurable
import java.util.*;

public class BasicAssign2 {
  public static void main(String []args) {
    //scanner object for console input
    Scanner kb = new Scanner(System.in);
    //try{}finally{} so i can close kb and get rid of squigglies
    try {

      //input string
      String in = kb.nextLine();

      //current char (ex. B), initialized as the first char in the input string
      char current = in.toCharArray()[0];
      //count variable used to keep track of how many of each char is used in a row
      int count = 0;

      //output string
      String out = "";
      //at each char in the string, checks if it is the same as the ones before and if not adds the counter and the char to the output
      for (char c : in.toCharArray()) {
        if (c == current) {
          count ++;
        } else {
          out += "" + count + (char)current; 
          count = 1;
          current = c;
        }
      }
      //adds final set of chars
      out += "" + count + (char)current;
      //outputs the finished output string
      System.out.print(out);

      //closes kb
    } finally {
      kb.close();
    }
  }
}
