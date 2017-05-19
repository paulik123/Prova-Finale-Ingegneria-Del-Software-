package it.polimi.ingsw.ps45.model.player;

public class DefaultBonusTile extends BonusTile {
	
	private static int militaryPoints = 1;
	private static int coins = 1;
	
	private static int wood = 1;
	private static int stone = 1;
	private static int servants = 1;
	
	private static int productionLevel = 1;
	private static int harvestLevel = 1;

	@Override
	public void production(Player p) {
		ConsumableSet cs = new ConsumableSet();
		cs.setMilitaryPoins(militaryPoints);
		cs.setCoins(coins);
		p.getResourceSet().collect(cs);
	}

	@Override
	public void harvest(Player p) {
		ConsumableSet cs = new ConsumableSet();
		cs.setWood(wood);
		cs.setStone(stone);
		cs.setServants(servants);
		p.getResourceSet().collect(cs);
	}

	@Override
	public int getProductionLevel() {
		// TODO Auto-generated method stub
		return productionLevel;
	}

	@Override
	public int getHarvestLevel() {
		// TODO Auto-generated method stub
		return harvestLevel;
	}

}
