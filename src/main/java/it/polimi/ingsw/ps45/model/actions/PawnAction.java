package it.polimi.ingsw.ps45.model.actions;

import it.polimi.ingsw.ps45.model.player.RequirementsSet;

public abstract class PawnAction {
		public RequirementsSet getCost(){
			return cost;
		}
		protected RequirementsSet cost;
		abstract protected void calculateCost();
		
}
