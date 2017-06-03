package it.polimi.ingsw.ps45.controller.command;

import it.polimi.ingsw.ps45.controller.Connection;
import it.polimi.ingsw.ps45.model.actions.ActionBuilder;
import it.polimi.ingsw.ps45.model.actions.CouncilPrivilege.CouncilPrivilege;
import it.polimi.ingsw.ps45.model.game.Game;

public class ExchangeCouncilPrivilegeThreeCommand implements Command{
	
	String cp1;
	String cp2;
	String cp3;
	
	public ExchangeCouncilPrivilegeThreeCommand(String cp1, String cp2, String cp3){
		this.cp1 = cp1;
		this.cp2 = cp2;
		this.cp3 = cp3;
	}

	@Override
	public void run(Connection connection, String playerID) {
		Game g;
		ActionBuilder ab = null;
		CouncilPrivilege privilege1 = null;
		CouncilPrivilege privilege2 = null;
		CouncilPrivilege privilege3 = null;
		
		try{
			g = connection.getGameCreator().getGameFromPlayerID(playerID);
			ab = g.getPlayerByID(playerID).getActionBuilder();
			CommandAdapter ca = new CommandAdapter(g.getBoard());
			privilege1 = ca.getCouncilPrivilege(cp1);
			privilege2 = ca.getCouncilPrivilege(cp2);
			privilege3 = ca.getCouncilPrivilege(cp3); 
			ab.exchangeCouncilPrivilegeThree(privilege1, privilege2, privilege3);
			g.notifyObservers();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
