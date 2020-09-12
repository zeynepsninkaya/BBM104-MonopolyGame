import java.io.PrintStream;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class property extends monopoly {
	protected String whose;
	protected int type;
	protected int status=0; // satilmissa 1 degilse 0
	public property(String Name, int Id, int Cost, int Type) {
		super(Name, Id, Cost);
		this.type = Type; 
	}
	public property() {
	}
	@Override
	public void game(ArrayList<player> players, int dice, banker bank, ArrayList<monopoly> mnp, String plyName) {
		int rent = 0;
		int ind = player.findPlayer(players, plyName);
		
		if (monopoly.isBankrupt(players) == true) {
		;
			if (mnp.get(players.get(ind).position) instanceof property){
				
				if (mnp.get(players.get(ind).position) != null && ((property)mnp.get(players.get(ind).position)).status == 1) {
					int otherind = player.findOtherPlayer(players, plyName);
					if (((property)mnp.get(players.get(ind).position)).type == 1) {
						
						
						if (((property)mnp.get(players.get(ind).position)).cost <= 2000) {
							rent = (int) (((property)mnp.get(players.get(ind).position)).cost * 0.4);
						}
						else if(((property)mnp.get(players.get(ind).position)).cost > 2000 && ((property)mnp.get(players.get(ind).position)).cost <=3000) {
							rent = (int) (((property)mnp.get(players.get(ind).position)).cost * 0.3);
						}
						else if(((property)mnp.get(players.get(ind).position)).cost > 3000 && ((property)mnp.get(players.get(ind).position)).cost <=4000) {
							rent = (int) (((property)mnp.get(players.get(ind).position)).cost * 0.35);
						}
						
						
						
					}
					
					else if(((property)mnp.get(players.get(ind).position)).type == 2) {
						
						int railroad_count = 0;
						int index_other = IntStream.range(0,players.size()).filter(plyr -> ! players.get(plyr).name.equals(plyName))
								.findFirst()
								.getAsInt();
						for (int own = 0;own<players.get(index_other).ownList.size();own++) {
							if (players.get(index_other).ownList.get(own).type == 2)
								railroad_count++;
						}
						
						rent = 25 * railroad_count;
						
					}
					
					else if(((property)mnp.get(players.get(ind).position)).type == 3) {
						rent = 4 * dice;
					}
					
					
					if (((property)mnp.get(players.get(ind).position)).whose.equals(players.get(ind).name)) {
						System.out.println(monopoly.printString(players, bank, dice, plyName)+ 
								players.get(ind).name + " has " + ((property)mnp.get(players.get(ind).position)).name);
					}
					
					else {
						
						if (players.get(ind).money >= rent) {
							
							players.get(player.findPlayer(players, plyName)).money = players.get(player.findPlayer(players, plyName)).money - rent ;
							players.get(otherind).money = players.get(otherind).money + rent;
							
							System.out.println( monopoly.printString(players, bank, dice, plyName) + 
									players.get(ind).name + " paid rent for " + ((property)mnp.get(players.get(ind).position)).name );
							
						}
						else {
							System.out.println(monopoly.printString(players, bank, dice, plyName) + 
									players.get(ind).name + " goes bankrupt" );
							
							monopoly.show(players, bank);
							System.exit(0);
						}
						
					}
					
					
					
				}
				
				else {
					if (((property)mnp.get(players.get(ind).position)).cost <= players.get(ind).money) {
						players.get(ind).ownList.add((property)mnp.get(players.get(ind).position));
						players.get(ind).money -= ((property)mnp.get(players.get(ind).position)).cost;
						((property)mnp.get(players.get(ind).position)).status = 1;
						((property)mnp.get(players.get(ind).position)).whose = players.get(ind).name;
						bank.money += ((property)mnp.get(players.get(ind).position)).cost;
						System.out.println(monopoly.printString(players, bank, dice, plyName)+
								players.get(ind).name + " bought " + ((property)mnp.get(players.get(ind).position)).name);
						
						
					}
					else {
						System.out.println(monopoly.printString(players, bank, dice, plyName)+
								players.get(ind).name + " goes bankrupt");
						
						monopoly.show(players, bank);
						System.exit(0);
					}
				}
				
			}
			
			
		}
		
		
		
	}
	@Override
	public void pathWrite(PrintStream print) {
		
		super.pathWrite(print);
	}

}
