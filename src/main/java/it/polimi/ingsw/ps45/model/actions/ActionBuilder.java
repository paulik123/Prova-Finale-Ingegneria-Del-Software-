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


/**
 * ActionBuilder verifies that all actions are permitted before actually running them. It uses a State desing pattern to see which
 * actions the player can do.
 */
public class ActionBuilder {
	private ActionBuilderState state;
	private Board board;
	private Player p;
	private Observer errorObserver;
	
	/**
 	 * Constructor
	 * @param  p the player-owner of the actionbuilder.
	 * @param  board the board of the game because some actions depend on the state of the board.
	 * @param  errorObserver an observer of the player which gets notified when the player commits an error.
	 */
	public ActionBuilder(Player p, Board board, Observer errorObserver){
		this.p = p;
		this.board = board;
		this.errorObserver = errorObserver;
		
		state = new NoActionState();
	}

	/**
	 * @return      the state of this actionbuilder
	 */
	public ActionBuilderState getState() {
		return state;
	}

	/**
	 * @param state      the state to which the actionbuilder will be set
	 */
	public void setState(ActionBuilderState state) {
		this.state = state;
	}

	/**
	 * @return      the board to which the actionBuilder checks the conditions
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * @param board      the board of the current game
	 */
	public void setBoard(Board board) {
		this.board = board;
	}
	
	/**
 	 * Verifies all the requirements and if everything is allright the action is executed
 	 * Also calculates the cost of the action and makes the player "pay".
 	 * @throws Exception If one of the requirements are not met.
 	 * 		   It also notifies the player's error observer with the error.
	 * @param  area the area in which the player wants to place the pawn in.
 	 * @param  pt the "color" of the pawn that the player wants to use.
 	 * @param  servantsAdded the number of servants the player adds to the action.
	 */
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
	
	/**
 	 * Verifies all the requirements and if everything is allright the action is executed
 	 * Also calculates the cost of the action and makes the player "pay".
 	 * @throws Exception If one of the requirements are not met.
 	 * 		   It also notifies the player's error observer with the error.
	 * @param  area the area in which the player wants to place the pawn in.
 	 * @param  pt the "color" of the pawn that the player wants to use.
 	 * @param  servantsAdded the number of servants the player adds to the action.
	 */
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

	/**
 	 * Verifies all the requirements and if everything is allright the action is executed
 	 * Also calculates the cost of the action and makes the player "pay".
 	 * @throws Exception If one of the requirements are not met.
 	 * 		   It also notifies the player's error observer with the error.
	 * @param  area the area in which the player wants to place the pawn in.
 	 * @param  pt the "color" of the pawn that the player wants to use.
 	 * @param  servantsAdded the number of servants the player adds to the action.
	 */
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
	
	/**
 	 * Verifies all the requirements and if everything is allright the action is executed
 	 * Also calculates the cost of the action and makes the player "pay".
 	 * @throws Exception If one of the requirements are not met.
 	 * 		   It also notifies the player's error observer with the error.
	 * @param  area the territory card area in which the player wants to place the pawn in.
 	 * @param  pt the "color" of the pawn that the player wants to use.
 	 * @param  servantsAdded the number of servants the player adds to the action.
	 */
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
	
	/**
 	 * Verifies all the requirements and if everything is allright the action is executed
 	 * Also calculates the cost of the action and makes the player "pay".
 	 * @throws Exception If one of the requirements are not met.
 	 * 		   It also notifies the player's error observer with the error.
	 * @param  area the character card area in which the player wants to place the pawn in.
 	 * @param  pt the "color" of the pawn that the player wants to use.
 	 * @param  servantsAdded the number of servants the player adds to the action.
	 */
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
	
	/**
 	 * Verifies all the requirements and if everything is allright the action is executed
 	 * Also calculates the cost of the action and makes the player "pay".
 	 * @throws Exception If one of the requirements are not met.
 	 * 		   It also notifies the player's error observer with the error.
	 * @param  area the building card area in which the player wants to place the pawn in.
 	 * @param  pt the "color" of the pawn that the player wants to use.
 	 * @param  servantsAdded the number of servants the player adds to the action.
	 */
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
	
