package it.polimi.ingsw.ps45.client;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import it.polimi.ingsw.ps45.view.gui.GUI;
import it.polimi.ingsw.ps45.view.gui.GUIController;

/**
 * Client that runs the GUI view.
 */

public class GUIClient extends Client{
	
	private GUIClientWelcomeScreen screen;
    

    
	/**
 	 * Constructor
 	 * Doesn't do any initialization because it has to get input from user first.
	 */
    public GUIClient(){
    	screen = new GUIClientWelcomeScreen(this);
    }
    
	
    
    
	/**
	 * Called when the user clicks the reconnect button in the welcome screen.
	 * It tries to reconnect to the server and then it send a ReconnectCommand to the server.
	 */
    public void reconnectButtonPressed(){
    	Runnable r = new Runnable(){

			@Override
			public void run() {
		    	try{
		    		String playerID = screen.getNameText();
					socket = new Socket(screen.getIpText(), PORTNUMBER);
					
					GUI gui = new GUI(playerID);
					view = gui;
					
					GUIController ctrl = new GUIController(gui, new OutputStreamWriter(socket.getOutputStream()), playerID);
					controller = ctrl;
					
					observerThread = new ObserverThread(new BufferedReader(new InputStreamReader(socket.getInputStream())), gui);
					observerThread.start();
					
					controller.sendReconnectCommand();
					gui.addListener(ctrl);
		    	}catch(Exception e){
		    		screen.setMessage("Error: could not reconnect.");
		    	}
			}
		};
		new Thread(r).start();
    }
    
	/**
	 * Called when the user clicks the connect button in the welcome screen.
	 * It tries to connect to the server and then it send a AddPlayerCommand to the server.
	 */
    public void connectButtonPressed(){
    	Runnable r = new Runnable(){

			@Override
			public void run() {
		    	try{
		        	String playerID = screen.getNameText();
		    		socket = new Socket(screen.getIpText(), PORTNUMBER);
		    		
		    		GUI gui = new GUI(playerID);
		    		view = gui;
		    		
		    		GUIController ctrl = new GUIController(gui, new OutputStreamWriter(socket.getOutputStream()), playerID);
		    		controller = ctrl;
		    		
		    		observerThread = new ObserverThread(new BufferedReader(new InputStreamReader(socket.getInputStream())), gui);
		    		observerThread.start();
		    		
		    		controller.sendJoinCommand(screen.getBonusTile());
		    		gui.addListener(ctrl);
		    		screen.setMessage("Connected to server.");
		    	}catch(Exception e){
		    		screen.setMessage("Error: could not connect.");
		    	}
			}
		};
		new Thread(r).start();
    }
}


