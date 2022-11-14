import java.util.Arrays;

public class Arraystuff {
  public static void main(String []args) {
    int []nums = {45,23,67};

    //only thing you can do is is .length

    System.out.println(nums.length);

    Arrays.sort(nums);

    System.out.println(Arrays.toString(nums));

    nums = new int[100];
    System.out.println(nums.length);
    
    //nullpointerexception error when things arent correctly initialized and you try x.y();

    int tot = 0;
    for (int n : nums) {
      tot+=n;
    }

    //ternary:   condition ? true : false   ex. y<10? x : x*2

    System.out.println(tot);
  }
}
