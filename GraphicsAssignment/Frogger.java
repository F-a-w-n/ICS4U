//Frogger.java - original code by Fawn Barisic - it's frogger but with worse graphics, worse sound design, and less levels

//swing graphics <333
import javax.swing.*;
//always a use for these guys
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Scanner;
//i just really wanted to store highscores
import java.io.*;

//main frame (please don't hack into it)
class Frogger extends JFrame {
  public static void main(String []args) {
    Frogger frame = new Frogger();
  }

  public Frogger() {
    //pretty much just makes a normal window with the panel in it and the panel does the rest
    super("Just a Froggy Lil' Guy"); //calls constructor and immediate superclass
    Panel pane = new Panel(); //main panel below
    add(pane);
    pack();
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//means my code stops running when window is closed
    setVisible(true); //dont show till everything is loaded
  }

}

//contains annd paints everything here           interfaces
class Panel extends JPanel implements ActionListener, MouseListener, KeyListener {
  //timer runs the game loop (sorta a loop more like re-running it) periodically at 50Hz
  Timer myTimer;
  //stores key inputs (either WASD or arrows for the most part)
  int []keys;
  //this is, believe it or not, the frog in the game
  private Frog froggy;
  //arraylists for cars and logs since theyre always spawning and getting removed
  private ArrayList<Car> cars = new ArrayList<Car>();
  private ArrayList<Log> logs = new ArrayList<Log>();
  //screen stores the current page or the game to display (start, game, score (which is really death), win, options, highscores)
  String screen = "start";
  //initial background image of a swanky young gentle-frog
  Image startscreen;
  //currentpath stores the directory of the game so I can use files for images and such without going insane because for some reason I need the entire gosh darn file path to get any work done around here
  File file = new File("");
  String currentPath = file.getAbsolutePath(); //plus look at how easy it is to type out currentPath anyways
  //deltaT stores the time spent in game, used for the timer and other fun little gizmos dependent on time
  int deltaT = 0;
  //background stored as an arraylist of tiles (not entirely necessary but I like arraylists and I also couldnt figure out how to make primitive arrays work that day)
  ArrayList<Backtile> background = new ArrayList<Backtile>();
  //image paths mapped to the backtile's type variables (i added the endzone after left and right which explains the empties that are never used)
  String []backimages = {"grass.jpg", "pavement.jpg", "water.jpg", "", "", "endzone.jpg"};
  //death string, used in the lose() method to let the user know how they screwed up this time
  String death = "If you're seeing this the game broke";
  //mode stores the level number 0-2
  int mode = 0;
  //speeds changes how fast each level goes, cars, logs, spawn rates, etc are all based on these
  int [][]speeds = {{110, 2, 35, 2, 75}, {85, 3, 75, 3, 65}, {75, 5, 60, 5, 50}};
  //stores the time you died so the game can loop for another fraction of a second so you can realize "oh heck I darn done did mess up there"
  int deathTime = 0;
  //buttons on the start screen (not stored in fancy arraylists like the others since theres just a couple) (also ignore that jframe has a button class built in I totally knew and decided to make better buttons)
  Button startButt, optionsButt, highScoreButt;
  //options screen buttons, not entirely necessary to be an arraylist but its nice for when I paint them
  ArrayList<Button> opts = new ArrayList<Button>(0);
  //very scientific, just the places (divided by 50) on the endzone where your frog doesnt run headfirst into a cinder block
  int []endspots = {2, 6, 9, 10, 13, 16, 17};
  //stores the types of the backtiles for each level so I can quickly add them to the current background arraylist initialized above
  int [][]levels = {
    {Backtile.GRASS, //note: they go bottom - top despite reading top - bottom 
    Backtile.GRASS, 
    Backtile.GRASS, 
    Backtile.PAVEMENT, 
    Backtile.GRASS, 
    Backtile.WATER, 
    Backtile.GRASS, 
    Backtile.PAVEMENT, 
    Backtile.PAVEMENT, 
    Backtile.GRASS, 
    Backtile.WATER, 
    Backtile.PAVEMENT, 
    Backtile.GRASS, 
    Backtile.PAVEMENT, 
    Backtile.PAVEMENT, 
    Backtile.ENDZONE},
    {Backtile.GRASS,
    Backtile.GRASS, 
    Backtile.GRASS, 
    Backtile.PAVEMENT, 
    Backtile.PAVEMENT, 
    Backtile.PAVEMENT, 
    Backtile.GRASS, 
    Backtile.WATER, 
    Backtile.WATER, 
    Backtile.GRASS, 
    Backtile.WATER, 
    Backtile.PAVEMENT, 
    Backtile.GRASS, 
    Backtile.PAVEMENT, 
    Backtile.WATER, 
    Backtile.ENDZONE},
    {Backtile.GRASS,
    Backtile.GRASS, 
    Backtile.GRASS, 
    Backtile.WATER, 
    Backtile.WATER, 
    Backtile.WATER, 
    Backtile.PAVEMENT, 
    Backtile.GRASS, 
    Backtile.PAVEMENT, 
    Backtile.PAVEMENT, 
    Backtile.WATER, 
    Backtile.PAVEMENT, 
    Backtile.PAVEMENT, 
    Backtile.GRASS, 
    Backtile.WATER, 
    Backtile.ENDZONE}                  
};
//time for each level (minus the 3 for count down so its effectively {30, 20, 15})
int []leveltime = {33, 23, 18};
//an array of buttons because I can't decide which type to use I am so sorry anyways this stores the restart, next, and menu buttons you see when you die or win a level
Button []resetButtons = {new Button(150, 600, 300, 75, "Restart"), new Button(500, 600, 200, 75, "Menu"), new Button(150, 600, 250, 75, "Next")};
//stores the controls for the frog in an array so that when you change the controls they're easier to pass in did i mention you can change the controls I am a user experience expert
int []controls = {KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT};
//arraylist of highscores because I am an overachiever, reads them from the file on init and updates each time you check the highscores tab
ArrayList<Score> highscores = readFileScores();
//current score of the player, not exactly handled perfect but works for the standard level 1 to 2 to 3 then save it setup
Score curScore;
//sounds for the background music and the death sound as well (I literally chose the first 2 off a site with 8-bit arcade sounds)
Sounds backMusic = new Sounds(currentPath+"/GraphicsAssignment/images/bMusic.wav");
Sounds dead = new Sounds(currentPath+"/GraphicsAssignment/images/dead.wav");
//menu button in game so you can go back to easy mode once youve tried level 3 for the tenth time 
Button inGameMenu = new Button(0, 750, 150, 50, "Menu");
//username is just randomly generated so you can tell them apart please dont ask for user input I tried for 3 seconds and failed
String username = "";

