package it.polimi.ingsw.ps45.controller.command;

import java.util.logging.Level;
import java.util.logging.Logger;

import it.polimi.ingsw.ps45.controller.Connection;
import it.polimi.ingsw.ps45.controller.SocketObserver;
import it.polimi.ingsw.ps45.model.actions.ActionBuilder;
import it.polimi.ingsw.ps45.model.game.ErrorNotifier;
import it.polimi.ingsw.ps45.model.game.Game;


/**
 * Command that allows the player to use the once-per-turn effect of a leader card.
 */
public class UseLeaderCardCommand  implements Command{
	private static final Logger LOGGER = Logger.getLogger( UseLeaderCardCommand.class.getName());
	private int index;
	
	/**
 	 * @param  index the index of the leader card in the players activated leader card list.
	 */
	public UseLeaderCardCommand(int index){
		this.index = index;
	}

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
			ab.useLeaderCard(index);
			g.notifyObservers();
		}catch(Exception e){
			ErrorNotifier n = new ErrorNotifier(new SocketObserver(connection.getOutputStreamWriter()), "Bad command arguments");
			n.start();
			LOGGER.log(Level.SEVERE, "context", e);
		}
		
	}

}
