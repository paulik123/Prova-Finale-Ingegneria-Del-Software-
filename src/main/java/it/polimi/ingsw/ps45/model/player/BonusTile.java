package it.polimi.ingsw.ps45.model.player;

public abstract class BonusTile {
	public abstract void production(Player p);
	public abstract void harvest(Player p);
	public abstract int getProductionLevel();
	public abstract int getHarvestLevel();
}
