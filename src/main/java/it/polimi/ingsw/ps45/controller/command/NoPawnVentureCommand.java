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

/**
 * Command that allows the player to acquire a venture without placing a pawn.
 */
public class NoPawnVentureCommand implements Command{
	private static final Logger LOGGER = Logger.getLogger( NoPawnVentureCommand.class.getName());
	private String ventureCardArea;
	private int servantsAdded;
	private String ventureMode;
	
	/**
 	 * Constructor
	 * @param  area the area in which the player wants to take the card from.
	 * @param  servants the number of servants that the player wants to add.
	 */
	public NoPawnVentureCommand(String area, int servants, String ventureMode){
		this.ventureCardArea = area;
		this.servantsAdded = servants;
		this.ventureMode = ventureMode;
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
		VentureMode vm = null;
		ActionBuilder ab = null;
		try{
			g = connection.getGameCreator().getGameFromPlayerID(playerID);
			ab = g.getPlayerByID(playerID).getActionBuilder();
			CommandAdapter ca = new CommandAdapter(g.getBoard());
			tca = ca.getVentureArea(ventureCardArea);
			vm = ca.getVentureMode(ventureMode);
			ab.NoPawnVenture(tca, servantsAdded, vm);
			g.notifyObservers();
		}catch(Exception e){
			ErrorNotifier n = new ErrorNotifier(new SocketObserver(connection.getOutputStreamWriter()), "Bad command arguments");
			n.start();
			LOGGER.log(Level.SEVERE, "context", e);
		}
	}

}
