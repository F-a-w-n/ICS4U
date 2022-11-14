import java.util.*;

class BasicEx1 {
    public static void main(String []args) {
        Scanner kb = new Scanner(System.in);
        try {
        int loops=0;
        int total=0;
        int inp=0;
        int minimum=0;
        int maximum=0;
        do{
            System.out.println("Input next number:");
            inp = kb.nextInt();
            if (inp !=0) {
                total+=inp;
                loops++;
                if (inp < minimum || minimum == 0) {
                    minimum=inp;
                }
                if (inp > maximum || maximum == 0) {
                    maximum=inp;
                }

            };
        }while(inp!=0);
        System.out.printf("Average: %.1f\n", (double)total/loops);
        System.out.println("min:"+minimum+", max:"+maximum);
        } finally {
            kb.close();
        }
    }
}

