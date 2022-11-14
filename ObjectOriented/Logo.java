public class Logo
{

     public static void main (String[] args)
    {
		Turtle t = new Turtle();
		t.start();  // wait for the Turtle to be ready to draw stuff

	/*
		THIS IS WHERE YOU RUN YOUR LOGO-LIKE CODE
		e.g.*/
    t.penDown();
    t.star(100);
    //t.penUp();
    t.forward(75);
    t.penDown();
    t.dashedLine(101);
    }
}