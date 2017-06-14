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
import it.polimi.ingsw.ps45.model.area.Area;
import it.polimi.ingsw.ps45.model.area.HasDictionary;
import it.polimi.ingsw.ps45.model.effects.Effect;
import it.polimi.ingsw.ps45.model.player.Player;

public class VentureTower implements Tower, HasDictionary{
	
	HashMap<String, VentureCardArea> dictionary;
	
	
	public VentureTower(){
		dictionary = new HashMap<String, VentureCardArea>();
		
		
		v0 = loadFromFile("serialized//areas//cardareas//VentureCardAreaGroundFloor.json");
		v1 = loadFromFile("serialized//areas//cardareas//VentureCardAreaFirstFloor.json");
		v2 = loadFromFile("serialized//areas//cardareas//VentureCardAreaSecondFloor.json");
		v3 = loadFromFile("serialized//areas//cardareas//VentureCardAreaThirdFloor.json");
		
		
		dictionary.put("v0", v0);
		dictionary.put("v1", v1);
		dictionary.put("v2", v2);
		dictionary.put("v3", v3);
	}
	
	private VentureCardArea v0;
	private VentureCardArea v1;
	private VentureCardArea v2;
	private VentureCardArea v3;
	
	@Override
	public boolean isOccupied() {
		int occupants = v0.getOccupants().size() + v1.getOccupants().size() + v2.getOccupants().size() + v3.getOccupants().size(); 
		if(occupants > 0)return true;
		return false;
	}

	@Override
	public boolean isOccupiedByPlayer(Player p) {
		return 	    v0.isOccupiedByPlayerWithColoredPawn(p) || 
					v1.isOccupiedByPlayerWithColoredPawn(p) || 
					v2.isOccupiedByPlayerWithColoredPawn(p) || 
					v3.isOccupiedByPlayerWithColoredPawn(p);
	}
	
	public VentureCardArea loadFromFile(String path){
		Gson gson = new GsonBuilder()
                .registerTypeAdapter(Effect.class,
                        new PropertyBasedInterfaceMarshal()).create();
	 
		VentureCardArea c = null;
		try {
			c = gson.fromJson(new FileReader(path), VentureCardArea.class);
		} catch (JsonSyntaxException e) {
			
			e.printStackTrace();
		} catch (JsonIOException e) {
			
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
	 return c;
	}

	public VentureCardArea getGroundFloor() {
		return v0;
	}

	public VentureCardArea getFirstFloor() {
		return v1;
	}

	public VentureCardArea getSecondFloor() {
		return v2;
	}

	public VentureCardArea getThirdFloor() {
		return v3;
	}
	
	public ArrayList<VentureCardArea> getAreas(){
		ArrayList<VentureCardArea> list = new ArrayList<VentureCardArea>();
		list.add(v0);
		list.add(v1);
		list.add(v2);
		list.add(v3);
		return list;
	}
	
	public VentureCardArea getAreaFromString(String s) throws Exception {
		if(!dictionary.containsKey(s.toLowerCase())) throw new Exception("No such key");
		return dictionary.get(s.toLowerCase());
	}

	public HashMap<String, VentureCardArea> getDictionary() {
		return dictionary;
	}

	@Override
	public Area getAreaFromDictionary(String s) throws Exception {
		if(!dictionary.containsKey(s.toLowerCase())) throw new Exception("No such key");
		return dictionary.get(s.toLowerCase());
	}
	
	
	
	

}
