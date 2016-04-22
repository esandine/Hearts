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
    private String name;
    //Name of the player
    private ArrayList<Card> cardsPlayed;
    private String strategy;

    //Constructors
    public Player(String n, String s){
	setName(n);
	setStrategy(s);
	hand = new ArrayList<Card>();
	heartsBroken=false;
	cardsPlayed=new ArrayList<Card>();
    }
    public Player(){
	this("Player","random");
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
    public String getName(){
	return name;
    }
    public ArrayList<Card> getCardsPlayed(){
	return cardsPlayed;
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
    public void setName(String s){
	name = s;
    }
    public void setStrategy(String s){
	strategy=s;
    }
    public void addCardsPlayed(Card c){
	cardsPlayed.add(c);
    }
    public void clearCardsPlayed(){
	cardsPlayed.clear();
    }
    //Hand functions
    public boolean addCard(Card c){
	c.setOwner(this);
	hand.add(c);
	return true;
    }
    public Card selectCard(Trick t){
	if(isLead(t)){
	    return findLead(t);
	}
	if(getStrategy().equals("random")){
	    return randomSelect(t);
	}
	else if(getStrategy().equals("low")){
	    return lowSelect(t);
	}
	else if(getStrategy().equals("high")){
	    return highSelect(t);
	}
	else if(getStrategy().equals("greedy")){
	    return greedySelect(t);
	}
	throw new IllegalArgumentException("Not valid Strategy"+getStrategy()+"hello");
    }
    public Card playCard(Trick t){
	Card c = selectCard(t);
	hand.remove(c);
	addCardsPlayed(c);
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
		if(getHeartsBroken()||c.getSuit()<3){
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
    private boolean isLead(Trick t){
	ArrayList<Card> l = playableCards(t);
	for(Card c : l){
	    if((c.getNumber()==0)&&(c.getSuit()==0)){
		return true;
	    }
	}
	return false;
    }

    private Card findLead(Trick t){
	ArrayList<Card> l = playableCards(t);
	for(Card c : l){
	    if((c.getNumber()==0)&&(c.getSuit()==0)){
		return c;
	    }
	}
	throw new IllegalStateException();
    }
    private Card randomSelect(Trick t){
	ArrayList<Card> l = playableCards(t);
	return l.get((int)(Math.random()*l.size()));
    }

    private Card lowSelect(Trick t){
	ArrayList<Card> l = playableCards(t);
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

    private Card highSelect(Trick t){
	ArrayList<Card> l = playableCards(t);
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

    private Card greedySelect(Trick t){
	ArrayList<Card> l = playableCards(t);
	if((t.cardsPlayed()==0)||l.get(0).getSuit()==t.getTrump().getSuit()){
	    return lowSelect(t);
	}else{
	    return highSelect(t);
	}
    }
}