  public Panel() {
    super();
    setFocusable(true); //focuses before doing anything so it doesnt crash on start, I love swing
    requestFocus();
    //sets the stuff up that only need to be set once (not in reset() method)
    startscreen = new ImageIcon(currentPath+"/GraphicsAssignment/images/startbackground.png").getImage();
    keys = new int[500];
    //big window that you can't resize, good luck on a small screen >:)
    setPreferredSize(new Dimension(1000,800));
    //sets timer to run every 20ms as mentioned before
    myTimer = new Timer(20, this);
    //adds listeners for mouse and keys (theyre handled somewhere a couple hundred lines down i think)
    addMouseListener(this);
    addKeyListener(this);
    //runs reset to get all the other things set up
    reset();
  }
  
  //hey look reset's right here my code is legible
  public void reset() {
    //restarts times and brings you to the start screen
    deltaT=0;
    screen = "start";
    myTimer.start();
    //empties the keys array (not eternally holding down the up arrow key just yet)
    keys = new int[500];
    //resets frog and cars and logs to the beginning state
    froggy = new Frog("coolfrog.png", 450, 750, 50, 50, 1, controls);
    cars = new ArrayList<Car>();
    logs = new ArrayList<Log>();
    //adds all my buttons again (just in case the sound toggle gets hit too hard after 10 seconds in level 1)
    startButt = new Button(375, 300, 250, 75, "Start");
    optionsButt = new Button(350, 400, 300, 75, "Options");
    highScoreButt = new Button(375, 500, 250, 75, "Scores");
    opts.add(new Button(100, 200, 175, 75, "Easy"));
    opts.add(new Button(300, 200, 275, 75, "Medium"));
    opts.add(new Button(600, 200, 175, 75, "Hard"));
    opts.add(new Button(100, 350, 275, 75, "Arrows"));
    opts.add(new Button(525, 350, 250, 75, "WASD"));
    opts.add(new Button(600, 25, 250, 75, "Back"));
    opts.add(new Button(100, 450, 500, 75, "Reset Scores"));
    opts.add(new Button(100, 550, 500, 74, "Sound on/off"));
    
    //sets username here, literally 3 random letters
    String []alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");
    username = "";
    for (int i = 0; i<3; i++) {
      username+=alpha[randint(0, alpha.length)];
    }
    //creates a new score object for you to put a nice -15 on the leaderboards
    curScore = new Score(username+",0");
  }

