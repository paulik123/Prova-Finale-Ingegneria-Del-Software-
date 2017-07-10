package it.polimi.ingsw.ps45.controller.command;

import java.util.logging.Level;
import java.util.logging.Logger;

import it.polimi.ingsw.ps45.controller.Connection;
import it.polimi.ingsw.ps45.controller.SocketObserver;
import it.polimi.ingsw.ps45.model.actions.ActionBuilder;
import it.polimi.ingsw.ps45.model.game.ErrorNotifier;
import it.polimi.ingsw.ps45.model.game.Game;

/**
 * Command that allows the player to accept vatican at the end of each era.
 */
public class AcceptVaticanCommand implements Command{

	private static final Logger LOGGER = Logger.getLogger( AcceptVaticanCommand.class.getName());
	/**
 	 * 
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
			ab.acceptVatican();
			g.notifyObservers();
		}catch(Exception e){
			ErrorNotifier n = new ErrorNotifier(new SocketObserver(connection.getOutputStreamWriter()), "Bad command arguments");
			n.start();
			LOGGER.log(Level.SEVERE, "context", e);
		}
		
	}

}