	/**
 	 * Verifies all the requirements and if everything is allright the action is executed
 	 * Also calculates the cost of the action and makes the player "pay".
 	 * @throws Exception If one of the requirements are not met.
 	 * 		   It also notifies the player's error observer with the error.
	 * @param  area the venture card area in which the player wants to place the pawn in.
 	 * @param  pt the "color" of the pawn that the player wants to use.
 	 * @param  servantsAdded the number of servants the player adds to the action.
 	 * @param  mode the mode(first or second cost) of the venture card.
	 */
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
	
	/**
 	 * Verifies all the requirements and if everything is allright the harvest is executed
 	 * @throws Exception If one of the requirements are not met.
 	 * 		   It also notifies the player's error observer with the error.
	 */
	public void harvest() throws Exception{
		if(!state.harvestAction()){
			notifyError("Action not allowed - state is false");
			throw new Exception("Action not allowed - state is false");
		}
		setState(new NoActionState());
		new HarvestAction(p, state.actionValue() + + p.getResourceSet().getActionValueModifier().getHarvest()).run();
	}
	
	/**
 	 * Verifies all the requirements and if everything is allright the action is executed
 	 * @throws Exception If one of the requirements are not met.
 	 * 		   It Also notifies the player's error observer with the error.
	 * @param  pm An array with the modes of each building card in the players building list.
	 */
	public void production(ProductionMode[] pm) throws Exception{
		if(!state.productionAction()){
			notifyError("Action not allowed - state is false");
			throw new Exception("Action not allowed - state is false");
		}
		setState(new NoActionState());
		new ProductionAction(p, pm, state.actionValue() + p.getResourceSet().getActionValueModifier().getProduction()).run();
	}
	
	/**
 	 * Verifies all the requirements and if everything is allright the action is executed
 	 * Also calculates the cost of the action and makes the player "pay".
 	 * @throws Exception If one of the requirements are not met.
 	 * 		   It also notifies the player's error observer with the error.
	 * @param  servantsAdded the number of servants the player wants to add.
	 */
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
	
	/**
 	 * Verifies all the requirements and if everything is allright the action is executed
 	 * Also calculates the cost of the action and makes the player "pay".
 	 * @throws Exception If one of the requirements are not met.
 	 * 		   It also notifies the player's error observer with the error.
	 * @param  servantsAdded the number of servants the player wants to add.
	 */
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
	
	/**
 	 * Verifies all the requirements and if everything is allright the action is executed
 	 * Also calculates the cost of the action and makes the player "pay".
 	 * @throws Exception If one of the requirements are not met.
 	 * 		   It also notifies the player's error observer with the error.
	 * @param  area the territory card area in which the player wants to place the pawn in.
 	 * @param  servantsAdded the number of servants the player wants to add.
	 */
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
	
	/**
 	 * Verifies all the requirements and if everything is allright the action is executed
 	 * Also calculates the cost of the action and makes the player "pay".
 	 * @throws Exception If one of the requirements are not met.
 	 * 		   It also notifies the player's error observer with the error.
	 * @param  area the venture card area in which the player wants to place the pawn in.
 	 * @param  servantsAdded the number of servants the player wants to add.
	 */
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
	
	/**
 	 * Verifies all the requirements and if everything is allright the action is executed
 	 * Also calculates the cost of the action and makes the player "pay".
 	 * @throws Exception If one of the requirements are not met.
 	 * 		   It also notifies the player's error observer with the error.
	 * @param  area the venture card area in which the player wants to place the pawn in.
 	 * @param  servantsAdded the number of servants the player wants to add.
	 */
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
	
	/**
 	 * Verifies all the requirements and if everything is allright the action is executed
 	 * Also calculates the cost of the action and makes the player "pay".
 	 * @throws Exception If one of the requirements are not met.
 	 * 		   It also notifies the player's error observer with the error.
	 * @param  area the venture card area in which the player wants to place the pawn in.
 	 * @param  servantsAdded the number of servants the player wants to add.
 	 * @param  mode the mode(first or second cost) of the venture card.
	 */
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
	
	/**
 	 * Verifies all the requirements and if everything is allright the action is executed
 	 * @throws Exception If one of the requirements are not met.
 	 * 		   It also notifies the player's error observer with the error.
	 * @param  cp1 the type of council privilege that the player wants to exchange.
	 */
	public void exchangeCouncilPrivilegeOne(CouncilPrivilege cp1) throws Exception{
		if(!state.coucilPrivilegeActionOne()){
			notifyError("Action not allowed - state is false");
			throw new Exception("Action not allowed - state is false");
		}
		setState(new NoActionState());
		new CouncilPrivilegeOneAction(p, cp1).run();
		
	}
	
