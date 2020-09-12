import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;

public class monopoly {
	protected String name;
	protected int id;
	protected int cost;
	

	public monopoly(String name2, int id2, int cost2) {
		
		this.name = name2;
		this.id = id2;
		this.cost = cost2;
	}
	
	public monopoly() {}
	
	
	
	public void game(ArrayList<player> players, int dice, banker bank,ArrayList<monopoly> mnp, String plyName) {
		
		int ind = player.findPlayer(players, plyName);
		
		if (isBankrupt(players) == false) {
			//System.out.println("GAME OVER");
		}
		
	}
	
	public static boolean isBankrupt(ArrayList<player> players) {
		boolean result = true;
		for (player p : players) {
			if(p.money<0)
				result = false;
		}
		
		return result;
	}
	
	public static void positionImplement(player p, int dice, banker bank) {
		if (p.position + dice >= 40) {
			p.position = p.position + dice - 40;
			p.money += 200;
			bank.money -= 200;
			
		}
		else {
			p.position += dice;
		}
	}
	
	public void pathWrite (PrintStream print) {
		System.setOut(print);
	}
	
	
	public static String printString(ArrayList<player> players , banker b , int dice, String plyName) {
		String print = "";
		
		print = 
				players.get(player.findPlayer(players, plyName)).name + "\t" + dice + "\t"+ 
				(int )(players.get(player.findPlayer(players, plyName)).position + 1)
				+ "\t" + players.get(0).money + "\t" + players.get(1).money + "\t";
		return print;
	}
	
	public boolean isInJail(player play) {
		boolean result = false;
		if (play.position == 10) {
			result =  true;
		}
		
		return result;
	}
	
	public static void show(ArrayList<player> play, banker bank) {
		
		String winner;
		String tire = "-";
		String.join("", Collections.nCopies(10, tire));
		System.out.println(String.join("", Collections.nCopies(107, tire)));
		for (player p : play) {
			
			System.out.print(p.name + "\t" +  p.money + "\thave:" );
			p.ownList.forEach(o -> System.out.print(" "+o.name+ ","));
			System.out.print(System.lineSeparator());
			
			
		}
		System.out.println("Banker  "+ bank.money);
		
		if (play.get(0).money == play.get(1).money) {
			System.out.println("Scoreless");
		}
		else {
			winner = (play.get(0).money > play.get(1).money) ? play.get(0).name : play.get(1).name;
			System.out.println("Winner  " + winner);
		}
		
		
		
		System.out.println(String.join("", Collections.nCopies(107, tire)));
	}
}
