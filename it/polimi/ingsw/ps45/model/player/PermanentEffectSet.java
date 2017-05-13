package it.polimi.ingsw.ps45.model.player;

import java.util.HashMap;

public class PermanentEffectSet {
	private HashMap<String, Boolean> map;
	
	public PermanentEffectSet(){
		map = new HashMap<String, Boolean>();
	}
	
	public void addModifier(String key, boolean value){
		map.put(key, value);
	}
	
	public Boolean getModifier(String key){
		if(!map.containsKey(key)) map.put(key, false);
		return map.get(key);
	}
}
