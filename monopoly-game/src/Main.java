import java.awt.List;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
public class Main {

	public static void main(String[] args) throws Exception, ParseException, FileNotFoundException, IOException{
		
		ArrayList<String> line = new ArrayList<String>();
		ArrayList<monopoly> monopoly = new ArrayList<monopoly>();
		ArrayList<player> playerList = new ArrayList<player>();
		banker bank = new banker("banker", 100000);
		for (int m=0;m<40;m++) {
			monopoly dummy = new monopoly("0",0,0);
			monopoly.add(dummy);
		}
		
		monopoly goObj = new go("go", 1,200);
		monopoly.set(0, goObj);
		
		monopoly taxObj = new tax("income",5,100);
		monopoly.set(4, taxObj);
		monopoly Taxobj = new tax("super",39,100);
		monopoly.set(38, Taxobj);
		
		monopoly freeObj = new freePark("free park",21,1);
		monopoly.set(20,freeObj);
		
		monopoly jailObj = new jail("jail",11,3);
		monopoly.set(10, jailObj);
		
		monopoly goToJail = new goJail("go to jail",31,0);
		monopoly.set(30,goToJail);
		
		monopoly chanceobj = new chanceCard("chance",8,0);
		monopoly chanceobj1 = new chanceCard("chance",23,0);
		monopoly chanceobj2 = new chanceCard("chance",37,0);
		
		monopoly communityObj = new chestCard("chest",3,0);
		monopoly communityObj1 = new chestCard("chest",18,0);
		monopoly communityObj2 = new chestCard("chest",34,0);
		
		monopoly.set(7, chanceobj); monopoly.set(22, chanceobj1); monopoly.set(36, chanceobj2);
		monopoly.set(2, communityObj); monopoly.set(17, communityObj); monopoly.set(33, communityObj);
		
		
		
		JSONParser parsr = new JSONParser();


        Object obj = parsr.parse(new FileReader("property.json"));

        JSONObject jsn = (JSONObject) obj;
        JSONArray msg = (JSONArray) jsn.get("1");
        property pro = new property();
		Iterator<JSONObject> it=msg.iterator();
		
		for (int i = 2;i<=4;i++) {
			
				while(it.hasNext()) {
				
					JSONObject a = (JSONObject) it.next();
					String pro_name =  (String) a.get("name");
					int pro_id = Integer.parseInt((String) a.get("id"));
					int pro_cost = Integer.parseInt((String) a.get("cost"));
					
					monopoly propertyObj = new property(pro_name,pro_id,pro_cost,i-1);
					monopoly.set(pro_id-1, propertyObj);
					
				}
				
				if(i<4) {
					String num = String.valueOf(i);
					msg=(JSONArray) jsn.get(num);
					it=msg.iterator();	
				}
		}
		
		
		JSONParser parse = new JSONParser();
        Object objct = parse.parse(new FileReader("list.json"));
        JSONObject jsnobj = (JSONObject) objct;
        JSONArray jsnar = (JSONArray) jsnobj.get("chanceList");
        int chance_id = 0;
		Iterator<JSONObject> iterate =jsnar.iterator();
		while(iterate.hasNext()) {
			JSONObject add = (JSONObject) iterate.next();
			String item = (String) add.get("item");
			chanceCard add_chance = new chanceCard(item,chance_id,0);
			add_chance.chanceList.add(add_chance);
			chance_id++;
		}
		
		
		int chest_id = 0;
		JSONArray jsnar1 = (JSONArray) jsnobj.get("communityChestList");
		
		Iterator<JSONObject> iterate1 =jsnar1.iterator();
		while(iterate1.hasNext()) {
			JSONObject add1 = (JSONObject) iterate1.next();
			String item1 = (String) add1.get("item");
			chestCard add_chest = new chestCard(item1,chest_id,0);
			add_chest.chestList.add(add_chest);
			chest_id++;
		}
		
		player play1 = new player("Player 1",15000);
		playerList.add(play1);
		player play2 = new player("Player 2",15000);
		playerList.add(play2);
		play1.jailCount = 0;
		play2.jailCount = 0;
		File file = new File(args[0]);
		Scanner scan = new Scanner(file);
		monopoly mon = new monopoly();
		int count = 0;
		
		
		
		PrintStream printStream = new PrintStream(new FileOutputStream("output.txt"));
		
		while(scan.hasNextLine()) {
			
			
			String commandline = scan.nextLine();
			line.add(commandline);
			
			if (commandline.contains(";")) {
				
				String ply_name = commandline.substring(0, commandline.indexOf(";"));
				String ply_dice = commandline.substring(commandline.indexOf(";")+1);
				int dice = Integer.valueOf(ply_dice);
				
				
				if (player.findPlayer(playerList, ply_name) == -1) {
					player ply = new player(ply_name,15000);
					ply.jailCount = 0;
					playerList.set(count,ply);
					count++;
					
					if (playerList.get(player.findPlayer(playerList, ply_name)).jailCount == 0 ){
						
						mon.positionImplement(playerList.get(player.findPlayer(playerList, ply_name)), dice,bank);
						monopoly.get((playerList.get(player.findPlayer(playerList, ply_name))).position).pathWrite(printStream);
						monopoly.get((playerList.get(player.findPlayer(playerList, ply_name))).position).game(playerList, dice, bank, monopoly, ply_name);
						
						
						
					}
					else {
						monopoly.get(10).pathWrite(printStream);
						monopoly.get(10).game(playerList, dice, bank, monopoly, ply_name);
						
						
						
						
					}
					
				}
				else {
					if (playerList.get(player.findPlayer(playerList, ply_name)).jailCount != 0 ) { 
						
						monopoly.get(10).pathWrite(printStream);
						monopoly.get(10).game(playerList, dice, bank, monopoly, ply_name);
						
						
						
						
						
					}
					else{
						mon.positionImplement(playerList.get(player.findPlayer(playerList, ply_name)), dice, bank);
						monopoly.get((playerList.get(player.findPlayer(playerList, ply_name))).position).pathWrite(printStream);
						
						monopoly.get((playerList.get(player.findPlayer(playerList, ply_name))).position).game(playerList, dice, bank, monopoly, ply_name);
						
					
					}
				}
				
			}
			else {
				mon.pathWrite(printStream);
				mon.show(playerList, bank);
				
				
			}
			
			if (!scan.hasNextLine()) {
				mon.pathWrite(printStream);
				mon.show(playerList, bank);
				
				
			}
			
			
		}
		
		
		
	}
}
