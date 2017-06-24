package it.polimi.ingsw.ps45.controller.command;

import it.polimi.ingsw.ps45.controller.Connection;
import it.polimi.ingsw.ps45.model.actions.ActionBuilder;
import it.polimi.ingsw.ps45.model.game.Game;

public class UseLeaderCardCommand  implements Command{
	
	int index;
	
	public UseLeaderCardCommand(int index){
		this.index = index;
	}

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