  //literally just copied your randints (but they only go up to max-1 which is nice for arrays but will mean that im always going one more than maximum below)
  public int randint(int min, int max) {
    return (int)Math.floor(Math.random()*(max-min)+min);
  }
  public int randint(int max) {
    return (int)Math.floor(Math.random()*max);
  }
  //paints everything but the game here, code legibility now stops
  @Override
  public void paint(Graphics g) { 
    if (screen.equals("game")) { //just calls paintgame method directly below so I can breathe a little
      paintgame(g);
    } else if (screen.equals("start")) { //home screen
      //background
      g.drawImage(startscreen, 0, 0, getWidth(), getHeight(), this);
      //paints my buttons on
      startButt.paint(g);
      optionsButt.paint(g);
      highScoreButt.paint(g);
    
    } else if (screen.equals("highscores")) { //so you can see that XCY did better than you by a long shot
      g.setColor(Color.WHITE);
      g.drawImage(startscreen, 0, 0, getWidth(), getHeight(), this); //same background as before im not lazy its a great background
      g.setFont(new Font("Comic Sans MS", Font.BOLD, 50)); //first and only use of not bolded font (still comic sans you cant avoid the truth its the best one)
      g.drawString("High Scores:", 400, 200); 
      //look at her, doing nice things to make this easier to do
      for (int i = 0; i<highscores.size(); i++) {
        g.drawString(highscores.get(i).toString(), 250, 250+75*i); //look at that tostring going perfectly into the draw method i'm literally the best
      }
      opts.get(5).paint(g); //stole the back button from options because why reinvent the wheel
    } else if (screen.equals("score")) {
      if (deltaT-deathTime > 25) { //the literal only use for storing the time of death was to paint this a little after so they could watch in horror as their little froggy gets run over by car.png
        g.setColor(Color.BLACK);
        g.fillRect(100, 100, getWidth()-200, 350); //nice lookin black box over everything (you will see this is an aesthetic choice I make throughout)
        g.setColor(Color.WHITE);
        g.setFont(new Font("Comic Sans MS", Font.BOLD, 75));
        g.drawString(death, 100, 200);  
        g.drawString("Level: "+(mode+1), 100, 300); //lets you know those 3 deaths were only level 1
        g.drawString("Score: "+curScore.getScore(), 100, 400); //and there's no recovering from this score deficit, yes you can go into debt in this game it is a mechanic designed to crush anyone who plays it within their first level
        for (int b = 0; b<resetButtons.length-1; b++) { //paints all resetbuttons except the last one since the next button is only for winners, and this player clearly is a loser
          resetButtons[b].paint(g);
        }
      } else { //when it's not past the half second of regret the game still draws so you can watch the frog die by your hands
        paintgame(g);
      }
    } else if (screen.equals("win")) { 
      paintgame(g); //this one just paints the game behind it always since you're not going to die in the hole in the wall and it takes less lines
      g.setColor(Color.BLACK);
      g.fillRect(100, 100, getWidth()-200, 350); //the void reappears, but this time with good news
      g.setColor(Color.WHITE);
      g.setFont(new Font("Comic Sans MS", Font.BOLD, 75));
      g.drawString("You Win!", 100, 200);
      g.drawString(((mode < levels.length-1)? "Click to play next level!" : "No more levels"), 100, 300); //ternary operator saving the day, separates the meh winners from the actual winners 
      g.drawString("Score: "+curScore.getScore(), 100, 400); 
      for (int b = 1; b<resetButtons.length; b++) { //now it's reversed and it skips restart and shows next
        if (mode < levels.length-1 || b < resetButtons.length-1) { //but wait there's more: the reset button, this way you can't go next on the last level
          resetButtons[b].paint(g);
        }
      }

    } else if (screen.equals("options")) {  //now this is where I show off (kinda)
      g.drawImage(startscreen, 0, 0, getWidth(), getHeight(), this); //best background, love thematic consistency, graphic design is my passion
      g.setColor(Color.WHITE);
      g.setFont(new Font("Comic Sans MS", Font.BOLD, 75));  
      g.drawString("Options", 50, 100);
      //LOOK AT THAT IT'S REAL IT'S THE ARRAYLIST POWER 3 LINES FOR LIKE 8 BUTTONS BE PROUD OF ME PLEASE THIS IS THE ONLY THING I DID RIGHT SORTA
      for (Button b : opts) {
        b.paint(g);
      }
    } else { //if you create a new screen I just send you back to the start
      screen = "start";
    }
  }

