import java.io.PrintStream;
import java.util.ArrayList;

public class freePark extends monopoly {

	public freePark(String Name, int Id, int Cost) {
		super(Name, Id, Cost);
		
	}
	//wait 1 time

	@Override
	public void game(ArrayList<player> players, int dice, banker bank, ArrayList<monopoly> mnp, String plyName) {
		
		System.out.println(monopoly.printString(players, bank, dice, plyName)+
				players.get(player.findPlayer(players, plyName)).name + " is in Free Parking");
	}

	@Override
	public void pathWrite(PrintStream print) {
		
		super.pathWrite(print);
	}
}
