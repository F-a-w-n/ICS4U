//HashAssign2.java - original code by Fawn Barisic - creates a nice little interactive map of windsor so we can see how stressed university students are (note: pressing any key shows all of the emotions in one, it's slow but fun to see and good for testing)

package HashTable;

import java.io.*;           //needs to read files
import java.util.ArrayList; //technically not cheating since it's not a hash table
import java.util.Scanner;   //need this to read the files (refuse to use the other method of reading)
import java.util.LinkedList; //also technically not cheating, just need this to get individual objects from the linkedlist format 
import javax.swing.*;        //my JFrame homies
import java.awt.*;
import java.awt.event.*;

class HashAssign2 extends JFrame {  //makes a neat little window and then lets the panel do the rest
  public static void main(String []args) {
    HashAssign2 frame = new HashAssign2();
  }

  public HashAssign2() {
    super("Basic frame"); //calls constructor and immediate superclass
    Pane pane = new Pane();     //makes the little panel
    add(pane);
    pack();
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //lets you close the window when you want to stop empathizing
    setVisible(true);       //dont show till everything is loaded
  }

}

class Pane extends JPanel implements ActionListener, MouseListener, KeyListener { //panel with all the little interfaces
  int x = 0, y = 0, cx = 0, cy = 0;             //mouse x and y, circle (or magnifying glass I guess) x and y
  ArrayList<int[]> rgb = new ArrayList<int[]>(); //stores the pixels in our circle to display to the screen, arraylist stores int array set up [red, green, blue, alpha, x, y] so I can quickly grab the values
  Timer myTimer;                                //timer to redraw frames     
  Image background;                             //neat little map of windsor
  EmoTable tab = new EmoTable();                //this is the one that does all the work
  ArrayList<box> boxes = new ArrayList<box>();  //stores the rgb values for all the pixels in the showAll mode, didn't use this for the main part since it ruins the point of using the hashtable
  boolean showAll = false;                      //laggy but good for testing and I figured I'd leave it in, shows all of the emotions in one
  boolean mDown = false;                        //stores if the mouse is down, if it is I'll have to contact my emotable for a new set of emos to show

  public Pane() {                                         //panel setup stuff
    super();                                              //grabs all the things from JPanel
    background = new ImageIcon(getClass().getResource("windsor.png")).getImage(); //gets our background image
    setPreferredSize(new Dimension(800,600)); //sets the size to the expected dimensions
    myTimer = new Timer(20, this);                  //50Hz timer, so you can experience people's feelings in high fps
    myTimer.start();                                       //starts timer up on init
    addMouseListener(this);                                //listens for mouse inputs
    addKeyListener(this);                                  //listens for keyboard inputs (I totally didn't forget about this for like an hour during testing)
    setFocusable(true);                          //focuses on the panel cause it needs your attention or it will cry and throw errors I guess
    requestFocus();
  }

