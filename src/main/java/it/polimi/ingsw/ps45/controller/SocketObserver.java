package it.polimi.ingsw.ps45.controller;

import java.io.IOException;
import java.io.OutputStreamWriter;

import it.polimi.ingsw.ps45.model.game.Observer;

public class SocketObserver implements Observer{
	OutputStreamWriter os;
	
	public SocketObserver(OutputStreamWriter os){
		this.os = os;
	}

	@Override
	public void notify(String json) {
        try {
        	
			os.write(json);
			os.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
