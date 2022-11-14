//comfort method, makes it easier to access info about attacks

import java.util.Random;

public class Attack {
  private String name, special;
  private int energy, damage;
  private Random rand = new Random();

  //did all my string handling in pokemon (thanks past self)
  public Attack(String name, int energy, int damage, String special) {
    this.name = name;
    this.energy = energy;
    this.damage = damage;
    this.special = special;
  }

  //gets values, literally most boring method possible
  public String getName() {return this.name;}
  public int getEnergy() {return this.energy;}
  public int getDamage() {return this.damage;}
  public String getSpecial() {return this.special;}
  //checks if pokemon lands special
  public boolean landSpecial() {
    //if it's a 50% chance flips a coin to return true
    if (this.special.equals("stun") || this.special.equals("wild card") ||this.special.equals("wild storm")) {
      if (rand.nextInt(2) == 1) {
        return true;
      } else {
        return false;
      }
      //if it's always then it just returns true
    } else {
      return true;
    }
  }

}
