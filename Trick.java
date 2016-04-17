import java.util.*;
public class Trick{
    //Instance Variables
    private ArrayList<Card> cardsPlayed;
    private Card trump;
    private boolean heartsBroken;
    //Constructors
    public Trick(ArrayList<Card> cardsPlayed,Card trump,boolean b){
        this.cardsPlayed=cardsPlayed;
	this.trump=trump;
	this.heartsBroken=b;
    }
    public Trick(boolean b){
	cardsPlayed=new ArrayList<Card>();
	trump=new Card();
	heartsBroken=b;
    }
    public Trick(){
	cardsPlayed=new ArrayList<Card>();
	trump=new Card();

    }

    //Accessors
    public ArrayList<Card> getCardsPlayed(){
	return cardsPlayed;
    }
    public Card getTrump(){
	return trump;
    }
    public boolean getHeartsBroken(){
	return heartsBroken;
    }
    //Returns number of cardsPlayed in the Trick so far
    public int cardsPlayed(){
	return cardsPlayed.size();
    }

   //Mutators
    public void addCard(Card c){
	if(cardsPlayed.size()==0){
	    trump=c;
	}
	cardsPlayed.add(c);
	if((c.getSuit()==trump.getSuit())&&(c.getNumber()>trump.getNumber())){
	    trump = c;
	}
    }
    public void addPoints(){
	for(Card c : getCardsPlayed()){
	    getTrump().getOwner().addPointsRound(c.getValue());
	    //System.out.println("This just happened");
	}
    }
    public void setHeartsBroken(boolean b){
	heartsBroken=b;
    }
    public void breakHearts(){
	setHeartsBroken(true);
    }
}


