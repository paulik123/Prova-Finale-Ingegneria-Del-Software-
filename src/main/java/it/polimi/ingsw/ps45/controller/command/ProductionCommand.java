package it.polimi.ingsw.ps45.controller.command;

import java.util.logging.Level;
import java.util.logging.Logger;

import it.polimi.ingsw.ps45.controller.Connection;
import it.polimi.ingsw.ps45.controller.SocketObserver;
import it.polimi.ingsw.ps45.model.actions.ActionBuilder;
import it.polimi.ingsw.ps45.model.actions.ProductionMode;
import it.polimi.ingsw.ps45.model.game.ErrorNotifier;
import it.polimi.ingsw.ps45.model.game.Game;

/**
 * Command that allows the player execute a production and choosing the modes of each building.
 */
public class ProductionCommand implements Command{
	private static final Logger LOGGER = Logger.getLogger( ProductionCommand.class.getName());
	private String productionMode;
	
	/**
 	 * @param  productionModeArray the modes of each building card.
	 */
	public ProductionCommand(String productionModeArray){
		if(!(productionModeArray == null || productionModeArray.length() == 0 )){
			this.productionMode = productionModeArray;
		}
		else{
			this.productionMode = "000000";
		}
		
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
			ErrorNotifier n = new ErrorNotifier(new SocketObserver(connection.getOutputStreamWriter()), "Bad command arguments");
			n.start();
			LOGGER.log(Level.SEVERE, "context", e);
		}
		
	}

}
