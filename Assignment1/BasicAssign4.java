//BasicAssign4.java - Original code by Fawn Barisic - experimentally determines the average size of a group of people where two people share a birthday using a basic monte carlo method

//java.util for random because even without input it's useful <33333
import java.util.*;

public class BasicAssign4 {
  public static void main(String []args) {

    //sum of sizes of groups required for a shared birthday
    int sum = 0;

    //loops 1000 times
    for (int i = 0; i<=1000; i++) {
    //array of days in a year, used to store and compare birth dates
    int []days = new int[365];

    //creates random object to assign a random day of the year
    Random r = new Random();
    //cDay is the current birthday
    int cDay = 0;
    //length is the amount of people in the current set, increments until a matching birthday is found
    int length = 0;

    do {

      //sets current birthday to a random day 0-364
      cDay = r.nextInt(365);

      //adds 1 to the array of days in the year at the position of the current birthday
      days[cDay]++;

      //increments length to track how many people have been added
      length++;

      //loops until the current day has more than one person's birthday
    } while (!(days[cDay] > 1));

    //adds the length of the current iteration to the sum total
    sum += length;
  }
  //after looping 1000 times, divides the sum of the lengths by 1000, giving the average length
  System.out.println("Average amount before repeat:" + (double)sum/1000);

  //so much cleaner with no scanner in here
  }
}
