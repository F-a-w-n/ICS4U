//Method3.java - Original code by Fawn Barisic - returns an array containing a shorter array and an integer to be added to the end of it

//I dislike typing (ignore the overcomplicated code you may see later)
import static java.lang.System.out;
//util for array output
import java.util.*;

public class Method3 {
  //arrayAdd method, takes in short array and integer for the end as parameters
  public static int []arrayAdd(int []arr, int n) {
    //constructs new array with one additional slot for n
    int []newArr = new int[arr.length+1];

    //loops through the new array
    for (int i=0; i<newArr.length; i++) {
      //sets the first part of the new array to the old array, but sets the last position to n (note arr.length is 1 less than the new array's length, so I just tried to save typing it could also be newArr.length-1)
      newArr[i] = (i<arr.length)? arr[i] : n;
    }

    return newArr;
  }
  //main method creates an example array and outputs the added together array in a visible form
  public static void main(String []args) {
    //old array
    int []arr = {1,2,3,4};
    //prints out the old array with 5 added on the end, formatted as a string to output
    out.println(Arrays.toString(arrayAdd(arr, 5)));

  }
}
