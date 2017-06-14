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

public class TerritoryTower implements Tower, HasDictionary{
	
	HashMap<String, TerritoryCardArea> dictionary;
	
	public TerritoryTower(){
		dictionary = new HashMap<String, TerritoryCardArea>();
		
		t0 = loadFromFile("serialized//areas//cardareas//TerritoryCardAreaGroundFloor.json");
		t1 = loadFromFile("serialized//areas//cardareas//TerritoryCardAreaFirstFloor.json");
		t2 = loadFromFile("serialized//areas//cardareas//TerritoryCardAreaSecondFloor.json");
		t3 = loadFromFile("serialized//areas//cardareas//TerritoryCardAreaThirdFloor.json");
		
		dictionary.put("t0", t0);
		dictionary.put("t1", t1);
		dictionary.put("t2", t2);
		dictionary.put("t3", t3);
	}
	
	private TerritoryCardArea t0;
	private TerritoryCardArea t1;
	private TerritoryCardArea t2;
	private TerritoryCardArea t3;
	
	@Override
	public boolean isOccupied() {
		int occupants = t0.getOccupants().size() + t1.getOccupants().size() + t2.getOccupants().size() + t3.getOccupants().size(); 
		if(occupants > 0)return true;
		return false;
	}

	@Override
	public boolean isOccupiedByPlayer(Player p) {
		return 	    t0.isOccupiedByPlayerWithColoredPawn(p) || 
					t1.isOccupiedByPlayerWithColoredPawn(p) || 
					t2.isOccupiedByPlayerWithColoredPawn(p) || 
					t3.isOccupiedByPlayerWithColoredPawn(p);
	}
	
	public TerritoryCardArea loadFromFile(String path){
		Gson gson = new GsonBuilder()
                .registerTypeAdapter(Effect.class,
                        new PropertyBasedInterfaceMarshal()).create();
	 
		TerritoryCardArea c = null;
		try {
			c = gson.fromJson(new FileReader(path), TerritoryCardArea.class);
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

	public TerritoryCardArea getGroundFloor() {
		return t0;
	}

	public TerritoryCardArea getFirstFloor() {
		return t1;
	}

	public TerritoryCardArea getSecondFloor() {
		return t2;
	}

	public TerritoryCardArea getThirdFloor() {
		return t3;
	}
	
	public ArrayList<TerritoryCardArea> getAreas(){
		ArrayList<TerritoryCardArea> list = new ArrayList<TerritoryCardArea>();
		list.add(t0);
		list.add(t1);
		list.add(t2);
		list.add(t3);
		return list;
	}
	
	public TerritoryCardArea getAreaFromString(String s) throws Exception {
		if(!dictionary.containsKey(s.toLowerCase())) throw new Exception("No such key");
		return dictionary.get(s.toLowerCase());
	}

	public HashMap<String, TerritoryCardArea> getDictionary() {
		return dictionary;
	}

	@Override
	public Area getAreaFromDictionary(String s) throws Exception {
		if(!dictionary.containsKey(s.toLowerCase())) throw new Exception("No such key");
		return dictionary.get(s.toLowerCase());
	}
	
	
	
	

}
