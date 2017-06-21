package it.polimi.ingsw.ps45.model.player;

import it.polimi.ingsw.ps45.model.effects.CollectEffect;

public class PersonalBonusTile{
	
	private CollectEffect productionEffect;
	private CollectEffect harvestEffect;
	
	private int productionLevel;
	private int harvestLevel;
	
	private String id;
	
	public PersonalBonusTile(ConsumableSet productionCosnumables, ConsumableSet harvestConsumables,String id, int productionLevel, int harvestLevel){
		productionEffect = new CollectEffect(productionCosnumables);
		harvestEffect = new CollectEffect(harvestConsumables);
		this.productionLevel = productionLevel;
		this.harvestLevel = harvestLevel;
		this.id = id;
	}

	public void production(Player p) {
		productionEffect.runEffect(p, 0);
	}

	public void harvest(Player p) {
		harvestEffect.runEffect(p, 0);
	}

	public int getProductionLevel() {
		// TODO Auto-generated method stub
		return productionLevel;
	}

	public int getHarvestLevel() {
		// TODO Auto-generated method stub
		return harvestLevel;
	}
	
	public String getId(){
		return id;
	}

}
