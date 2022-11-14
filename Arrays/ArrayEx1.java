import java.util.*;

public class ArrayEx1 {
  public static void main(String []args) {
    Scanner kb = new Scanner(System.in);
    try {
      System.out.println("Please enter your marks: ");

      double []marks ={kb.nextDouble(),kb.nextDouble(),kb.nextDouble(),kb.nextDouble(),kb.nextDouble(),kb.nextDouble(),kb.nextDouble(),kb.nextDouble()};
      double avg =0;
      for (double i : marks) {
        avg += i;
      }
      avg /= 8;

      System.out.println("Your average is: " + avg);
      for (double j : marks) {
        if (j>=avg) {
        System.out.printf("%.2f percent is %.2f more than your average \n", j, j-avg);
        } else {
          System.out.printf("%.2f percent is %.2f less than your average \n", j, avg-j);
        }
      }
    } finally {
      kb.close();
    }
  }
}
