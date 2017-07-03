package it.polimi.ingsw.ps45.model.effects;

import java.io.Serializable;

import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.player.Player;

/**
 * An effect that gives a player some ConsumableSet reward based on how many characters he has. For example if he has 3 characters he "collects" the reward 3 times.
 */
public class ConsumableSetPerCharactersEffect implements Effect, Serializable {

	private ConsumableSet cs;
	
	/**
 	 * Constructor
 	 * @param cs The ConsumableSet that the player will collect.
	 */
	public ConsumableSetPerCharactersEffect(ConsumableSet cs){
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
		int times = p.getResourceSet().getCharacterList().size();
		
		ConsumableSet reward = new ConsumableSet();
		
		for(int i=0;i<times;i++){
			reward.collect(cs);
		}
		
		p.getResourceSet().collect(reward);
		
	}
	
}
