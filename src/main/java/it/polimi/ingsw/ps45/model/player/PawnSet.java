package it.polimi.ingsw.ps45.model.player;

import java.util.HashMap;

/**
 * Set that contains all the pawns a player owns.
 */
public class PawnSet {

	
	private HashMap<PawnType,Pawn> pawnMap;
	private int defaultNeutralValue;
	
	
	/**
 	 * Constructor
 	 * All pawns have a default value of 0 and are available
	 */
	public PawnSet(){
		pawnMap = new HashMap<PawnType, Pawn>();
		pawnMap.put(PawnType.WHITE, new Pawn(0, true, PawnType.WHITE));
		pawnMap.put(PawnType.BLACK, new Pawn(0, true, PawnType.BLACK));
		pawnMap.put(PawnType.ORANGE, new Pawn(0, true, PawnType.ORANGE));
		pawnMap.put(PawnType.NEUTRAL, new Pawn(0, true, PawnType.NEUTRAL));
		
		defaultNeutralValue = 0;
	}
	
	
	/**
	 * @return the pawn that corresponds with that pawn type.
	 */
	public Pawn get(PawnType type){
		return pawnMap.get(type);
	}
	
	/**
	 * Sets the new value of a pawn in the set.
	 * @param type the type of the pawn in the set.
	 * @param value the new value of the pawn.
	 * @param available the new availability of the pawn.
	 */
	public void set(PawnType key,Integer value, boolean available){
		Pawn p = pawnMap.get(key);
		p.setValue(value);
		p.setAvailable(available);
	}

	/**
	 * @return the set as a hashmap with the pawntype as key.
	 */
	public HashMap<PawnType, Pawn> getPawnMap() {
		return pawnMap;
	}

	/**
	 * @return the neutral pawn's default value.
	 */
	public int getDefaultNeutralValue() {
		return defaultNeutralValue;
	}

	/**
	 * Sets the neutral pawn's default value.
	 * @param defaultNeutralValue the new value.
	 */
	public void setDefaultNeutralValue(int defaultNeutralValue) {
		this.defaultNeutralValue = defaultNeutralValue;
	}
	
	
	
	
	
}
