package it.polimi.ingsw.ps45.model.actions;

import it.polimi.ingsw.ps45.model.actions.state.*;
import it.polimi.ingsw.ps45.model.area.Area;
import it.polimi.ingsw.ps45.model.area.Board;
import it.polimi.ingsw.ps45.model.area.HarvestArea;
import it.polimi.ingsw.ps45.model.area.NoCardArea;
import it.polimi.ingsw.ps45.model.area.ProductionArea;
import it.polimi.ingsw.ps45.model.area.cardarea.BuildingCardArea;
import it.polimi.ingsw.ps45.model.area.cardarea.CharacterCardArea;
import it.polimi.ingsw.ps45.model.area.cardarea.TerritoryCardArea;
import it.polimi.ingsw.ps45.model.area.cardarea.VentureCardArea;
import it.polimi.ingsw.ps45.model.cards.VentureMode;
import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.player.Pawn;
import it.polimi.ingsw.ps45.model.player.PawnType;
import it.polimi.ingsw.ps45.model.player.Player;

public class ActionBuilder {
	private ActionBuilderState state;
	private Board board;
	private Player p;
	
	
	public ActionBuilder(Player p, Board board){
		this.p = p;
		this.board = board;
		
		state = new NoActionState();
	}


	public ActionBuilderState getState() {
		return state;
	}


	public void setState(ActionBuilderState state) {
		this.state = state;
	}


	public Board getBoard() {
		return board;
	}


	public void setBoard(Board board) {
		this.board = board;
	}
	
	public void placePawnProduction(ProductionArea area, PawnType pt, int servantsAdded) throws Exception{
		if(!state.placePawnAction()) throw new Exception("Action not allowed - state is false");
		
		Pawn pawn = p.getResourceSet().getPawn(pt);
		int pawnValue = calculatePawnValue(pt, servantsAdded);
		ConsumableSet servantsSet = servantsToConsumableSet(servantsAdded);
		
		if(canPlacePawnProductionRun(pawn, pawnValue, area, servantsSet)){
			makePlayerPay(servantsSet, pawn);
			new PlacePawnProductionAction(p, area, pt, pawnValue).run();
		}else throw new Exception("Action not allowed - pawn/consumables unavailable");
	}
	
	public void placePawnHarvest(HarvestArea area, PawnType pt, int servantsAdded) throws Exception{
		if(!state.placePawnAction()) throw new Exception("Action not allowed - state is false");
		
		Pawn pawn = p.getResourceSet().getPawn(pt);
		int pawnValue = calculatePawnValue(pt, servantsAdded);
		ConsumableSet servantsSet = servantsToConsumableSet(servantsAdded);
		
		if(canPlacePawnHarvestRun(pawn, pawnValue, area, servantsSet)){
			makePlayerPay(servantsSet, pawn);
			new PlacePawnHarvestAction(p, area, pt, pawnValue).run();
		}else throw new Exception("Action not allowed - pawn/consumables unavailable");
	}

	public void placePawnNoCard(NoCardArea area, PawnType pt, int servantsAdded) throws Exception{
		if(!state.placePawnAction()) throw new Exception("Action not allowed - state is false");
		
		Pawn pawn = p.getResourceSet().getPawn(pt);
		int pawnValue = calculatePawnValue(pt, servantsAdded);
		ConsumableSet servantsSet = servantsToConsumableSet(servantsAdded);
		
		if(canPlacePawnNoCardRun(pawn, pawnValue, area, servantsSet)){
			makePlayerPay(servantsSet, pawn);
			new PlacePawnNoCardAction(p, area, pt, pawnValue).run();
		}else throw new Exception("Action not allowed - pawn/consumables unavailable");
	}
	
	public void placePawnTerritory(TerritoryCardArea area, PawnType pt, int servantsAdded) throws Exception{
		if(!state.placePawnAction()) throw new Exception("Action not allowed - state is false");
		Pawn pawn = p.getResourceSet().getPawn(pt);
		int pawnValue = calculatePawnValue(pt, servantsAdded);
		ConsumableSet servantsSet = servantsToConsumableSet(servantsAdded);
		
		if(canPlacePawnTerritoryRun(pawn, pawnValue, area, servantsSet)){
			makePlayerPay(servantsSet, pawn);
			new PlacePawnTerritoryAction(p, area, pt, pawnValue).run();
		}else throw new Exception("Action not allowed - pawn/consumables unavailable");

	}
	
	public void placePawnCharacter(CharacterCardArea area, PawnType pt, int servantsAdded) throws Exception{
		if(!state.placePawnAction()) throw new Exception("Action not allowed - state is false");
		Pawn pawn = p.getResourceSet().getPawn(pt);
		int pawnValue = calculatePawnValue(pt, servantsAdded);
		ConsumableSet cost = area.getCharacter().cost();
		cost.collect(servantsToConsumableSet(servantsAdded));
		
		if(canPlacePawnCharacterRun(pawn, pawnValue, area, cost)){
			makePlayerPay(cost, pawn);
			new PlacePawnCharacterAction(p, area, pt, pawnValue).run();
		}else throw new Exception("Action not allowed - pawn/consumables unavailable");

	}
	
