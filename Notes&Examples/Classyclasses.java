//import java.util.*;
import java.awt.Point;

public class Classyclasses {
  public static void main(String []args) {
    Point pt = new Point(-10, 25);
    pt.move(10,-25);

    //String is special
    String name="bob";
    //strings are immutable, can't be changed
    name = "Dr. " + name;
    //creates new memory address, doesn't alter old one
    System.out.println(name);

    String n2 = "bob";
    System.out.println(name == n2);

    //== is a shallow comparison
    //java optimizes first 500 strings to have unique memory addresses
    //need to use deep equals
    System.out.println(name.equals(n2));

    name="bob";

    //useful string methods
    char c = name.charAt(2);
    System.out.println(c);
    //char is just ascii values

    for (int i=0; i<name.length(); i++) {
      c = name.charAt(i);
      System.out.println(c+1);
    }
    for (char d : name.toCharArray()) {
      System.out.println((char)d);
    }

    String n3 = name.substring(2);
    System.out.println(n3);

    System.out.println(name.toUpperCase());

    System.out.println(name.contains("Dr."));

    System.out.println(name.equalsIgnoreCase("bOb"));

    String fancy = String.format("%s is %d and likes %.2f \n", name, 16, 4.20);
    System.out.println(fancy);

    String []wordlist = fancy.split(" ");
    System.out.println(wordlist[2]);

    System.out.println(fancy.replace("s", "TH"));

  }
}
