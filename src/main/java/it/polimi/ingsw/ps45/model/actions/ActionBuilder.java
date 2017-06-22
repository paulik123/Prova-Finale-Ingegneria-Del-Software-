package it.polimi.ingsw.ps45.model.actions;


import it.polimi.ingsw.ps45.gson.GsonWithInterface;

import it.polimi.ingsw.ps45.model.actions.CouncilPrivilege.CouncilPrivilege;
import it.polimi.ingsw.ps45.model.actions.state.ActionBuilderState;
import it.polimi.ingsw.ps45.model.actions.state.HarvestState;
import it.polimi.ingsw.ps45.model.actions.state.NoActionState;
import it.polimi.ingsw.ps45.model.actions.state.ProductionState;
import it.polimi.ingsw.ps45.model.area.Area;
import it.polimi.ingsw.ps45.model.area.Board;
import it.polimi.ingsw.ps45.model.area.NoCardArea;
import it.polimi.ingsw.ps45.model.area.cardarea.BuildingCardArea;
import it.polimi.ingsw.ps45.model.area.cardarea.CharacterCardArea;
import it.polimi.ingsw.ps45.model.area.cardarea.TerritoryCardArea;
import it.polimi.ingsw.ps45.model.area.cardarea.VentureCardArea;
import it.polimi.ingsw.ps45.model.cards.VentureMode;
import it.polimi.ingsw.ps45.model.game.ErrorNotifier;
import it.polimi.ingsw.ps45.model.game.Notifier;
import it.polimi.ingsw.ps45.model.game.Observer;
import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.player.Pawn;
import it.polimi.ingsw.ps45.model.player.PawnType;
import it.polimi.ingsw.ps45.model.player.Player;

public class ActionBuilder {
	private ActionBuilderState state;
	private Board board;
	private Player p;
	Observer errorObserver;
	
	
	public ActionBuilder(Player p, Board board, Observer errorObserver){
		this.p = p;
		this.board = board;
		this.errorObserver = errorObserver;
		
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
		if(!state.placePawnAction()){
			notifyError("Action not allowed - state is false");
			throw new Exception("Action not allowed - state is false");
		}
		
		Pawn pawn = p.getResourceSet().getPawn(pt);
		int pawnValue = calculatePawnValue(pt, servantsAdded);
		ConsumableSet servantsSet = servantsToConsumableSet(servantsAdded);
		
		if(canPlacePawnProductionRun(pawn, pawnValue + p.getResourceSet().getActionValueModifier().getProduction(), area, servantsSet)){
			makePlayerPay(servantsSet, pawn);
			setState(new NoActionState());
			new PlacePawnProductionAction(p, area, pt, pawnValue).run();
		}else{ 
			notifyError("Action not allowed - pawn/consumables unavailable");
			throw new Exception("Action not allowed - pawn/consumables unavailable");
		}
	}
	
	public void placePawnHarvest(NoCardArea area, PawnType pt, int servantsAdded) throws Exception{
		if(!state.placePawnAction()){
			notifyError("Action not allowed - state is false");
			throw new Exception("Action not allowed - state is false");
		}
		
		Pawn pawn = p.getResourceSet().getPawn(pt);
		int pawnValue = calculatePawnValue(pt, servantsAdded);
		ConsumableSet servantsSet = servantsToConsumableSet(servantsAdded);
		
		if(canPlacePawnHarvestRun(pawn, pawnValue + p.getResourceSet().getActionValueModifier().getHarvest(), area, servantsSet)){
			makePlayerPay(servantsSet, pawn);
			setState(new NoActionState());
			new PlacePawnHarvestAction(p, area, pt, pawnValue).run();
		}else{
			notifyError("Action not allowed - pawn/consumables unavailable");
			throw new Exception("Action not allowed - pawn/consumables unavailable");
		}
	}

