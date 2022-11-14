//Method1.java - Original code by Fawn Barisic - outputs mean of an array of integers

//I don't like typing System.
import static java.lang.System.out;


public class Method1 {
  //method to find mean value, takes int array as parameter
  public static double mean(int []nums) {
    //double m adds together every integer in the array, set as double to easily get decimal values output 
    double m = 0;
    //loops through the array to add each index
    for (int n : nums) {
      m+=n;
    }
    //divides by the length of the array before returning
    return m/nums.length;
  }
  //main method just calls mean and outputs it here
  public static void main(String []args) {

      //array of integers to test
      int []nums = {1, 5, 11, 2, 7};

      //outputs the mean of the array declared above
      out.println(mean(nums));
  }
}
