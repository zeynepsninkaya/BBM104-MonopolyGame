import java.io.PrintStream;
import java.util.ArrayList;

public class tax extends monopoly {

	public tax(String Name, int Id, int Cost) {
		super(Name, Id, Cost);
		
	}
	//pay 100tl to banker

	@Override
	public void game(ArrayList<player> players, int dice, banker bank, ArrayList<monopoly> mnp, String plyName) {
		int ind = player.findPlayer(players, plyName);
		if (players.get(ind).money >= 100) {
			players.get(ind).money -= 100;
			bank.money += 100;
			System.out.println(monopoly.printString(players, bank, dice, plyName)+
					players.get(ind).name + " paid Tax.");
		}
		else {
			System.out.println(monopoly.printString(players, bank, dice, plyName) 
					+  players.get(ind).name + " goes bankrupt");
			
			monopoly.show(players, bank);
			System.exit(0);
		}
	}

	@Override
	public void pathWrite(PrintStream print) {
		
		super.pathWrite(print);
	}
}
