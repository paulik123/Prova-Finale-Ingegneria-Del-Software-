package it.polimi.ingsw.ps45.controller.command;

import it.polimi.ingsw.ps45.controller.Connection;
import it.polimi.ingsw.ps45.model.actions.ActionBuilder;
import it.polimi.ingsw.ps45.model.game.Game;


/**
 * Command that increases the number of servants of a production actions that is waiting to be started.
 */
public class AddServantsToProductionCommand implements Command{
	
	private int servants;
	
	/**
 	 * Constructor
	 * @param  servants the number of servants that the player wants to add.
	 */
	public AddServantsToProductionCommand(int servants){
		this.servants = servants;
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
			ab.addServantsToProduction(servants);
			g.notifyObservers();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}