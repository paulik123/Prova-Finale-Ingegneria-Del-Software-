package it.polimi.ingsw.ps45.controller.command;

import java.util.logging.Level;
import java.util.logging.Logger;

import it.polimi.ingsw.ps45.controller.Connection;
import it.polimi.ingsw.ps45.model.actions.ActionBuilder;
import it.polimi.ingsw.ps45.model.area.NoCardArea;
import it.polimi.ingsw.ps45.model.game.Game;
import it.polimi.ingsw.ps45.model.player.PawnType;

/**
 * Command that allows the player to make a production action while also placing a pawn.
 */
public class PlacePawnProductionCommand implements Command{
	private static final Logger LOGGER = Logger.getLogger( PlacePawnProductionCommand.class.getName());
	private String productionArea;
	private String pawnType;
	private int servantsAdded;
	
	/**
 	 * 
 	 * @param  connection the players Connection used to get a reference to the gameCreator.
 	 * @param  pt the "color" of the pawn that the player wants to use.
 	 * @param  playerID the id of the player that "executes the command".
	 */
	public PlacePawnProductionCommand(String area, String pt, int servants){
		this.productionArea = area;
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
		NoCardArea nca = null;
		PawnType pt = null;
		ActionBuilder ab = null;
		try{
			g = connection.getGameCreator().getGameFromPlayerID(playerID);
			ab = g.getPlayerByID(playerID).getActionBuilder();
			CommandAdapter ca = new CommandAdapter(g.getBoard());
			nca = ca.getProductioArea(productionArea);
			pt = ca.getPawnType(pawnType);
			ab.placePawnProduction(nca, pt, servantsAdded);
			g.notifyObservers();
		}catch(Exception e){
			LOGGER.log(Level.SEVERE, "context", e);
		}
	}

}
