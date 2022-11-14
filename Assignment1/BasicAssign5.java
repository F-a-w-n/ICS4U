//BasicAssign5.java - Original code by Fawn Barisic - checks validity of a product code based upon the simple rules provided: all letters must be uppercase, and all numbers in the first part (before the space) must multiply in groups of two ex AB6LDJ7 -> 67 to equal the second part

//java.util here to grant me the scanner
import java.util.*;

public class BasicAssign5 {
  public static void main(String []args) {
    //scanner object, now called stdin because I can't decide which I like better
    Scanner stdin = new Scanner(System.in);
    //try{}finally{} to close scanner
    try {

      //input string from scanner input
      String in = stdin.nextLine();

      //valid bool used to keep track and save time calculating for a code that is already invalid
      boolean valid = true;

      //checks for lowercase by comparing the forced uppercase to the unchanged input string
      if (in.toUpperCase() != in) {
        //sets valid to false, since all characters should be uppercase
        valid = false;
      } 

      //not entirely necessary array of strings, used to add integers together in different place values easier
      String []nums = {"", "", ""};
      
      //count variable, used to place the integers in the string into the right places in the array
      int count = 1;

      //set to true when passing the space in between the two parts
      boolean part2 = false;

      //since the for loop goes through the char array I use the second half of it to append to the string, admittedly lazy but works well
      String second = "";

      //checks if the product code is valid before wasting time
      if (valid) {
        //loops through every char in the input
        for (char c : in.toCharArray()) {
          //when in the first part and the char is a number adds to count and adds the char to the array of numbers
          if (!part2 && 48 <= c && c < 58) {
            count++;
            if (count < 7) {
              nums[count/2-1] += c;
            } else {
              valid = false;
            }
            //if the char is a space sets it to part 2
          } else if (!part2 && c == 32) {
            part2 = true;
            //while in part 2 adds the char to the string for the second part
          } else if (part2) {
            second += c;
          }
        }
        //product variable, used to multiple together the integers from the product code
        int prod = 1;
        //loops through the numbers array
        for (String s : nums) {
          //multiplies the numbers together (with parseInt because up until now they have been stored as strings)
          prod *= Integer.parseInt(s);
        }
        //evaluates if the product of the numbers from part 1 is equal to the second part and sets valid to false if they are different
          if (!(prod == Integer.parseInt(second))) {
            valid = false;
        }
      }

      //prints the result valid or not
      if (valid) {
        System.out.println("You're valid!");
      } else {
        System.out.println("Invalid icky gross");
      }
      
      //i hate squiggly lines only a little more than typing this every time
    } finally {
      stdin.close();
    }
  }
}
