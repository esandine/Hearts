import java.util.*;
public class Driver{
    public static void main(String[]args){
	int total;
	if(args.length!=6){
	    throw new IllegalArgumentException();
	}else{
	    total=Integer.parseInt(args[0]);
	    if(args[5].equals("debug")){
		Game.completeGame(total,args[1],args[2],args[3],args[4],true);
	    }else{
		Game.completeGame(total,args[1],args[2],args[3],args[4],false);
	    }
	}
    }
}