package it.polimi.ingsw.ps45.controller.command;

import it.polimi.ingsw.ps45.controller.Connection;
import it.polimi.ingsw.ps45.model.actions.ActionBuilder;
import it.polimi.ingsw.ps45.model.area.NoCardArea;
import it.polimi.ingsw.ps45.model.area.cardarea.TerritoryCardArea;
import it.polimi.ingsw.ps45.model.game.Game;
import it.polimi.ingsw.ps45.model.player.PawnType;

public class PlacePawnTerritoryCommand implements Command{

	private String territoryCardArea;
	private String pawnType;
	private int servantsAdded;
	
	public PlacePawnTerritoryCommand(String area, String pt, int servants){
		this.territoryCardArea = area;
		this.pawnType = pt;
		this.servantsAdded = servants;
	}
	
	
	@Override
	public void run(Connection connection, String playerID) {
		Game g;
		TerritoryCardArea tca = null;
		PawnType pt = null;
		ActionBuilder ab = null;
		try{
			g = connection.getGameCreator().getGameFromPlayerID(playerID);
			ab = g.getPlayerByID(playerID).getActionBuilder();
			CommandAdapter ca = new CommandAdapter(g.getBoard());
			tca = ca.getTerritoryArea(territoryCardArea);
			pt = ca.getPawnType(pawnType);
			ab.placePawnTerritory(tca, pt, servantsAdded);
			g.notifyObservers();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
