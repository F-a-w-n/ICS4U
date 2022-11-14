//Method5.java - Original code by Fawn Barisic - outputs a randomized license plate, filtered so it does not contain objectionable words

//I am really sorry I know you regret teaching me this right now I am coming from cout and simply cannot type this much
import static java.lang.System.out;
//util for random this time, always a pleasure to hang with my pal util
import java.util.*;

public class Method5 {
  //licenseplate takes in an array of objectionable words as parameters
  public static String licensePlate(String []obj) {
    //constructs new random object to get random values later
    Random rand = new Random();
    //String array of the alphabet, I love that .split() works like this
    String []let = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");
    //valid bool, when false the method remakes the license plate, when true the license plate is allowed to pass
    boolean valid = false;
    //test string, this is hopefully the end license plate, and if not it will be incinerated and reborn of the ashes 
    String test = "";
    //while loop that ends when the plate is deemed valid
    while (!valid) {
      //resets test each loop, prevents it becoming {objectionable word} 123dakj 432fkfm678 when it has to re-make
      test="";
      //adds 4 random letters
      for (int i=0; i<4; i++) {
        test+=let[rand.nextInt(let.length)];
      }
      //adds a space
      test+= " ";
      //adds 3 numbers
      for (int i=0; i<3; i++) {
        test+=""+rand.nextInt(10);
      }
      //sets valid to true for now
      valid = true;
      //loops through objectionable words
      for (String word : obj) {
        //when the plate contains a bad word it sets valid to false, causing it to remake
        if (test.contains(word)) {
          valid = false;
        }
      }
    }
    //returns test when it passes all the filters
    return test;
  }
  //main creates an array of bad words and outputs a license plate that doesn't contain them
  public static void main(String []args) {

      String []badwords = {"bad", "word", "icky", "poop", "fail", "69"};
      
      out.println(licensePlate(badwords));
      
  }
}
