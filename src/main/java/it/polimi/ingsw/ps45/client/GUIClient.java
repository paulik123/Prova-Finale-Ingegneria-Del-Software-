package it.polimi.ingsw.ps45.client;


import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

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
					gui.addController(ctrl);
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
		    		gui.addController(ctrl);
		    	}catch(Exception e){
		    		screen.setMessage("Error: could not connect.");
		    	}
			}
		};
		new Thread(r).start();
    }
}


