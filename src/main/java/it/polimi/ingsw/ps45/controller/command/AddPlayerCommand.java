package it.polimi.ingsw.ps45.controller.command;

import it.polimi.ingsw.ps45.controller.Connection;
import it.polimi.ingsw.ps45.controller.GameCreator;
import it.polimi.ingsw.ps45.controller.SocketObserver;

public class AddPlayerCommand implements Command{

	String playerID;
	
	public AddPlayerCommand(String id){
		this.playerID = id;
	}
	
	
	@Override
	public void run(Connection connection) {
		try {
			connection.getGameCreator().addPlayer(playerID, new SocketObserver(connection.getOutputStreamWriter()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
