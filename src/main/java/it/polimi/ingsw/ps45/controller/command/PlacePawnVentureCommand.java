package it.polimi.ingsw.ps45.controller.command;

import java.util.logging.Level;
import java.util.logging.Logger;

import it.polimi.ingsw.ps45.controller.Connection;
import it.polimi.ingsw.ps45.controller.SocketObserver;
import it.polimi.ingsw.ps45.model.actions.ActionBuilder;
import it.polimi.ingsw.ps45.model.area.cardarea.VentureCardArea;
import it.polimi.ingsw.ps45.model.cards.VentureMode;
import it.polimi.ingsw.ps45.model.game.ErrorNotifier;
import it.polimi.ingsw.ps45.model.game.Game;
import it.polimi.ingsw.ps45.model.player.PawnType;


/**
 * Command that allows the player to acquire a venture while also placing a pawn.
 */
public class PlacePawnVentureCommand implements Command{
	private static final Logger LOGGER = Logger.getLogger( PlacePawnVentureCommand.class.getName());
	private String ventureCardArea;
	private String pawnType;
	private int servantsAdded;
	private String ventureMode;
	
	
	/**
 	 * 
 	 * @param  connection the players Connection used to get a reference to the gameCreator.
 	 * @param  pt the "color" of the pawn that the player wants to use.
 	 * @param  playerID the id of the player that "executes the command".
	 */
	public PlacePawnVentureCommand(String area, String pt, int servants, String ventureMode){
		this.ventureCardArea = area;
		this.pawnType = pt;
		this.servantsAdded = servants;
		if(!(ventureMode == null || ventureMode.length() == 0)){
			this.ventureMode = ventureMode;
		}else{
			this.ventureMode = "first";
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
		VentureCardArea tca = null;
		PawnType pt = null;
		VentureMode vm = null;
		ActionBuilder ab = null;
		try{
			g = connection.getGameCreator().getGameFromPlayerID(playerID);
			ab = g.getPlayerByID(playerID).getActionBuilder();
			CommandAdapter ca = new CommandAdapter(g.getBoard());
			tca = ca.getVentureArea(ventureCardArea);
			pt = ca.getPawnType(pawnType);
			vm = ca.getVentureMode(ventureMode);
			ab.placePawnVenture(tca, pt, servantsAdded, vm);
			g.notifyObservers();
		}catch(Exception e){
			ErrorNotifier n = new ErrorNotifier(new SocketObserver(connection.getOutputStreamWriter()), "Bad command arguments");
			n.start();
			LOGGER.log(Level.SEVERE, "context", e);
		}
	}

}
