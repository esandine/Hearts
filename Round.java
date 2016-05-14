import java.util.*;
public class Round{
    //Instance Variables
    private ArrayList<Player> players;
    private boolean heartsBroken;
    private int[] pointsTaken;
    private Trick currentTrick;
    private Player lead;
    private int numberTrick;
    private ArrayList<ArrayList<Card>> cardsPlayed;
    //Constructors
    public Round(ArrayList<Player> p){
	players = p;
	pointsTaken = new int[p.size()];
	heartsBroken = false;
	currentTrick=new Trick();
	startLead();
	initializeCardsPlayed();
    }
    //Mutators
    public void breakHearts(){
	heartsBroken=true;
    }
    public void addPoints(Player p, int points){
	pointsTaken[players.indexOf(p)]+=points;
    }
    public void addPointsTrick(Trick t){
	for(Card c : t.getCardsPlayed()){
	    addPoints(t.getTrump().getOwner(),c.getValue());
	}
    }
    public void addPointsRound(){
	for(int i = 0;i<pointsTaken.length;i++){
	    if(pointsTaken[i]==26){
		for(Player p : players){
		    if(players.indexOf(p)!=i){
			p.addPoints(26);
		    }
		}
	    }else{
		players.get(i).addPoints(pointsTaken[i]);
	    }
	}
    }
    public void resetCurrentTrick(){
	currentTrick=new Trick();
    }
    public void setLead(Player p){
	lead = p;
    }
    public void incrementNumberTrick(){
	numberTrick++;
    }
    public void addCardsPlayed(Card c){
	cardsPlayed.get(players.indexOf(c.getOwner())).add(c);
    }
    private void initializeCardsPlayed(){
	cardsPlayed=new ArrayList<ArrayList<Card>>();
	for(int i = 0;i<players.size();i++){
	    cardsPlayed.add(new ArrayList<Card>());
	}
    }
    //Accessors
    public ArrayList<Player> getPlayers(){
	return players;
    }
    public Trick getCurrentTrick(){
	return currentTrick;
    }
    public boolean getHeartsBroken(){
	return heartsBroken;
    }
    public Player getLead(){
	return lead;
    }
    public int getNumberTrick(){
	return numberTrick;
    }
    //Beginning of round functions:
    private void startLead(){
        for(Player p : players){
            for(Card c : p.getHand()){
                if((c.getNumber()==0)&&(c.getSuit()==0)){
		    setLead(p);
                }
            }
        }
    }
}