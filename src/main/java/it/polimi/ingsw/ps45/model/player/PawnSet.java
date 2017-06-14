package it.polimi.ingsw.ps45.model.player;

import java.util.HashMap;

public class PawnSet {

	
	private HashMap<PawnType,Pawn> pawnMap;
	
	public PawnSet(){
		pawnMap = new HashMap<PawnType, Pawn>();
		pawnMap.put(PawnType.WHITE, new Pawn(0, true, PawnType.WHITE));
		pawnMap.put(PawnType.BLACK, new Pawn(0, true, PawnType.BLACK));
		pawnMap.put(PawnType.ORANGE, new Pawn(0, true, PawnType.ORANGE));
		pawnMap.put(PawnType.NEUTRAL, new Pawn(0, true, PawnType.NEUTRAL));
	}
	
	public Pawn get(PawnType type){
		return pawnMap.get(type);
	}
	
	public void set(PawnType key,Integer value, boolean available){
		Pawn p = pawnMap.get(key);
		p.setValue(value);
		p.setAvailable(available);
	}
	
	
	public boolean canThePawnDoTheAction(PawnType pt, int cost){
		if(pawnMap.get(pt).getValue() >= cost) return true;
		return false;
	}

	public HashMap<PawnType, Pawn> getPawnMap() {
		return pawnMap;
	}
	
	
	
}
