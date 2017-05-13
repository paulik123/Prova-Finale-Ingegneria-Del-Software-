package it.polimi.ingsw.ps45.model.player;

import java.util.HashMap;
import java.util.Set;

public class ConsumableSet {
	
	private static String[] consumables = {"wood", "stone", "coins", "servants", "victoryPoints", "faithPoints", "militaryPoints"};
	
	private HashMap<String,Integer> consumablesMap;
	public ConsumableSet() {
			consumablesMap = new HashMap<String,Integer>();
			for(String s: consumables){
				consumablesMap.put(s, 0);
			}
	}
	public ConsumableSet(PlayerType type, int coins) {
		consumablesMap = new HashMap<String,Integer>();
		for(String s: consumables){
			consumablesMap.put(s, 0);
		}
		
		consumablesMap.put("wood",2);
		consumablesMap.put("stone",2);
		consumablesMap.put("servants",3);
		consumablesMap.put("coins", coins);
		
	}
	
	
	
	// Returns false if there is a consumable value in the cost bigger than it's own value for that consumable
	public boolean hasConsumablesAvailable(ConsumableSet cost){
		for(String key: consumablesMap.keySet()){
			if(cost.getConsumableValue(key) > consumablesMap.get(key)) return false;
		}
		return true;
	}
	
	public void pay(ConsumableSet cost){
		

		
		if(!hasConsumablesAvailable(cost)){
			//TODO: ASK PLAYER FOR A NEW COMMAND
			return;
		}
		
		for(String key: consumablesMap.keySet()){
			if(cost.getConsumableValue(key) != 0){
				int newValue = consumablesMap.get(key) - cost.getConsumableValue(key);
				consumablesMap.put(key, newValue);
			}
		}
	}
	
	public Integer getConsumableValue(String key){
		return consumablesMap.get(key);
		
	}
	
	public void collect(ModifierSet<String> ms, ConsumableSet cSet){
		
		applyModifiers(ms, cSet);
		
		for(String key: consumablesMap.keySet()){
			if(cSet.getConsumableValue(key) != 0){
				int newValue = consumablesMap.get(key) + cSet.getConsumableValue(key);
				consumablesMap.put(key, newValue);
			}
		}
	}
	
	public Set<String> getKeys(){
		return consumablesMap.keySet();
	}
	
	
	
	
	
	public void applyModifiers(ModifierSet<String> ms, ConsumableSet cs){
		
		for(String key: cs.getKeys()){
			int newValue = cs.getConsumableValue(key) + ms.getModifier(key);
			cs.consumablesMap.put(key,newValue);
		}
	}
	
}
