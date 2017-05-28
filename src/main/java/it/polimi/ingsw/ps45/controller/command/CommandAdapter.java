package it.polimi.ingsw.ps45.controller.command;

import it.polimi.ingsw.ps45.model.actions.ProductionMode;
import it.polimi.ingsw.ps45.model.actions.CouncilPrivilege.CoinsCouncilPrivilege;
import it.polimi.ingsw.ps45.model.actions.CouncilPrivilege.CouncilPrivilege;
import it.polimi.ingsw.ps45.model.actions.CouncilPrivilege.FaithPointsCouncilPrivilege;
import it.polimi.ingsw.ps45.model.actions.CouncilPrivilege.MilitaryPoinsCouncilPrivilege;
import it.polimi.ingsw.ps45.model.actions.CouncilPrivilege.ServantsCouncilPrivilege;
import it.polimi.ingsw.ps45.model.actions.CouncilPrivilege.WoodAndStoneCouncilPrivilege;
import it.polimi.ingsw.ps45.model.area.Board;
import it.polimi.ingsw.ps45.model.area.NoCardArea;
import it.polimi.ingsw.ps45.model.area.cardarea.BuildingCardArea;
import it.polimi.ingsw.ps45.model.area.cardarea.CharacterCardArea;
import it.polimi.ingsw.ps45.model.area.cardarea.TerritoryCardArea;
import it.polimi.ingsw.ps45.model.area.cardarea.VentureCardArea;
import it.polimi.ingsw.ps45.model.cards.VentureMode;
import it.polimi.ingsw.ps45.model.player.PawnType;

public class CommandAdapter {
	private Board b;
	
	public CommandAdapter(Board b){
		this.b = b;
	}
	
	public VentureMode getVentureMode(String mode) throws Exception{
		switch(mode.toLowerCase()){
		case "first":
			return VentureMode.FIRST;
		case "second":
			return VentureMode.SECOND;
		default: throw new Exception("Wrong Venture Mode");
		}
	}
	
	public ProductionMode[] getProductionMode(String productionMode){
		ProductionMode[] pm = new ProductionMode[6];
		char[] modes = productionMode.toCharArray();
		for(int i=0;i<pm.length;i++){
			switch(modes[i]){
			case '1': pm[i] = ProductionMode.FIRST;
						break;
			case '2': pm[i] = ProductionMode.SECOND;
						break;
			default: pm[i] = ProductionMode.NONE;
						break;
			}
		}
		return pm;
		
	}
	
	public CouncilPrivilege getCouncilPrivilege(String cp){
		switch(cp.toLowerCase()){
		case "coins": return new CoinsCouncilPrivilege();
		case "faith": return new FaithPointsCouncilPrivilege();
		case "military": return new MilitaryPoinsCouncilPrivilege();
		case "servants": return new ServantsCouncilPrivilege();
		case "woodandstone": return new WoodAndStoneCouncilPrivilege();
		default: return new CoinsCouncilPrivilege();
		}
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
