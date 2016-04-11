public class Driver{
    public static void main(String[]args){
	Card c1 = new Card(0,1);
	System.out.println(c1.getSuit());
	System.out.println(c1.getNumber());
	try{
	    Card c2 = new Card(5,0);
	}catch(IllegalArgumentException e){
	    System.out.println("Excpetion 1 works");
	}
	try{
	    Card c2 = new Card(1,20);
	}catch(IllegalArgumentException e){
	    System.out.println("Excpetion 2 works");
	}
	System.out.println(c1);
	Card c3 = new Card('Q','S');
	System.out.println(c3);
    }
}