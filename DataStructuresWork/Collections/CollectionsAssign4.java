//CollectionsAssign4.java - original code by Fawn Barisic - program that tracks and stores parking offences made by the Massey parents who found a license on the side of the road and decided they were qualified to drive to school today

package Collections;

import java.io.*;     //file IO again :(
import java.util.*;   //collections again :)

public class CollectionsAssign4 {                         //controls the main functions: file read/write, UI, etc.
    public static void main(String []args) {              //just starts it up
      HashMap<String, car> plates = readFile();           //grabs our neat little stored data
      if (plates != null)
      menu(plates);                                       //if it works we run the menu
    }

    public static void menu(HashMap<String, car> plates) {  //menu loops infinitely taking user input and playing around with the data
      boolean running = true;                               //loop on/off, this is switched to false to close the program
      Scanner stdin = new Scanner(System.in);               //user input
      while (running) {                                     //loops forever if you so choose
      System.out.println("Select an action: \n1: plate lookup\n2:add an offence\n3: exit"); //such careful consideration made for the user interface, have to be an absolute genius to pull this off
      int opt = Integer.parseInt(stdin.nextLine());         //option converted to int
      switch (opt) {                                        //SHE DID A SWITCH CASE??? THE WORLD IS ENDING??? NO IF STATEMENTS????
        case (1):                                           //if ya put one it looks up the plate
          System.out.println("Enter a plate:");
          String plate = stdin.nextLine();                  //plate goes here
          if (plates.containsKey(plate))                    //if we got it on record itll show up
          System.out.println(plates.get(plate));
          else                                              //if not your attempt at investigative police work has failed, or you can't type license plates with high accuracy
          System.out.println("No offences found");
          break;
        case (2):                                                 //2 goes along and adds an offence
          System.out.println("Enter offender's license plate");
          String p = stdin.nextLine();                            //takes the plate
          System.out.println("Enter time of offence");
          String t = stdin.nextLine();                            //time
          System.out.println("Enter your initials");
          String i = stdin.nextLine();                            //initials
          if (plates.containsKey(p)) {                            //if it's in our "database" already it adds an offence and lets you know about their previous offences
            plates.get(p).add(t, i);
            System.out.println("offence added, check out their other "+(plates.get(p).offences-1)+" offence(s)");
          } else {                                                //if it's not in there, we make a new entry and lets ya know theyre a first time offender so go extra hard on them so you never have to see another offence
            plates.put(p, new car(p, t, i));
            System.out.println("offence added, no other offences");
          }
          break;
        case 3:                                                   //you leave to go home after a long day of reading the rusty plates of de-muffler-ed 2003 BCE honda civics
          System.out.println("Ending session, good work stalking today");
          running = false;                                        //breaks the loop
          break;
        }
      }
      writeFile(plates);                                          //writes the new stuff to the file
      stdin.close();                                              //closes scanner up never to be used again
    }

    public static HashMap<String, car> readFile() {               //believe it or not, it reads the file
      HashMap<String, car> out = new HashMap<String, car>();      //output map
      try {
        File cars = new File(/*if this isn't right these will look really stupid ..\\..\\*/"DataStructuresWork\\advanced data structures\\data\\cars.txt");
        Scanner inFile = new Scanner(cars);                       //scans in the file of cars...
        int ln = 0;                                               //stores line number, not necessary but ensures I don't go out of bounds on my car counting
        if (inFile.hasNextLine() && ln == 0) {                    //checks to make sure I'm on the first line and there are more lines
          ln++;
          int plateCount = Integer.parseInt(inFile.nextLine());   //keeps track of how many plates I can expect
          for (int i = 0; i<plateCount; i++) {                    //makes an entry for each plate 
            String plate = inFile.nextLine();                     //plate name storage unit
            int offences = Integer.parseInt(inFile.nextLine());   //offence number storage unit
            car c = new car(plate, inFile.nextLine(), inFile.nextLine()); //creates a new car and grabs the first offence's time and initials
            out.put(plate, c);                                    //puts it in the map
            for (int j = 0; j<offences-1; j++) {                  //grabs each offence and adds it in the same way as the constructor
              c.add(inFile.nextLine(), inFile.nextLine());
            }
          }
        }
        
        inFile.close();                                           //closes scanner :cool_emoji:
      } catch (FileNotFoundException e) {                         //I've actually become a menace over the course of these error messages
        System.out.println("no file haha");                     //as always try the quick path fix I add in to all of them
      }
      return out;

    }

    public static void writeFile(HashMap<String, car> plates) {                   //puts the data back into the data file, now with the new offences
      try{                                                                        //file IO errors actually going to kill me
        File path = new File(/*..\\..\\*/"DataStructuresWork\\advanced data structures\\data\\cars.txt");
        BufferedWriter bw = new BufferedWriter(new FileWriter(path, false)); //my good ol' pal bufferedWriter, makes a writer from a writer from a file and makes sure it writes over the old data
        String wr = ""+plates.size()+"\n";                                          //adds the amount of plates back to the top there
        for (car c : plates.values()) {                                             //then I add the perfectly formatted file string to the writer
          wr+=c.toFileString();
        }
        bw.write(wr);                                                              //writes the string, closes the writer, we can all be happy
        bw.close();
      } catch (IOException e) {                                                   //descriptive, efficient error messages that get down to the details of the problem
        System.out.println("stuff's writing weird");                           //really gets to the root of the problem
      }
    }
}

class car {                                           //stores/handles most of the offender's info

  String plate;                                       //plate name
  ArrayList<String> dates = new ArrayList<String>();    //dates of offences
  ArrayList<String> initials = new ArrayList<String>(); //initials of offences    these could be a separate object but the interaction is the same anyways especially since I'm not removing or doing anything else fancy 
  int offences;                                         //total number of offences

  public car(String plate, String time, String ini) {   //constructor takes plate name as well as the info from the initial offence
    offences = 1;                                       //starts at 1, if there's more it'll obviously add on then
    this.plate = plate;                                 
    dates.add(time);                                    //just adds the rest of the info in
    initials.add(ini);
  }
  public void add(String time, String ini) {            //add takes the time of offence and the initials
    dates.add(time);                                    //since this is the only time I really interact with it, arraylists work fine
    initials.add(ini);                                  //anyways I add the data in there and increment the offences to keep track
    offences++;
  }
  @Override
  public String toString() {                                            //nice output for user to see
    String regString = String.format("%d offences\n", offences);  //stores our regular output string
    for (int i = 0; i < offences; i++) {                                  //details all the offences on newlines and with nice formatting
      regString+= String.format("%d. date: %s\n\tinitial:%s\n", i+1, dates.get(i), initials.get(i));
    }
    return regString;
  }

  /*
   *  toString for user:
   *    8 offences
   *    1. date: 1 jan. 12:00
   *      initial: HS
   *   etc.
   */


  public String toFileString() {                                             //icky output for the file to see gross ew files
    String fileString = String.format("%s\n%d\n", plate, offences);  //stores our file output string
    for (int i = 0; i < offences; i++) {                                    //adds each offence 
      fileString+= String.format("%s\n%s\n", dates.get(i), initials.get(i));
    }
    return fileString;
  }
}

/*
 * toFile:
 *    NSHA 234
 *    5
 *    22 July 5:30
 *    LS
 *   etc.
 */