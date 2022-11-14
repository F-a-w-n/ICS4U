import javax.swing.*;
import java.awt.*;

    
public class Turtle extends JFrame 
{
    private boolean penDown;
    private double x,y,angle;

    public static void delay (long len){
		try	{
		    Thread.sleep (len);
		}
		catch (InterruptedException ex)	{
		    System.out.println("I hate when my sleep is iterrupted");
		}
    }
    
    public Turtle () {
		super ("Turtle");
		x=250;
		y=250;
		angle=90;
		penDown = true;
		
		setSize (500, 500);
		setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		setVisible (true);
    }
    

    public void start(){
		Graphics g=null;
		while(g==null)
		    g=getGraphics();        
		delay(100);
    }

    public void penDown(){
		penDown = true;
    }
    public void penUp(){
		penDown = false;
    }
    
    // You do not need to understand how this works to use it.
    public void forward(int dist){
    	if(dist==0) return;
		double x2 = x+(dist*Math.cos(Math.toRadians(angle)));
		double y2 = y-(dist*Math.sin(Math.toRadians(angle)));
		if(penDown){
		    Graphics g=getGraphics();       
		    g.drawLine((int)x,(int)y,(int)x2,(int)y2);
		}  
		x=x2;
		y=y2;
    }

    public void turn (int deg){
		angle = (angle + deg%360 + 360) % 360;
    } 
    

    public void square(int side){
		forward(side);
		turn(-90);
		forward(side);
		turn(-90);
		forward(side);
		turn(-90);
		forward(side);
		turn(-90);
    }
 
    public void left() {
      turn(90);     
    }
    public void right() {
      turn(-90);     
    }

    public void rectangle(int len, int width){
      forward(len);
      turn(-90);
      forward(width);
      turn(-90);
      forward(len);
      turn(-90);
      forward(width);
      turn(-90);
      }

    public void dashedLine(int len) {
      if (penDown == true) {
        for (int i=0; i<len/5+1; i++) {
          penDown = !penDown;
          if (i<len/5)
          forward(5);
          else
          forward(len%5);
        }
        penDown();
      }
    }

    public void star(int size) {
      turn(-54);
      for (int i=0; i<5; i++) {
        forward(size);
        turn(144);
      }
      turn(54);
    }

}