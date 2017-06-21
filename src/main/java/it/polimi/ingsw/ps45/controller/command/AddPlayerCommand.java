package it.polimi.ingsw.ps45.controller.command;

import it.polimi.ingsw.ps45.controller.Connection;
import it.polimi.ingsw.ps45.controller.SocketObserver;

public class AddPlayerCommand implements Command{

	private String bonusTile;
	
	public AddPlayerCommand(String bonusTile){
		this.bonusTile = bonusTile;
	}
	
	
	@Override
	public void run(Connection connection, String playerID) {
		try {
			connection.getGameCreator().addPlayer(playerID, bonusTile, new SocketObserver(connection.getOutputStreamWriter()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
