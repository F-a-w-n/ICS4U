import java.util.*;

public class makinMethods {
  public static void main(String []args) {

    Scanner kb = new Scanner(System.in);

    System.out.println(add(kb.nextInt(), kb.nextInt()));

    kb.close();
  }

  public static int add(int a, int b) {
    return a+b;
  }

}
