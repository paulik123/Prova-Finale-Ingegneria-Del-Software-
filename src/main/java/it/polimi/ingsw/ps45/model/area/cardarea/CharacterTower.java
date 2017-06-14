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

public class CharacterTower implements Tower, HasDictionary{
	
	HashMap<String, CharacterCardArea> dictionary;
	
	public CharacterTower(){
		dictionary = new HashMap<String, CharacterCardArea>();
		
		c0 = loadFromFile("serialized//areas//cardareas//CharacterCardAreaGroundFloor.json");
		c1 = loadFromFile("serialized//areas//cardareas//CharacterCardAreaFirstFloor.json");
		c2 = loadFromFile("serialized//areas//cardareas//CharacterCardAreaSecondFloor.json");
		c3 = loadFromFile("serialized//areas//cardareas//CharacterCardAreaThirdFloor.json");
		
		dictionary.put("c0", c0);
		dictionary.put("c1", c1);
		dictionary.put("c2", c2);
		dictionary.put("c3", c3);
		
	}
	
	private CharacterCardArea c0;
	private CharacterCardArea c1;
	private CharacterCardArea c2;
	private CharacterCardArea c3;
	
	@Override
	public boolean isOccupied() {
		int occupants = c0.getOccupants().size() + c1.getOccupants().size() + c2.getOccupants().size() + c3.getOccupants().size(); 
		if(occupants > 0)return true;
		return false;
	}

	@Override
	public boolean isOccupiedByPlayer(Player p) {
		return 	    c0.isOccupiedByPlayerWithColoredPawn(p) || 
					c1.isOccupiedByPlayerWithColoredPawn(p) || 
					c2.isOccupiedByPlayerWithColoredPawn(p) || 
					c3.isOccupiedByPlayerWithColoredPawn(p);
	}
	
	public CharacterCardArea loadFromFile(String path){
		Gson gson = new GsonBuilder()
                .registerTypeAdapter(Effect.class,
                        new PropertyBasedInterfaceMarshal()).create();
	 
		CharacterCardArea c = null;
		try {
			c = gson.fromJson(new FileReader(path), CharacterCardArea.class);
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

	public CharacterCardArea getGroundFloor() {
		return c0;
	}

	public CharacterCardArea getFirstFloor() {
		return c1;
	}

	public CharacterCardArea getSecondFloor() {
		return c2;
	}

	public CharacterCardArea getThirdFloor() {
		return c3;
	}
	
	public ArrayList<CharacterCardArea> getAreas(){
		ArrayList<CharacterCardArea> list = new ArrayList<CharacterCardArea>();
		list.add(c0);
		list.add(c1);
		list.add(c2);
		list.add(c3);
		return list;
	}
	
	public CharacterCardArea getAreaFromString(String s) throws Exception {
		if(!dictionary.containsKey(s.toLowerCase())) throw new Exception("No such key");
		return dictionary.get(s.toLowerCase());
	}

	public HashMap<String, CharacterCardArea> getDictionary() {
		return dictionary;
	}

	@Override
	public Area getAreaFromDictionary(String s) throws Exception {
		if(!dictionary.containsKey(s.toLowerCase())) throw new Exception("No such key");
		return dictionary.get(s.toLowerCase());
	}
	
	
	
	

}
