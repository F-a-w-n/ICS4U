//this is where the little babies fight to the death

//util <3
import java.util.*;

public class Battle {
  //list of player's pokemon, the enemy pokemon, and the currently used pokemon
  private ArrayList<Pokemon> player = new ArrayList<Pokemon>(4);
  private Pokemon enemy;
  private Pokemon current;

  //move scanner is how most of the inputs are handled (i know it's bad practice to have multiple scanners but it works so how bad can it be?)
  private Scanner move;
  //random for literally just the enemy attacks
  private Random rand = new Random();
  //stores the result of the fight (loss = false, win = true), used in main to see if you suck or you can keep going
  private boolean result = false;
  
  //constructor just sets pokemon and starts fighting right away
  public Battle(ArrayList<Pokemon> player, Pokemon enemy) {
    this.player=player;
    this.enemy = enemy;
    fight();
  }

  //fight method, basically loops through options till someone dies
  public void fight() {
    //starts up the scanner
    move = new Scanner(System.in);
    
    //resets energy at the start of each fight
    for (Pokemon p : player) {
      p.setEnergy(50);
    }

    //lets player know who theyre fighting (for nerds who care about stats)
    System.out.printf("> Enemy %s challenges you!\n", enemy.getname());

    //runs choosepokemon method so user can select which pokemon will fight the goat/bird/dog they have to kill
    choosePokemon();

    if (rand.nextInt(2) == 1) { //coinflip for who starts
      System.out.printf("Enemy %s starts the battle! \n", enemy.getname());
      System.out.printf("> Your %s's hp: %d energy: %d || Enemy %s's hp: %d energy: %d\n", current.getname(), current.getHP(), current.getEnergy(), enemy.getname(), enemy.getHP(), enemy.getEnergy());
      enemyChoice();
    } else { //if the player starts it runs code below normally
      System.out.printf("Your %s starts the battle! \n", current.getname());
    }

    //loving a good while(true), just loops till something dies
    while(true) {
      //updates energy normally (+10 for each pokemon in battle up till max)
      energyUpdate();
      //when you kill the enemy it breaks the loop and sets the result to true indicating a win
      if (enemy.getHP() <= 0) {
        System.out.println("> You beat " + enemy.getname());
        result = true;
        break;
      //when current pokemon dies, get rid of it
      } if (current.getHP() <= 0) {
        System.out.println("> " + current.getname() + " has been killed horrifically in battle");
        player.remove(player.indexOf(current));
        current = null;
      //if all your pokemon have died you suck and should consider a new career path besides pokemon training
      } if (player.size() <= 0) {
        System.out.println("> all your pokemon have died, L");
        result = false;
        break;
      //if your pokemon aren't all dead but your current pokemon died, it'll prompt to choose the next one
      } else if (current == null) {
        choosePokemon();
      //if you aren't dying, runs the next round of the battle
      } else {
        System.out.printf("> Your %s's hp: %d energy: %d || Enemy %s's hp: %d energy: %d\n", current.getname(), current.getHP(), current.getEnergy(), enemy.getname(), enemy.getHP(), enemy.getEnergy());
        turn();
      }
    }
  }

  //sets each pokemon's energy up 10 or to max (player contains current so it updates it there)
  private void energyUpdate() {
    for (int i=0; i<player.size(); i++) {
      player.get(i).setEnergy(Math.min(player.get(i).getEnergy()+10, 50));
    }
    enemy.setEnergy(Math.min(enemy.getEnergy()+10, 50));
  }

  //returns true for a win and false for a loss
  public boolean getOutcome() {
    return result;
  }
  //returns all the surviving pokemon, healed back to full health
  public ArrayList<Pokemon> getSurvivors() {
    for (Pokemon p : player) {
      p.heal();
    }
    return player;
  }

  //pokemon selection, used for when pokemon dies, retreats, or at the start to choose who's fighting
  private void choosePokemon() {
    //prompts user
    System.out.println("> Choose a Pokemon to start the battle with!");
    //displays each pokemon option
    for (int i = 0; i<player.size(); i++) {
      System.out.printf("%d) %s\n", i, player.get(i).getname());
    }
    //catches input error cause i had extra time and dont want it to crash on the 20th enemy defeated because i type attack instead of 0
    try {
      //grabs integer input
      int curval = Integer.parseInt(move.nextLine());
      //if it's within the bounds, selects that pokemon
      if (curval < player.size()) {
        current = player.get(curval);
      //if the user is dumb, runs again until they get it
      } else {
        choosePokemon();
      }
      //bugs user to use numbers and runs again
    } catch (NumberFormatException e) {
      System.out.println("> please input a number");
      choosePokemon();
    }
  }
  //runs each fighting turn, processes user choices and runs the advanced AI enemy's response
  private void turn() {
    System.out.println("> What will you do next? attack(0), retreat(1), pass(2)");
    String choice = move.nextLine();
    //fancy inputs with multiple options, quite pish posh fancy sauce indeed
    if (choice.equals("attack") || choice.equals("0")) {
      //runs user attack method
      playerChoice();
      //resets status after move
      current.setStatus(0);
      //enemy does the same (but cooler with more advanced AI obviously)
      enemyChoice();
      enemy.setStatus(0);
    } else if (choice.equals("retreat") || choice.equals("1")) {
      System.out.println("> retreating");
      //resets pokemon and chooses next one
      current.setStatus(0);
      choosePokemon();
      //player chose to give up turn, so enemy can attack but user cant
      enemyChoice();
      enemy.setStatus(0);
    } else if (choice.equals("pass") || choice.equals("2")) {
      //does nothing, resets status, enemy still attacks (the AI is very aggressive but i promise it's thoroughly calculated)
      System.out.println("> passing");
      current.setStatus(0);
      enemyChoice();
      enemy.setStatus(0);
    } else {
      //if it doesn't fit my fancy inputs pish posh fancy sauce system you gotta run that back again buster
      System.out.println("> invalid move, try again");
      turn();
    }
  }

