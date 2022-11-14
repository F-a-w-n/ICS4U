//Method4.java - Original code by Fawn Barisic - outputs a "folded" version of an array, adds the first half to the second half, making an array at half length

//blatant disregard for java's style
import static java.lang.System.out;
//util for arrays.tostring again
import java.util.*;

public class Method4 {
  //fold method, takes unfolded array as parameter
  public static int []fold (int []unf) {
    //half length of the array, cleans up code not necessary
    int half = unf.length/2;
    //constructs new array at half of the original's length, will become folded array soon
    int []f = new int[half];
    //loops through the indices of f and adds the value in the unfolded array before and after the halfway point
    for (int i=0; i<half; i++) {
      f[i] = unf[i] + unf[half+i];
    }
    return f;
  }
  //main just creates the unfolded array and prints the folded result
  public static void main(String []args) {
      //unfolded array, soon to be folded mwahahahahaha
      int []unfolded = {1,2,3,4,5,6,7,8,9};
      //outputs string formatted array once it is folded
      out.println(Arrays.toString(fold(unfolded)));

  }
}
