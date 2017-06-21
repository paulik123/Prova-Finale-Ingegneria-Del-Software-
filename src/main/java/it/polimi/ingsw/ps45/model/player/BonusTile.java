package it.polimi.ingsw.ps45.model.player;

public interface BonusTile {
	public void production();
	public void harvest();
	public int getProductionLevel();
	public int getHarvestLevel();
	public String getId();
}