	public void placePawnBuilding(BuildingCardArea area, PawnType pt, int servantsAdded) throws Exception{
		if(!state.placePawnAction()) throw new Exception("Action not allowed - state is false");
		Pawn pawn = p.getResourceSet().getPawn(pt);
		int pawnValue = calculatePawnValue(pt, servantsAdded);
		ConsumableSet cost = area.getBuilding().cost();
		cost.collect(servantsToConsumableSet(servantsAdded));
		
		if(canPlacePawnBuildingRun(pawn, pawnValue, area, cost)){
			makePlayerPay(cost, pawn);
			new PlacePawnBuildingAction(p, area, pt, pawnValue).run();
		}else throw new Exception("Action not allowed - pawn/consumables unavailable");

	}
	
	public void placePawnVenture(VentureCardArea area, PawnType pt, int servantsAdded, VentureMode mode) throws Exception{
		if(!state.placePawnAction()) throw new Exception("Action not allowed - state is false");
		Pawn pawn = p.getResourceSet().getPawn(pt);
		int pawnValue = calculatePawnValue(pt, servantsAdded);
		
		ConsumableSet cost;
		if(mode == VentureMode.FIRST) cost = area.getVenture().costI();
		else cost = area.getVenture().costII();
		
		cost.collect(servantsToConsumableSet(servantsAdded));
		
		if(canPlacePawnVentureRun(pawn, pawnValue, area, cost)){
			makePlayerPay(cost, pawn);
			new PlacePawnVentureAction(p, area, pt, pawnValue).run();
		}else throw new Exception("Action not allowed - pawn/consumables unavailable");

	}
	

	
	
	
	
	
	
	private boolean canPlacePawnVentureRun(Pawn pawn, int pawnValue, VentureCardArea area, ConsumableSet cost){
		return !board.getVentureTower().isOccupiedByPlayer(p) &&
				p.getResourceSet().getVentureList().size() < 6 &&
				hasGeneralRequirementsWithPawn(pawn, pawnValue, area, cost);
	}
	
	
	private boolean canPlacePawnBuildingRun(Pawn pawn, int pawnValue, BuildingCardArea area, ConsumableSet cost){
		return !board.getBuildingTower().isOccupiedByPlayer(p) &&
				p.getResourceSet().getBuildingList().size() < 6 &&
				hasGeneralRequirementsWithPawn(pawn, pawnValue, area, cost);
	}
	
	private boolean canPlacePawnCharacterRun(Pawn pawn, int pawnValue, CharacterCardArea area, ConsumableSet cost){
		return !board.getCharacterTower().isOccupiedByPlayer(p) &&
				p.getResourceSet().getCharacterList().size() < 6 &&
				hasGeneralRequirementsWithPawn(pawn, pawnValue, area, cost);
	}
	
	private boolean canPlacePawnTerritoryRun(Pawn pawn, int pawnValue, TerritoryCardArea area, ConsumableSet cost){
		return !board.getTerritoryTower().isOccupiedByPlayer(p) &&
				hasMilitaryForTerritory() &&
				p.getResourceSet().getTerritoryList().size() < 6 &&
				hasGeneralRequirementsWithPawn(pawn, pawnValue, area, cost);
	}
	
	private boolean canPlacePawnNoCardRun(Pawn pawn, int pawnValue, NoCardArea area, ConsumableSet cost){
		return hasGeneralRequirementsWithPawn(pawn, pawnValue, area, cost);
	}
	
	private boolean canPlacePawnProductionRun(Pawn pawn, int pawnValue, ProductionArea area, ConsumableSet cost){
		return !board.getProductionAreas().isOccupiedByPlayer(p) &&
				hasGeneralRequirementsWithPawn(pawn, pawnValue, area, cost);
	}
	
	private boolean canPlacePawnHarvestRun(Pawn pawn, int pawnValue, HarvestArea area, ConsumableSet cost){
		return !board.getHarvestAreas().isOccupiedByPlayer(p) &&
				hasGeneralRequirementsWithPawn(pawn, pawnValue, area, cost);
	}
	
	private boolean hasGeneralRequirementsWithPawn(Pawn pawn, int pawnValue, Area area, ConsumableSet cost){
		   return   area.isAvailable() &&
				   	pawn.isAvailable() &&
				   	pawnValue > area.getCost() && 
				   	p.getResourceSet().hasConsumables(cost);
	}
	
	private boolean hasMilitaryForTerritory(){
		int size = p.getResourceSet().getTerritoryList().size() + 1;
		int militaryPoints = p.getResourceSet().getResources().getMilitaryPoins();
		if(size <= 2) return true;
		if(size == 3 && militaryPoints >= 3) return true;
		if(size == 4 && militaryPoints >= 7) return true;
		if(size == 5 && militaryPoints >= 12) return true;
		if(size == 6 && militaryPoints >= 18) return true;
		return false;
	}
	
	private void makePlayerPay(ConsumableSet cs, Pawn pawn){
		p.getResourceSet().pay(cs, pawn);
	}
	
	private int calculatePawnValue(PawnType pt, int servantsAdded){
		int pawnValue = p.getResourceSet().getPawnValue(pt);
		
		//Checking if user has to add 2 servants to increase value by one. Odd #s are rounded down.
		if(p.getResourceSet().getPermanentEffects().isAddingServantsPenalty())return pawnValue  + (int) ((double)servantsAdded * 0.5);
		else return pawnValue + servantsAdded;
	}
	
	private ConsumableSet servantsToConsumableSet(int servantsAdded){
		ConsumableSet cs = new ConsumableSet();
		cs.setServants(servantsAdded);
		return cs;
	}
	
	
	
}
