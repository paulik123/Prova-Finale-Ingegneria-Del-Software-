package it.polimi.ingsw.ps45.controller.command;

import it.polimi.ingsw.ps45.controller.GameCreator;

public class AddPlayerCommand implements Command{

	String playerID;
	
	public AddPlayerCommand(String id){
		this.playerID = id;
	}
	
	
	@Override
	public void run(GameCreator gameCreator) {
		try {
			gameCreator.addPlayer(playerID);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
