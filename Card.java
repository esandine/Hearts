import java.util.*;
public class Card{
    private char[] suits = {'c','d','s','h'};
    private char[] numbers = {'2','3','4','5','6','7','8','9','0','J','Q','K','A'};
    //For conversion
    private int suit;
    //0=club, 1=diamond, 2=spade, 3=heart
    private int number;
    //0=least valued card, 12=ace
    private int value;
    //How many points the card is worth
    //Mutators
    public void setSuit(int s){
	if(s>3||s<0){
	    throw new IllegalArgumentException();
	}
	suit=s;
    }
    public void setNumber(int n){
	if(n<0||n>12){
	    throw new IllegalArgumentException();
	}
	number = n;
    }
    public void setValue(int v){
	value = v;
    }
    //Accessors
    public int getSuit(){
	return suit;
    }
    public int getNumber(){
	return number;
    }
    public int getValue(){
	return value;
    }
    //Constructors
    public Card(int s, int n){
	setSuit(s);
	setNumber(n);
    }    
    //    private char suitToChar(int n){
    //	return 
}