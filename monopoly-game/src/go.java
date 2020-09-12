import java.util.ArrayList;

public class go extends monopoly {

	
	//take 200tl from banker

	public go(String name, int id, int cost) {
		
		super(name,id,cost);
	}

	@Override
	public void game(ArrayList<player> players, int dice, banker bank, ArrayList<monopoly> mnp, String plyName) {
		
		int plyindex = player.findPlayer(players, plyName);
		
		System.out.println(monopoly.printString(players, bank, dice, plyName)+
				players.get(plyindex).name + " is in GO square");
		
	}

	
}
