import java.io.*;

public class ioTest {
  public static void main(String []args) {
    try {
      PrintWriter out = new PrintWriter(new File("nums2.txt"));
      for (int i = 0; i<1000; i++) {
        out.print(i*22);
      }
      out.close();
    } catch(IOException e) {
      System.out.println(e);
    }
  }
}
