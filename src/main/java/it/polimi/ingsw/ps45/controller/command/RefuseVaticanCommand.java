package it.polimi.ingsw.ps45.controller.command;

import java.util.logging.Level;
import java.util.logging.Logger;

import it.polimi.ingsw.ps45.controller.Connection;
import it.polimi.ingsw.ps45.model.actions.ActionBuilder;
import it.polimi.ingsw.ps45.model.game.Game;


/**
 * Command that allows the player to refuse vatican at the end of each era.
 */
public class RefuseVaticanCommand implements Command{
	private static final Logger LOGGER = Logger.getLogger( RefuseVaticanCommand.class.getName());
	
	/**
 	 * @param  connection the players Connection used to get a reference to the gameCreator
 	 * @param  playerID the id of the player that "executes the command"
	 */
	@Override
	public void run(Connection connection, String playerID) {
		Game g;
		ActionBuilder ab = null;
		
		try{
			g = connection.getGameCreator().getGameFromPlayerID(playerID);
			ab = g.getPlayerByID(playerID).getActionBuilder();
			ab.refuseVatican();
			g.notifyObservers();
		}catch(Exception e){
			LOGGER.log(Level.SEVERE, "context", e);
		}
		
	}

}
