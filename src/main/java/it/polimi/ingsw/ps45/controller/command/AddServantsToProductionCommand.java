package it.polimi.ingsw.ps45.controller.command;

import it.polimi.ingsw.ps45.controller.Connection;
import it.polimi.ingsw.ps45.model.actions.ActionBuilder;
import it.polimi.ingsw.ps45.model.area.cardarea.VentureCardArea;
import it.polimi.ingsw.ps45.model.cards.VentureMode;
import it.polimi.ingsw.ps45.model.game.Game;
import it.polimi.ingsw.ps45.model.player.PawnType;

public class AddServantsToProductionCommand implements Command{
	
	private int servants;
	
	public AddServantsToProductionCommand(int servants){
		this.servants = servants;
	}

	@Override
	public void run(Connection connection, String playerID) {
		Game g;
		ActionBuilder ab = null;
		
		try{
			g = connection.getGameCreator().getGameFromPlayerID(playerID);
			ab = g.getPlayerByID(playerID).getActionBuilder();
			ab.addServantsToProduction(servants);
			g.notifyObservers();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}