	/**
 	 * Verifies all the requirements and if everything is allright the action is executed
 	 * @throws Exception If one of the requirements are not met.
 	 * 		   It also notifies the player's error observer with the error.
	 * @param  cp1 the type of council privilege that the player wants to exchange.
	 * @param  cp2 the type of the second council privilege that the player wants to exchange.
	 */
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
	
	/**
 	 * Verifies all the requirements and if everything is allright the action is executed
 	 * @throws Exception If one of the requirements are not met.
 	 * 		   It also notifies the player's error observer with the error.
	 * @param  cp1 the type of council privilege that the player wants to exchange.
	 * @param  cp2 the type of the second council privilege that the player wants to exchange.
	 * @param  cp3 the type of the third council privilege that the player wants to exchange.
	 */
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
	
	/**
 	 * Verifies all the requirements and if everything is allright the action is executed
 	 * @throws Exception If one of the requirements are not met.
 	 * 		   It also notifies the player's error observer with the error.
	 */
	public void acceptVatican() throws Exception{
		if(!state.vaticanChoice()){
			notifyError("Action not allowed - state is false");
			throw new Exception("Action not allowed - state is false");
		}
		new AcceptVaticanOffer(p).run();
		this.setState(new NoActionState());
	}
	
	/**
 	 * Verifies all the requirements and if everything is allright the action is executed
 	 * @throws Exception If one of the requirements are not met.
 	 * 		   It also notifies the player's error observer with the error.
	 */
	public void refuseVatican() throws Exception{
		if(!state.vaticanChoice()){
			notifyError("Action not allowed - state is false");
			throw new Exception("Action not allowed - state is false");
		}
		new RefuseVaticanOffer(p, state.getExcommunicationCard()).run();
		this.setState(new NoActionState());
	}
	
	/**
 	 * Verifies all the requirements and if everything is allright the action is executed
 	 * @throws Exception If one of the requirements are not met.
 	 * 		   It also notifies the player's error observer with the error.
 	 * @param  index the index of the LeaderCard in the player's LeaderCard list.
	 */
	public void activateLeaderCard(int index) throws Exception{
		if(index >= p.getResourceSet().getLeaderCardList().size() || index < 0) {
			notifyError("Action not allowed - leader card with that index does not exist");
			throw new Exception("Action not allowed - leader card with that index does not exist");
		}
		if(!p.getResourceSet().getLeaderCardList().get(index).canActivate(p)){
			notifyError("You don't meet the requirements to activate the leader card");
			throw new Exception("You don't meet the requirements to activate the leader card");
		}
		new ActivateLeaderCardAction(p, index).run();
	}
	
	/**
 	 * Verifies all the requirements and if everything is allright the action is executed
 	 * @throws Exception If one of the requirements are not met.
 	 * 		   It also notifies the player's error observer with the error.
 	 * @param  index the index of the LeaderCard in the player's LeaderCard list.
	 */
	public void useLeaderCard(int index) throws Exception{
		if(index >= p.getResourceSet().getActivatedLeaderCardList().size() || index < 0 || p.getResourceSet().getActivatedLeaderCardList().get(index).usedEffectThisRound()) {
			notifyError("Action not allowed - leader card with that index does not exist or has been used this round");
			throw new Exception("Action not allowed - leader card with that index does not exist or has been used this round");
		}

		new UseLeaderCardAction(p, index).run();
	}
	
	

	
	
	
	
	
	
	/**
	 * @return      true if all necessary conditions for a NoPawnVentureAction are met.
	 */
	private boolean canNoPawnVentureRun(int value, VentureCardArea area, ConsumableSet cost){
		return !board.getVentureTower().isOccupiedByPlayer(p) &&
				area.getVenture() != null &&
				p.getResourceSet().getVentureList().size() < 6 &&
				hasGeneralRequirementsNoPawn(value, area, cost);
	}
	
	/**
	 * @return      true if all necessary conditions for a NoPawnBuildingAction are met.
	 */
	private boolean canNoPawnBuildingRun(int value, BuildingCardArea area, ConsumableSet cost){
		return !board.getBuildingTower().isOccupiedByPlayer(p) &&
				area.getBuilding() != null &&
				p.getResourceSet().getBuildingList().size() < 6 &&
				hasGeneralRequirementsNoPawn(value, area, cost);
	}
	
