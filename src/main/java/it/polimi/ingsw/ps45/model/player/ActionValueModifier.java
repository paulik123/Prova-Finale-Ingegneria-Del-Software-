package it.polimi.ingsw.ps45.model.player;

/**
 * Class that stores all the permanent penalties/bonuses of a player's actions values.
 */
public class ActionValueModifier {
	private int territoryAction;
	private int characterAction;
	private int buildingAction;
	private int ventureAction;
	private int harvest;
	private int production;
	
	
	/**
 	 * Constructor
 	 * Initializes everything to value 0 (no penalties/bonuses).
	 */
	public ActionValueModifier(){
		this.territoryAction = 0;
		this.characterAction = 0;
		this.buildingAction = 0;
		this.ventureAction = 0;
		this.harvest = 0;
		this. production = 0;
	}

	/**
	 * @return the modifier of all territory actions.
	 */
	public int getTerritoryAction() {
		return territoryAction;
	}

	/**
	 * Sets the value of bonuses/penalties of  territory actions.
	 * @param territoryAction the value to be set at.
	 */
	public void setTerritoryAction(int territoryAction) {
		this.territoryAction = territoryAction;
	}

	/**
	 * @return the modifier of all character actions.
	 */
	public int getCharacterAction() {
		return characterAction;
	}

	/**
	 * Sets the value of bonuses/penalties of character actions.
	 * @param characterAction the value to be set at.
	 */
	public void setCharacterAction(int characterAction) {
		this.characterAction = characterAction;
	}

	/**
	 * @return the modifier of all building actions.
	 */
	public int getBuildingAction() {
		return buildingAction;
	}

	/**
	 * Sets the value of bonuses/penalties of building actions.
	 * @param buildingAction the value to be set at.
	 */
	public void setBuildingAction(int buildingAction) {
		this.buildingAction = buildingAction;
	}

	/**
	 * @return the modifier of all venrure actions.
	 */
	public int getVentureAction() {
		return ventureAction;
	}

	/**
	 * Sets the value of bonuses/penalties of venture actions.
	 * @param ventureAction the value to be set at.
	 */
	public void setVentureAction(int ventureAction) {
		this.ventureAction = ventureAction;
	}

	/**
	 * @return the modifier of all harvest actions.
	 */
	public int getHarvest() {
		return harvest;
	}

	/**
	 * Sets the value of bonuses/penalties of harvest actions.
	 * @param harvest the value to be set at.
	 */
	public void setHarvest(int harvest) {
		this.harvest = harvest;
	}

	/**
	 * @return the modifier of all production actions.
	 */
	public int getProduction() {
		return production;
	}

	/**
	 * Sets the value of bonuses/penalties of production actions.
	 * @param production the value to be set at.
	 */
	public void setProduction(int production) {
		this.production = production;
	}
}
