package it.polimi.ingsw.ps45.controller.command;

import java.util.logging.Level;
import java.util.logging.Logger;

import it.polimi.ingsw.ps45.controller.Connection;
import it.polimi.ingsw.ps45.controller.SocketObserver;
import it.polimi.ingsw.ps45.model.actions.ActionBuilder;
import it.polimi.ingsw.ps45.model.area.cardarea.BuildingCardArea;
import it.polimi.ingsw.ps45.model.game.ErrorNotifier;
import it.polimi.ingsw.ps45.model.game.Game;
import it.polimi.ingsw.ps45.model.player.PawnType;

/**
 * Command that allows the player to acquire a building while also placing a pawn.
 */
public class PlacePawnBuildingCommand implements Command{
	private static final Logger LOGGER = Logger.getLogger( PlacePawnBuildingCommand.class.getName());
	private String buildingCardArea;
	private String pawnType;
	private int servantsAdded;
	
	/**
 	 * 
 	 * @param  connection the players Connection used to get a reference to the gameCreator.
 	 * @param  pt the "color" of the pawn that the player wants to use.
 	 * @param  playerID the id of the player that "executes the command".
	 */
	public PlacePawnBuildingCommand(String area, String pt, int servants){
		this.buildingCardArea = area;
		this.pawnType = pt;
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
		BuildingCardArea tca = null;
		PawnType pt = null;
		ActionBuilder ab = null;
		try{
			g = connection.getGameCreator().getGameFromPlayerID(playerID);
			ab = g.getPlayerByID(playerID).getActionBuilder();
			CommandAdapter ca = new CommandAdapter(g.getBoard());
			tca = ca.getBuildingArea(buildingCardArea);
			pt = ca.getPawnType(pawnType);
			ab.placePawnBuilding(tca, pt, servantsAdded);
			g.notifyObservers();
		}catch(Exception e){
			ErrorNotifier n = new ErrorNotifier(new SocketObserver(connection.getOutputStreamWriter()), "Bad command arguments");
			n.start();
			LOGGER.log(Level.SEVERE, "context", e);
		}
	}

}