  public void showAll() {                         //adds feeling boxes if needed, toggles showAll to true so other methods can keep up, and repaints once so we can see the monstrous amount of data all at once
    if (boxes.size() == 0) 
    for (int i = 0; i<800; i++) {
      for (int j = 0; j<600; j++) {
        boxes.add(new box(i,j,tab.nearby(i,j)));
      }
    }
    showAll = true;
    repaint();
  }
  @Override
  public void paint(Graphics g) {   
    g.drawImage(background, 0, 0, getWidth(), getHeight(), this); //draws our background on
    if(!showAll) {                                                     //when you don't show all of them as one should
      g.setColor(Color.BLACK);                                         //draws a circle around the area being checked
      g.drawOval(cx, cy, 22, 22);
      for (int [] px : rgb) {                                         //grabs each pixel in that range we search, and paints it in its colour
        g.setColor(new Color(px[0], px[1], px[2], px[3]));
        g.fillRect(px[4], px[5], 1, 1);
      }
      g.setColor(Color.BLACK);                                        //sets up our font and colour
      g.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
      g.drawString(String.format("Current Area: %d - %d, %d - %d", cx, cx+20, cy, cy+20), 100, 20); //lets ya know around about where you're looking
    } else {                  //so you have chosen death
      for (box b : boxes) {   //loops through every single emotion (no clue why I called it a box, actually I think the plan was to make them bigger)
        b.paint(g);
      }
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {              //each frame's actions are controlled here
    Point mouse = MouseInfo.getPointerInfo().getLocation(); //gets mouse point
    Point offset = new Point(0,0);
    try{                                                      //don't know why offset was erroring, but it doesn't anymore
      offset = getLocationOnScreen();
    } catch (IllegalComponentStateException err) {
      System.out.println(err);
    }
    x=(int)mouse.getX() - (int)offset.getX();               //look at these neat little mouse coordinates
    y=(int)mouse.getY() - (int)offset.getY();

    if (mDown) {                                             //if the mouse is held down (note this covers clicking and dragging because I felt very tempted to do so when testing)
      rgb = tab.check(x, y);                                  //loads pixels in the 10 px radius
      cx = x-11;                                            //sets the circle's new coords to 11 up to the left from the mouse (not 10 pixels since that would cut off the pixels 10 away, width and height of circle are set to 22px to counteract this 11px radius though)
      cy = y-11;
    }

    if (!showAll)                                           //only repaints if it's not in showAll mode, since painting that many small little guys 50 times a second would brick most computers
    repaint();
  }

  @Override
  public void mouseClicked(MouseEvent e) {}                 //surprising lack of mouse events do anything looking back
  @Override
  public void mouseEntered(MouseEvent e) {}
  @Override
  public void mouseExited(MouseEvent e) {}
  @Override
  public void mousePressed(MouseEvent e) {                  //when held down (or quickly tapped like a physcho) mDown is set to true and our little magnifying glass slash emotion-ray vision device follows you around
    mDown = true;
  }
  @Override
  public void mouseReleased(MouseEvent e) {                 //when it's let go, though it stays put and lets you stare at your house wondering why there's no excitement in your life
    mDown = false;
  }
  @Override
  public void keyPressed(KeyEvent e) {}
  @Override
  public void keyReleased(KeyEvent e) {}
  @Override
  public void keyTyped(KeyEvent e) {                        //quick toggle on every single key to show all emotions, please do not hold down the keys for long enough that they go into full automatic mode and spam toggle the lag mode on and off
    showAll=!showAll;
    if (showAll)
    showAll();
  }
}

class box {                                             //stores the stinky little emotions that want to be seen at all times

  int x, y;                                             //each has coordinates and rgb value for emotions
  int []rgb;

  public box(int x, int y, int []rgb) {
    this.x=x;
    this.y=y;
    this.rgb=rgb;
  }

  public void paint(Graphics g) {                         //similarly to how the pixels in the magnifying glass are painted, the box grabs its rgb and draws a 1x1 rect at its position
    g.setColor(new Color(rgb[0], rgb[1],rgb[2], rgb[3]));
    g.fillRect(x,y,1,1);
  }

  //wow, that's it
}

class EmoTable {                                                //the big boy, carries the program, absolutely massive hulking piece of pure glory that stores a city's whole emotional state and lets us look at it and laugh at the areas McKenzie wants to make fun of today


public HashyTable<Emotion> emos = new HashyTable<Emotion>();  //stores our emotions

public EmoTable() {                                           //on construction, just reads the file
  readFile();
}
  public ArrayList<int[]> check(int x, int y) {             //checks a radius around a coordinate
    ArrayList<int[]> out = new ArrayList<int[]>();          //outputs an arraylist of int arrays which handle each pixel
    for (int i = x-10; i<x+10; i++) {                       //loops through x -10 to +10 and y -10 to +10 relative to mouse coords
      for (int j = y-10; j<y+10; j++) {
        if ((x-i)*(x-i) + (y-j)*(y-j) <= 100)               //relative x^2 + relative y^2 less than or equal to 10^2, so it's within the 10px radius and I dont get ugly corners, also why I can't just store the pixels in a 2d array without x and y values stored :( 
          out.add(nearby(i, j));                            //gets the pixel at the current x,y and adds them to our list, nearby handles all the funky formatting
      }
    }
    return out;
  }
  
  public void readFile() {                                //grabs the data from the file
    try {
      File creeper = new File(/*again maybe try ..\\..\\ */"DataStructuresWork\\advanced data structures\\data\\creeper.txt");
      Scanner inFile = new Scanner(creeper);              //scans through the lines in the file, all 100,000 of them...
      while (inFile.hasNextLine()) {                      //checks if there's still more, safe to assume there is anyways but
        emos.add(new Emotion(inFile.nextLine()));         //look at my neat little constructor making my life easier
      }
      inFile.close();                                     //always closes scanners, nobel peace prize winner 2022 surely
    } catch (FileNotFoundException e) {                   //I've gotten more specialized with my error messages
      System.out.println("No creeper file ya dingus");
    }
  
  }

  public int []nearby(int x, int y) {                 //grabs the pixels at a set x, y and returns a neat little array for me to use when drawing them on later
    int []out = new int[6];                           //output array, stored [r, g, b, a, x, y]
    LinkedList<Emotion>near = emos.get(x*1000+y);     //grabs the values at the hash for this position
    if (near != null && near.size() != 0) {           //if there's emotions there
      for (Emotion e : near) {                        //grabs each emotion
        if (e.getX() == x) {                          //double checks that each one is at the right spot
        out[0] += e.LH;                               //adds together all of the emotions to their values
        out[1] += e.HS;
        out[2] += e.EB;
        out[3] = 1;                                   //alpha is just full opacity if we have values
        }
      }
      for (int i=0; i<out.length-1; i++) {              //for each value we map -100 to 100 to 0 to 255 
        out[i] /= near.size();
        out[i] += 100;
        out[i] *= 1.275;
      }
    } else {                                          //if there's nothing there it's transparent (but still has placeholder values just in case)
      out[0] = 123;
      out[1] = 123;
      out[2] = 123;
      out[3] = 0;
    }
    out[4] = x;                                     //no matter what it returns x and y
    out[5] = y;
    return out;
  }

}

class Emotion {                               //handles individual emotion readings so I can easily grab em and make colourful little dots out of their anger at a wendy's employee

  public int LH, HS, EB, X, Y;                //love/hate happy/sad excitement/boredom x-coord y-coord

  public Emotion(String fromFile) {           //take a look at this nice constructor
    String []spl = fromFile.split(" "); //string.split() has saved me countless hours of pain
    this.X = Integer.parseInt(spl[0]);        //just grabs them in the order they appear in the file
    this.Y = Integer.parseInt(spl[1]);        //parseInt has also saved me countless hours
    this.LH = Integer.parseInt(spl[2]);
    this.HS = Integer.parseInt(spl[3]);
    this.EB = Integer.parseInt(spl[4]);
  }

  public int getX() {return X;}               //gets coords to confirm that my hash isn't screwing it up
  public int GetY() {return Y;}

  @Override
  public int hashCode() {                     //look at that she overrode something that wasn't toString() massive character growth
    return X*1000 + Y;
  }
  @Override
  public String toString() {                  //oh
    return String.format("\n LH:%d, HS:%d, EB:%d", LH, HS, EB);  //outputs "LH:number, HS:number, EB:number", just for testing
  }
}