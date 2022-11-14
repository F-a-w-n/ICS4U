//basic test i made for the scrabble

public class ScrabbleTest {
  public static void main(String []args) {
    GameWord game = new GameWord("ABCDEFG");
    System.out.println(game.anagram("GEFDBCA"));
    System.out.println(game.reverse());
    System.out.println(game.permutations());
    System.out.println(game.pointValue(8, 8, GameWord.DOWN));
    System.out.println(game.toString());
  }
}
