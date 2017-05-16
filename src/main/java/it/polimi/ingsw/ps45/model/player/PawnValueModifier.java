package it.polimi.ingsw.ps45.model.player;

import java.util.HashMap;

public class PawnValueModifier {
	private HashMap<PawnType, Integer> map;
	
	public PawnValueModifier(){
		map = new HashMap<PawnType, Integer>();
		map.put(PawnType.BLACK, 0);
		map.put(PawnType.WHITE, 0);
		map.put(PawnType.ORANGE, 0);
		map.put(PawnType.NEUTRAL, 0);
	}

	public void setValue(PawnType pt, int value){
		map.put(pt, value);
	}
	public int getValue(PawnType pt){
		return map.get(pt);
	}
}
