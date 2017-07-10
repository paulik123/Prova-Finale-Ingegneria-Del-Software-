package it.polimi.ingsw.ps45.controller.command;

import java.util.logging.Level;
import java.util.logging.Logger;

import it.polimi.ingsw.ps45.controller.Connection;
import it.polimi.ingsw.ps45.controller.SocketObserver;
import it.polimi.ingsw.ps45.model.actions.ActionBuilder;
import it.polimi.ingsw.ps45.model.actions.CouncilPrivilege.CouncilPrivilege;
import it.polimi.ingsw.ps45.model.game.ErrorNotifier;
import it.polimi.ingsw.ps45.model.game.Game;


/**
 * Command that makes the exchange between one CouncilPrivilege and resources.
 */
public class ExchangeCouncilPrivilegeOneCommand implements Command{
	
	private String cp1;
	private static final Logger LOGGER = Logger.getLogger( ExchangeCouncilPrivilegeOneCommand.class.getName());
	
	/**
 	 * Constructor
	 * @param  cp1 the type of council privilege that the player wants to exchange as a string.
	 */
	public ExchangeCouncilPrivilegeOneCommand(String cp1){
		this.cp1 = cp1;
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
		CouncilPrivilege privilege1 = null;
		
		try{
			g = connection.getGameCreator().getGameFromPlayerID(playerID);
			ab = g.getPlayerByID(playerID).getActionBuilder();
			CommandAdapter ca = new CommandAdapter(g.getBoard());
			privilege1 = ca.getCouncilPrivilege(cp1); 
			ab.exchangeCouncilPrivilegeOne(privilege1);
			g.notifyObservers();
		}catch(Exception e){
			ErrorNotifier n = new ErrorNotifier(new SocketObserver(connection.getOutputStreamWriter()), "Bad command arguments");
			n.start();
			LOGGER.log(Level.SEVERE, "context", e);
		}
	}

}
