/**
 * @(#)ElevatorTest.java
 *
 *
 * @author
 *	Mr. McKenzie
 * @version 1.00 2019/12/11
 *  
 * This is a very basic Elevator simulator
 *
 */
 

public class ElevatorTest{
	public static final int MAXFLOOR = 10, MAXTIME = 100;
		
	public static int randint(int low, int high){
		return (int)(Math.random()*(high-low+1)+low);
	}
	
    public static void delay (long len){
		try	{
		    Thread.sleep (len);
		}
		catch (InterruptedException ex)	{
		    System.out.println(ex);
		}
    }
    
	public static void main(String[] args) {
		Elevator ev = new Elevator(MAXFLOOR);
		for(int time = 0; time <MAXTIME; time++){
			for(int floor=0; floor<=MAXFLOOR; floor++){
				if(randint(1,20 + floor*4)==1){
					ev.request(floor);
				}
			}
			ev.move();
			ev.display();
			delay(100);
		}
	}
}

