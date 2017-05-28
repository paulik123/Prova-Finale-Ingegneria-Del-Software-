package it.polimi.ingsw.ps45.controller.command;

import it.polimi.ingsw.ps45.controller.Connection;
import it.polimi.ingsw.ps45.model.actions.ActionBuilder;
import it.polimi.ingsw.ps45.model.area.NoCardArea;
import it.polimi.ingsw.ps45.model.area.cardarea.BuildingCardArea;
import it.polimi.ingsw.ps45.model.area.cardarea.CharacterCardArea;
import it.polimi.ingsw.ps45.model.area.cardarea.TerritoryCardArea;
import it.polimi.ingsw.ps45.model.area.cardarea.VentureCardArea;
import it.polimi.ingsw.ps45.model.cards.VentureMode;
import it.polimi.ingsw.ps45.model.game.Game;
import it.polimi.ingsw.ps45.model.player.PawnType;

public class NoPawnVentureCommand implements Command{

	private String ventureCardArea;
	private int servantsAdded;
	private String ventureMode;
	
	public NoPawnVentureCommand(String area, int servants, String ventureMode){
		this.ventureCardArea = area;
		this.servantsAdded = servants;
		this.ventureMode = ventureMode;
	}
	
	
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
		}catch(Exception e){
			e.printStackTrace();
		}
		try {
			ab.NoPawnVenture(tca, servantsAdded, vm);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
