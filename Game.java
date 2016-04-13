import java.util.*;
public class Game{
    public static void main(String[] args){
	ArrayList<Player> Players= new ArrayList<Player>();
	Players.add(new Player());	
	Players.add(new Player());	
	Players.add(new Player());	
	Players.add(new Player());	
	deal(Players);
	//System.out.println(printHands(Players));
	playTrick(Players,0);
    }
    
    //Deal function deals the cards randomly to all the players
    public static void deal(ArrayList<Player> players){
	Player dealt;
	int randIndex;
	ArrayList<Card> deck = new ArrayList<Card>();
	for(int suit = 0;suit<4;suit++){
	    for(int number = 0;number<13;number++){
		deck.add(new Card(number,suit));
	    }
	}
	int i = 0;
	while(deck.size()>0){
	    dealt=players.get(i%players.size());
	    randIndex=(int)(Math.random()*deck.size());
	    dealt.addCard(deck.get(randIndex));
	    deck.remove(randIndex);
	    i++;
	}
    }

    //printHands prints the hands of all the players
    public static String printHands(ArrayList<Player> players){
	String retStr = "Hands: ";
	Player currentPlayer;
	for(int i = 0;i<players.size();i++){
	    retStr+="\nPlayer "+i+":";
	    currentPlayer=players.get(i);
	    for(int ii = 0;ii<currentPlayer.cardsInHand();ii++){
		retStr+="\n";
		retStr+=currentPlayer.card(ii);
	    }	    
	}
	return retStr;
    }
    
    //Plays a trick
    public static int playTrick(ArrayList<Player> players, int start){
	Trick currentTrick = new Trick();
	Player p;
	int index = start;
	while(currentTrick.cardsPlayed()<players.size()){
	    p=players.get(index);
	    currentTrick.addCard(p.playCard(currentTrick));
	    index++;
	    if(index==players.size()){
		index = 0;
	    }
	}
	for(Card c : currentTrick.getCardsPlayed()){
	    System.out.println(c.toStringDebug());
	}
	System.out.println(currentTrick.getTrump().toStringDebug());
	return 0;
    }
}