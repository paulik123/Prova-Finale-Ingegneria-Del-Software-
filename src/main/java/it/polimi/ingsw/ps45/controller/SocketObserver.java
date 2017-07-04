package it.polimi.ingsw.ps45.controller;

import java.io.IOException;
import java.io.OutputStreamWriter;

import it.polimi.ingsw.ps45.model.game.Observer;

/**
 * Implementation of the Observer interface that uses socket, so the clients can be notified.
 */

public class SocketObserver implements Observer{
	private OutputStreamWriter os;
	
	public SocketObserver(OutputStreamWriter os){
		this.os = os;
	}

	@Override
	public void notify(String json) throws IOException {

        	StringBuilder sb = new StringBuilder();
        	sb.append(json);
        	sb.append("\n");

        	
			os.write(sb.toString());
			os.flush();

	}
	
	

}
