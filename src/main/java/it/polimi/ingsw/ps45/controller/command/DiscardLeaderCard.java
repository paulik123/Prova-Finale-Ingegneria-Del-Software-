package it.polimi.ingsw.ps45.controller.command;

import java.util.logging.Level;
import java.util.logging.Logger;

import it.polimi.ingsw.ps45.controller.Connection;
import it.polimi.ingsw.ps45.model.actions.ActionBuilder;
import it.polimi.ingsw.ps45.model.actions.CouncilPrivilege.CouncilPrivilege;
import it.polimi.ingsw.ps45.model.game.Game;


/**
 * Command that allows the player to discard a leader card and get a councilPrivilege.
 */
public class DiscardLeaderCard  implements Command{
	private static final Logger LOGGER = Logger.getLogger( DiscardLeaderCard.class.getName());
	
	private int index;
	private String cp;
	
	/**
 	 * Constructor
	 * @param  index the index of the LeaderCard in the player's LeaderCard list.
	 */
	public DiscardLeaderCard(int index, String cp){
		this.index = index;
		this.cp = cp;
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
			CommandAdapter ca = new CommandAdapter(g.getBoard());
			CouncilPrivilege privilege1 = ca.getCouncilPrivilege(cp); 
			ab.discardLeaderCard(index, privilege1);
			g.notifyObservers();
		}catch(Exception e){
			LOGGER.log(Level.SEVERE, "context", e);
		}
		
	}

}
