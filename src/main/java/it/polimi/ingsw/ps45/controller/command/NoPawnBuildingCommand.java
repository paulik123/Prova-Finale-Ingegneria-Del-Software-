package it.polimi.ingsw.ps45.controller.command;

import it.polimi.ingsw.ps45.controller.Connection;
import it.polimi.ingsw.ps45.model.actions.ActionBuilder;
import it.polimi.ingsw.ps45.model.area.NoCardArea;
import it.polimi.ingsw.ps45.model.area.cardarea.BuildingCardArea;
import it.polimi.ingsw.ps45.model.area.cardarea.CharacterCardArea;
import it.polimi.ingsw.ps45.model.area.cardarea.TerritoryCardArea;
import it.polimi.ingsw.ps45.model.game.Game;
import it.polimi.ingsw.ps45.model.player.PawnType;

public class NoPawnBuildingCommand implements Command{

	private String buildingCardArea;
	private int servantsAdded;
	
	public NoPawnBuildingCommand(String area, int servants){
		this.buildingCardArea = area;
		this.servantsAdded = servants;
	}
	
	
	@Override
	public void run(Connection connection, String playerID) {
		Game g;
		BuildingCardArea tca = null;
		ActionBuilder ab = null;
		try{
			g = connection.getGameCreator().getGameFromPlayerID(playerID);
			ab = g.getPlayerByID(playerID).getActionBuilder();
			CommandAdapter ca = new CommandAdapter(g.getBoard());
			tca = ca.getBuildingArea(buildingCardArea);
			ab.NoPawnBuilding(tca, servantsAdded);
			g.notifyObservers();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