	public void placePawnNoCard(NoCardArea area, PawnType pt, int servantsAdded) throws Exception{
		if(!state.placePawnAction()){
			notifyError("Action not allowed - state is false");
			throw new Exception("Action not allowed - state is false");
		}
		
		Pawn pawn = p.getResourceSet().getPawn(pt);
		int pawnValue = calculatePawnValue(pt, servantsAdded);
		ConsumableSet servantsSet = servantsToConsumableSet(servantsAdded);
		
		if(canPlacePawnNoCardRun(pawn, pawnValue, area, servantsSet)){
			makePlayerPay(servantsSet, pawn);
			setState(new NoActionState());
			new PlacePawnNoCardAction(p, area, pt, pawnValue).run();
		}else{
			notifyError("Action not allowed - pawn/consumables unavailable");
			throw new Exception("Action not allowed - pawn/consumables unavailable");
		}
	}
	
	public void placePawnMarket(NoCardArea area, PawnType pt, int servantsAdded) throws Exception{
		if(!state.placePawnAction()){
			notifyError("Action not allowed - state is false");
			throw new Exception("Action not allowed - state is false");
		}
		
		Pawn pawn = p.getResourceSet().getPawn(pt);
		int pawnValue = calculatePawnValue(pt, servantsAdded);
		ConsumableSet servantsSet = servantsToConsumableSet(servantsAdded);
		
		if(canPlacePawnMarketRun(pawn, pawnValue, area, servantsSet)){
			makePlayerPay(servantsSet, pawn);
			setState(new NoActionState());
			new PlacePawnNoCardAction(p, area, pt, pawnValue).run();
		}else{
			notifyError("Action not allowed - pawn/consumables unavailable");
			throw new Exception("Action not allowed - pawn/consumables unavailable");
		}
	}
	
	public void placePawnTerritory(TerritoryCardArea area, PawnType pt, int servantsAdded) throws Exception{
		if(!state.placePawnAction()){
			notifyError("Action not allowed - state is false");
			throw new Exception("Action not allowed - state is false");
		}
		Pawn pawn = p.getResourceSet().getPawn(pt);
		int pawnValue = calculatePawnValue(pt, servantsAdded) + p.getResourceSet().getActionValueModifier().getTerritoryAction();
		ConsumableSet servantsSet = servantsToConsumableSet(servantsAdded);
		servantsSet.makeDiscount(p.getResourceSet().getTerritoryActionDiscount());
		if(board.getVentureTower().isOccupied() && !p.getResourceSet().getPermanentEffects().isNoThreeCoinsTowerPenalty()) servantsSet.collect(towerCoinsPenaltySet());
		
		if(canPlacePawnTerritoryRun(pawn, pawnValue, area, servantsSet)){
			makePlayerPay(servantsSet, pawn);
			setState(new NoActionState());
			new PlacePawnTerritoryAction(p, area, pt, pawnValue).run();
		}else{
			notifyError("Action not allowed - pawn/consumables unavailable");
			throw new Exception("Action not allowed - pawn/consumables unavailable");
		}

	}
	
	public void placePawnCharacter(CharacterCardArea area, PawnType pt, int servantsAdded) throws Exception{
		if(!state.placePawnAction()){
			notifyError("Action not allowed - state is false");
			throw new Exception("Action not allowed - state is false");
		}
		Pawn pawn = p.getResourceSet().getPawn(pt);
		int pawnValue = calculatePawnValue(pt, servantsAdded) + p.getResourceSet().getActionValueModifier().getCharacterAction();
		ConsumableSet cost = area.getCharacter().cost();
		cost.collect(servantsToConsumableSet(servantsAdded));
		cost.makeDiscount(p.getResourceSet().getCharacterActionDiscount());
		if(board.getVentureTower().isOccupied() && !p.getResourceSet().getPermanentEffects().isNoThreeCoinsTowerPenalty()) cost.collect(towerCoinsPenaltySet());
		
		if(canPlacePawnCharacterRun(pawn, pawnValue, area, cost)){
			makePlayerPay(cost, pawn);
			setState(new NoActionState());
			new PlacePawnCharacterAction(p, area, pt, pawnValue).run();
		}else{
			notifyError("Action not allowed - pawn/consumables unavailable");
			throw new Exception("Action not allowed - pawn/consumables unavailable");
		}

	}
	
