package it.polimi.ingsw.ps45.model.actions;

import it.polimi.ingsw.ps45.model.actions.CouncilPrivilege.CouncilPrivilege;
import it.polimi.ingsw.ps45.model.actions.state.*;
import it.polimi.ingsw.ps45.model.area.Area;
import it.polimi.ingsw.ps45.model.area.Board;
import it.polimi.ingsw.ps45.model.area.NoCardArea;
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
	
	public void placePawnProduction(NoCardArea area, PawnType pt, int servantsAdded) throws Exception{
		if(!state.placePawnAction()) throw new Exception("Action not allowed - state is false");
		
		Pawn pawn = p.getResourceSet().getPawn(pt);
		int pawnValue = calculatePawnValue(pt, servantsAdded);
		ConsumableSet servantsSet = servantsToConsumableSet(servantsAdded);
		
		if(canPlacePawnProductionRun(pawn, pawnValue + p.getResourceSet().getActionValueModifier().getProduction(), area, servantsSet)){
			makePlayerPay(servantsSet, pawn);
			new PlacePawnProductionAction(p, area, pt, pawnValue).run();
		}else throw new Exception("Action not allowed - pawn/consumables unavailable");
	}
	
	public void placePawnHarvest(NoCardArea area, PawnType pt, int servantsAdded) throws Exception{
		if(!state.placePawnAction()) throw new Exception("Action not allowed - state is false");
		
		Pawn pawn = p.getResourceSet().getPawn(pt);
		int pawnValue = calculatePawnValue(pt, servantsAdded);
		ConsumableSet servantsSet = servantsToConsumableSet(servantsAdded);
		
		if(canPlacePawnHarvestRun(pawn, pawnValue + p.getResourceSet().getActionValueModifier().getHarvest(), area, servantsSet)){
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
	
	public void placePawnMarket(NoCardArea area, PawnType pt, int servantsAdded) throws Exception{
		if(!state.placePawnAction()) throw new Exception("Action not allowed - state is false");
		
		Pawn pawn = p.getResourceSet().getPawn(pt);
		int pawnValue = calculatePawnValue(pt, servantsAdded);
		ConsumableSet servantsSet = servantsToConsumableSet(servantsAdded);
		
		if(canPlacePawnMarketRun(pawn, pawnValue, area, servantsSet)){
			makePlayerPay(servantsSet, pawn);
			new PlacePawnNoCardAction(p, area, pt, pawnValue).run();
		}else throw new Exception("Action not allowed - pawn/consumables unavailable");
	}
	
	public void placePawnTerritory(TerritoryCardArea area, PawnType pt, int servantsAdded) throws Exception{
		if(!state.placePawnAction()) throw new Exception("Action not allowed - state is false");
		Pawn pawn = p.getResourceSet().getPawn(pt);
		int pawnValue = calculatePawnValue(pt, servantsAdded) + p.getResourceSet().getActionValueModifier().getTerritoryAction();
		ConsumableSet servantsSet = servantsToConsumableSet(servantsAdded);
		servantsSet.makeDiscount(p.getResourceSet().getTerritoryActionDiscount());
		
		if(canPlacePawnTerritoryRun(pawn, pawnValue, area, servantsSet)){
			makePlayerPay(servantsSet, pawn);
			new PlacePawnTerritoryAction(p, area, pt, pawnValue).run();
		}else throw new Exception("Action not allowed - pawn/consumables unavailable");

	}
	
	public void placePawnCharacter(CharacterCardArea area, PawnType pt, int servantsAdded) throws Exception{
		if(!state.placePawnAction()) throw new Exception("Action not allowed - state is false");
		Pawn pawn = p.getResourceSet().getPawn(pt);
		int pawnValue = calculatePawnValue(pt, servantsAdded) + p.getResourceSet().getActionValueModifier().getCharacterAction();
		ConsumableSet cost = area.getCharacter().cost();
		cost.collect(servantsToConsumableSet(servantsAdded));
		cost.makeDiscount(p.getResourceSet().getCharacterActionDiscount());
		
		if(canPlacePawnCharacterRun(pawn, pawnValue, area, cost)){
			makePlayerPay(cost, pawn);
			new PlacePawnCharacterAction(p, area, pt, pawnValue).run();
		}else throw new Exception("Action not allowed - pawn/consumables unavailable");

	}
	
	public void placePawnBuilding(BuildingCardArea area, PawnType pt, int servantsAdded) throws Exception{
		if(!state.placePawnAction()) throw new Exception("Action not allowed - state is false");
		Pawn pawn = p.getResourceSet().getPawn(pt);
		int pawnValue = calculatePawnValue(pt, servantsAdded) + p.getResourceSet().getActionValueModifier().getBuildingAction();
		ConsumableSet cost = area.getBuilding().cost();
		cost.collect(servantsToConsumableSet(servantsAdded));
		cost.makeDiscount(p.getResourceSet().getBuildingActionDiscount());
		
		if(canPlacePawnBuildingRun(pawn, pawnValue, area, cost)){
			makePlayerPay(cost, pawn);
			new PlacePawnBuildingAction(p, area, pt, pawnValue).run();
		}else throw new Exception("Action not allowed - pawn/consumables unavailable");

	}
	
	public void placePawnVenture(VentureCardArea area, PawnType pt, int servantsAdded, VentureMode mode) throws Exception{
		if(!state.placePawnAction()) throw new Exception("Action not allowed - state is false");
		Pawn pawn = p.getResourceSet().getPawn(pt);
		int pawnValue = calculatePawnValue(pt, servantsAdded) + p.getResourceSet().getActionValueModifier().getVentureAction();
		
		ConsumableSet cost;
		if(mode == VentureMode.FIRST) cost = area.getVenture().costI();
		else cost = area.getVenture().costII();
		
		cost.collect(servantsToConsumableSet(servantsAdded));
		cost.makeDiscount(p.getResourceSet().getVentureActionDiscount());
		
		if(canPlacePawnVentureRun(pawn, pawnValue, area, cost)){
			makePlayerPay(cost, pawn);
			new PlacePawnVentureAction(p, area, pt, pawnValue).run();
		}else throw new Exception("Action not allowed - pawn/consumables unavailable");

	}
	
	public void harvest() throws Exception{
		if(!state.harvestAction()) throw new Exception("Action not allowed - state is false");
		new HarvestAction(p, state.actionValue() + + p.getResourceSet().getActionValueModifier().getHarvest()).run();
	}
	
	public void production(ProductionMode[] pm) throws Exception{
		if(!state.productionAction()) throw new Exception("Action not allowed - state is false");
		new ProductionAction(p, pm, state.actionValue() + p.getResourceSet().getActionValueModifier().getProduction()).run();
	}
	
	public void addServantsToHarvest(int servantsAdded) throws Exception{
		if(!state.addServantsToHarvestAction()) throw new Exception("Action not allowed - state is false");
		int value = calculateValue(state.actionValue(), servantsAdded);
		ConsumableSet servantsSet = servantsToConsumableSet(servantsAdded);
		
		if(p.getResourceSet().hasConsumables(servantsSet)){
			makePlayerPay(servantsSet);
			this.setState(new HarvestState(value));
		}
	}
	
	public void addServantsToProduction(int servantsAdded) throws Exception{
		if(!state.addServantsToHarvestAction()) throw new Exception("Action not allowed - state is false");
		int value = calculateValue(state.actionValue(), servantsAdded);
		ConsumableSet servantsSet = servantsToConsumableSet(servantsAdded);
		
		if(p.getResourceSet().hasConsumables(servantsSet)){
			makePlayerPay(servantsSet);
			this.setState(new ProductionState(value));
		}
	}
	
	public void NoPawnTerritory(TerritoryCardArea area, int servantsAdded) throws Exception{
		if(!state.takeTerritoryAction()) throw new Exception("Action not allowed - state is false");
		int value = calculateValue(state.actionValue(), servantsAdded) + p.getResourceSet().getActionValueModifier().getTerritoryAction();
		ConsumableSet servantsSet = servantsToConsumableSet(servantsAdded);
		servantsSet.makeDiscount(p.getResourceSet().getTerritoryActionDiscount());
		
		if(canNoPawnTerritoryRun(value, area, servantsSet)){
			makePlayerPay(servantsSet);
			new NoPawnTerritoryAction(p, area).run();
		}else throw new Exception("Action not allowed - pawn/consumables unavailable");
	}
	
	public void NoPawnCharacter(CharacterCardArea area, int servantsAdded) throws Exception{
		if(!state.takeCharacterAction()) throw new Exception("Action not allowed - state is false");
		int value = calculateValue(state.actionValue(), servantsAdded) + p.getResourceSet().getActionValueModifier().getCharacterAction();
		ConsumableSet cost = area.getCharacter().cost();
		cost.collect(servantsToConsumableSet(servantsAdded));
		cost.makeDiscount(p.getResourceSet().getCharacterActionDiscount());
		
		if(canNoPawnCharacterRun(value, area, cost)){
			makePlayerPay(cost);
			new NoPawnCharacterAction(p, area).run();
		}else throw new Exception("Action not allowed - pawn/consumables unavailable");
	}
	
	public void NoPawnBuilding(BuildingCardArea area, int servantsAdded) throws Exception{
		if(!state.takeBuildingAction()) throw new Exception("Action not allowed - state is false");
		int value = calculateValue(state.actionValue(), servantsAdded) + p.getResourceSet().getActionValueModifier().getBuildingAction();
		ConsumableSet cost = area.getBuilding().cost();
		cost.collect(servantsToConsumableSet(servantsAdded));
		cost.makeDiscount(p.getResourceSet().getBuildingActionDiscount());
		
		if(canNoPawnBuildingRun(value, area, cost)){
			makePlayerPay(cost);
			new NoPawnBuildingAction(p, area).run();
		}else throw new Exception("Action not allowed - pawn/consumables unavailable");
	}
	
	public void NoPawnVenture(VentureCardArea area, int servantsAdded, VentureMode mode) throws Exception{
		if(!state.takeVentureAction()) throw new Exception("Action not allowed - state is false");
		int value = calculateValue(state.actionValue(), servantsAdded) + p.getResourceSet().getActionValueModifier().getVentureAction();
		
		ConsumableSet cost;
		if(mode == VentureMode.FIRST) cost = area.getVenture().costI();
		else cost = area.getVenture().costII();
		
		cost.collect(servantsToConsumableSet(servantsAdded));
		cost.makeDiscount(p.getResourceSet().getVentureActionDiscount());
		
		if(canNoPawnVentureRun(value, area, cost)){
			makePlayerPay(cost);
			new NoPawnVentureAction(p, area).run();
		}else throw new Exception("Action not allowed - pawn/consumables unavailable");
	}
	
	public void exchangeCouncilPrivilegeOne(CouncilPrivilege cp1) throws Exception{
		if(!state.coucilPrivilegeActionOne())throw new Exception("Action not allowed - state is false");
		new CouncilPrivilegeOneAction(p, cp1).run();
		setState(new NoActionState());
	}
	
	public void exchangeCouncilPrivilegeTwo(CouncilPrivilege cp1, CouncilPrivilege cp2) throws Exception{
		if(!state.coucilPrivilegeActionTwo())throw new Exception("Action not allowed - state is false");
		if(CouncilPrivilege.areDifferent(cp1, cp2)) new CouncilPrivilegeTwoAction(p, cp1, cp2).run();
		else throw new Exception("Council privileges are not different");
		setState(new NoActionState());
	}
	
	public void exchangeCouncilPrivilegeThree(CouncilPrivilege cp1, CouncilPrivilege cp2, CouncilPrivilege cp3) throws Exception{
		if(!state.coucilPrivilegeActionThree())throw new Exception("Action not allowed - state is false");
		
		if(CouncilPrivilege.areDifferent(cp1, cp2, cp3)) new CouncilPrivilegeThreeAction(p, cp1, cp2, cp3).run();
		else throw new Exception("Council privileges are not different");
		setState(new NoActionState());
	}
	
	public void acceptVatican() throws Exception{
		if(!state.vaticanChoice())throw new Exception("Action not allowed - state is false");
		new AcceptVaticanOffer(p).run();
		this.setState(new NoActionState());
	}
	
	public void refuseVatican() throws Exception{
		if(!state.vaticanChoice())throw new Exception("Action not allowed - state is false");
		new RefuseVaticanOffer(p, state.getExcommunicationCard()).run();
		this.setState(new NoActionState());
	}

	
	
	
	
	
	
	
	private boolean canNoPawnVentureRun(int value, VentureCardArea area, ConsumableSet cost){
		return !board.getVentureTower().isOccupiedByPlayer(p) &&
				p.getResourceSet().getVentureList().size() < 6 &&
				hasGeneralRequirementsNoPawn(value, area, cost);
	}
	
	private boolean canNoPawnBuildingRun(int value, BuildingCardArea area, ConsumableSet cost){
		return !board.getBuildingTower().isOccupiedByPlayer(p) &&
				p.getResourceSet().getBuildingList().size() < 6 &&
				hasGeneralRequirementsNoPawn(value, area, cost);
	}
	
	private boolean canNoPawnCharacterRun(int value, CharacterCardArea area, ConsumableSet cost){
		return !board.getCharacterTower().isOccupiedByPlayer(p) &&
				p.getResourceSet().getCharacterList().size() < 6 &&
				hasGeneralRequirementsNoPawn(value, area, cost);
	}
	
	private boolean canNoPawnTerritoryRun(int value, TerritoryCardArea area, ConsumableSet cost){
		return !board.getTerritoryTower().isOccupiedByPlayer(p) &&
				hasMilitaryForTerritory() &&
				p.getResourceSet().getTerritoryList().size() < 6 &&
				hasGeneralRequirementsNoPawn(value, area, cost);
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
	
	private boolean canPlacePawnMarketRun(Pawn pawn, int pawnValue, NoCardArea area, ConsumableSet cost){
		return hasGeneralRequirementsWithPawn(pawn, pawnValue, area, cost) &&
			   !p.getResourceSet().getPermanentEffects().isNoPawnOnMarketPenalty();
	}
	
	private boolean canPlacePawnProductionRun(Pawn pawn, int pawnValue, NoCardArea area, ConsumableSet cost){
		return !board.getProductionAreas().isOccupiedByPlayer(p) &&
				hasGeneralRequirementsWithPawn(pawn, pawnValue, area, cost);
	}
	
	private boolean canPlacePawnHarvestRun(Pawn pawn, int pawnValue, NoCardArea area, ConsumableSet cost){
		return !board.getHarvestAreas().isOccupiedByPlayer(p) &&
				hasGeneralRequirementsWithPawn(pawn, pawnValue, area, cost);
	}
	
	private boolean hasGeneralRequirementsWithPawn(Pawn pawn, int pawnValue, Area area, ConsumableSet cost){
		   return   area.isAvailable() &&
				   	pawn.isAvailable() &&
				   	pawnValue > area.getCost() && 
				   	p.getResourceSet().hasConsumables(cost);
	}
	
	private boolean hasGeneralRequirementsNoPawn(int value, Area area, ConsumableSet cost){
		   return   area.isAvailable() &&
				   	value > area.getCost() && 
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
	
	private void makePlayerPay(ConsumableSet cs){
		p.getResourceSet().getResources().pay(cs);
	}
	
	private int calculatePawnValue(PawnType pt, int servantsAdded){
		int pawnValue = p.getResourceSet().getPawnValue(pt);
		
		//Checking if user has to add 2 servants to increase value by one. Odd #s are rounded down.
		if(p.getResourceSet().getPermanentEffects().isAddingServantsPenalty())return pawnValue  + (int) ((double)servantsAdded * 0.5);
		else return pawnValue + servantsAdded;
	}
	
	private int calculateValue(int value, int servantsAdded){
		if(p.getResourceSet().getPermanentEffects().isAddingServantsPenalty())return value  + (int) ((double)servantsAdded * 0.5);
		else return value + servantsAdded;
	}
	
	private ConsumableSet servantsToConsumableSet(int servantsAdded){
		ConsumableSet cs = new ConsumableSet();
		cs.setServants(servantsAdded);
		return cs;
	}
	
	
	
}
