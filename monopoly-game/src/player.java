import java.util.ArrayList;
import java.util.stream.IntStream;

public class player {
	protected String name;
	protected int money;
	protected int position = 0;
	protected int jailCount = 0;
	ArrayList<property> ownList = new ArrayList<property>();
	
	public player() {}
	
	public player(String name,int moneysi) {
		this.money = moneysi;
		this.name = name;
	}
	
	public static int findPlayer(ArrayList<player> playerList, String playername) {
		int index = -1;
		
		if (playerList.parallelStream().anyMatch(p -> p.name.equals(playername))) {
			index = IntStream.range(0,playerList.size())
					.filter(ply -> playerList.get(ply).name.equals(playername))
					.findFirst()
					.getAsInt();
		}
		else
			index = -1;
		
		return index;
	}
	
	public static int findOtherPlayer(ArrayList<player> playerList, String playername) {
		int index = -1;
		if (playerList.parallelStream().anyMatch(p -> !( p.name.equals(playername)))) {
			index = IntStream.range(0,playerList.size())
					.filter(ply -> !playerList.get(ply).name.equals(playername))
					.findFirst()
					.getAsInt();
		}
		else
			index = -1;
		
		return index;
	}
}
