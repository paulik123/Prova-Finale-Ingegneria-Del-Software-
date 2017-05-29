package it.polimi.ingsw.ps45.controller.command;

import it.polimi.ingsw.ps45.controller.Connection;
import it.polimi.ingsw.ps45.model.actions.ActionBuilder;
import it.polimi.ingsw.ps45.model.actions.ProductionMode;
import it.polimi.ingsw.ps45.model.game.Game;

public class ProductionCommand implements Command{
	
	String productionMode;
	
	public ProductionCommand(String productionModeArray){
		this.productionMode = productionModeArray;
	}

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
