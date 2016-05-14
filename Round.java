import java.util.*;
public class Round{
    //Instance Variables
    private ArrayList<Player> players;
    private boolean heartsBroken;
    private int[] pointsTaken;
    private Trick currentTrick;
    //Constructors
    public Round(ArrayList<Player> p){
	players = p;
	pointsTaken = new int[p.size()];
	heartsBroken = false;
	currentTrick=new Trick();
    }
    //Mutators
    public void breakHearts(){
	heartsBroken=true;
    }
    public void addPoints(Player p, int points){
	pointsTaken[players.indexOf(p)]+=points;
    }
    public void resetCurrentTrick(){
	currentTrick=new Trick();
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
}