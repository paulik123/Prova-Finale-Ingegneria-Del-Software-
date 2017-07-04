package it.polimi.ingsw.ps45.controller.command;

import java.util.logging.Level;
import java.util.logging.Logger;

import it.polimi.ingsw.ps45.controller.Connection;
import it.polimi.ingsw.ps45.model.actions.ActionBuilder;
import it.polimi.ingsw.ps45.model.game.Game;


/**
 * Command that allows the player to activate a leader card.
 */
public class ActivateLeaderCardCommand  implements Command{
	private static final Logger LOGGER = Logger.getLogger( ActivateLeaderCardCommand.class.getName());
	
	private int index;
	
	/**
 	 * Constructor
	 * @param  index the index of the LeaderCard in the player's LeaderCard list.
	 */
	public ActivateLeaderCardCommand(int index){
		this.index = index;
	}

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
			ab.activateLeaderCard(index);
			g.notifyObservers();
		}catch(Exception e){
			LOGGER.log(Level.SEVERE, "context", e);
		}
		
	}

}