	/**
	 * @return      true if all necessary conditions for a NoPawnCharacterAction are met.
	 */
	private boolean canNoPawnCharacterRun(int value, CharacterCardArea area, ConsumableSet cost){
		return !board.getCharacterTower().isOccupiedByPlayer(p) &&
				area.getCharacter() != null &&
				p.getResourceSet().getCharacterList().size() < 6 &&
				hasGeneralRequirementsNoPawn(value, area, cost);
	}
	
	/**
	 * @return      true if all necessary conditions for a NoPawnTerritoryAction are met.
	 */
	private boolean canNoPawnTerritoryRun(int value, TerritoryCardArea area, ConsumableSet cost){
		return !board.getTerritoryTower().isOccupiedByPlayer(p) &&
				area.getTerritory() != null &&
				hasMilitaryForTerritory() &&
				p.getResourceSet().getTerritoryList().size() < 6 &&
				hasGeneralRequirementsNoPawn(value, area, cost);
	}
	
	/**
	 * @return      true if all necessary conditions for a PlacePawnVentureAction are met.
	 */
	private boolean canPlacePawnVentureRun(Pawn pawn, int pawnValue, VentureCardArea area, ConsumableSet cost){
		return !board.getVentureTower().isOccupiedByPlayer(p) &&
				area.getVenture() != null &&
				p.getResourceSet().getVentureList().size() < 6 &&
				hasGeneralRequirementsWithPawn(pawn, pawnValue, area, cost);
	}
	
	
	/**
	 * @return      true if all necessary conditions for a PlacePawnBuildingAction are met.
	 */
	private boolean canPlacePawnBuildingRun(Pawn pawn, int pawnValue, BuildingCardArea area, ConsumableSet cost){
		return !board.getBuildingTower().isOccupiedByPlayer(p) &&
				area.getBuilding() != null &&
				p.getResourceSet().getBuildingList().size() < 6 &&
				hasGeneralRequirementsWithPawn(pawn, pawnValue, area, cost);
	}
	
	/**
	 * @return      true if all necessary conditions for a PlacePawnCharacterAction are met.
	 */
	private boolean canPlacePawnCharacterRun(Pawn pawn, int pawnValue, CharacterCardArea area, ConsumableSet cost){
		return !board.getCharacterTower().isOccupiedByPlayer(p) &&
				area.getCharacter() != null &&
				p.getResourceSet().getCharacterList().size() < 6 &&
				hasGeneralRequirementsWithPawn(pawn, pawnValue, area, cost);
	}
	
	/**
	 * @return      true if all necessary conditions for a PlacePawnTerritoryAction are met.
	 */
	private boolean canPlacePawnTerritoryRun(Pawn pawn, int pawnValue, TerritoryCardArea area, ConsumableSet cost){
		return !board.getTerritoryTower().isOccupiedByPlayer(p) &&
				area.getTerritory() != null &&
				hasMilitaryForTerritory() &&
				p.getResourceSet().getTerritoryList().size() < 6 &&
				hasGeneralRequirementsWithPawn(pawn, pawnValue, area, cost);
	}
	
	/**
	 * @return      true if all necessary conditions for a PlacePawnNoCardAction are met.
	 */
	private boolean canPlacePawnNoCardRun(Pawn pawn, int pawnValue, NoCardArea area, ConsumableSet cost){
		
		if(!p.getResourceSet().getPermanentEffects().isNoPawnOnMarketPenalty()){
			return hasGeneralRequirementsWithPawn(pawn, pawnValue, area, cost);
		}else{
			return hasGeneralRequirementsWithPawn(pawn, pawnValue, area, cost) &&
					area.equals(board.getCouncilPalaceArea());
		}
		
	}
	
	
	/**
	 * @return      true if all necessary conditions for a PlacePawnProductionAction are met.
	 */
	private boolean canPlacePawnProductionRun(Pawn pawn, int pawnValue, NoCardArea area, ConsumableSet cost){
		return !board.getProductionAreas().isOccupiedByPlayer(p) &&
				hasGeneralRequirementsWithPawn(pawn, pawnValue, area, cost);
	}
	
