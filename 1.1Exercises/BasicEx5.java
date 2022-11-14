import java.util.*;
public class BasicEx5 {
    public static void main(String []args) {
        Scanner kb = new Scanner(System.in);
        try {
        System.out.println("Yo dawg you got the integer to factorial? I got the stuff.");
        int n = kb.nextInt();

        int prod=1;
        for (int i=n; i>0; i--) {
            prod *= i;
        }
        System.out.println(prod);
    } finally {
        kb.close();
    }
    }
}
