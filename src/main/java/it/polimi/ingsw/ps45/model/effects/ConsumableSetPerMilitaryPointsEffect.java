package it.polimi.ingsw.ps45.model.effects;

import java.io.Serializable;

import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.player.Player;

/**
 * An effect that gives a player some ConsumableSet reward based on how many buildings he has. The player "collects" the reward "militaryPoints/2" times.
 */
public class ConsumableSetPerMilitaryPointsEffect implements Effect{

	private ConsumableSet cs;
	
	/**
 	 * Constructor
 	 * @param cs The ConsumableSet that the player will collect.
	 */
	public ConsumableSetPerMilitaryPointsEffect(ConsumableSet cs){
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
		int militaryPoints = p.getResourceSet().getResources().getMilitaryPoins();
		
		ConsumableSet reward = new ConsumableSet();
		
		for(int i=0;i<militaryPoints/2;i++){
			reward.collect(cs);
		}
		
		p.getResourceSet().collect(reward);
		
	}
	
}
