package it.polimi.ingsw.ps45.model.player;

public class PermanentEffects {
 //TODO:
	private boolean addingServantsPenalty;
	private boolean noPawnOnMarketPenalty;
	private boolean noFirstTurn;
	private boolean noCardAreaEffect;
	
	//ENDGAME
	private boolean noCharacterVictoryPoints;
	private boolean noVentureVictoryPoints;
	private boolean noTerritoryVictoryPoints;
	private boolean aFifthVictoryPointsPenalty;
	private boolean militaryPointsVictoryPointsPenalty;
	private boolean buildingCostVictoryPointsPenalty;
	private boolean resourceVictoryPointsPenalty;

	public boolean isAddingServantsPenalty() {
		return addingServantsPenalty;
	}

	public void setAddingServantsPenalty(boolean addingServantsPenalty) {
		this.addingServantsPenalty = addingServantsPenalty;
	}

	public boolean isNoPawnOnMarketPenalty() {
		return noPawnOnMarketPenalty;
	}

	public void setNoPawnOnMarketPenalty(boolean noPawnOnMarketPenalty) {
		this.noPawnOnMarketPenalty = noPawnOnMarketPenalty;
	}

	public boolean isNoFirstTurn() {
		return noFirstTurn;
	}

	public void setNoFirstTurn(boolean noFirstTurn) {
		this.noFirstTurn = noFirstTurn;
	}

	public boolean isNoCharacterVictoryPoints() {
		return noCharacterVictoryPoints;
	}

	public void setNoCharacterVictoryPoints(boolean noCharacterVictoryPoints) {
		this.noCharacterVictoryPoints = noCharacterVictoryPoints;
	}

	public boolean isNoVentureVictoryPoints() {
		return noVentureVictoryPoints;
	}

	public void setNoVentureVictoryPoints(boolean noVentureVictoryPoints) {
		this.noVentureVictoryPoints = noVentureVictoryPoints;
	}

	public boolean isNoTerritoryVictoryPoints() {
		return noTerritoryVictoryPoints;
	}

	public void setNoTerritoryVictoryPoints(boolean noTerritoryVictoryPoints) {
		this.noTerritoryVictoryPoints = noTerritoryVictoryPoints;
	}

	public boolean isaFifthVictoryPointsPenalty() {
		return aFifthVictoryPointsPenalty;
	}

	public void setaFifthVictoryPointsPenalty(boolean aFifthVictoryPointsPenalty) {
		this.aFifthVictoryPointsPenalty = aFifthVictoryPointsPenalty;
	}

	public boolean isMilitaryPointsVictoryPointsPenalty() {
		return militaryPointsVictoryPointsPenalty;
	}

	public void setMilitaryPointsVictoryPointsPenalty(boolean militaryPointsVictoryPointsPenalty) {
		this.militaryPointsVictoryPointsPenalty = militaryPointsVictoryPointsPenalty;
	}

	public boolean isBuildingCostVictoryPointsPenalty() {
		return buildingCostVictoryPointsPenalty;
	}

	public void setBuildingCostVictoryPointsPenalty(boolean buildingCostVictoryPointsPenalty) {
		this.buildingCostVictoryPointsPenalty = buildingCostVictoryPointsPenalty;
	}

	public boolean isResourceVictoryPointsPenalty() {
		return resourceVictoryPointsPenalty;
	}

	public void setResourceVictoryPointsPenalty(boolean resourceVictoryPointsPenalty) {
		this.resourceVictoryPointsPenalty = resourceVictoryPointsPenalty;
	}

	public boolean isNoCardAreaEffect() {
		return noCardAreaEffect;
	}

	public void setNoCardAreaEffect(boolean noCardAreaEffect) {
		this.noCardAreaEffect = noCardAreaEffect;
	}
	
	
	
	
	
	
	
	
	
}
