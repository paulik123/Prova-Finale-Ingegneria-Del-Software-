package it.polimi.ingsw.ps45.model.player;

/**
 * A players permanent effects.
 */

public class PermanentEffects {
	
	private boolean addingServantsPenalty;
	private boolean noPawnOnMarketPenalty;
	private boolean noFirstTurn;
	private boolean noCardAreaEffect;
	private boolean hasActivatedSisto;
	private boolean noTerritoryMilitaryPointsRequirements;
	private boolean hasActivatedSantaRita;
	private boolean canPlacePawnOnOccupiedAreas;
	private boolean valueFivePawn;
	private boolean noThreeCoinsTowerPenalty;
	private boolean smallestPawnHasSixValue;
	
	//ENDGAME
	private boolean noCharacterVictoryPoints;
	private boolean noVentureVictoryPoints;
	private boolean noTerritoryVictoryPoints;
	private boolean aFifthVictoryPointsPenalty;
	private boolean militaryPointsVictoryPointsPenalty;
	private boolean buildingCostVictoryPointsPenalty;
	private boolean resourceVictoryPointsPenalty;

	/**
	 * @return true if the player is affected by the addingServantsPenalty.
	 */
	public boolean isAddingServantsPenalty() {
		return addingServantsPenalty;
	}

	/**
	 * Sets the effect
	 * @param addingServantsPenalty effect true or false.
	 */
	public void setAddingServantsPenalty(boolean addingServantsPenalty) {
		this.addingServantsPenalty = addingServantsPenalty;
	}

	/**
	 * @return true if the player is affected by the noPawnOnMarketPenalty effect.
	 */
	public boolean isNoPawnOnMarketPenalty() {
		return noPawnOnMarketPenalty;
	}

	/**
	 * Sets the effect
	 * @param noPawnOnMarketPenalty effect true or not.
	 */
	public void setNoPawnOnMarketPenalty(boolean noPawnOnMarketPenalty) {
		this.noPawnOnMarketPenalty = noPawnOnMarketPenalty;
	}

	/**
	 * @return true if the player is affected by the noFirstTurnPenalty effect.
	 */
	public boolean isNoFirstTurn() {
		return noFirstTurn;
	}

	/**
	 * Sets the effect
	 * @param noFirstTurn effect true or not.
	 */
	public void setNoFirstTurn(boolean noFirstTurn) {
		this.noFirstTurn = noFirstTurn;
	}

	/**
	 * @return true if the player is affected by the noCharacterVictoryPoints penalty effect.
	 */
	public boolean isNoCharacterVictoryPoints() {
		return noCharacterVictoryPoints;
	}

	/**
	 * Sets the effect
	 * @param noCharacterVictoryPoints effect true or not.
	 */
	public void setNoCharacterVictoryPoints(boolean noCharacterVictoryPoints) {
		this.noCharacterVictoryPoints = noCharacterVictoryPoints;
	}

	/**
	 * @return true if the player is affected by the noVentureVictoryPoints Penalty effect.
	 */
	public boolean isNoVentureVictoryPoints() {
		return noVentureVictoryPoints;
	}

	/**
	 * Sets the effect
	 * @param noVentureVictoryPoints effect true or not.
	 */
	public void setNoVentureVictoryPoints(boolean noVentureVictoryPoints) {
		this.noVentureVictoryPoints = noVentureVictoryPoints;
	}

	/**
	 * @return true if the player is affected by the noTerritoryVictoryPoints Penalty effect.
	 */
	public boolean isNoTerritoryVictoryPoints() {
		return noTerritoryVictoryPoints;
	}

	/**
	 * Sets the effect
	 * @param noTerritoryVictoryPoints effect true or not.
	 */
	public void setNoTerritoryVictoryPoints(boolean noTerritoryVictoryPoints) {
		this.noTerritoryVictoryPoints = noTerritoryVictoryPoints;
	}

	/**
	 * @return true if the player is affected by the aFifthVictoryPoints Penalty effect.
	 */
	public boolean isaFifthVictoryPointsPenalty() {
		return aFifthVictoryPointsPenalty;
	}

	/**
	 * Sets the effect
	 * @param aFifthVictoryPointsPenalty effect true or not.
	 */
	public void setaFifthVictoryPointsPenalty(boolean aFifthVictoryPointsPenalty) {
		this.aFifthVictoryPointsPenalty = aFifthVictoryPointsPenalty;
	}

	/**
	 * @return true if the player is affected by the militaryPointsVictoryPointsPenalty effect.
	 */
	public boolean isMilitaryPointsVictoryPointsPenalty() {
		return militaryPointsVictoryPointsPenalty;
	}

	/**
	 * Sets the effect
	 * @param militaryPointsVictoryPointsPenalty effect true or not.
	 */
	public void setMilitaryPointsVictoryPointsPenalty(boolean militaryPointsVictoryPointsPenalty) {
		this.militaryPointsVictoryPointsPenalty = militaryPointsVictoryPointsPenalty;
	}

