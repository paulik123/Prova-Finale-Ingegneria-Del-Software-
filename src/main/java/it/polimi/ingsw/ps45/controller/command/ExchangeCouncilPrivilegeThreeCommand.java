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
 * Command that makes the exchange between three different CouncilPrivilege and resources.
 */
public class ExchangeCouncilPrivilegeThreeCommand implements Command{
	private static final Logger LOGGER = Logger.getLogger( ExchangeCouncilPrivilegeThreeCommand.class.getName());
	private String cp1;
	private String cp2;
	private String cp3;
	
	/**
 	 * Constructor
	 * @param  cp1 the type of council privilege that the player wants to exchange as a string.
	 * @param  cp2 the type of the second council privilege that the player wants to exchange as a string.
	 * @param  cp3 the type of the third council privilege that the player wants to exchange as a string.
	 */
	public ExchangeCouncilPrivilegeThreeCommand(String cp1, String cp2, String cp3){
		this.cp1 = cp1;
		this.cp2 = cp2;
		this.cp3 = cp3;
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
		CouncilPrivilege privilege2 = null;
		CouncilPrivilege privilege3 = null;
		
		try{
			g = connection.getGameCreator().getGameFromPlayerID(playerID);
			ab = g.getPlayerByID(playerID).getActionBuilder();
			CommandAdapter ca = new CommandAdapter(g.getBoard());
			privilege1 = ca.getCouncilPrivilege(cp1);
			privilege2 = ca.getCouncilPrivilege(cp2);
			privilege3 = ca.getCouncilPrivilege(cp3); 
			ab.exchangeCouncilPrivilegeThree(privilege1, privilege2, privilege3);
			g.notifyObservers();
		}catch(Exception e){
			ErrorNotifier n = new ErrorNotifier(new SocketObserver(connection.getOutputStreamWriter()), "Bad command arguments");
			n.start();
			LOGGER.log(Level.SEVERE, "context", e);
		}
	}

}