	public void placePawnBuilding(BuildingCardArea area, PawnType pt, int servantsAdded) throws Exception{
		if(!state.placePawnAction()){
			notifyError("Action not allowed - state is false");
			throw new Exception("Action not allowed - state is false");
		}
		Pawn pawn = p.getResourceSet().getPawn(pt);
		int pawnValue = calculatePawnValue(pt, servantsAdded) + p.getResourceSet().getActionValueModifier().getBuildingAction();
		ConsumableSet cost = area.getBuilding().cost();
		
		cost.collect(servantsToConsumableSet(servantsAdded));
		cost.makeDiscount(p.getResourceSet().getBuildingActionDiscount());
		if(board.getVentureTower().isOccupied() && !p.getResourceSet().getPermanentEffects().isNoThreeCoinsTowerPenalty()) cost.collect(towerCoinsPenaltySet());
		
		if(canPlacePawnBuildingRun(pawn, pawnValue, area, cost)){
			makePlayerPay(cost, pawn);
			setState(new NoActionState());
			new PlacePawnBuildingAction(p, area, pt, pawnValue).run();
		}else{
			notifyError("Action not allowed - pawn/consumables unavailable");
			throw new Exception("Action not allowed - pawn/consumables unavailable");
		}

	}
	
	public void placePawnVenture(VentureCardArea area, PawnType pt, int servantsAdded, VentureMode mode) throws Exception{
		if(!state.placePawnAction()){
			notifyError("Action not allowed - state is false");
			throw new Exception("Action not allowed - state is false");
		}
		Pawn pawn = p.getResourceSet().getPawn(pt);
		int pawnValue = calculatePawnValue(pt, servantsAdded) + p.getResourceSet().getActionValueModifier().getVentureAction();
		
		ConsumableSet cost;
		if(mode == VentureMode.FIRST) cost = area.getVenture().costI();
		else cost = area.getVenture().costII();
		
		cost.collect(servantsToConsumableSet(servantsAdded));
		cost.makeDiscount(p.getResourceSet().getVentureActionDiscount());
		if(board.getVentureTower().isOccupied() && !p.getResourceSet().getPermanentEffects().isNoThreeCoinsTowerPenalty()) cost.collect(towerCoinsPenaltySet());
		
		if(canPlacePawnVentureRun(pawn, pawnValue, area, cost)){
			makePlayerPay(cost, pawn);
			setState(new NoActionState());
			new PlacePawnVentureAction(p, area, pt, pawnValue).run();
		}else{
			notifyError("Action not allowed - pawn/consumables unavailable");
			throw new Exception("Action not allowed - pawn/consumables unavailable");
		}

	}
	
	public void harvest() throws Exception{
		if(!state.harvestAction()){
			notifyError("Action not allowed - state is false");
			throw new Exception("Action not allowed - state is false");
		}
		setState(new NoActionState());
		new HarvestAction(p, state.actionValue() + + p.getResourceSet().getActionValueModifier().getHarvest()).run();
	}
	
	public void production(ProductionMode[] pm) throws Exception{
		if(!state.productionAction()){
			notifyError("Action not allowed - state is false");
			throw new Exception("Action not allowed - state is false");
		}
		setState(new NoActionState());
		new ProductionAction(p, pm, state.actionValue() + p.getResourceSet().getActionValueModifier().getProduction()).run();
	}
	
	public void addServantsToHarvest(int servantsAdded) throws Exception{
		if(!state.addServantsToHarvestAction()){
			notifyError("Action not allowed - state is false");
			throw new Exception("Action not allowed - state is false");
		}
		int value = calculateValue(state.actionValue(), servantsAdded);
		ConsumableSet servantsSet = servantsToConsumableSet(servantsAdded);
		
		if(p.getResourceSet().hasConsumables(servantsSet)){
			makePlayerPay(servantsSet);
			this.setState(new HarvestState(value));
		}
	}
	
	public void addServantsToProduction(int servantsAdded) throws Exception{
		if(!state.addServantsToHarvestAction()){
			notifyError("Action not allowed - state is false");
			throw new Exception("Action not allowed - state is false");
		}
		int value = calculateValue(state.actionValue(), servantsAdded);
		ConsumableSet servantsSet = servantsToConsumableSet(servantsAdded);
		
		if(p.getResourceSet().hasConsumables(servantsSet)){
			makePlayerPay(servantsSet);
			this.setState(new ProductionState(value));
		}
	}
	
