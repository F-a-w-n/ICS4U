import static java.lang.System.out;

public class RobotTest {
  public static void main(String []args) {
    MyRobot jim = new MyRobot();
    MyRobot joe = new MyRobot(50.0, 20, 90.0);
    
    jim.advance(50);
		out.println(jim);
		joe.turn(-600);
		joe.advance(50);
		out.println(joe);

  }
}