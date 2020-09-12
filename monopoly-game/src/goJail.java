import java.io.PrintStream;
import java.util.ArrayList;

public class goJail extends monopoly {

	public goJail(String Name, int Id, int Cost) {
		super(Name, Id, Cost);
		
	}
	//got to "jail" square

	@Override
	public void game(ArrayList<player> players, int dice, banker bank, ArrayList<monopoly> mnp, String plyName) {
		
		int id = player.findPlayer(players, plyName);
		players.get(id).position = 10;
		mnp.get(players.get(id).position).game(players, dice, bank, mnp, plyName);
	}

	@Override
	public void pathWrite(PrintStream print) {
		
		super.pathWrite(print);
	}
}
