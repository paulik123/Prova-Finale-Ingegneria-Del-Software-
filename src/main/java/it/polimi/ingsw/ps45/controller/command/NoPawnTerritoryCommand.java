package it.polimi.ingsw.ps45.controller.command;

import it.polimi.ingsw.ps45.controller.Connection;
import it.polimi.ingsw.ps45.model.actions.ActionBuilder;
import it.polimi.ingsw.ps45.model.area.cardarea.TerritoryCardArea;
import it.polimi.ingsw.ps45.model.game.Game;

/**
 * Command that allows the player to acquire a territory without placing a pawn.
 */
public class NoPawnTerritoryCommand implements Command{

	private String territoryCardArea;
	private int servantsAdded;
	
	/**
 	 * Constructor
	 * @param  area the area in which the player wants to take the card from.
	 * @param  servants the number of servants that the player wants to add.
	 */
	public NoPawnTerritoryCommand(String area, int servants){
		this.territoryCardArea = area;
		this.servantsAdded = servants;
	}
	
	/**
 	 * 
 	 * @param  connection the players Connection used to get a reference to the gameCreator
 	 * @param  playerID the id of the player that "executes the command"
	 */
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
