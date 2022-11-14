import java.util.Arrays;

public class Elevator {
  private int pos = 0;
  public int max = 0;
  private int []floors = new int[1];
  private int lastmove = 0;

  public Elevator(int max) {
    this.max = max+1;
    this.floors = new int[this.max];
  }

  public void request(int floor) {
    if (floor <=max) {
      this.floors[floor]++;
    }
  }
  public void display() {
    String space = " ";
    for (int i=0; i<pos; i++) {
      space+="   ";
    }
    System.out.println(Arrays.toString(this.floors));
    System.out.println(space + "E");
  }

  private int count(int start, int end) {
    int c = 0;
    for (int i = start; i<end; i++) {
      c+=floors[i];
    }
    return c;
  }

  public void move() {
    int below = count(0,pos);
    int above = count(pos+1, max);
    if (floors[pos] > 0) {
      floors[pos] = 0;
    } else if (below != 0 || above != 0) {
      if (below > 0 && above == 0) {
        lastmove = -1;
        pos--;
      } else if (above > 0 && below == 0) {
        lastmove = 1;
        pos++;
      } else {
        pos+=lastmove;
      }
    }
  }
}
