package it.polimi.ingsw.ps45.model.effects;

import java.io.Serializable;

import it.polimi.ingsw.ps45.model.actions.state.NoActionState;
import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.player.Player;

public class CollectEffect implements Effect, Serializable{
	ConsumableSet cs;
	
	public CollectEffect(ConsumableSet cs){
		this.cs = cs;
	}
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
