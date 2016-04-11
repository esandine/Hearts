import java.util.*;
public class Card{
    private int suit;
    //0=club, 1=diamond, 2=spade, 3=heart
    private int number;
    //0=least valued card, 12=ace
    private int value;
    //How many points the card is worth
    public Card(int s, int n){
	setSuit(s);
	setNumber(n);
    }
    public void setNumber(int n){
	if(n<0||n>12){
	    throw new IllegalArgumentException();
	}
	number = n;
    }
    public void setSuit(int s){
	if(s>3||s<0){
	    throw new IllegalArgumentException();
	}
	suit=s;
    }
    
}