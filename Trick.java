import java.util.*;
public class Trick{
    //Instance Variables
    private ArrayList<Card> cardsPlayed;
    private Player lead;
    private Card trump;

    //Constructors
    public Trick(ArrayList<Card> cardsPlayed,Player lead,Card trump){
        this.cardsPlayed=cardsPlayed;
        this.lead=lead;
	this.trump=trump;
    }
    public Trick(Player p){
	cardsPlayed=new ArrayList<Card>();
	lead=p;
	trump=new Card();
    }

    //Accessors
    public ArrayList<Card> getCardsPlayer(){
	return cardsPlayed;
    }
    public Player getLead(){
	return lead;
    }
    public Card getTrump(){
	return trump;
    }
    public int getCardsPlayed(){
	return cardsPlayed.size();
    }

   //Mutators
    public void addCard(Card c){
	if(cardsPlayed.size()==0){
	    trump=c;
	}
	cardsPlayed.add(c);
	if((c.getSuit()==trump.getSuit())&&(c.getValue()>trump.getValue())){
	    trump = c;
	}
    }
}


