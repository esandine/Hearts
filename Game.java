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
    public static Player playTrick(ArrayList<Player> players, Player start, boolean debug){
	Trick currentTrick = new Trick();
	Player toPlay;
	int index = players.indexOf(start);
	while(currentTrick.cardsPlayed()<players.size()){
	    toPlay=players.get(index);
	    currentTrick.addCard(toPlay.playCard(currentTrick));
	    index++;
	    if(index==players.size()){
		index = 0;
	    }
	}
	for(Card c : currentTrick.getCardsPlayed()){
	    debug(c.toStringDebug(),debug);
	}
	debug("Winner of trick: "+currentTrick.getTrump().getOwner()+"\n",debug);
	breakHearts(players);
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
    public static void playRound(ArrayList<Player> Players,boolean debug){
	deal(Players);
	Player lead = lead(Players);
	int i = 1;
	while(lead.cardsInHand()>0){
	    debug("Trick "+i+", Starting Player: "+lead,debug);
	    lead=playTrick(Players,lead,debug);
	    i++;
	}
	updatePoints(Players,debug);
    }
    public static void printPoints(ArrayList<Player> Players, boolean debug){
	for(Player p : Players){
	    debug(p.getPoints()+": "+p.getName(),debug);
	}
    }

    //updates the points after a round of 13 tricks
    public static void updatePoints(ArrayList<Player> Players, boolean debug){
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
	}
	debug("Points:",debug);
	for(Player P : Players){
	    debug(""+P+": "+(P.getPoints()-P.getPointsRound())+" -> "+P.getPoints(),debug);
	    P.setPointsRound(0);

	}

    }

    public static void breakHearts(ArrayList<Player> players){
	for(Player p : players){
	    if(p.getHeartsBroken()){
		for(Player h : players){
		    if(!h.getHeartsBroken()){
			h.breakHearts();
		    }
		}
	    }
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

    public static void completeGame(int total, boolean debug){
	ArrayList<Player> Players= new ArrayList<Player>();
	Players.add(new Player("Player 1"));	
	Players.add(new Player("Player 2"));	
	Players.add(new Player("Player 3"));	
	Players.add(new Player("Player 4"));	
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