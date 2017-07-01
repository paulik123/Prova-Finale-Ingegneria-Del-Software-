package it.polimi.ingsw.ps45.controller.command;

import it.polimi.ingsw.ps45.controller.Connection;
import it.polimi.ingsw.ps45.model.actions.ActionBuilder;
import it.polimi.ingsw.ps45.model.game.Game;


/**
 * Command that allows the player to use the once-per-turn effect of a leader card.
 */
public class UseLeaderCardCommand  implements Command{
	
	int index;
	
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
			e.printStackTrace();
		}
		
	}

}
