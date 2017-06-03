package it.polimi.ingsw.ps45.model.area.cardarea;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import it.polimi.ingsw.ps45.gson.PropertyBasedInterfaceMarshal;
import it.polimi.ingsw.ps45.model.effects.Effect;
import it.polimi.ingsw.ps45.model.player.Player;

public class BuildingTower implements Tower{
	
	HashMap<String, BuildingCardArea> dictionary;
	
	public BuildingTower(){
		dictionary = new HashMap<String, BuildingCardArea>();
		b0 = loadFromFile("serialized//areas//cardareas//BuildingCardAreaGroundFloor.json");
		b1 = loadFromFile("serialized//areas//cardareas//BuildingCardAreaFirstFloor.json");
		b2 = loadFromFile("serialized//areas//cardareas//BuildingCardAreaSecondFloor.json");
		b3 = loadFromFile("serialized//areas//cardareas//BuildingCardAreaThirdFloor.json");
		
		dictionary.put("b0", b0);
		dictionary.put("b1", b1);
		dictionary.put("b2", b2);
		dictionary.put("b3", b3);
	}
	
	private BuildingCardArea b0;
	private BuildingCardArea b1;
	private BuildingCardArea b2;
	private BuildingCardArea b3;
	
	@Override
	public boolean isOccupied() {
		int occupants = b0.getOccupants().size() + b1.getOccupants().size() + b2.getOccupants().size() + b3.getOccupants().size(); 
		if(occupants > 0)return true;
		return false;
	}

	@Override
	public boolean isOccupiedByPlayer(Player p) {
		return 	    b0.isOccupiedByPlayerWithColoredPawn(p) || 
					b1.isOccupiedByPlayerWithColoredPawn(p) || 
					b2.isOccupiedByPlayerWithColoredPawn(p) || 
					b3.isOccupiedByPlayerWithColoredPawn(p);
	}
	
	public BuildingCardArea loadFromFile(String path){
		Gson gson = new GsonBuilder()
                .registerTypeAdapter(Effect.class,
                        new PropertyBasedInterfaceMarshal()).create();
	 
		BuildingCardArea c = null;
		try {
			c = gson.fromJson(new FileReader(path), BuildingCardArea.class);
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 return c;
	}

	public BuildingCardArea getGroundFloor() {
		return b0;
	}

	public BuildingCardArea getFirstFloor() {
		return b1;
	}

	public BuildingCardArea getSecondFloor() {
		return b2;
	}

	public BuildingCardArea getThirdFloor() {
		return b3;
	}
	
	public ArrayList<BuildingCardArea> getAreas(){
		ArrayList<BuildingCardArea> list = new ArrayList<BuildingCardArea>();
		list.add(b0);
		list.add(b1);
		list.add(b2);
		list.add(b3);
		return list;
	}
	
	public BuildingCardArea getAreaFromString(String s) throws Exception {
		if(!dictionary.containsKey(s.toLowerCase())) throw new Exception("No such key");
		return dictionary.get(s.toLowerCase());
	}
	
	
	
}


