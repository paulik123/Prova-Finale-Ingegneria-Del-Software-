package it.polimi.ingsw.ps45.client;

import java.awt.EventQueue;
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


/**
 * Thread that continuously listens to the socket's input stream.
 */
public class ObserverThread extends Thread{
	private BufferedReader br;
	private ClientServerResponseVisitor serverResponseVisitor;
	private Gson gson;
	
	
	/**
 	 * Constructor
	 * @param br A buffered reader that listens to the socket's input stream.
	 * @param view the client's view so the the thread know where to send the messages from the server to.
	 */
	public ObserverThread(BufferedReader br, View view){
		this.br = br;
		serverResponseVisitor = new ClientServerResponseVisitor(view);
		gson = GsonWithInterface.getGson();
	}
	
	/**
	 * Contains a loop that continuously listens for responses from the server.
	 * br.readLine() is blocking.
	 * 
	 * Uses the visitor pattern so it can know which type of response the server sent.
	 */
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

	/**
	 * @return the thread's buffered reader.
	 */
	public BufferedReader getBufferedReader() {
		return br;
	}
	
}
