import java.util.*;
public class Player{
    //Instance Variables
    private ArrayList<Card> hand;
    //hand is the Cards in the players hand

    //Constructors
    public Player(){
	hand = new ArrayList<Card>();
    }

    //Hand functions
    public boolean addCard(Card c){
	hand.add(c);
	return true;
    }
    public int selectCard(){
	return (int)(Math.random()*hand.size());
    }
    public Card playCard(){
	return hand.remove(selectCard());
    }
}