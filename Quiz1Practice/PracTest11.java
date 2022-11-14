//import java.util.*;

public class PracTest11 {
  public static void main(String []args) {

      int put = 2000;
      int sav = 0;

      for (int i=0; i<20; i++) {
        sav+=put;
        put*=1.05;
        sav*=1.08;
      }

      System.out.println(sav);

  }
}
