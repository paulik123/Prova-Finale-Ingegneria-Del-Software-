package it.polimi.ingsw.ps45.model.player;

public class RequirementsSet {
	private ConsumableSet cs;
	private int pawnCost;
	
	public RequirementsSet(ConsumableSet cost, int pawnCost){
		this.cs = cost;
		this.pawnCost = pawnCost;
	}
	


	public ConsumableSet getConsumableSet() {
		return cs;
	}



	public void setCs(ConsumableSet cs) {
		this.cs = cs;
	}



	public int getPawnCost() {
		return pawnCost;
	}
	
	
	
	public void applyPenalty(int penalty){
		pawnCost = pawnCost + penalty;
	}
	
	
}
