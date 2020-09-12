import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;

public class chanceCard extends monopoly{

	public chanceCard(String Name, int Id, int Cost) {
		super(Name, Id, Cost);
		
	}
	static ArrayList<chanceCard> chanceList = new ArrayList<chanceCard>();
	@Override
	public void game(ArrayList<player> players, int dice, banker bank, ArrayList<monopoly> mnp, String plyName) {
		;
		
		if(monopoly.isBankrupt(players)== true) {
			chanceCard card = chanceList.get(0);
			
			if ((card.name.toLowerCase().contains("collect") || card.name.toLowerCase().contains("inherit") || card.name.toLowerCase().contains("get")) 
					&& !card.name.toLowerCase().contains("player") && !card.name.toLowerCase().contains("advance")) {
				String plusStr = card.name.replaceFirst(".*?(\\d+).*", "$1");
				
				int cost = Integer.valueOf(plusStr);
				bank.money -= cost;
				players.get(player.findPlayer(players, plyName)).money += cost;
				
				System.out.println(monopoly.printString(players, bank, dice, plyName) +
						players.get(player.findPlayer(players, plyName)).name + " draw " + card.name);
				
			}
			
			else if(card.name.toLowerCase().contains("advance") && card.name.toLowerCase().contains("go") ) {
				players.get(player.findPlayer(players, plyName)).position = 0;
				players.get(player.findPlayer(players, plyName)).money += 200;
				bank.money -= 200;
				System.out.println(monopoly.printString(players, bank, dice, plyName)+
						players.get(player.findPlayer(players, plyName)).name + " draw " + card.name);
				
			}
			
			else if(card.name.toLowerCase().contains("collect") && card.name.toLowerCase().contains("player") && card.name.toLowerCase().contains("from")) {
				String plusStr = card.name.replaceFirst(".*?(\\d+).*", "$1");
				int cost = Integer.valueOf(plusStr);
				
				if (players.get(player.findOtherPlayer(players, plyName)).money >= cost) {
					players.get(player.findOtherPlayer(players, plyName)).money -= cost;
					players.get(player.findPlayer(players, plyName)).money += cost;
					System.out.println(monopoly.printString(players, bank, dice, plyName)+
							players.get(player.findPlayer(players, plyName)).name + " draw " + card.name);
				}
				else {
					System.out.println(monopoly.printString(players, bank, dice, plyName)+
							players.get(player.findOtherPlayer(players, plyName)).name + " goes bankrupt");
					
					monopoly.show(players, bank);
					System.exit(0);
				}
				
			}
			
			else if(card.name.toLowerCase().contains("pay")) {
				String payStr = card.name.replaceFirst(".*?(\\d+).*", "$1");
				int cost = Integer.valueOf(payStr);
				if (players.get(player.findPlayer(players, plyName)).money >= cost) {
					players.get(player.findPlayer(players, plyName)).money -= cost;
					bank.money += cost;
					System.out.println(monopoly.printString(players, bank, dice, plyName)+
							players.get(player.findPlayer(players, plyName)).name + " draw " + card.name);
				}
				else {
					System.out.println(monopoly.printString(players, bank, dice, plyName)+
							players.get(player.findPlayer(players, plyName)).name + " goes bankrupt");
					
					monopoly.show(players, bank);
					System.exit(0);
				}
			}
			
			else if(card.name.toLowerCase().contains("advance") && card.name.toLowerCase().contains("square") ) {
				if (players.get(player.findPlayer(players, plyName)).position > 26) {
					players.get(player.findPlayer(players, plyName)).money += 200;
					bank.money -= 200;
				}
				
				System.out.print(monopoly.printString(players, bank, dice, plyName)+
						players.get(player.findPlayer(players, plyName)).name + " draw " + card.name + " ");
				players.get(player.findPlayer(players, plyName)).position = 26;
				
				
				((property)mnp.get(26)).game(players, dice, bank, mnp, plyName);
				
				
			}
			
			else if(card.name.toLowerCase().contains("go") && card.name.toLowerCase().contains("back") ) {
				String backstr = card.name.replaceFirst(".*?(\\d+).*", "$1");
				int back = Integer.valueOf(backstr);
				players.get(player.findPlayer(players, plyName)).position -= back;
				System.out.print(monopoly.printString(players, bank, dice, plyName)+
						players.get(player.findPlayer(players, plyName)).name + " draw " + card.name +" ");
				mnp.get(players.get(player.findPlayer(players, plyName)).position).game(players, dice, bank, mnp, plyName);
				
			}
			
			Collections.rotate(chanceList, -1);
		}
			
		}
	@Override
	public void pathWrite(PrintStream print) {
		
		super.pathWrite(print);
	}
		

	
}
