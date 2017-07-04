package it.polimi.ingsw.ps45.controller.command;

import java.util.logging.Level;
import java.util.logging.Logger;

import it.polimi.ingsw.ps45.controller.Connection;
import it.polimi.ingsw.ps45.model.actions.ActionBuilder;
import it.polimi.ingsw.ps45.model.area.cardarea.CharacterCardArea;
import it.polimi.ingsw.ps45.model.game.Game;

/**
 * Command that allows the player to acquire a character without placing a pawn.
 */
public class NoPawnCharacterCommand implements Command{
	private static final Logger LOGGER = Logger.getLogger( NoPawnCharacterCommand.class.getName());
	private String characterCardArea;
	private int servantsAdded;
	
	/**
 	 * Constructor
	 * @param  area the area in which the player wants to take the card from.
	 * @param  servants the number of servants that the player wants to add.
	 */
	public NoPawnCharacterCommand(String area, int servants){
		this.characterCardArea = area;
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
		CharacterCardArea tca = null;
		ActionBuilder ab = null;
		try{
			g = connection.getGameCreator().getGameFromPlayerID(playerID);
			ab = g.getPlayerByID(playerID).getActionBuilder();
			CommandAdapter ca = new CommandAdapter(g.getBoard());
			tca = ca.getCharacterArea(characterCardArea);
			ab.NoPawnCharacter(tca, servantsAdded);
			g.notifyObservers();
		}catch(Exception e){
			LOGGER.log(Level.SEVERE, "context", e);
		}
	}

}
