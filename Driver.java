import java.util.*;
public class Driver{
    public static void main(String[]args){
	ArrayList<Card> deck=new ArrayList<Card>();
	for(int i = 0;i<4;i++){
	    for(int ii = 0;ii<13;ii++){
		deck.add(new Card(ii,i));
	    }
	}
	for(Card c : deck){
	    System.out.println(c.toStringDebug());
	}
    }
}