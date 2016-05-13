import java.util.*;
public class Round{
    //Instance Variables
    private ArrayList<Player> players;
    private boolean heartsBroken;
    private int[] pointsTaken;
    //Constructors
    public Round(ArrayList<Player> p){
	players = p;
	pointsTaken = new int[p.size()];
	heartsBroken = false;
    }
    //Mutators
    public void breakHearts(){
	heartsBroken=true;
    }
    public void addPoints(Player p, int points){
	pointsTaken[players.indexOf(p)]+=points;
    }
    //Accessors
    public ArrayList<Player> getPlayers(){
	return players;
    }
}