package it.polimi.ingsw.ps45.model.area.cardarea;


import java.io.FileNotFoundException;
import java.io.FileReader;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import it.polimi.ingsw.ps45.gson.PropertyBasedInterfaceMarshal;
import it.polimi.ingsw.ps45.model.effects.Effect;
import it.polimi.ingsw.ps45.model.player.Player;

public class VentureTower implements Tower{
	
	public VentureTower(){
		v0 = loadFromFile("serialized//areas//cardareas//VentureCardAreaGroundFloor.json");
		v1 = loadFromFile("serialized//areas//cardareas//VentureCardAreaFirstFloor.json");
		v2 = loadFromFile("serialized//areas//cardareas//VentureCardAreaSecondFloor.json");
		v3 = loadFromFile("serialized//areas//cardareas//VentureCardAreaThirdFloor.json");
		
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

}
