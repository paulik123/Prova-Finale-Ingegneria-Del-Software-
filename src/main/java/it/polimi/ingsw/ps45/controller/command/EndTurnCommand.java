package it.polimi.ingsw.ps45.controller.command;

import it.polimi.ingsw.ps45.controller.Connection;
import it.polimi.ingsw.ps45.model.game.Game;

public class EndTurnCommand implements Command{


	
	
	@Override
	public void run(Connection connection, String playerID) {
		try {
			Game g = connection.getGameCreator().getGameFromPlayerID(playerID);
			g.nextTurn(playerID);
			g.notifyObservers();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
