package it.polimi.ingsw.ps45.model.player;

import it.polimi.ingsw.ps45.model.effects.CollectEffect;


/**
 * Model representation of a personal bonus tile.
 */
public class PersonalBonusTile{
	
	private CollectEffect productionEffect;
	private CollectEffect harvestEffect;
	
	private int productionLevel;
	private int harvestLevel;
	
	private String id;
	
	/**
 	 * Constructor
 	 * @param productionConsumables the ConsumableSet the player will collect when he makes a production.
 	 * @param harvestConsumables the ConsumableSet the player will collect when he makes a harvest.
 	 * @param id an identification string.
 	 * @param productionLevel the minimum value of a production action the player must make to collect the rewards.
 	 * @param harvestLevel the minimum value of a harvest action the player must make to collect the rewards.
	 */
	public PersonalBonusTile(ConsumableSet productionConsumables, ConsumableSet harvestConsumables,String id, int productionLevel, int harvestLevel){
		productionEffect = new CollectEffect(productionConsumables);
		harvestEffect = new CollectEffect(harvestConsumables);
		this.productionLevel = productionLevel;
		this.harvestLevel = harvestLevel;
		this.id = id;
	}

	/**
	 * Runs the effect of the production.
	 * @param p The player that does the production.
	 */
	public void production(Player p) {
		productionEffect.runEffect(p, 0);
	}

	/**
	 * Runs the effect of the harvest.
	 * @param p The player that does the harvest.
	 */
	public void harvest(Player p) {
		harvestEffect.runEffect(p, 0);
	}

	/**
	 * @return p the minimum production value of this bonus tile.
	 */
	public int getProductionLevel() {
		// TODO Auto-generated method stub
		return productionLevel;
	}

	/**
	 * @return p the minimum harvest value of this bonus tile.
	 */
	public int getHarvestLevel() {
		// TODO Auto-generated method stub
		return harvestLevel;
	}
	
	/**
	 * @return p the id of this bonus tile.
	 */
	public String getId(){
		return id;
	}

}
