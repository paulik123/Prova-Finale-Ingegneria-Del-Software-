package it.polimi.ingsw.ps45.controller.command;

import it.polimi.ingsw.ps45.controller.Connection;
import it.polimi.ingsw.ps45.model.actions.ActionBuilder;
import it.polimi.ingsw.ps45.model.area.cardarea.VentureCardArea;
import it.polimi.ingsw.ps45.model.cards.VentureMode;
import it.polimi.ingsw.ps45.model.game.Game;
import it.polimi.ingsw.ps45.model.player.PawnType;

public class PlacePawnVentureCommand implements Command{

	private String ventureCardArea;
	private String pawnType;
	private int servantsAdded;
	private String ventureMode;
	
	public PlacePawnVentureCommand(String area, String pt, int servants, String ventureMode){
		this.ventureCardArea = area;
		this.pawnType = pt;
		this.servantsAdded = servants;
		this.ventureMode = ventureMode;
	}
	
	
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
			e.printStackTrace();
		}
	}

}
