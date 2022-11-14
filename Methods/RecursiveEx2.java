import static java.lang.System.out;
//import static java.lang.Math.*;
import java.util.*;

public class RecursiveEx2 {

  public static void randcaps(String password) {
    randcaps("",password);
  }
  public static void randcaps(String cap, String pass) {
    if (pass.equals("")) {
      out.println(cap);
    } else {
      String upper = "" + pass.charAt(0);
      upper=upper.toUpperCase();
      String downer = "" + pass.charAt(0);
      downer=downer.toLowerCase();
      randcaps(cap+upper, pass.substring(1));
      randcaps(cap+downer, pass.substring(1));
    }
  }
  public static void main(String []args) {
    Scanner kb = new Scanner(System.in);
    try {

      randcaps("password");

    } finally {
      kb.close();
    }

  }
}
