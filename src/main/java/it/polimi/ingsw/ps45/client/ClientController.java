package it.polimi.ingsw.ps45.client;

import java.io.IOException;

public interface ClientController {
	
	public void send(String message) throws IOException;
	public void sendJoinCommand(String bonusTile);
	public void sendReconnectCommand();

}
