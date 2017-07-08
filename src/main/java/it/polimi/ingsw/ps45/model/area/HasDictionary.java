package it.polimi.ingsw.ps45.model.area;

import java.util.HashMap;

import it.polimi.ingsw.ps45.exceptions.WrongCommandArgumentException;

/**
 * Interface that says that a class that contains areas also has a dictionary so it can return them using their name as argument.
 */
public interface HasDictionary {
	
	/**
	 * @return The a HashMap which stores the name of an area as key and it's instantiated object as value.
	 */
	public HashMap<String, ? extends Area> getDictionary();
	
	/**
	 * @throws Exception if the an area with the given name doesn't exist
	 * @param s name of the area. Also key in the dictionary.
	 * @return A NoCardArea that corresponds with the parameter string s.
	 */
	public Area getAreaFromString(String s) throws WrongCommandArgumentException; 

}
