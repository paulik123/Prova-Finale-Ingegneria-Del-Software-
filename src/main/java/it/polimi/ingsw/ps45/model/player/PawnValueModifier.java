package it.polimi.ingsw.ps45.model.player;

import java.util.HashMap;

/**
 * Class that contains penalties/bonuses regarding players pawn value.
 */
public class PawnValueModifier {
	private HashMap<PawnType, Integer> map;
	
	
	/**
 	 * Constructor
 	 * Sets everything to zero.
	 */
	public PawnValueModifier(){
		map = new HashMap<PawnType, Integer>();
		map.put(PawnType.BLACK, 0);
		map.put(PawnType.WHITE, 0);
		map.put(PawnType.ORANGE, 0);
		map.put(PawnType.NEUTRAL, 0);
	}

	/**
	 * Sets the new value of the modifier
	 * @param value the new value.
	 * @param pt the pawntype of the modifier.
	 */
	public void setValue(PawnType pt, int value){
		map.put(pt, value);
	}
	
	/**
	 * @param pt the pawntype of the modifier.
	 * @return 	the value of the modifier with the specified pawn type.
	 */
	public int getValue(PawnType pt){
		return map.get(pt);
	}
}
