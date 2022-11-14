import java.util.*;

public class StringEx3 {
  public static void main(String []args) {
    Scanner kb = new Scanner(System.in);
    try {
      String input = kb.nextLine();
      //could also use regex method with ReplaceAll("[^0-9]", "")
      String []split = input.split(" ");

      String adding ="";
      for (String s : split) {
        try {
        adding+=Integer.parseInt(s);
        } catch (Exception e) {}
      }
      int fin = Integer.parseInt(adding);
      System.out.println(fin);
    }finally {
      kb.close();
    }
  }
}