package it.polimi.ingsw.ps45.model.player;

import java.util.HashMap;

public class ActionConsumableDiscountSet {
	private HashMap<String, ConsumableSet> map;
	
	public ActionConsumableDiscountSet(){
		map = new HashMap<String, ConsumableSet>();
	}
	
	public void getConsumableSet(String key){
		map.get(key);
	}
	
	public ConsumableSet getModifier(String key){
		if(!map.containsKey(key)) map.put(key, new ConsumableSet());
		return map.get(key);
	}
}