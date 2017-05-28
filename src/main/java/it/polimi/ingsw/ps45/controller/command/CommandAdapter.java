package it.polimi.ingsw.ps45.controller.command;

import it.polimi.ingsw.ps45.model.area.Board;
import it.polimi.ingsw.ps45.model.area.NoCardArea;
import it.polimi.ingsw.ps45.model.area.cardarea.BuildingCardArea;
import it.polimi.ingsw.ps45.model.area.cardarea.CharacterCardArea;
import it.polimi.ingsw.ps45.model.area.cardarea.TerritoryCardArea;
import it.polimi.ingsw.ps45.model.area.cardarea.VentureCardArea;
import it.polimi.ingsw.ps45.model.player.PawnType;

public class CommandAdapter {
	private Board b;
	
	public CommandAdapter(Board b){
		this.b = b;
	}
	public PawnType getPawnType(String pawnType) throws Exception{
		
		String s = pawnType.toLowerCase();
		
		switch(s){
		case "white":
			return PawnType.WHITE;
		case "black":
			return PawnType.BLACK;
		case "orange":
			return PawnType.ORANGE;
		case "neutral":
			return PawnType.NEUTRAL;
		default: throw new Exception("Wrong Pawn Type");
		}
	}
	
	public NoCardArea getNoCardArea(String s) throws Exception{
		return b.getAreaFromString(s);
	}
	
	public NoCardArea getProductioArea(String s) throws Exception{
		return b.getProductionAreas().getAreaFromString(s);
	}
	
	public NoCardArea getHarvestArea(String s) throws Exception{
		return b.getHarvestAreas().getAreaFromString(s);
	}
	
	public BuildingCardArea getBuildingArea(String s) throws Exception{
		return b.getBuildingTower().getAreaFromString(s);
	}
	
	public CharacterCardArea getCharacterArea(String s) throws Exception{
		return b.getCharacterTower().getAreaFromString(s);
	}
	
	public TerritoryCardArea getTerritoryArea(String s) throws Exception{
		return b.getTerritoryTower().getAreaFromString(s);
	}
	
	public VentureCardArea getVentureArea(String s) throws Exception{
		return b.getVentureTower().getAreaFromString(s);
	}
}