	public void NoPawnTerritory(TerritoryCardArea area, int servantsAdded) throws Exception{
		if(!state.takeTerritoryAction()){
			notifyError("Action not allowed - state is false");
			throw new Exception("Action not allowed - state is false");
		}
		int value = calculateValue(state.actionValue(), servantsAdded) + p.getResourceSet().getActionValueModifier().getTerritoryAction();
		ConsumableSet servantsSet = servantsToConsumableSet(servantsAdded);
		servantsSet.makeDiscount(p.getResourceSet().getTerritoryActionDiscount());
		if(board.getVentureTower().isOccupied() && !p.getResourceSet().getPermanentEffects().isNoThreeCoinsTowerPenalty()) servantsSet.collect(towerCoinsPenaltySet());
		
		if(canNoPawnTerritoryRun(value, area, servantsSet)){
			makePlayerPay(servantsSet);
			setState(new NoActionState());
			new NoPawnTerritoryAction(p, area).run();
		}else{
			notifyError("Action not allowed - pawn/consumables unavailable");
			throw new Exception("Action not allowed - pawn/consumables unavailable");
		}
	}
	
	public void NoPawnCharacter(CharacterCardArea area, int servantsAdded) throws Exception{
		if(!state.takeCharacterAction()){
			notifyError("Action not allowed - state is false");
			throw new Exception("Action not allowed - state is false");
		}
		int value = calculateValue(state.actionValue(), servantsAdded) + p.getResourceSet().getActionValueModifier().getCharacterAction();
		ConsumableSet cost = area.getCharacter().cost();
		cost.collect(servantsToConsumableSet(servantsAdded));
		cost.makeDiscount(p.getResourceSet().getCharacterActionDiscount());
		if(board.getVentureTower().isOccupied() && !p.getResourceSet().getPermanentEffects().isNoThreeCoinsTowerPenalty()) cost.collect(towerCoinsPenaltySet());
		
		if(canNoPawnCharacterRun(value, area, cost)){
			makePlayerPay(cost);
			setState(new NoActionState());
			new NoPawnCharacterAction(p, area).run();
		}else{
			notifyError("Action not allowed - pawn/consumables unavailable");
			throw new Exception("Action not allowed - pawn/consumables unavailable");
		}
	}
	
	public void NoPawnBuilding(BuildingCardArea area, int servantsAdded) throws Exception{
		if(!state.takeBuildingAction()){
			notifyError("Action not allowed - state is false");
			throw new Exception("Action not allowed - state is false");
		}
		int value = calculateValue(state.actionValue(), servantsAdded) + p.getResourceSet().getActionValueModifier().getBuildingAction();
		ConsumableSet cost = area.getBuilding().cost();
		cost.collect(servantsToConsumableSet(servantsAdded));
		cost.makeDiscount(p.getResourceSet().getBuildingActionDiscount());
		if(board.getVentureTower().isOccupied() && !p.getResourceSet().getPermanentEffects().isNoThreeCoinsTowerPenalty()) cost.collect(towerCoinsPenaltySet());
		
		if(canNoPawnBuildingRun(value, area, cost)){
			makePlayerPay(cost);
			setState(new NoActionState());
			new NoPawnBuildingAction(p, area).run();
		}else{
			notifyError("Action not allowed - pawn/consumables unavailable");
			throw new Exception("Action not allowed - pawn/consumables unavailable");
		}
	}
	
