package it.polimi.ingsw.ps45.model.effects;

import java.io.Serializable;

import it.polimi.ingsw.ps45.model.actions.state.TakeCardState;
import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.player.Player;

public class TakeAnyCardEffect implements Effect, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3670827548198421592L;

	int val;
	ConsumableSet discount;
	
	public TakeAnyCardEffect(int val, ConsumableSet discount){
		this.val = val;
		this.discount = discount;
	}
	
	@Override
	public void runEffect(Player p, int value) {
		p.getActionBuilder().setState(new TakeCardState(val, discount));
		p.setStatus("Take any card. Value: " + val);
	}

}
