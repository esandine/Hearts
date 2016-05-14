import java.util.*;
public class Player{
    //Instance Variables
    private ArrayList<Card> hand;
    //hand is the Cards in the players hand
    private int points;
    //points is the number of points a player has
    private String name;
    //Name of the player
    private String strategy;
    //Constructors
    public Player(String n, String s){
	setName(n);
	setStrategy(s);
	hand = new ArrayList<Card>();
    }
    public Player(){
	this("Player","random");
    }
    //Accessors
    public int getPoints(){
	return points;
    }
    public ArrayList<Card> getHand(){
	return hand;
    }
    public String getName(){
	return name;
    }
    public String getStrategy(){
	return strategy;
    }
    //Mutators
    public void setPoints(int n){
	points=n;
    }
    public void addPoints(int n){
	setPoints(getPoints()+n);
    }
    public void setName(String s){
	name = s;
    }
    public void setStrategy(String s){
	strategy=s;
    }
    //Hand functions
    public boolean addCard(Card c){
	c.setOwner(this);
	hand.add(c);
	return true;
    }
    public Card selectCard(Round r){
	Trick t = r.getCurrentTrick();
	if(getStrategy().equals("random")){
	    return randomSelect(r);
	}
	else if(getStrategy().equals("low")){
	    return lowSelect(r);
	}
	else if(getStrategy().equals("high")){
	    return highSelect(r);
	}
	else if(getStrategy().equals("greedy")){
	    return greedySelect(r);
	}
	throw new IllegalArgumentException("Not valid Strategy"+getStrategy()+"hello");
    }
    public Card playCard(Round r){
	Card c = selectCard(r);
	if((!r.getHeartsBroken())&&(c.getSuit()==3)){
	    r.breakHearts();
	}
	hand.remove(c);
	r.addCardsPlayed(c);
	return c;
    }
    public int cardsInHand(){
	return hand.size();
    }
    public String card(int i){
	return hand.get(i).toStringDebug();
    }
    private ArrayList<Card> playableCards(Round r){
	Trick t = r.getCurrentTrick();
	ArrayList<Card> retArray = new ArrayList<Card>();
	//First trick
	if((r.getNumberTrick()==0)&&(this.equals(r.getLead()))){
	    for(Card c : hand){
		if((c.getSuit()==0)&&(c.getNumber()==0)){
		    retArray.add(c);
		}
	    }
	    return retArray;
	}
	for(Card c : getHand()){
	    //You are the lead or match the lead
	    if((t.cardsPlayed()==0)||(c.getSuit()==t.getTrump().getSuit())){
		if(r.getHeartsBroken()||c.getSuit()<3){
		    retArray.add(c);
		}
	    }
	}
	if(retArray.size()==0){
	    for(Card c : getHand()){
		retArray.add(c);
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

    //ToString
    public String toString(){
	return name;
    }
    
    //Strategies
    private Card randomSelect(Round r){
	ArrayList<Card> l = playableCards(r);
	return l.get((int)(Math.random()*l.size()));
    }
    private Card lowSelect(Round r){
	ArrayList<Card> l = playableCards(r);
	Card retCard = l.get(0);
	for(int i = 0;i<l.size();i++){
	    if((l.get(i).getNumber()<retCard.getNumber()||
		((l.get(i).getNumber()==l.get(i).getNumber())&&
		 (l.get(i).getSuit()<retCard.getSuit())))){
		retCard = l.get(i);
	    }
	}
	return retCard;
    }

    private Card highSelect(Round r){
	ArrayList<Card> l = playableCards(r);
	Card retCard = l.get(0);
	for(int i = 0;i<l.size();i++){
	    if((l.get(i).getNumber()>retCard.getNumber()||
		((l.get(i).getNumber()==l.get(i).getNumber())&&
		 (l.get(i).getSuit()>retCard.getSuit())))){
		retCard = l.get(i);
	    }
	}
	return retCard;
    }

    private Card greedySelect(Round r){
	ArrayList<Card> l = playableCards(r);
	if((r.getCurrentTrick().cardsPlayed()==0)||l.get(0).getSuit()==r.getCurrentTrick().getTrump().getSuit()){
	    return lowSelect(r);
	}else{
	    return highSelect(r);
	}
    }
}