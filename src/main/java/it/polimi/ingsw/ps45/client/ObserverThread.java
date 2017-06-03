package it.polimi.ingsw.ps45.client;

import java.io.BufferedReader;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import it.polimi.ingsw.ps45.controller.command.Command;
import it.polimi.ingsw.ps45.controller.command.CommandHolder;
import it.polimi.ingsw.ps45.gson.GsonWithInterface;
import it.polimi.ingsw.ps45.gson.PropertyBasedInterfaceMarshal;
import it.polimi.ingsw.ps45.model.effects.Effect;
import it.polimi.ingsw.ps45.model.game.ServerResponseWrapper;
import it.polimi.ingsw.ps45.view.View;

public class ObserverThread extends Thread{
	private BufferedReader br;
	CLIServerResponseVisitor serverResponseVisitor;
	Gson gson;
	
	public ObserverThread(BufferedReader br, View view){
		this.br = br;
		serverResponseVisitor = new CLIServerResponseVisitor(view);
		gson = GsonWithInterface.getGson();
	}
	
	public void run(){
        String fromServer = "";
      

        try {
            while((fromServer = br.readLine()) != null) {
            	ServerResponseWrapper respWrapper = gson.fromJson(fromServer, ServerResponseWrapper.class);
            	respWrapper.getResponse().accept(serverResponseVisitor);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	public BufferedReader getBufferedReader() {
		return br;
	}
	
}
