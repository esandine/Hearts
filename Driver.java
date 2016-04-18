import java.util.*;
public class Driver{
    public static void main(String[]args){
	int total = 100;
	if(args.length>0){
	    total=Integer.parseInt(args[0]);
	}
	Game.completeGame(total,true);
    }
}