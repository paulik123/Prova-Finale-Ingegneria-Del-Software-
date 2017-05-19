package it.polimi.ingsw.ps45.model.actions;

import it.polimi.ingsw.ps45.model.actions.state.*;
import it.polimi.ingsw.ps45.model.area.Board;
import it.polimi.ingsw.ps45.model.area.HarvestArea;
import it.polimi.ingsw.ps45.model.area.NoCardArea;
import it.polimi.ingsw.ps45.model.area.ProductionArea;
import it.polimi.ingsw.ps45.model.area.cardarea.TerritoryCardArea;
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
			area.immediateEffect(p, pawnValue);
		}else throw new Exception("Action not allowed - pawn/consumables unavailable");
	}
	
	public void placePawnHarvest(HarvestArea area, PawnType pt, int servantsAdded) throws Exception{
		if(!state.placePawnAction()) throw new Exception("Action not allowed - state is false");
		
		Pawn pawn = p.getResourceSet().getPawn(pt);
		int pawnValue = calculatePawnValue(pt, servantsAdded);
		ConsumableSet servantsSet = servantsToConsumableSet(servantsAdded);
		
		if(canPlacePawnHarvestRun(pawn, pawnValue, area, servantsSet)){
			makePlayerPay(servantsSet, pawn);
			area.immediateEffect(p, pawnValue);
		}else throw new Exception("Action not allowed - pawn/consumables unavailable");
	}
	
	
	
	public void placePawnTerritory(TerritoryCardArea area, PawnType pt, int servantsAdded) throws Exception{
		if(!state.placePawnAction()) throw new Exception("Action not allowed - state is false");
		Pawn pawn = p.getResourceSet().getPawn(pt);
		int pawnValue = calculatePawnValue(pt, servantsAdded);
		ConsumableSet servantsSet = servantsToConsumableSet(servantsAdded);

	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private boolean canPlacePawnProductionRun(Pawn pawn, int pawnValue, ProductionArea area, ConsumableSet cost){
		return !board.getProductionAreas().isOccupiedByPlayer(p) &&
			   area.isAvailable() &&
			   pawn.isAvailable() &&
			   pawnValue > area.getCost() && 
			   p.getResourceSet().hasConsumables(cost);
	}
	
	private boolean canPlacePawnHarvestRun(Pawn pawn, int pawnValue, HarvestArea area, ConsumableSet cost){
		return !board.getHarvestAreas().isOccupiedByPlayer(p) &&
			   area.isAvailable() &&
			   pawn.isAvailable() &&
			   pawnValue > area.getCost() && 
			   p.getResourceSet().hasConsumables(cost);
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
