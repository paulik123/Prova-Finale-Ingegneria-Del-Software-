package it.polimi.ingsw.ps45.controller.command;

import it.polimi.ingsw.ps45.controller.Connection;
import it.polimi.ingsw.ps45.controller.SocketObserver;

public class ReconnectCommand implements Command{

	
	@Override
	public void run(Connection connection, String playerID) {
		try {
			connection.getGameCreator().reconnect(playerID, new SocketObserver(connection.getOutputStreamWriter()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
