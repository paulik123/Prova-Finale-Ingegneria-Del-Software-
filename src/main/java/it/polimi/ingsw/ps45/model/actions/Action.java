package it.polimi.ingsw.ps45.model.actions;

import it.polimi.ingsw.ps45.model.player.RequirementsSet;

public abstract class Action {
	abstract public void run();

	private boolean hasParametersSet;
	public boolean isSet() {
		return hasParametersSet;
	}
	public void setHasParameters(boolean hasParametersSet) {
		this.hasParametersSet = hasParametersSet;
	}
	
	
}
