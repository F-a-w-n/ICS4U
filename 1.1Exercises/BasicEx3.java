import java.util.*;

public class BasicEx3 {
    public static void main(String []args) {
        Scanner kb = new Scanner(System.in);
        try{
        System.out.println("Hey man can i get a uhhh....... an integer combo with uhhhhhh a side of fries and a uhh coke zero?");
        int combo = kb.nextInt();

        System.out.println("That'll be $13.49 is that on debit or credit?");
        for (int i=1; i<=combo/2; i++) {
            if (combo %i==0) {
                System.out.println(i);
            }
        }
        System.out.println(combo);
      } finally {
        kb.close();
      }
    }
}