  //attack processing method
  private void smack(Pokemon att, Pokemon opp, Attack a) {
    //modifier based on weaknesses and resistances
    double mod = (opp.getResistance().equals(att.getType()))? 0.5 : 1;
    mod *= (opp.getWeakness().equals(att.getType()))? 2 : 1;

    //checks if attacker has the energy to use the attack, and isn't stunned or dead
    if (att.useAttack(a)) {
      System.out.printf("> %s used %s\n", att.getname(), a.getName());
      //removes attack's energy cost
      att.setEnergy(att.getEnergy()-a.getEnergy());
      //runs special for the attack (or does nothing for lame non-special attacks)
      special(att, opp, a);
      //damages the other pokemon(-10 if attacker is disabled)
      if (att.getStatus() == Pokemon.DISABLE) 
      opp.damage((int)Math.floor(a.getDamage() * mod - 10));
      else 
      opp.damage((int)Math.floor(a.getDamage() * mod));
      //generic can't attack statement to save me 50 if statements
    } else {
      System.out.printf("> %s was unable to attack!\n", att.getname());
    }
  }
  //user attack, processes inputs and runs the attack method above
  private void playerChoice() {
    System.out.println("> Choose an attack!");
    //stores usable attacks so user doesn't get stuck wasting turns on abilities that cost too much
    ArrayList<Attack> usable = new ArrayList<Attack>(1);
    //displays pokemon's attacks
    for (int i=0; i<current.getAttacks().length; i++) {
      //if the pokemon has enough energy to use the attack, display it and add it to the list of attacks I can use
      if (current.getAttacks()[i].getEnergy() <= current.getEnergy()) {
        System.out.printf("%d: %s (energy cost: %d)\n", usable.size(), current.getAttacks()[i].getName(), current.getAttacks()[i].getEnergy());
        usable.add(current.getAttacks()[i]);
      }
    }
    //input handling so it doesn't crash as I'm about to kill onix after 50 attacks
    try {
      //if there are any attacks I can use
      if (usable.size() > 0) {
        //selects attack from pokemon and tries to use the attack above
        Attack ply = usable.get(Integer.parseInt(move.nextLine()));
        smack(current, enemy, ply);
      } else { //when I have no energy and can't attack, give options again (note: can't call turn without enemy pokemon hitting me twice for one move)
        System.out.println("Unable to attack at this time! (pass: 0, retreat: 1)");
        if (Integer.parseInt(move.nextLine()) == 1) { //same code as retreat code above
          current.setStatus(0);
          choosePokemon();
        } //nothing on pass move, just wastes a turn until pokemon has energy
      }
      //if the user can't read it makes them type the walk of shame and choose an attack again
    } catch (IndexOutOfBoundsException e) {
      System.out.println("> invalid move!");
      playerChoice();
      //if the user is literally blind gives them another chance to hit a number on the keyboard
    } catch (NumberFormatException e) {
      System.out.println("> please input a number");
      playerChoice();
    }
  }
  //SUPER ADVANCED ENEMY POKEMON AI (TM) (R) (C) CERTIFIED FANCY PISH POSH SAUCE GOOD ARTIFICIAL INTELLIGENCE
  private void enemyChoice() {
    //makes an arraylist of all available moves
    ArrayList<Attack> possible = new ArrayList<Attack>(enemy.getAttacks().length);
    for (Attack a : enemy.getAttacks()) {
      if (enemy.getEnergy() > a.getEnergy()) {
        possible.add(a);
      }
    }
    //random attack from the list of available moves
    if (possible.size() > 0) {
    Attack en = possible.get(rand.nextInt(possible.size()));
    //tries to use attack
    smack(enemy, current, en);
    } else { //if the enemy has no moves (and you can't kick them off the dance floor), they pass their turn
      System.out.println("> enemy passed their turn");
    }
  }

  //handles special abilities (so i can stunlock on mareep), very insightful comments below
  private void special(Pokemon att, Pokemon opp, Attack a) {
    //checks if the special lands(depending on the attack it lands every time or half the time)
    if (a.landSpecial()) {
      //using a switch case for the first time in like 4 years
      switch (a.getSpecial()) {
        case "stun": //the stun attack stuns the enemy
          opp.setStatus(Pokemon.STUNNED);
          System.out.println("> Attack stunned!");
          break;
        case "disable": //the disable attack disables the enemy
          opp.setStatus(Pokemon.DISABLE);
          System.out.println("> Attack disabled!");
          break;
        case "wild storm": //runs the attack again and again until it doesn't land (voltorb makes me think i can win gambling games for real)
          opp.damage(a.getDamage());
          System.out.println("> Wild storm landed!");
          special(att, opp, a);
          break;
        case "wild card": //if the wild card special activates it heals back the attacks damage (net 0 damage)
          opp.damage(-a.getDamage());
          System.out.println("> Wild card attack missed!");
          break;
        case "recharge": //recharges attack's pokemon's energy
          att.setEnergy(Math.min(50, att.getEnergy() + 20));
          System.out.println("> Attack recharged energy!");
          break;
      }
    }
  }
}
