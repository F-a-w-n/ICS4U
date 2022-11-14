//main class, takes pokemon from file and allows player to pick their 4, arranges random enemy battles

//beloved util
import java.util.*;
//io to grab from files
import java.io.*;

public class Arena {
  //stores total amount of pokemon from the file, used to ensure no funky inputs out of the possible choices
  static int len = 0;

  public static void main(String []args) {

    //kb scanner really just to grab a few integers for pokemon selection
    Scanner kb = new Scanner(System.in);
    //arraylist of all pokemon determined by file input from setup method
    ArrayList<Pokemon> pokes = setup();
    //list of pokemon the player chooses below
    ArrayList<Pokemon> player = new ArrayList<Pokemon>(4);

    //random to assign random enemy battles, more interesting than a first round gyrados every time
    Random rand = new Random();
  
    //choosing pokemon for player
    //loops through 4 times for 4 pokemon
    for (int i=0; i<4; i++) {
      //loops infinitely, breaking when the user gets a brain and can input a proper pokemon's number
      while (true) {
        //temporary storage of user input until i know it's safe to use
        int temp = kb.nextInt()-1;
        //checks if temp is within the proper bounds (1-last pokemon)
        if (temp >= len || temp < 0) {
          System.out.println("> not a valid pokemon!");
        //checks if pokemon is already chosen by the player (don't want an error from choosing mareep 4 times)
        } else if (player.contains(pokes.get(temp))) {
          System.out.println("> already chosen!");
        //if theyre not stupid it adds the pokemon to the player's list (but doesnt remove from the main one yet so indeces stay the same)
        } else {
          player.add(pokes.get(temp));
          break;
        }
      }
      //outputs the chosen pokemon so users with ocd like me can confirm
      System.out.println(player.get(i).toString());
    }
    //now we loop through and get rid of the player's pokemon from the enemy list (so you don't have to fight yourself)
    for (Pokemon p : player) {
      pokes.remove(p);
    }

    //loving while(true) loops recently, goes until you die or you kill them all
    while (true) {
      //if there are still enemies
      if (pokes.size() > 0) {
        //chooses random enemy and makes a new battle
        int enemyindex = rand.nextInt(pokes.size());
        Battle curBattle = new Battle(player, pokes.get(enemyindex));
        //removes enemy from the list of potential enemies (so you eventually run out)
        pokes.remove(enemyindex);
        //if the outcome is true you win, updates your arraylist for next time so you arent fighting with the corpse of your deceased pikachu
        if (curBattle.getOutcome()){
          player = curBattle.getSurvivors();
        //if you lose it degrades you and stops looping battles
        } else {
          System.out.printf("> you suck! There were %d pokemon left!", pokes.size()+1);
          break;
        }
      //if there are no more enemies you have taken the gym, but at what cost? Is some mere title worthy of giving your soul and the lives of countless innocent creatures? probably.
      } else {
        System.out.println("> you are the trainer supreme!");
        break;
      }
    }
    //closes scanner so i can sleep at night
    kb.close();
  } 

  //setup method, grabs all pokemon from file and lets main know the amount of pokemon
  public static ArrayList<Pokemon> setup() {
    try {
    //grabs pokemon.txt from folder
    Scanner infile = new Scanner(new File("pokemon.txt"));

    //the amount of pokemon is specified by a lone integer on the first line
    len = Integer.parseInt(infile.nextLine());

    //arraylist is created to store all the pokemons
    ArrayList<Pokemon> all = new ArrayList<Pokemon>(len);

    //loops through each pokemon's line and constructs a new pokemon with that string, adding it to the list of all pokemon
    for (int i=0; i<len; i++) {
      all.add(new Pokemon(infile.nextLine()));
      //also outputs the stats and attacks so the nerds can rest easy
      System.out.println(i+1+") "+all.get(i).toString());
      System.out.println(all.get(i).AttacksToString());
    }
    //closes file scanner
    infile.close();
    //returns our fancy arraylist of all the pokemon
    return all;

    } catch (FileNotFoundException e) { //degrades you if there is no file at that location
      System.out.println("> File isn't there stupidhead");
      return null;
    } 
  }
  
}
