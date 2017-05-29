package it.polimi.ingsw.ps45.controller.command;

import it.polimi.ingsw.ps45.controller.Connection;
import it.polimi.ingsw.ps45.model.actions.ActionBuilder;
import it.polimi.ingsw.ps45.model.area.NoCardArea;
import it.polimi.ingsw.ps45.model.area.cardarea.TerritoryCardArea;
import it.polimi.ingsw.ps45.model.game.Game;
import it.polimi.ingsw.ps45.model.player.PawnType;

public class NoPawnTerritoryCommand implements Command{

	private String territoryCardArea;
	private int servantsAdded;
	
	public NoPawnTerritoryCommand(String area, int servants){
		this.territoryCardArea = area;
		this.servantsAdded = servants;
	}
	
	
	@Override
	public void run(Connection connection, String playerID) {
		Game g;
		TerritoryCardArea tca = null;
		ActionBuilder ab = null;
		try{
			g = connection.getGameCreator().getGameFromPlayerID(playerID);
			ab = g.getPlayerByID(playerID).getActionBuilder();
			CommandAdapter ca = new CommandAdapter(g.getBoard());
			tca = ca.getTerritoryArea(territoryCardArea);
			ab.NoPawnTerritory(tca, servantsAdded);
			g.notifyObservers();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
