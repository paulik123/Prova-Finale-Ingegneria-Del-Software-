package it.polimi.ingsw.ps45.controller.command;

import it.polimi.ingsw.ps45.controller.Connection;
import it.polimi.ingsw.ps45.model.actions.ActionBuilder;
import it.polimi.ingsw.ps45.model.actions.ProductionMode;
import it.polimi.ingsw.ps45.model.game.Game;

/**
 * Command that allows the player execute a production and choosing the modes of each building.
 */
public class ProductionCommand implements Command{
	
	private String productionMode;
	
	/**
 	 * @param  productionModeArray the modes of each building card.
	 */
	public ProductionCommand(String productionModeArray){
		this.productionMode = productionModeArray;
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
		ProductionMode[] pm = null;
		
		try{
			g = connection.getGameCreator().getGameFromPlayerID(playerID);
			ab = g.getPlayerByID(playerID).getActionBuilder();
			CommandAdapter ca = new CommandAdapter(g.getBoard());
			pm = ca.getProductionMode(productionMode);
			ab.production(pm);
			g.notifyObservers();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
