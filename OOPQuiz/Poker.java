import java.util.*;

public class Poker{

    public static void main(String[] args) {
		Deck deck = new Deck();
		for(int i=1; i<=10; i++){					
			ArrayList<Card>player = deck.deal(3);
			ArrayList<Card>computer = deck.deal(3);
			
			String pOutput = "";
			String cOutput = "";
			for (Card c : player) {
				pOutput+=c.toString();
			}
			pOutput=pOutput.substring(0, pOutput.length()-2);
			for (Card c : computer) {
				cOutput+=c.toString();
			}
			cOutput=cOutput.substring(0, cOutput.length()-2);

			System.out.println("\nGame "+i+":");
			System.out.println("------------");
			System.out.println("Player has: " + pOutput);
			showHand(player);
			System.out.println("Computer has: " + cOutput);
			showHand(computer);
			
			if(evaluateHand(computer) > evaluateHand(player)){
				System.out.println("Computer Wins!");
			}
			else if(evaluateHand(player) > evaluateHand(computer)){
				System.out.println("Player Wins!");
			}
			else{
				System.out.println("Its a tie.");
			}
		}
		
    }

	public static boolean threeKind(ArrayList<Card>hand){
		return hand.get(0).getVal() == hand.get(1).getVal()  && hand.get(1).getVal() == hand.get(2).getVal();	
	}

	public static void showHand(ArrayList<Card>hand){
		String []hands={"High Card","Pair","Flush","Straight","Three of a kind","Straight Flush"};
		System.out.println(hands[evaluateHand(hand)]);
	}

	public static boolean flush(ArrayList<Card>hand) {
		if (hand.get(0).getSuit() == hand.get(1).getSuit() && hand.get(1).getSuit() == hand.get(2).getSuit()) {
			return true;
		} else {
			return false;
		}
	}
	public static boolean consec(ArrayList<Card>hand) {
		ArrayList<Integer> sorted = new ArrayList<Integer>();
		sorted.add(hand.get(0).getVal());
		sorted.add(hand.get(1).getVal());
		sorted.add(hand.get(2).getVal());
		sorted.sort(null);
		if (sorted.get(0) == sorted.get(1)-1 && sorted.get(1) == sorted.get(2)-1) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean twoKind(ArrayList<Card>hand) {
		for (int i=0; i<3; i++) {
			for (int j=i+1; j<3; j++) {
				if (hand.get(i).getVal() == hand.get(j).getVal()){
					return true;
				}

			}
		}
		return false;
	}

	public static int evaluateHand(ArrayList<Card>hand){
		if (consec(hand) && flush(hand)) {
			return 5;
		} else if(threeKind(hand)){
			return 4;
		} else if (consec(hand)) {
			return 3;
		} else if (flush(hand)) {
			return 2;
		} else if (twoKind(hand)){
			return 1;
		} else {
			return 0;
		}		
	}
}

class Deck{
	private ArrayList<Card>cards;
	
	public Deck(){
		init();
	}

	public void init(){
		cards = new ArrayList<Card>();
		for(int i = 0; i<52; i++){
			cards.add(new Card(i));
		}
		Collections.shuffle(cards);	
	}

	public ArrayList<Card>deal(int n){
		if(n>cards.size()){
			init();
		}
		ArrayList<Card>ans = new ArrayList<Card>(cards.subList(0,n));
		cards.removeAll(ans);
		return ans;
	}
	
}

class Card{
	private int val, suit;
	private String []suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
	private String []cards = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
	
	public Card(int n){
		val = n%13+1;
		suit = n/13;
	}
	
	public int getVal(){
		return val;
	}
	public int getSuit(){
		return suit;
	}
	@Override
	public String toString() {
		return String.format("%s of %s, ", cards[val-1], suits[suit]);
	
	}	
}