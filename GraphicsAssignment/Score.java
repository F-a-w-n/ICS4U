//Score.java - original code by Fawn Barisic - a nice class to make score management easier on me because I'm the best

//stores and formats highscores
public class Score {
  //name is really just a random string but dont tell anyone
  private String name;
  private int score;

  public Score(String name, int score) { //from separate variables
    this.name = name;
    this.score = score;
  }
  public Score(String fromfile) { //string manipulation to get em from the file
    String []splile = fromfile.split(","); //splile as in split file i swear it makes sense
    this.name = splile[0];
    this.score = Integer.parseInt(splile[1]);
  }

  public String toFile() { //quick formatting
    return String.format("%s,%d\n", name, score);
  }

  public int getScore() { //gets the score for comparisons and such in adding to high score
    return score;
  }
  public void addScore(int x) { //adds score in game
    score+=x;
  }
  public String getName() { //it really gets the name, who could have guessed?
    return name;
  }

  @Override
  public String toString() { //formatted legibly for the highscores screen
    return String.format("%s - Score: %d", name, score);
  }
}
