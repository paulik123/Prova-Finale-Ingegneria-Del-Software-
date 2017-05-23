package it.polimi.ingsw.ps45.model.area.cardarea;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import it.polimi.ingsw.ps45.gson.PropertyBasedInterfaceMarshal;
import it.polimi.ingsw.ps45.model.area.NoCardArea;
import it.polimi.ingsw.ps45.model.effects.Effect;
import it.polimi.ingsw.ps45.model.player.Player;

public class BuildingTower implements Tower{
	
	public BuildingTower(){
		b0 = loadFromFile("serialized//areas//cardareas//BuildingCardAreaGroundFloor.json");
		b1 = loadFromFile("serialized//areas//cardareas//BuildingCardAreaFirstFloor.json");
		b2 = loadFromFile("serialized//areas//cardareas//BuildingCardAreaSecondFloor.json");
		b3 = loadFromFile("serialized//areas//cardareas//BuildingCardAreaThirdFloor.json");
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
	}


