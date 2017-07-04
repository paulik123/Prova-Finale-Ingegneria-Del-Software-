package it.polimi.ingsw.ps45.model.effects;

import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.player.Player;

/**
 * An effect that makes a the player collect a consumable set.It also checks for the SantaRita effect. 
 */
public class CollectEffect implements Effect{
	private ConsumableSet cs;
	
	/**
 	 * Constructor
 	 * @param cs The ConsumableSet that the player will collect.
	 */
	public CollectEffect(ConsumableSet cs){
		this.cs = cs;
	}
	
	/**
	 * The method that runs the effect.
	 * 
	 *@param p the player that runs the effect.
	 *@param value the value of the effect. It is needed only when nesting actions(an action makes you do another action with a certain value).
	 */
	@Override
	public void runEffect(Player p, int value) {
		
		// Santa Rita... 
		if(p.getResourceSet().getPermanentEffects().isHasActivatedSantaRita()){
			ConsumableSet newCS = new ConsumableSet();
			newCS.setCoins(cs.getCoins());
			cs.setCoins(0);
			
			newCS.setWood(cs.getWood());
			cs.setWood(0);
			
			newCS.setStone(cs.getStone());
			cs.setStone(0);
			
			newCS.setServants(cs.getServants());
			cs.setServants(0);
			
			p.getResourceSet().collect(newCS);
			p.getResourceSet().collect(newCS);
			p.getResourceSet().collect(cs);
			return;
		}
		
		p.getResourceSet().collect(cs);
	}
}
