package it.polimi.ingsw.ps45.model.player;

import java.util.HashMap;

public class ModifierSet<T> {
	private HashMap<T, Integer> map;
	
	
	public ModifierSet(){
		map = new HashMap<T, Integer>();
	}
	
	public void addModifier(T key, Integer value){
		map.put(key, value);
	}
	
	public Integer getModifier(T key){
		if(!map.containsKey(key)) map.put(key, 0);
		return map.get(key);
	}
	

}
