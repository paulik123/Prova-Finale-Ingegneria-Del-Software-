package it.polimi.ingsw.ps45.controller.command;

import it.polimi.ingsw.ps45.controller.Connection;
import it.polimi.ingsw.ps45.model.actions.ActionBuilder;
import it.polimi.ingsw.ps45.model.actions.CouncilPrivilege.CouncilPrivilege;
import it.polimi.ingsw.ps45.model.area.cardarea.VentureCardArea;
import it.polimi.ingsw.ps45.model.cards.VentureMode;
import it.polimi.ingsw.ps45.model.game.Game;
import it.polimi.ingsw.ps45.model.player.PawnType;

public class ExchangeCouncilPrivilegeTwoCommand implements Command{
	
	String cp1;
	String cp2;
	
	public ExchangeCouncilPrivilegeTwoCommand(String cp1, String cp2){
		this.cp1 = cp1;
		this.cp2 = cp2;
	}

	@Override
	public void run(Connection connection, String playerID) {
		Game g;
		ActionBuilder ab = null;
		CouncilPrivilege privilege1 = null;
		CouncilPrivilege privilege2 = null;
		
		try{
			g = connection.getGameCreator().getGameFromPlayerID(playerID);
			ab = g.getPlayerByID(playerID).getActionBuilder();
			CommandAdapter ca = new CommandAdapter(g.getBoard());
			privilege1 = ca.getCouncilPrivilege(cp1);
			privilege2 = ca.getCouncilPrivilege(cp2); 
		}catch(Exception e){
			e.printStackTrace();
		}
		try {
			ab.exchangeCouncilPrivilegeTwo(privilege1, privilege2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
