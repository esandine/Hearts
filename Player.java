import java.util.*;
public class Player{
    //Instance Variables
    private ArrayList<Card> hand;
    //hand is the Cards in the players hand
    private int points;
    //points is the number of points a player has

    //Constructors
    public Player(){
	hand = new ArrayList<Card>();
	points = 0;
    }
    //Accessors
    public int getPoints(){
	return points;
    }
    
    //Mutators
    public void setPoints(int n){
	points=n;
    }
    public void addPoints(int n){
	setPoints(getPoints()+n);
    }

    //Hand functions
    public boolean addCard(Card c){
	c.setOwner(this);
	hand.add(c);
	return true;
    }
    public int selectCard(){
	return (int)(Math.random()*hand.size());
    }
    public Card playCard(){
	return hand.remove(selectCard());
    }
    public int cardsInHand(){
	return hand.size();
    }
    public String card(int i){
	return hand.get(i).toStringDebug();
    }
}