	/**
	 * @return      true if all necessary conditions for a PlacePawnHarvestAction are met.
	 */
	private boolean canPlacePawnHarvestRun(Pawn pawn, int pawnValue, NoCardArea area, ConsumableSet cost){
		return !board.getHarvestAreas().isOccupiedByPlayer(p) &&
				hasGeneralRequirementsWithPawn(pawn, pawnValue, area, cost);
	}
	
	/**
	 * @return      true if all necessary conditions for a pawn action are met.
	 */
	private boolean hasGeneralRequirementsWithPawn(Pawn pawn, int pawnValue, Area area, ConsumableSet cost){
		   return   (area.isAvailable() || p.getResourceSet().getPermanentEffects().isCanPlacePawnOnOccupiedAreas()) &&
				   	pawn.isAvailable() &&
				   	pawnValue >= area.getCost() && 
				   	p.getResourceSet().hasConsumables(cost);
	}
	
	/**
	 * @return      true if all necessary conditions for a no pawn action are met.
	 */
	private boolean hasGeneralRequirementsNoPawn(int value, Area area, ConsumableSet cost){
		   return   (area.isAvailable() || p.getResourceSet().getPermanentEffects().isCanPlacePawnOnOccupiedAreas()) &&
				   	value >= area.getCost() && 
				   	p.getResourceSet().hasConsumables(cost);
	}
	
	/**
	 * @return      true if the player has enough military points to acquire another territory.
	 */
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
	
	/**
	 * Makes the player "pay" the consumables necessary for the action.
	 * @param  cs  a ConsumableSet with all the consumables in the cost.
	 * @param  pawn the pawn that will be used.
	 * 
	 */
	private void makePlayerPay(ConsumableSet cs, Pawn pawn){
		p.getResourceSet().pay(cs, pawn);
	}
	
	/**
	 * Makes the player "pay" the consumables necessary for the action.
	 * @param  cs  a ConsumableSet with all the consumables in the cost.
	 * 
	 */
	private void makePlayerPay(ConsumableSet cs){
		p.getResourceSet().getResources().pay(cs);
	}
	
	/**
	 * @param  pt  the type of pawn that will be used.
	 * @param  servantsAdded the number of servants the player wants to add.
	 * @return      the value of the pawn added to the value of the added pawns.
	 */
	private int calculatePawnValue(PawnType pt, int servantsAdded){
		int pawnValue = p.getResourceSet().getPawnValue(pt);
		
		//Checking if user has to add 2 servants to increase value by one. Odd #s are rounded down.
		if(p.getResourceSet().getPermanentEffects().isAddingServantsPenalty())return pawnValue  + (int) ((double)servantsAdded * 0.5);
		else return pawnValue + servantsAdded;
	}
	
	/**
	 * 
	 * @param  value  the value of the action that will be executed.
	 * @param  servantsAdded the number of servants the player wants to add.
	 * @return      the value of the action added to the value of the added pawns.
	 */
	private int calculateValue(int value, int servantsAdded){
		if(p.getResourceSet().getPermanentEffects().isAddingServantsPenalty())return value  + (int) ((double)servantsAdded * 0.5);
		else return value + servantsAdded;
	}
	
	/**
	 * Transforms a number of servants in a ConsumableSet that is easier to pay with.
	 * @param  servantsAdded the number of servants the player wants to add.
	 * @return      A ConsumableSet containing that number of servants.
	 */
	private ConsumableSet servantsToConsumableSet(int servantsAdded){
		ConsumableSet cs = new ConsumableSet();
		cs.setServants(servantsAdded);
		return cs;
	}
	
	/**
	 * Transforms a number of coins equal to the Tower penalty in a ConsumableSet that is easier to pay with.
	 * @return      A ConsumableSet containing that number of coins.
	 */
	private ConsumableSet towerCoinsPenaltySet(){
		ConsumableSet cs = new ConsumableSet();
		cs.setCoins(3);
		return cs;
	}
	
	
	/**
	 * Notifies the player when some requirements are not met.
	 * @param  value  A string with the message of the error.
	 */
	private void notifyError(String error){
        StringBuilder sb = new StringBuilder();
        sb.append("ERROR: ");
        sb.append(error);

		ErrorNotifier n = new ErrorNotifier(errorObserver, sb.toString());
		n.start();
	}
	
	/**
	 * Changes the errorObserver to a new one, for example if the player has reconnected a new errorObserver will be needed.
	 * @param  o  The new errorObserver.
	 */
	public void changeObserver(Observer o){
		this.errorObserver = o;
	}
	
	
	
}
