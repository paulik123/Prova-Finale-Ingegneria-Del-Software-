package it.polimi.ingsw.ps45.model.player;

public class ActionValueModifier {
	private int territoryAction;
	private int characterAction;
	private int buildingAction;
	private int ventureAction;
	private int harvest;
	private int production;
	
	public ActionValueModifier(){
		this.territoryAction = 0;
		this.characterAction = 0;
		this.buildingAction = 0;
		this.ventureAction = 0;
		this.harvest = 0;
		this. production = 0;
	}

	public int getTerritoryAction() {
		return territoryAction;
	}

	public void setTerritoryAction(int territoryAction) {
		this.territoryAction = territoryAction;
	}

	public int getCharacterAction() {
		return characterAction;
	}

	public void setCharacterAction(int characterAction) {
		this.characterAction = characterAction;
	}

	public int getBuildingAction() {
		return buildingAction;
	}

	public void setBuildingAction(int buildingAction) {
		this.buildingAction = buildingAction;
	}

	public int getVentureAction() {
		return ventureAction;
	}

	public void setVentureAction(int ventureAction) {
		this.ventureAction = ventureAction;
	}

	public int getHarvest() {
		return harvest;
	}

	public void setHarvest(int harvest) {
		this.harvest = harvest;
	}

	public int getProduction() {
		return production;
	}

	public void setProduction(int production) {
		this.production = production;
	}
}
