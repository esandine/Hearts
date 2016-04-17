import java.util.*;
public class Game{
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
    
   //Plays a single trick
    public static Player playTrick(ArrayList<Player> players, Player start){
	Trick currentTrick = new Trick();
	Player p;
	int index = players.indexOf(start);
	while(currentTrick.cardsPlayed()<players.size()){
	    p=players.get(index);
	    currentTrick.addCard(p.playCard(currentTrick));
	    index++;
	    if(index==players.size()){
		index = 0;
	    }
	}/*
	for(Card c : currentTrick.getCardsPlayed()){
	    System.out.println(c.toStringDebug());
	}
	System.out.println(currentTrick.getTrump().toStringDebug());*/
	currentTrick.addPoints();
	return currentTrick.getTrump().getOwner();
    }

    //Determines the lead player at the beginning of the game
    public static Player lead(ArrayList<Player> players){
	for(Player p : players){
	    for(Card c : p.getHand()){
		if((c.getNumber()==0)&&(c.getSuit()==0)){
		    return p;
		}
	    }
	}
	throw new IndexOutOfBoundsException();
    }

    //Plays a single round of 13 tricks
    public static void playRound(ArrayList<Player> Players){
	deal(Players);	
	Player lead = lead(Players);
	while(lead.cardsInHand()>0){
	    lead=playTrick(Players,lead);
	}
	updatePoints(Players);
    }
    public static void printPoints(ArrayList<Player> Players){
	for(Player p : Players){
	    System.out.println(p.getPoints());
	}
    }

    //updates the points after a round of 13 tricks
    public static void updatePoints(ArrayList<Player> Players){
	Player p;
	for(int i = 0;i<Players.size();i++){
	    p=Players.get(i);
	    if(p.getPointsRound()==26){
		for(int ii = 0;ii<Players.size();ii++){
		    if(i!=ii){
			Players.get(ii).addPoints(26);
			//System.out.println("Shot the moon");
		    }
		}
	    }else{
		p.addPoints(p.getPointsRound());
	    }
	    p.setPointsRound(0);
	}
    }

    //Checks if anyone has won the game
    public static boolean notOver(ArrayList<Player> players, int total){
	for(Player p : players){
	    if(p.getPoints()>total){
		return false;
	    }
	}
	return true;
    }

    //Plays a complete game
    public static void playGame(ArrayList<Player> players, int total){
	while(notOver(players,total)){
	    playRound(players);
	}
    }

    public static void completeGame(int total){
	ArrayList<Player> Players= new ArrayList<Player>();
	Players.add(new Player());	
	Players.add(new Player());	
	Players.add(new Player());	
	Players.add(new Player());	
	playGame(Players,total);
	printPoints(Players);
	//System.out.println(printHands(Players));
    }
}