	public void NoPawnVenture(VentureCardArea area, int servantsAdded, VentureMode mode) throws Exception{
		if(!state.takeVentureAction()){
			notifyError("Action not allowed - state is false");
			throw new Exception("Action not allowed - state is false");
		}
		int value = calculateValue(state.actionValue(), servantsAdded) + p.getResourceSet().getActionValueModifier().getVentureAction();
		
		ConsumableSet cost;
		if(mode == VentureMode.FIRST) cost = area.getVenture().costI();
		else cost = area.getVenture().costII();
		
		cost.collect(servantsToConsumableSet(servantsAdded));
		cost.makeDiscount(p.getResourceSet().getVentureActionDiscount());
		if(board.getVentureTower().isOccupied() && !p.getResourceSet().getPermanentEffects().isNoThreeCoinsTowerPenalty()) cost.collect(towerCoinsPenaltySet());
		
		if(canNoPawnVentureRun(value, area, cost)){
			makePlayerPay(cost);
			setState(new NoActionState());
			new NoPawnVentureAction(p, area).run();
		}else{
			notifyError("Action not allowed - pawn/consumables unavailable");
			throw new Exception("Action not allowed - pawn/consumables unavailable");
		}
	}
	
	public void exchangeCouncilPrivilegeOne(CouncilPrivilege cp1) throws Exception{
		if(!state.coucilPrivilegeActionOne()){
			notifyError("Action not allowed - state is false");
			throw new Exception("Action not allowed - state is false");
		}
		setState(new NoActionState());
		new CouncilPrivilegeOneAction(p, cp1).run();
		
	}
	
	public void exchangeCouncilPrivilegeTwo(CouncilPrivilege cp1, CouncilPrivilege cp2) throws Exception{
		if(!state.coucilPrivilegeActionTwo()){
			notifyError("Action not allowed - state is false");
			throw new Exception("Action not allowed - state is false");
		}
		if(CouncilPrivilege.areDifferent(cp1, cp2)) new CouncilPrivilegeTwoAction(p, cp1, cp2).run();
		else{
			notifyError("Council privileges are not different");
			throw new Exception("Council privileges are not different");
		}
		setState(new NoActionState());
	}
	
	public void exchangeCouncilPrivilegeThree(CouncilPrivilege cp1, CouncilPrivilege cp2, CouncilPrivilege cp3) throws Exception{
		if(!state.coucilPrivilegeActionThree()){
			notifyError("Action not allowed - state is false");
			throw new Exception("Action not allowed - state is false");
		}
		
		if(CouncilPrivilege.areDifferent(cp1, cp2, cp3)) new CouncilPrivilegeThreeAction(p, cp1, cp2, cp3).run();
		else{
			notifyError("Council privileges are not different");
			throw new Exception("Council privileges are not different");
		}
		setState(new NoActionState());
	}
	
	public void acceptVatican() throws Exception{
		if(!state.vaticanChoice()){
			notifyError("Action not allowed - state is false");
			throw new Exception("Action not allowed - state is false");
		}
		new AcceptVaticanOffer(p).run();
		this.setState(new NoActionState());
	}
	
	public void refuseVatican() throws Exception{
		if(!state.vaticanChoice()){
			notifyError("Action not allowed - state is false");
			throw new Exception("Action not allowed - state is false");
		}
		new RefuseVaticanOffer(p, state.getExcommunicationCard()).run();
		this.setState(new NoActionState());
	}
	
	public void activateLeaderCard(int index) throws Exception{
		if(index >= p.getResourceSet().getLeaderCardList().size() || index < 0) {
			notifyError("Action not allowed - leader card with that index does not exist");
			throw new Exception("Action not allowed - leader card with that index does not exist");
		}
		new ActivateLeaderCardAction(p, index).run();
	}
	
	public void useLeaderCard(int index) throws Exception{
		if(index >= p.getResourceSet().getActivatedLeaderCardList().size() || index < 0 || p.getResourceSet().getActivatedLeaderCardList().get(index).usedEffectThisRound()) {
			notifyError("Action not allowed - leader card with that index does not exist or has been used this round");
			throw new Exception("Action not allowed - leader card with that index does not exist or has been used this round");
		}
		new UseLeaderCardAction(p, index).run();
	}
	
	

	
	
	
	
	
	
	
	private boolean canNoPawnVentureRun(int value, VentureCardArea area, ConsumableSet cost){
		return !board.getVentureTower().isOccupiedByPlayer(p) &&
				area.getVenture() != null &&
				p.getResourceSet().getVentureList().size() < 6 &&
				hasGeneralRequirementsNoPawn(value, area, cost);
	}
	
