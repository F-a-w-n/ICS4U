//where all the little fellas are constructed

public class Pokemon {
  private String name;
  private int hp, energy, maxHP;
  private String type, resist, weak;
  private Attack []attacks;
  private int status = 0;

  //fancy status storing constants so I can type Pokemon.STUNNED and not go insane because i set it to 2 instead of 1 or something
  public static final int STUNNED = 1;
  public static final int DISABLE = 2;

  //string manipulation <3
  public Pokemon(String poke) {
    //split poke, name sounds funny but it makes sense dont laugh at me it makes sense im not in denial
    String []sploke = poke.split(","); //splits at the commas in the lines for each pokemon
    this.name = sploke[0]; //first part is the name
    this.hp = Integer.parseInt(sploke[1]); //second bit of data is the max hp, sets maxhp and hp so i have one constant number and one that i can change to store current hp
    this.maxHP = Integer.parseInt(sploke[1]);
    this.type = sploke[2]; //third part indicates type
    this.resist = sploke[3]; //4th indicates resistance
    this.weak = sploke[4]; //5th indicates weakness
    attacks = new Attack[Integer.parseInt(sploke[5])]; //creates array of attacks at the specified length in the 6th section
    //loops through each attack's deets and makes an attack in the array above
    for (int i=0; i<attacks.length; i++) {
      //---------------------- 1st index is the name, second is energy, third is damage, fourth is special, adds i*4 so it get's the next attacks indeces 4 after the start of this one
      attacks[i] = new Attack(sploke[6+i*4], Integer.parseInt(sploke[7+i*4]), Integer.parseInt(sploke[8+i*4]), sploke[9+i*4]);
    }
    //energy is always 50, very helpful
    energy = 50;
  }

  //tostring outputs name, stats, and type info
  @Override
  public String toString() {
    return String.format("Name: %s, HP: %d, Type: %s, Resistance: %s, Weakness: %s", name, hp, type, resist, weak);
  }
  //attackstostring outputs each of the pokemon's attacks
  public String AttacksToString() {
    String o = "";
    //loops through each attack, with stats
    for (Attack a:attacks) {
      o+=String.format("> Attack: %s, Energy cost: %d, Damage: %d, Special: %s \n", a.getName(), a.getEnergy(), a.getDamage(), a.getSpecial());
    }
    return o;
  }
  //standard get methods so i can have info nicely
  public Attack []getAttacks () {return attacks;}
  public String getname() {return this.name;}
  public int getHP() {return this.hp;}
  public int getEnergy() {return this.energy;}
  public String getType() {return this.type;}
  public String getWeakness() {return this.weak;}
  public String getResistance() {return this.resist;}
  //damages pokemon (or heals if given negative input but lets be real theyre mostly just killing eachother)
  public boolean damage(int power) {
    //subtracts damage done from hp
    this.hp -= power;
    //if it's dead, returns true (can't remember if I use this or not but it's helpful)
    if (this.hp > 0) {
      return false;
    } else {
      return true;
    }
  }
  //standard setter method, didn't like how adding or subtracting in this method worked for me so i just to set(get() +/- energy)
  public void setEnergy(int newEn) {this.energy = newEn;}

  //checks if pokemon's able to use the attack
  public boolean useAttack(Attack a) {
    int cost = a.getEnergy();
    //checks if your pokemon has energy, is alive, and isn't stunned
    if (this.energy-cost >= 0 && this.hp > 0 && this.status!=Pokemon.STUNNED) {
      return true;
    } else {
      return false;
    }
  }
  //heals hp for between rounds
  public void heal() {this.hp = Math.min(hp+20, maxHP);}
  //standard setter/getter methods for status
  public void setStatus(int s) {this.status = s;}
  public int getStatus() {return this.status;}

}
