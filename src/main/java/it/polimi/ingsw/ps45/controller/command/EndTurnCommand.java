package it.polimi.ingsw.ps45.controller.command;

import it.polimi.ingsw.ps45.controller.Connection;
import it.polimi.ingsw.ps45.model.game.Game;


/**
 * Command that end the turn of the current player.
 */
public class EndTurnCommand implements Command{


	
	/**
 	 * 
 	 * @param  connection the players Connection used to get a reference to the gameCreator
 	 * @param  playerID the id of the player that "executes the command"
	 */
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
