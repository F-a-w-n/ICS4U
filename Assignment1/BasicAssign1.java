//BasicAssign1.java - Original code by Fawn Barisic - outputs a hollow square made of a string input by the user

//obligatory java.util.* for scanner
import java.util.*;

public class BasicAssign1 {
  public static void main(String []args) {
    //constructs scanner object
    Scanner stdin = new Scanner(System.in);
    //try{}finally{} so I can close stdin and get rid of vs code error
    try {
      //input string
      String in = stdin.nextLine();
      //length variable to save typing
      int len = in.length()-1;

      //creates space between letters
      String gap ="";
      for (int a =0; a<=len-1; a++) {
        gap+=" ";
      }

      //iterates over length of word
      for (int i=0; i<=len+1; i++) {
        //first line
        if (i==0) {
          //word written in order, start of word for right side
          System.out.println(in + in.charAt(0));
        //last line
        } else if (i==len+1) {
          //start of word for left side
          System.out.printf("%c", in.charAt(0));
          //iterates in reverse over the word to rewrite backwards, printf so it doesnt newline
          for (int j=len; j>0; j--) {
            System.out.printf("%c", in.charAt(j));
          }
          //newline and the start of the bottom word, printed last since it is reversed
          System.out.printf("%c\n", in.charAt(0));
        //in between
        } else {
          //prints letters on left end-beginning and on the right beginning-end with gap to make the spaces
          System.out.printf(in.charAt(len-i+1) + gap + in.charAt(i) + "\n");
        }
      }

    //the beloved closing of the input so I dont get yellow squigglies 
    } finally {
      stdin.close();
    }
  }
}
