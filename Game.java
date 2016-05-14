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
    public static Player playTrick(Round currentRound, boolean debug){
	ArrayList<Player> players = currentRound.getPlayers();
	Trick currentTrick = currentRound.getCurrentTrick();
	Player toPlay;
	int index = players.indexOf(currentRound.getLead());
	while(currentTrick.cardsPlayed()<players.size()){
	    toPlay=players.get(index);
	    currentTrick.addCard(toPlay.playCard(currentRound));
	    index++;
	    if(index==players.size()){
		index = 0;
	    }
	}
	for(Card c : currentTrick.getCardsPlayed()){
	    debug(c.toStringDebug(),debug);
	}
	debug("Winner of trick: "+currentTrick.getTrump().getOwner()+"\n",debug);
	currentRound.addPointsTrick(currentTrick);
	return currentTrick.getTrump().getOwner();
    }

    //Plays a single round of 13 tricks
    public static void playRound(ArrayList<Player> Players,boolean debug){
	deal(Players);
	Round current = new Round(Players);
	while(current.getLead().cardsInHand()>0){
	    debug("Trick "+current.getNumberTrick()+", Starting Player: "+current.getLead(),debug);
	    current.resetCurrentTrick();
	    current.setLead(playTrick(current,debug));
	    current.incrementNumberTrick();
	}
	current.addPointsRound();
	for(Player p : Players){
	    debug(p,debug);
	    for(Card c : p.getCardsPlayed()){
		debug(c,debug);
	    }
	    p.clearCardsPlayed();
	}
    }
    //Shows players earning points
    public static void printPoints(ArrayList<Player> Players, boolean debug){
	for(Player p : Players){
	    debug(p.getPoints()+": "+p.getName(),true);
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
    public static void playGame(ArrayList<Player> players, int total,boolean debug){
	while(notOver(players,total)){
	    debug("New Round",debug);
	    playRound(players,debug);
	}
    }

    public static void completeGame(int total, String s1, String s2, String s3, String s4, boolean debug){
	ArrayList<Player> Players= new ArrayList<Player>();
	Players.add(new Player("Player 1",s1));	
	Players.add(new Player("Player 2",s2));	
	Players.add(new Player("Player 3",s3));	
	Players.add(new Player("Player 4",s4));	
	debug("Game Started",debug);
	playGame(Players,total,debug);
	printPoints(Players,debug);
	//System.out.println(printHands(Players));
    }
    public static void debug(Object o, boolean debug){
	if(debug){
	    System.out.println(o);
	}
    }
}