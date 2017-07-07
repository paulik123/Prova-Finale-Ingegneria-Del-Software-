package it.polimi.ingsw.ps45.model.vatican;

import java.util.HashMap;
/**
 * Converter class which faith points to victory points for when the player accepts vatican or end-game calculation.
 */
public class VaticanVictoryPointsConverter {
	private HashMap<Integer, Integer> rates;
	
	/**
 	 * Constructor
 	 * Adds all the conversion rates in a map.
	 */
	VaticanVictoryPointsConverter(){
		rates = new HashMap<Integer, Integer>();
		rates.put(0, 0);
		rates.put(1, 1);
		rates.put(2, 2);
		rates.put(3, 3);
		rates.put(4, 4);
		rates.put(5, 5);
		rates.put(6, 7);
		rates.put(7, 9);
		rates.put(8, 11);
		rates.put(9, 13);
		rates.put(10, 15);
		rates.put(11, 17);
		rates.put(12, 19);
		rates.put(13, 22);
		rates.put(14, 25);
		rates.put(15, 30);
	}
	
	/**
	 * @param faithPoints the number of faithPoints to make the conversion from.
	 * @return the number of victory the number of given faithPoints corresponds to.
	 */
	public int getVictoryPoints(int faithPoints){
		if(faithPoints < 0) return 0;
		if(rates.containsKey(faithPoints)){
			return rates.get(faithPoints);
		}else {
			return 30;
		}
	}
}
