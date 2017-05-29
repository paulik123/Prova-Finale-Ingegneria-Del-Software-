package it.polimi.ingsw.ps45.controller.command;

import it.polimi.ingsw.ps45.controller.Connection;
import it.polimi.ingsw.ps45.model.actions.ActionBuilder;
import it.polimi.ingsw.ps45.model.area.cardarea.VentureCardArea;
import it.polimi.ingsw.ps45.model.cards.VentureMode;
import it.polimi.ingsw.ps45.model.game.Game;
import it.polimi.ingsw.ps45.model.player.PawnType;

public class AddServantsToHarvestCommand implements Command{
	
	private int servants;
	
	public AddServantsToHarvestCommand(int servants){
		this.servants = servants;
	}

	@Override
	public void run(Connection connection, String playerID) {
		Game g;
		ActionBuilder ab = null;
		
		try{
			g = connection.getGameCreator().getGameFromPlayerID(playerID);
			ab = g.getPlayerByID(playerID).getActionBuilder();
			ab.addServantsToHarvest(servants);
			g.notifyObservers();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}