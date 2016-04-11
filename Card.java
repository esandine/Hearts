import java.util.*;
public class Card{
    private char[] suits = {'C','D','S','H'};
    private char[] numbers = {'2','3','4','5','6','7','8','9','0','J','Q','K','A'};
    //For conversion
    private int suit;
    //0=club, 1=diamond, 2=spade, 3=heart
    private int number;
    //0=least valued card, 12=ace
    private int value;
    //How many points the card is worth
    //Mutators
    private void setSuit(int s){
	if(s>3||s<0){
	    throw new IllegalArgumentException();
	}
	suit=s;
    }
    private void setNumber(int n){
	if(n<0||n>12){
	    throw new IllegalArgumentException();
	}
	number = n;
    }
    private void setValue(){
	if(getSuit()==3){
	    value=1;
	}else if((getSuit()==2)&&(getNumber()==10)){
	    value=13;
	}else{
	    value = 0;
	}
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
    //Convertors
    private char suitToChar(int n){
	return suits[n];
    }
    private char numberToChar(int n){
	return numbers[n];
    }
    private int find(char[] chars,char c){
	for(int i = 0;i<chars.length;i++){
	    if(chars[i]==c){
		return i;
	    }
	}
	throw new IllegalArgumentException();
    }
    private int charToSuit(char c){
	return find(suits,c);
    }
    private int charToNumber(char c){
	return find(numbers,c);
    }
    //Constructors
    public Card(int n, int s){
	try{
	    setSuit(s);
	}catch(IllegalArgumentException e){
	    System.out.println("Suit: "+s);
	}
	try{
	    setNumber(n);
	}catch(IllegalArgumentException e){
	    System.out.println("Number: "+n);
	}
	setValue();
    }
    public Card(char n, char s){
	setNumber(charToNumber(n));
	setSuit(charToSuit(s));
	setValue();
    }   
    //toStrings
    public String toString(){
	return ""+numberToChar(getNumber())+suitToChar(getSuit());
    }
    public String toStringDebug(){
	String retStr=toString();
	retStr+=": ";
	retStr+=getValue();
	return retStr;
    }
}