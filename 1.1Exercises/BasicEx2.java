import java.util.*;

public class BasicEx2 {
    public static void main(String []args) {
        Scanner kb= new Scanner(System.in);
        try {
        System.out.println("Enter x:");
        double x = kb.nextDouble();

        System.out.println("Enter number of terms:");
        int t = kb.nextInt();

        double sum=0.0;
        for (int i=0; i<t; i++) {
            sum += Math.pow(x, i);
        }
        System.out.printf("Total %.2f\n",sum);
        } finally {
        kb.close();
        }
    }
}
