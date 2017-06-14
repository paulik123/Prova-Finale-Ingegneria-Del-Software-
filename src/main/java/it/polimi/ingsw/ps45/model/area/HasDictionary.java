package it.polimi.ingsw.ps45.model.area;

import java.util.HashMap;

public interface HasDictionary {
	
	public HashMap<String, ? extends Area> getDictionary();
	public Area getAreaFromDictionary(String s) throws Exception; 

}
