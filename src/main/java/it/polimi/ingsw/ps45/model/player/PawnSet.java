package it.polimi.ingsw.ps45.model.player;

import java.util.HashMap;

public class PawnSet {

	
	private HashMap<PawnType,Pawn> pawnMap;
	
	public PawnSet(){
		pawnMap = new HashMap<PawnType, Pawn>();
		pawnMap.put(PawnType.WHITE, new Pawn(0, true));
		pawnMap.put(PawnType.BLACK, new Pawn(0, true));
		pawnMap.put(PawnType.ORANGE, new Pawn(0, true));
		pawnMap.put(PawnType.NEUTRAL, new Pawn(0, true));
	}
	
	public Pawn get(PawnType type){
		return pawnMap.get(type);
	}
	
	public void set(PawnType key,Integer value, boolean available){
		Pawn p = pawnMap.get(key);
		p.setValue(value);
		p.setAvailable(available);
	}
	
	public void applyPawnModifiers(ModifierSet<PawnType> ms){
		for(PawnType pt: pawnMap.keySet()){
			int newValue = pawnMap.get(pt).getValue() + ms.getModifier(pt);
			Pawn p = pawnMap.get(pt);
			p.setValue(newValue);
		}
	}
	
}
