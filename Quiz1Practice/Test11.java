public class Test11 {
  public static void main(String []args) {
    int bal = 1000000;
    int time = 0;
    while (bal>=80000) {
      bal-=80000;
      bal*=1.03;
      System.out.println(bal);
      time++;
    }
    System.out.println("Years:" + time);
  }
}
