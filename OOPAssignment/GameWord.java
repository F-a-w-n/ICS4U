//GameWord.java - Original code by Fawn Barisic - it plays scrabble for you

//arraylists for permutations
import java.util.ArrayList;

public class GameWord {
  //stores contents of the object
  private String contents = "";
  //stores board's score and word multipliers as wordmultiplier * 10 + letter multiplier, ex: triple word = 31, double letter = 12
  public static final int [][]gameBoard = {
    {31,0,0,12,0,0,0,31,0,0,0,12,0,0,31},
    {0,21,0,0,0,13,0,0,0,13,0,0,0,21,0},
    {0,0,21,0,0,0,12,0,12,0,0,0,21,0,0},
    {12,0,0,21,0,0,0,12,0,0,0,21,0,0,12},
    {0,0,0,0,21,0,0,0,0,0,21,0,0,0,0},
    {0,13,0,0,0,13,0,0,0,13,0,0,0,13,0},
    {0,0,12,0,0,0,12,0,12,0,0,0,12,0,0},
    {31,0,0,12,0,0,0,12,0,0,0,12,0,0,31},
    {0,0,12,0,0,0,12,0,12,0,0,0,12,0,0},
    {0,13,0,0,0,13,0,0,0,13,0,0,0,13,0},
    {0,0,0,0,21,0,0,0,0,0,21,0,0,0,0},
    {12,0,0,21,0,0,0,12,0,0,0,21,0,0,12},
    {0,0,21,0,0,0,12,0,12,0,0,0,21,0,0},
    {0,21,0,0,0,13,0,0,0,13,0,0,0,21,0},
    {31,0,0,12,0,0,0,31,0,0,0,12,0,0,31},
  };
  //right direction and down direction constants
  public static final int RIGHT = 1;
  public static final int DOWN = 2;

  //constructor, sets contents from parameter
  public GameWord(String contents) {
    this.contents = contents;
  }
  //returns contents in .toString()
  @Override
  public String toString() {
    return contents;
  }
  //stores strings of each letter score, so i can run .contains() on the letters for point values
  private static final String onep = "EAIONRTLSU";
  private static final String twop = "DG";
  private static final String threep = "BCMP";
  private static final String fourp = "FHVWY";
  private static final String fivep = "K";
  private static final String eightp = "JX";

  public String reverse() {
    //output string
    String ostr = "";
    //loops through the contents, adds each char in front so it goes end to front
    for (String s : contents.split("")) {
      ostr = s + ostr;
    }
    return ostr;
  }

  public boolean anagram(String other) {
    //checks to make sure they are the same length, so cde cant be an anagram of abcdef
    if (other.length() != contents.length()) {
      return false;
    }
    //checks every letter and confirms that contents has that letter in it
    for (char c : other.toCharArray()) {
      if (!contents.contains(""+c)) {
        //if it's not in contents, breaks the loop and returns false
        return false;
      }
    }
    //if it passed, return true
    return true;
  }

  //easy to call method for permutations
  public ArrayList<String> permutations() {
    //final arraylist of permutations from the fancy recursive function below
    ArrayList<String> ana = perms("", contents);

    return ana;
  }
  private ArrayList<String> perms(String cur, String left) {
    //makes an arraylist with the length needed for each permutation
    ArrayList<String> ana = new ArrayList<String>(cur.length()*left.length());
    //if it's the last letter, just return the only possible string
    if (left.length() ==1) {
      ana.add(cur + left);
    } else { //otherwise recurse with each possible next letter
      for (int i=0; i<left.length(); i++) {
        //adds the outcomes from the next letters, removes that letter from the leftovers
        ana.addAll(perms(cur+left.charAt(i), left.substring(0, i) + left.substring(i+1)));
      }
    }
    return ana;
  }

  public int pointValue(int x, int y, int dir) {
    //stores the points on the board (for multipliers)
    int []points = new int[contents.length()];
    //word multiplier storage for later
    int mult = 1;

    //loops through the length of the word
    for (int i=0; i<contents.length(); i++) {
      //adds the points the word touches to the points array
      points[i] = gameBoard[(dir == GameWord.RIGHT)? x+i : x][(dir == GameWord.DOWN)? y+i : y];
      //multiplies the word multiplier by the current point's word multiplier (divided by 10 because it's the tens column for word multiplier)
      mult *= (points[i] > 0)? points[i]/10: 1;
    }

    //point count variable
    int count = 0;
    //loops through the word length
    for (int j=0; j<contents.length(); j++) {
      //let is the current letter (couldnt just loop through string because i use points[j] below)
      String let = contents.split("")[j];
      //stores the letter value
      int add = 0;
      //if statement hell, checks which set of points contains the current letter
      if (onep.contains(let)){add+=1;} 
      else if (twop.contains(let)){add+=2;} 
      else if (threep.contains(let)){add+=3;}
      else if (fourp.contains(let)){add+=4;}
      else if (fivep.contains(let)){add+=5;}
      else if (eightp.contains(let)){add+=8;}
      else {add+=10;}
      //multiplies added value by letter multiplier at the current point (point mod 10 to just get ones digits)
      add *= (points[j] > 0)? points[j] % 10: 1;
      //adds the value to total count now
      count += add;
    }
    //once it's fully counted the score is multiplied by the word multiplier
    count *= mult;

    return count;
  }
}
