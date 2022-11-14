//this tester lets you input your own stuff but doesn't have cool graphics

import java.util.*;

public class CheckersTest {
  public static void main(String []args) {
    Scanner kb = new Scanner(System.in);
    try {
    CheckersBoard bord = new CheckersBoard();
    //System.out.println(bord.count(CheckersBoard.RED));
    bord.display();
  
    String inp = "";

    while (true) {
      inp = kb.nextLine();
      if (inp.equals("-1")) {
        break;
      } else if (inp.equals("count")) {
        System.out.printf("Black: %d, Red %d \n", bord.count(CheckersBoard.BLACK), bord.count(CheckersBoard.RED));
      }
      String []splinp = inp.split("/");
      if (splinp.length == 4)
      bord.move(Integer.parseInt(splinp[0]), Integer.parseInt(splinp[1]), Integer.parseInt(splinp[2]), Integer.parseInt(splinp[3]));
      bord.display();
    };
    } finally {
      kb.close();
    }
  }
}
