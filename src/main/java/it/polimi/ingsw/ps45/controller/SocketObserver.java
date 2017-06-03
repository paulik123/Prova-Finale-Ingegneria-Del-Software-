package it.polimi.ingsw.ps45.controller;

import java.io.IOException;
import java.io.OutputStreamWriter;

import com.google.gson.Gson;

import it.polimi.ingsw.ps45.gson.GsonWithInterface;
import it.polimi.ingsw.ps45.model.game.Observer;
import it.polimi.ingsw.ps45.model.game.ServerResponseWrapper;

public class SocketObserver implements Observer{
	OutputStreamWriter os;
	
	public SocketObserver(OutputStreamWriter os){
		this.os = os;
	}

	@Override
	public void notify(String json) {
        try {
        	
        	StringBuilder sb = new StringBuilder();
        	sb.append(json);
        	sb.append("\n");

        	
			os.write(sb.toString());
			os.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
