package it.polimi.ingsw.ps45.controller.command;

import it.polimi.ingsw.ps45.controller.Connection;
import it.polimi.ingsw.ps45.model.actions.ActionBuilder;
import it.polimi.ingsw.ps45.model.area.NoCardArea;
import it.polimi.ingsw.ps45.model.game.Game;
import it.polimi.ingsw.ps45.model.player.PawnType;

public class PlacePawnProductionCommand implements Command{

	private String productionArea;
	private String pawnType;
	private int servantsAdded;
	
	
	public PlacePawnProductionCommand(String area, String pt, int servants){
		this.productionArea = area;
		this.pawnType = pt;
		this.servantsAdded = servants;
	}
	
	
	@Override
	public void run(Connection connection, String playerID) {
		Game g;
		NoCardArea nca = null;
		PawnType pt = null;
		ActionBuilder ab = null;
		try{
			g = connection.getGameCreator().getGameFromPlayerID(playerID);
			ab = g.getPlayerByID(playerID).getActionBuilder();
			CommandAdapter ca = new CommandAdapter(g.getBoard());
			nca = ca.getHarvestArea(productionArea);
			pt = ca.getPawnType(pawnType);
			ab.placePawnProduction(nca, pt, servantsAdded);
			g.notifyObservers();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
