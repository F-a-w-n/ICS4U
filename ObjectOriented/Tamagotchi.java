class Tamagotchi {
  private int hunger = 50, happiness = 50, discipline=50;
  private int age = 0;
  private String name="";

  public Tamagotchi(String name) {
    this.name=name;
  }
  public void age() {
    this.age+=1;
    this.happiness = Math.max(-1, this.happiness-1);
    this.discipline = Math.max(-1, this.discipline-1);
    this.hunger = Math.min(100, this.hunger+1);
  }
  public void feed() {
    this.hunger-=10;
  }
  public void scold() {
    this.discipline+=10;
  }
  public void play() {
    this.happiness+=10;
  }
  @Override
  public String toString() {
    //could also be array of strings and indeces that point to it (eg. happiness/20) but eh
    String hungermess = " Hunger: ";
    String dismess = " Discipline: ";
    String hapmess = " Happiness: ";
    String agemess = "";
    
    if (this.hunger > 90) {
      hungermess += " Starving ";
    } else if (this.hunger > 70) {
      hungermess += " Very Hungry ";
    } else if (this.hunger < 30) {
      hungermess += " Full! ";
    } else if (this.hunger < 10) {
      hungermess += " okay im full now please stop ";
    } else {
      hungermess = "";
    }

    if (this.discipline > 90) {
      dismess += " control freak ";
    } else if (this.discipline > 70) {
      dismess += " Good kid ";
    } else if (this.discipline < 30) {
      dismess += " questionable behaviour ";
    } else if (this.discipline < 10) {
      dismess += " okay im full now please stop ";
    } else {
      dismess = "";
    }

    if (this.happiness> 90) {
      hapmess += " literally high ";
    } else if (this.happiness > 70) {
      hapmess += " happy boi ";
    } else if (this.happiness < 30) {
      hapmess += " sad ";
    } else if (this.happiness < 10) {
      hapmess += " my mind is a cage and I am but a prisoner ";
    } else {
      hapmess = "";
    }

    agemess+=" "+this.age/(24*60) + "Days";
    agemess+=" "+this.age/60%24 + "Hours";
    agemess+=" "+this.age%60+" Minutes";
    return "Name: "+this.name+hapmess+dismess+hungermess+ "Age: "+ agemess;
  }
}