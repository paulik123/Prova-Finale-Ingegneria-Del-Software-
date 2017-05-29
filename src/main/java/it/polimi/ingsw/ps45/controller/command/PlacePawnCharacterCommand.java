package it.polimi.ingsw.ps45.controller.command;

import it.polimi.ingsw.ps45.controller.Connection;
import it.polimi.ingsw.ps45.model.actions.ActionBuilder;
import it.polimi.ingsw.ps45.model.area.NoCardArea;
import it.polimi.ingsw.ps45.model.area.cardarea.CharacterCardArea;
import it.polimi.ingsw.ps45.model.area.cardarea.TerritoryCardArea;
import it.polimi.ingsw.ps45.model.game.Game;
import it.polimi.ingsw.ps45.model.player.PawnType;

public class PlacePawnCharacterCommand implements Command{

	private String characterCardArea;
	private String pawnType;
	private int servantsAdded;
	
	public PlacePawnCharacterCommand(String area, String pt, int servants){
		this.characterCardArea = area;
		this.pawnType = pt;
		this.servantsAdded = servants;
	}
	
	
	@Override
	public void run(Connection connection, String playerID) {
		Game g;
		CharacterCardArea tca = null;
		PawnType pt = null;
		ActionBuilder ab = null;
		try{
			g = connection.getGameCreator().getGameFromPlayerID(playerID);
			ab = g.getPlayerByID(playerID).getActionBuilder();
			CommandAdapter ca = new CommandAdapter(g.getBoard());
			tca = ca.getCharacterArea(characterCardArea);
			pt = ca.getPawnType(pawnType);
			ab.placePawnCharacter(tca, pt, servantsAdded);
			g.notifyObservers();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
