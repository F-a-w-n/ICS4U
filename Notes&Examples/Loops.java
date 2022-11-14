//import java.util.*;

class Loops {
    public static void main(String[] args) {
        //String different from other types, points to memory address, use .Equals() and not ==
        /*
        Scanner kb = new Scanner(System.in);

        System.out.println("enter mark:");
        int mark = kb.nextInt();
        if (mark>=80) {
            System.out.println("you pass so cool swag!");
        } else if (mark >=50) {
            System.out.println("ur cool ig.");
        } else {
            System.out.println("you suck dweeb go git gud");
        }
    */
        int x=1;
        while (x<=100) {
            x*=2;
            if (x>50) {
                break;
            }
            if (x%10==6) {
                continue;
            }
            System.out.println(x);
        }

        do{ //loops at least once
            x/=2;
            System.out.println(x);
        }while (x>0);

        for (int i=0; i<10; i++) {  //(<do at start>; <check each loop>; <do at the end of each loop>), can do multiple separated by comma
            System.out.println(i);
        }
        String []pets = {"possum", "possumagain", "onlypossums", "nootherpetsjustpossums"};
        for (String pet : pets) { //loops throught list, cannot change list while looping
            System.out.println(pet);
        }
    }
}