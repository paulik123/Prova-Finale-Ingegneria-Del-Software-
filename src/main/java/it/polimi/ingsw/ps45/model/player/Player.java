package it.polimi.ingsw.ps45.model.player;

import it.polimi.ingsw.ps45.model.actions.ActionBuilder;

public class Player {
	private ResourceSet resourceSet;
	private ActionBuilder actionBuilder;
	
	
	
	public ActionBuilder getActionBuilder() {
		return actionBuilder;
	}



	public ResourceSet getResourceSet(){
		return resourceSet;
	}
	
	
}