	/**
	 * @return true if the player is affected by the buildingCostVictoryPointsPenalt effect y.
	 */
	public boolean isBuildingCostVictoryPointsPenalty() {
		return buildingCostVictoryPointsPenalty;
	}

	/**
	 * Sets the effect
	 * @param buildingCostVictoryPointsPenalty effect true or not.
	 */
	public void setBuildingCostVictoryPointsPenalty(boolean buildingCostVictoryPointsPenalty) {
		this.buildingCostVictoryPointsPenalty = buildingCostVictoryPointsPenalty;
	}

	/**
	 * @return true if the player is affected by the resourceVictoryPointsPenalty effect.
	 */
	public boolean isResourceVictoryPointsPenalty() {
		return resourceVictoryPointsPenalty;
	}

	/**
	 * Sets the effect
	 * @param resourceVictoryPointsPenalty effect true or not.
	 */
	public void setResourceVictoryPointsPenalty(boolean resourceVictoryPointsPenalty) {
		this.resourceVictoryPointsPenalty = resourceVictoryPointsPenalty;
	}

	/**
	 * @return true if the player is affected by the noCardAreaEffect Penalty effect.
	 */
	public boolean isNoCardAreaEffect() {
		return noCardAreaEffect;
	}

	/**
	 * Sets the effect
	 * @param noCardAreaEffect effect true or not.
	 */
	public void setNoCardAreaEffect(boolean noCardAreaEffect) {
		this.noCardAreaEffect = noCardAreaEffect;
	}

	/**
	 * @return true if the player has activated Sisto Leader Card effect.
	 */
	public boolean isHasActivatedSisto() {
		return hasActivatedSisto;
	}
	
	/**
	 * Sets the effect
	 * @param hasActivatedSisto effect true or not.
	 */
	public void setHasActivatedSisto(boolean hasActivatedSisto) {
		this.hasActivatedSisto = hasActivatedSisto;
	}

	/**
	 * @return true if the player is affected by the noTerritoryMilitaryPointsRequirements Penalty effect.
	 */
	public boolean isNoTerritoryMilitaryPointsRequirements() {
		return noTerritoryMilitaryPointsRequirements;
	}

	/**
	 * Sets the effect
	 * @param noTerritoryMilitaryPointsRequirements effect true or not.
	 */
	public void setNoTerritoryMilitaryPointsRequirements(boolean noTerritoryMilitaryPointsRequirements) {
		this.noTerritoryMilitaryPointsRequirements = noTerritoryMilitaryPointsRequirements;
	}

	/**
	 * @return true if the player has activated Santa Rita effect.
	 */
	public boolean isHasActivatedSantaRita() {
		return hasActivatedSantaRita;
	}

	/**
	 * Sets the effect
	 * @param hasActivatedSantaRita effect true or not.
	 */
	public void setHasActivatedSantaRita(boolean hasActivatedSantaRita) {
		this.hasActivatedSantaRita = hasActivatedSantaRita;
	}

	/**
	 * @return true if the player can place pawns on occupied areas.
	 */
	public boolean isCanPlacePawnOnOccupiedAreas() {
		return canPlacePawnOnOccupiedAreas;
	}

	/**
	 * Sets the effect
	 * @param canPlacePawnOnOccupiedAreas effect true or not.
	 */
	public void setCanPlacePawnOnOccupiedAreas(boolean canPlacePawnOnOccupiedAreas) {
		this.canPlacePawnOnOccupiedAreas = canPlacePawnOnOccupiedAreas;
	}

	/**
	 * @return true if the player's colored pawns must all have value 5.
	 */
	public boolean isValueFivePawn() {
		return valueFivePawn;
	}

	/**
	 * Sets the effect
	 * @param valueFivePawn effect true or not.
	 */
	public void setValueFivePawn(boolean valueFivePawn) {
		this.valueFivePawn = valueFivePawn;
	}

	/**
	 * @return true if the player is affected by the noThreeCoinsTowerPenalty Effect.
	 */
	public boolean isNoThreeCoinsTowerPenalty() {
		return noThreeCoinsTowerPenalty;
	}

	/**
	 * Sets the effect
	 * @param noThreeCoinsTowerPenalty effect true or not.
	 */
	public void setNoThreeCoinsTowerPenalty(boolean noThreeCoinsTowerPenalty) {
		this.noThreeCoinsTowerPenalty = noThreeCoinsTowerPenalty;
	}

	/**
	 * @return true if the player is affected by the "smallest pawn has a value of six effect".
	 */
	public boolean isSmallestPawnHasSixValue() {
		return smallestPawnHasSixValue;
	}

	/**
	 * Sets the effect
	 * @param smallestPawnHasSixValue effect true or not.
	 */
	public void setSmallestPawnHasSixValue(boolean smallestPawnHasSixValue) {
		this.smallestPawnHasSixValue = smallestPawnHasSixValue;
	}
	
}
