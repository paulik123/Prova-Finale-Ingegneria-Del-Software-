package it.polimi.ingsw.ps45.model.actions.CouncilPrivilege;

import it.polimi.ingsw.ps45.model.player.ConsumableSet;

public abstract class CouncilPrivilege {
	
	private String type;
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public abstract ConsumableSet getConsumableSet();
	
	public static boolean areDifferent(CouncilPrivilege cp1, CouncilPrivilege cp2){
		return !cp1.equals(cp2);
	}
	
	public static boolean areDifferent(CouncilPrivilege cp1, CouncilPrivilege cp2, CouncilPrivilege cp3){
		return !cp1.equals(cp2) && !cp1.equals(cp3) && !cp2.equals(cp3);
	}
	
	public boolean equals(CouncilPrivilege cp){
		return this.getType().equals(cp.getType());
	}
}