  private void paintgame(Graphics g) { //yess game painting love it love it work it paintgame(g)

    g.setColor(Color.BLACK);
    g.fillRect(0, 0, getWidth(), getHeight()); //covers up any previous screen just in case backtiles are being funky

    for (Backtile s : background) { //paints my tiles
      s.Paint(g, this);
    }
    for (Log l : logs) { //paints my logs
      l.Paint(g, this);
    }
    froggy.Paint(g, this); //omg it paints my froggy too!
    for (Car c : cars) { //cars.
      c.Paint(g, this);
    }
    
    if (deltaT/50<3) { //counts down at the beginning
      g.setColor(Color.WHITE);
      g.setFont(new Font("Comic Sans MS", Font.BOLD, 75)); 
      //casts to int, rounds to decimal so it can be an int, then it subtracts deltaT converted to seconds from 3 (so it goes 3, 2, 1 not 1, 2, 3), I reuse this later too a few times to get time in seconds 
      g.drawString("Starting in:"+((int)Math.floor(3-(deltaT/50))), 100, 200); 
    } else {
      g.setColor(Color.WHITE);
      g.setFont(new Font("Comic Sans MS", Font.BOLD, 50)); 
      g.drawString("Level" + (mode+1), 10, 60); //shows level number at top
      g.setFont(new Font("Comic Sans MS", Font.BOLD, 30)); 
      if ((int)Math.floor(leveltime[mode]-(deltaT/50)) < 5) { //hey look i used it again to see if there's <5 seconds left to make it more intense
        g.setColor(Color.RED);
      }
      g.drawString("Time: " + (int)Math.floor(leveltime[mode]-(deltaT/50)) + "s", 750, getHeight()); //omg I used it again here to display the time left on the timer based on the level time
      inGameMenu.paint(g); //menu button again
    }
  }

  public void lose(String death, int timeOfDeath) { //makes it a little cleaner to kill a frog, just pass a small statement about how bad the player is and deltaT
    this.death = death;
    deathTime=timeOfDeath;
    screen = "score";
    backMusic.stop(); //dramatic music
    dead.Play();
    curScore.addScore(-5); //going into debt because the log went too fast for my brain
  }

