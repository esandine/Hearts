import java.util.*;
public class Player{
    //Instance Variables
    private ArrayList<Card> hand;
    //hand is the Cards in the players hand
    private int points;
    //points is the number of points a player has
    private int pointsRound;
    //points in a single round
    private boolean heartsBroken;
    //WHether or not hearts have been broken

    //Constructors
    public Player(){
	hand = new ArrayList<Card>();
	heartsBroken=false;
    }
    //Accessors
    public int getPoints(){
	return points;
    }
    public int getPointsRound(){
	return pointsRound;
    }
    public ArrayList<Card> getHand(){
	return hand;
    }
    public boolean getHeartsBroken(){
	return heartsBroken;
    }
    //Mutators
    public void setPoints(int n){
	points=n;
    }
    public void addPoints(int n){
	setPoints(getPoints()+n);
    }
    public void setPointsRound(int n){
	pointsRound=n;
    }
    public void addPointsRound(int n){
	setPointsRound(getPointsRound()+n);
    }
    public void setHeartsBroken(boolean b){
	heartsBroken=b;
    }
    public void breakHearts(){
	setHeartsBroken(true);
    }

    //Hand functions
    public boolean addCard(Card c){
	c.setOwner(this);
	hand.add(c);
	return true;
    }
    public Card selectCard(Trick t){
	ArrayList<Card> l = playableCards(t);
	for(Card c : l){
	    if((c.getNumber()==0)&&(c.getSuit()==0)){
		return c;
	    }
	}
	return l.get((int)(Math.random()*l.size()));
    }
    public Card playCard(Trick t){
	Card c = selectCard(t);
	hand.remove(c);
	if((c.getSuit()==3)&&(!getHeartsBroken())){
	    breakHearts();
	}
	return c;
    }
    public int cardsInHand(){
	return hand.size();
    }
    public String card(int i){
	return hand.get(i).toStringDebug();
    }
    private ArrayList<Card> playableCards(Trick t){
	ArrayList<Card> retArray = new ArrayList<Card>();
	for(Card c : getHand()){
	    if((t.cardsPlayed()==0)||(c.getSuit()==t.getTrump().getSuit())){
		retArray.add(c);
	    }
	}
	if(retArray.size()==0){
	    for(Card c : getHand()){
		retArray.add(c);
	    }
	}
	if(getHeartsBroken()){
	    for(Card c : retArray){
		if(c.getSuit==3){
		    retArray.remove(c);
		}
	    }
	}
	if((t.getTrump().getSuit()==0)&&(t.getTrump().getNumber()==0)){
	    for(Card c : retArray){
		if((c.getSuit()==2)&&(c.getSuit()==10)){
		    retArray.remove(c);
		}
	    }
	}
	return retArray;
    }
}