	private boolean canNoPawnBuildingRun(int value, BuildingCardArea area, ConsumableSet cost){
		return !board.getBuildingTower().isOccupiedByPlayer(p) &&
				area.getBuilding() != null &&
				p.getResourceSet().getBuildingList().size() < 6 &&
				hasGeneralRequirementsNoPawn(value, area, cost);
	}
	
	private boolean canNoPawnCharacterRun(int value, CharacterCardArea area, ConsumableSet cost){
		return !board.getCharacterTower().isOccupiedByPlayer(p) &&
				area.getCharacter() != null &&
				p.getResourceSet().getCharacterList().size() < 6 &&
				hasGeneralRequirementsNoPawn(value, area, cost);
	}
	
	private boolean canNoPawnTerritoryRun(int value, TerritoryCardArea area, ConsumableSet cost){
		return !board.getTerritoryTower().isOccupiedByPlayer(p) &&
				area.getTerritory() != null &&
				hasMilitaryForTerritory() &&
				p.getResourceSet().getTerritoryList().size() < 6 &&
				hasGeneralRequirementsNoPawn(value, area, cost);
	}
	
	private boolean canPlacePawnVentureRun(Pawn pawn, int pawnValue, VentureCardArea area, ConsumableSet cost){
		return !board.getVentureTower().isOccupiedByPlayer(p) &&
				area.getVenture() != null &&
				p.getResourceSet().getVentureList().size() < 6 &&
				hasGeneralRequirementsWithPawn(pawn, pawnValue, area, cost);
	}
	
	
	private boolean canPlacePawnBuildingRun(Pawn pawn, int pawnValue, BuildingCardArea area, ConsumableSet cost){
		return !board.getBuildingTower().isOccupiedByPlayer(p) &&
				area.getBuilding() != null &&
				p.getResourceSet().getBuildingList().size() < 6 &&
				hasGeneralRequirementsWithPawn(pawn, pawnValue, area, cost);
	}
	
	private boolean canPlacePawnCharacterRun(Pawn pawn, int pawnValue, CharacterCardArea area, ConsumableSet cost){
		return !board.getCharacterTower().isOccupiedByPlayer(p) &&
				area.getCharacter() != null &&
				p.getResourceSet().getCharacterList().size() < 6 &&
				hasGeneralRequirementsWithPawn(pawn, pawnValue, area, cost);
	}
	
	private boolean canPlacePawnTerritoryRun(Pawn pawn, int pawnValue, TerritoryCardArea area, ConsumableSet cost){
		return !board.getTerritoryTower().isOccupiedByPlayer(p) &&
				area.getTerritory() != null &&
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
		   return   (area.isAvailable() || p.getResourceSet().getPermanentEffects().isCanPlacePawnOnOccupiedAreas()) &&
				   	pawn.isAvailable() &&
				   	pawnValue >= area.getCost() && 
				   	p.getResourceSet().hasConsumables(cost);
	}
	
	private boolean hasGeneralRequirementsNoPawn(int value, Area area, ConsumableSet cost){
		   return   (area.isAvailable() || p.getResourceSet().getPermanentEffects().isCanPlacePawnOnOccupiedAreas()) &&
				   	value >= area.getCost() && 
				   	p.getResourceSet().hasConsumables(cost);
	}
	
	private boolean hasMilitaryForTerritory(){
		if(p.getResourceSet().getPermanentEffects().isNoTerritoryMilitaryPointsRequirements()) return true;
		
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
	
	private ConsumableSet towerCoinsPenaltySet(){
		ConsumableSet cs = new ConsumableSet();
		cs.setCoins(3);
		return cs;
	}
	
	private ConsumableSet coinsDiscountConsumableSet(){
		ConsumableSet cs = new ConsumableSet();
		cs.setCoins(3);
		return cs;
	}
	
	private void notifyError(String error){
        StringBuilder sb = new StringBuilder();
        sb.append("ERROR: ");
        sb.append(error);

		ErrorNotifier n = new ErrorNotifier(errorObserver, sb.toString());
		n.start();
	}
	
	
	
}