  public void periodic() { //oh boy hold on to your socks
    if (deltaT >= 150) { //runs all of this after the timer so that you dont see a log moving when it counts down, ruining your plans to hip hop across the river
      if ((int)Math.floor(leveltime[mode]-(deltaT/50)) <= 0) { //I USED IT AGAIN ITS SO USEFUL IF ONLY I MADE A METHOD OF THIS SO I DONT KEEP SENDING YOU TO LINE 284 FOR AN EXPLANATION
        lose("Took too long!", deltaT); //easier than a 6 line mess here to say the frog is dead for sure
      }
      froggy.Move(keys, deltaT); //moves frog deltaT stops you from spamming moves, but it's cool because I queue moves so you can just tap it
      if (cars.size() > 0) { //when there's cars you move them
        for (int i = 0; i<cars.size(); i++) {
          cars.get(i).Move(deltaT);
          if (froggy.collide(cars.get(i).getRect())) { //if the frog collides with the car you lose
            lose("Got hit by a car!", deltaT);
          }
          if (cars.get(i).getX() > getWidth() || cars.get(i).getX() < -50) { //if the car is off the screen I remove it and carefully (stupidly with no regard for my future self) sidestep concurrent modification 
            cars.remove(i);
            i--; //this line is all it takes to break the game it's kinda beautiful and poetic
          }
        }
      }
      for (int i = 0; i<logs.size(); i++) { 
        boolean kill = false; //if true, the log gets removed, since there is already a log
        if (logs.get(i).getX()<-250 || logs.get(i).getX() > getWidth()) { //if the log is off the screen, kill it
          kill = true;
        }
        for (int j = 0; j<logs.size(); j++) { //if the log collides with any other log, kill it
          if (logs.get(j) != logs.get(i)) {
            if (logs.get(j).getRect().intersects(logs.get(i).getRect())) {
              kill = true;
            } 
          }
        }
        if (kill) { //remove it, avoid concurrent modification, all the fun stuff from before
          logs.remove(i);
          i--;
        }
      }
      
      for (Backtile t : background) { //runs code for each backtile since there's some things sprites just don't have a good way of doing
        if (froggy.collide(t.getRect())) {
          if (t.getType() != Backtile.WATER) { //if the frog is getting off a log (which moves it in intervals <50px) it rounds it back up so the frog can still move the same and enter the endzone the right way
            froggy.roundX(50);//look at this nice roundX method I made that doesn't need a method but I couldn't easily adjust it outside of sprite class
          }
          if (t.getType()==Backtile.WATER) { //if it IS on water it checks if your frog is drowning or not
            boolean drowning = true; //assumed guilty until proven innocent
            int lmove = 0; //move the collided log makes, returned from my log moving method like a girlboss
            for (Log log : logs) { //checks each log for collision
              Rectangle oldlog = log.getRect(); //stores the log before moving it so you don't get the carpet pulled out from under you
              int lmaybe = log.Move(deltaT); //lmaybe stores the log's move but will be discarded by the next log, thus the need for lmove
              if (froggy.collide(log.getRect()) || froggy.collide(oldlog)) {
                drowning = false; //congrats you're not dead
                lmove = (lmaybe != 0)? lmaybe : lmove; //sets lmove to lmaybe if lmaybe is not 0, since that would reset lmove when you should be moving
                //note: can't break here because I handle log movement in the same loop
              }
            }
            if (!drowning) { //if you're not drowning the frog moves
              froggy.setX(lmove); 
            }
            
            if (drowning) { //frog dies and it's all your fault
              lose("Can't swim!", deltaT);
            }
            
          } else if (t.getType() == Backtile.ENDZONE) { 
            boolean wall = true; //if wall stays true your frog goes splat and dies
            for (int spot : endspots) { //loops through the endspots from the beginning
              if (froggy.getX() == spot*50) { //if you're on the spot you don't have to die
                wall = false;
              }
            }           
            if (!wall) { //no win method since this is the only way you win
              screen = "win";
              curScore.addScore((int)Math.floor(leveltime[mode]-(deltaT/50))*(mode+1)); //look now I use it to calculate scores, (multiplied by the level to adjust for difficulty of course)
              if (mode == levels.length-1) { //if it's the last level, writes your score to the leaderboard (unless it's full then you have to be good at the game)
                writeFileScore(curScore);
              } 
            } else { //for those nerds who cant jump into the black rectangle on brick.jpg
              lose("hit a brick wall!", deltaT);
            }
          } else {
            for (Log log : logs) {
              log.Move(deltaT); //so logs move when you aren't on a water tile
            }
          }
        } //now these aren't based on what your frog is on but just looping though to spawn cars and logs
        if (t.getType() == Backtile.PAVEMENT) {
          if (deltaT % (speeds[mode][0] + (t.getY()/50) * randint(-5, 5)) == 0) { //at different intervals for each tile (with a little hint of randint so they aren't just a diagonal of cars coming at you), cars spawn in
              //makes a rectangle with the car's spawn location
              Rectangle cartangle = new Rectangle(((t.getDir() == Backtile.LEFT)? getWidth() : -50), t.getY(), 50 ,50);
              boolean crashed = false; //stores whether or not there is another car where the car is spawning
              
              for (Car c : cars) { //loops through all cars
                if (cartangle.intersects(c.getRect())) {
                  crashed = true; //if it collides with one it can't spawn
                  break;//no need to loop through if it's not gonna spawn either way
                }
              }
              if (!crashed) { //if you don't crash you can spawn in 
                //types and everything for the car based on the tile's direction
                cars.add(new Car(((t.getDir() == Backtile.LEFT)? "lcar.png" : "rcar.png"), ((t.getDir() == Backtile.LEFT)? getWidth() : -50), t.getY(), 50, 50, t.getDir()-3, speeds[mode][1]));  
              }
          }
        } else if (t.getType() == Backtile.WATER) {
          //at different times for each tile, spawns a log when  the size is less than the max of 25, and if there's none always make a new log
          if ((deltaT % (speeds[mode][2] + (t.getY()/50)) == 0 && logs.size() < 25) || logs.size() == 0) {
            int len = randint(1, 5)*50;  //length random interval of 50 between 50 and 250
            String path = (len <= 100)? "turtle.png" : "log.png"; //oh did i mention some of the logs are hyperrealistic turtles? yeah don't thank me too much
            int logX = (t.getDir() == 4)? getWidth() : -250; //starting position always outside of the screen
            boolean pass = true; //keeps track of if the log has any very close neighbors
            for (Log l : logs) { 
              //if it intersects a log it doesnt pass
              if (new Rectangle(logX, t.getY(), len, 50).intersects(l.getRect())) {
                pass = false;
              }
            }
            if (pass) { //but if it does pass you get a new log
              logs.add(new Log(path, logX, t.getY(), len, 50, t.getDir()-3, speeds[mode][3]));
            }
          }
        }

      }
      if (froggy.getY() > getHeight()-50) { //if the frog goes too low it pops it back up
        froggy.setY(-1);
      }
    } else { //this is for before the countdown ends
      keys = new int[500]; //resets keys so you don't have any baggage going into each level
      if (deltaT <= 2) { //loops a couple times at the start of countdown
        for (Backtile t : background) { 
          if (t.getType() == Backtile.PAVEMENT) { //spawns a couple cars on each pavement tile
            cars.add(new Car(((t.getDir() == Backtile.LEFT)? "lcar.png" : "rcar.png"), ((t.getDir() == 4)? 400+randint(getWidth()/100)*50 : randint(8)*50), t.getY(), 50, 50, t.getDir()-3, speeds[mode][1]));
          } else if (t.getType() == Backtile.WATER) { //runs the same log spawning code from before
            int logWidth = randint(1, 5)*50;
            int logX = (t.getDir() == 4)? 400+randint(8)*50 : randint(8)*50; //only difference is that the X values are inside the player's view
            boolean pass = true;
            for (Log l : logs) {
              if (new Rectangle(logX, t.getY(), logWidth, 50).intersects(l.getRect())) {
                pass = false;
              }
            }
            if (pass) {
              logs.add(new Log("log.png", logX, t.getY(), logWidth, 50, t.getDir()-3, speeds[mode][3]));
            }
          }
        }      
      }
    }
  }
  public void loadbackground() { 
    background = new ArrayList<Backtile>(); //empties background
    for (int b = 0; b < levels[mode].length; b++) { //adds new background tile from the array of types at the start
      background.add(new Backtile(backimages[levels[mode][b]-1], getHeight()-50*(b+1), getWidth(), 50, levels[mode][b], randint(Backtile.LEFT, Backtile.RIGHT+1)));
    }
  }
  public void resetLevel(int newmode) { //resets the level (surprisingly)
    deltaT=0; //sets deltaT to 0 to reset the timer in game
    mode = newmode; //if the new mode passed is not the current mode it'll change that here
    if (mode < levels.length) { //makes sure the level exists
      froggy = new Frog("coolfrog.png", 450, 750, 50, 50, 1, controls); //new frog so it's at the right spot
      cars = new ArrayList<Car>(); //resets logs and cars so there arent logs on grass and cars on water
      logs = new ArrayList<Log>();
      screen = "game";
      loadbackground(); //hey we just saw this guy
    } else { //resets to start screen if you run out of levels
      reset();
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (screen.equals("game")) { //periodic only run in game
      periodic();
    }
    if (!screen.equals("win")) //deltaT only increases once you've won
    deltaT++;
    //repaint always runs I just didnt bracket that wasnt super clear of me
    repaint();
  }

  @Override
  public void mouseClicked(MouseEvent e) { 
    //mouse position
    Point mouse = MouseInfo.getPointerInfo().getLocation();
    //window position
    Point offset = getLocationOnScreen();
    //fixing mouse position to be relative to window (I didn't even copy/paste yours so it took me like an hour to fix)
    mouse.setLocation((int)mouse.getX() - (int)offset.getX(), (int)mouse.getY() - (int)offset.getY());
    if (screen.equals("start")) { //checks for the 3 button on start screen
      if (startButt.detectClick(mouse)) {
        if (mode >= 3) { //overflows level to 0
          mode = 0;
        }
        resetLevel(mode); //resets level to the new mode
        screen = "game"; //sends you to the game
        backMusic.stop(); //restarts the background music
        backMusic.Play();
      } else if (optionsButt.detectClick(mouse)) {
        screen = "options"; //just sends you to options
      } else if (highScoreButt.detectClick(mouse)) {
        screen = "highscores"; //sends you to highscores
        readFileScores(); //reads the highscores again to keep it up to date (especially if you just got a highscore and changed the file)
      }
     
    } else if (screen.equals("score")) {
      if (resetButtons[0].detectClick(mouse)) { //restart
        resetLevel(mode); //just restarts it all
        backMusic.stop();
        backMusic.Play();
      } else if (resetButtons[1].detectClick(mouse)) { //menu
        reset();
      }
    } else if (screen.equals("options")) {
      if (opts.get(0).detectClick(mouse)) { //easy
        mode=0; 
      } else if (opts.get(1).detectClick(mouse)) { //medium
        mode=1; 
      } else if (opts.get(2).detectClick(mouse)) { //hard
        mode=2; 
      } else if (opts.get(3).detectClick(mouse)) { //arrows
        int []arrows = {KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT};
        controls = arrows; //sets controls array to the arrow keys and remakes froggy with those controls
        froggy = new Frog("coolfrog.png", 450, 750, 50, 50, 1, controls);
      } else if (opts.get(4).detectClick(mouse)) { //wasd
        int []wasd = {KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D};
        controls = wasd; //same here, sets controls to wasd and resets froggy
        froggy = new Frog("coolfrog.png", 450, 750, 50, 50, 1, controls);
      } else if (opts.get(5).detectClick(mouse)) { //back
        screen = "start";
      } else if (opts.get(6).detectClick(mouse)) { //resets highscores
        resetHighScores();  //resets the file and then reads from the empty file
        highscores = readFileScores();
      } else if (opts.get(7).detectClick(mouse)) { //toggles background music on/off
        backMusic.togglePlay(); //another great method, thanks self
        dead.togglePlay(); 
      }
    } else if (screen.equals("win")) {
      if (resetButtons[0].detectClick(mouse) && mode < levels.length-1) { //next
        resetLevel(mode+1); //resets to the next level
      } else if (resetButtons[1].detectClick(mouse)) { //menu
        mode++; //just goes to start screen but with the level ticked up
        resetLevel(mode);
        screen="start";
      }
    } else if (screen.equals("highscores")) {
      if (opts.get(5).detectClick(mouse)) { //back
        screen = "start";
      }
    } else if (screen.equals("game")) {
      if (inGameMenu.detectClick(mouse)) { //menu
        screen = "start";
      }
    }
  }
  @Override
  public void mouseEntered(MouseEvent e) {}  //didnt use any of these
  @Override
  public void mouseExited(MouseEvent e) {}
  @Override
  public void mousePressed(MouseEvent e) {}
  @Override
  public void mouseReleased(MouseEvent e) {}
  @Override
  public void keyPressed(KeyEvent e) { //increments keys at that spot, doesn't do much but i feel like it helps more with the move queueing idea I wanted for the frog
    keys[e.getKeyCode()]+=1;
  }
  @Override
  public void keyReleased(KeyEvent e) {} //decrements keys in frog, nothing here
  @Override
  public void keyTyped(KeyEvent e) {}

  public ArrayList<Score> readFileScores() {
    try {
      File path = new File((currentPath+"/GraphicsAssignment/highscores.txt")); //reads from highscore file
      //System.out.println(path.exists());
      Scanner infile = new Scanner(path);
      ArrayList<Score> scores = new ArrayList<Score>(); //makes an arraylist of the highscores
      
      while (infile.hasNextLine()) {//reads every line in the file
        scores.add(new Score(infile.nextLine())); //Score class takes string from file so nice
      }

      infile.close(); //always close scanners or vs code will show up in your basement under the stairs to grab your ankles through the gaps
      return scores; //returns arraylist because I make too many voids in my life
    } catch (FileNotFoundException e) { //professional error handling
      System.out.println("no high score file found");
      return new ArrayList<Score>();
    } catch (NullPointerException e) {
      System.out.println("no high score file found");
      return new ArrayList<Score>();
    }
  }
  public boolean writeFileScore(Score s) {
    int index = -1; //if your score is lower than any other it stays -1 otherwise it's the index in the arraylist (1, 2, 3, etc)
    
    for (int i = highscores.size()-1; i>=0; i--) { // loops through the highscores backwards since the lower indeces are higher scores
      if (highscores.get(i).getScore() <= s.getScore()) { //if you're better than them your index is set to their index
        index = highscores.indexOf(highscores.get(i));
      }
    }
    if (index != -1) { //if you have a new highscore

      ArrayList<Score> newHighScores = new ArrayList<Score>();
      for (int i = 0; i < index; i++) { //all the scores better than you are added the same
        newHighScores.add(highscores.get(i));
      }
      newHighScores.add(s); //then you pop in
      int i = index; //then it adds the rest until the max amount of 5
      while (newHighScores.size()<5) {
        newHighScores.add(highscores.get(i));
        i++;
      }
      highscores = newHighScores; //sets highscores variable to the new arraylist
      
      try{
      File path = new File((currentPath+"/GraphicsAssignment/highscores.txt"));
      BufferedWriter bw = new BufferedWriter(new FileWriter(path)); //opens file up to write scores

      for (Score w : newHighScores) { //loops through the scores and writes them to the file all formatted nice
        bw.write(w.toFile());
      }
      bw.close(); //close your writers too

      } catch (IOException e) { //IO wants me dead some times
        System.out.println("no high score file found");
      }

      return true; //if you got a high score returns true
    }
    //if your score is bad returns false
    return false;
    
  }

  public void resetHighScores() { //empties the file out
    try{
      File path = new File((currentPath+"/GraphicsAssignment/highscores.txt"));
      BufferedWriter bw = new BufferedWriter(new FileWriter(path)); //writer again

      for (int i = 0; i<5; i++) {
        bw.write("EMPTY,0\n"); //puts "EMPTY,0" in each place, so it still reads but is clearly empty
      }

      bw.close(); //close writers :)

      } catch (IOException e) { //I hate files with a passion
        System.out.println("no high score file found");
      }
  }
  
}
//this is the 1,005th line of code