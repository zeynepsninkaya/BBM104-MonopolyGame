import java.io.PrintStream;
import java.util.ArrayList;

public class jail extends monopoly {

	public jail(String Name, int Id, int Cost) {
		super(Name, Id, Cost);
		
	}
	//wait 3 times

	@Override
	public void game(ArrayList<player> players, int dice, banker bank, ArrayList<monopoly> mnp, String plyName) {
		int ply = player.findPlayer(players, plyName);
		
		
		if (players.get(ply).jailCount>=4 ) {
			players.get(ply).jailCount = 0;
		}
		
		else {
			if (players.get(ply).jailCount != 0) {
				System.out.println(monopoly.printString(players, bank, dice, plyName)+ players.get(ply).name + 
						" in jail (count =" + players.get(ply).jailCount + " )");
				players.get(ply).jailCount++;
				
				if (players.get(ply).jailCount>=4 ) {
					players.get(ply).jailCount = 0;
				}
			}
			
			else if (players.get(ply).jailCount == 0) {
				System.out.println(monopoly.printString(players, bank, dice, plyName)+ players.get(ply).name + " went to jail" );
				players.get(ply).jailCount++;
				
			
			}
			
		}
		
		
	}

	@Override
	public void pathWrite(PrintStream print) {
		
		super.pathWrite(print);
	}
}
