package it.polimi.ingsw.ps45.controller.command;

import java.util.logging.Level;
import java.util.logging.Logger;

import it.polimi.ingsw.ps45.controller.Connection;
import it.polimi.ingsw.ps45.model.actions.ActionBuilder;
import it.polimi.ingsw.ps45.model.actions.CouncilPrivilege.CouncilPrivilege;
import it.polimi.ingsw.ps45.model.game.Game;


/**
 * Command that makes the exchange between two different CouncilPrivilege and resources.
 */
public class ExchangeCouncilPrivilegeTwoCommand implements Command{
	private static final Logger LOGGER = Logger.getLogger( ExchangeCouncilPrivilegeTwoCommand.class.getName());
	private String cp1;
	private String cp2;
	
	/**
 	 * Constructor
	 * @param  cp1 the type of council privilege that the player wants to exchange as a string.
	 * @param  cp2 the type of the second council privilege that the player wants to exchange as a string.
	 */
	public ExchangeCouncilPrivilegeTwoCommand(String cp1, String cp2){
		this.cp1 = cp1;
		this.cp2 = cp2;
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
		
		try{
			g = connection.getGameCreator().getGameFromPlayerID(playerID);
			ab = g.getPlayerByID(playerID).getActionBuilder();
			CommandAdapter ca = new CommandAdapter(g.getBoard());
			privilege1 = ca.getCouncilPrivilege(cp1);
			privilege2 = ca.getCouncilPrivilege(cp2); 
			ab.exchangeCouncilPrivilegeTwo(privilege1, privilege2);
			g.notifyObservers();
		}catch(Exception e){
			LOGGER.log(Level.SEVERE, "context", e);
		}
		
	}

}
