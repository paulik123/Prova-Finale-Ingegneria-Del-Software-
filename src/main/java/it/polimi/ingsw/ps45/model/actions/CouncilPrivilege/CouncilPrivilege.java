package it.polimi.ingsw.ps45.model.actions.CouncilPrivilege;

import it.polimi.ingsw.ps45.model.player.ConsumableSet;
/**
 * Abstract class that contains all the logic regarding CouncilPrivileges
 */
public abstract class CouncilPrivilege {
	
	private String type;
	
	/**
	 * @return      a string that represents the type of the councilPrivilege.
	 */

	public String getType() {
		return type;
	}

	/**
	 * @param  type a string representing the type of the instantiated councilPrivilege
	 */
	public void setType(String type) {
		this.type = type;
	}

	public abstract ConsumableSet getConsumableSet();
	
	/**
	 * Static method that compares two councilPrivileges
	 * @param  cp1  the first CouncilPrivilege to be compared
	 * @param  cp2 the second CouncilPrivilege to be compared
	 * @return      true if they are different
	 */
	public static boolean areDifferent(CouncilPrivilege cp1, CouncilPrivilege cp2){
		return !cp1.equals(cp2);
	}
	
	/**
	 * Static method that compares two councilPrivileges
	 * @param  cp1  the first CouncilPrivilege to be compared
	 * @param  cp2 the second CouncilPrivilege to be compared
	 * @param  cp3 the third CouncilPrivilege to be compared
	 * @return      true if they are different
	 */
	public static boolean areDifferent(CouncilPrivilege cp1, CouncilPrivilege cp2, CouncilPrivilege cp3){
		return !cp1.equals(cp2) && !cp1.equals(cp3) && !cp2.equals(cp3);
	}
	
	/**
	 * Redefined equals for CouncilPrivileges
	 * @param  cp  the CouncilPrivilege to check the equality with.
	 * @return      true if the type of the CouncilPrivilege in the params is the same.
	 */
	public boolean equals(CouncilPrivilege cp){
		return this.getType().equals(cp.getType());
	}
}
