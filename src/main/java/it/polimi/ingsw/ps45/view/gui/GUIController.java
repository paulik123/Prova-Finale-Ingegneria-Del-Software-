package it.polimi.ingsw.ps45.view.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;

import it.polimi.ingsw.ps45.client.ClientController;
import it.polimi.ingsw.ps45.controller.command.AddPlayerCommand;
import it.polimi.ingsw.ps45.controller.command.CommandHolder;
import it.polimi.ingsw.ps45.controller.command.ReconnectCommand;
import it.polimi.ingsw.ps45.gson.GsonWithInterface;


/**
 * Controller that listen to buttons in the view and sends commands to the server at the request of the user.
 */
public class GUIController implements ActionListener, ClientController{
	private static final Logger LOGGER = Logger.getLogger( GUIController.class.getName());
	private OutputStreamWriter os;
	private String playerID;
	private Gson gson;
	private GUI gui;
	
	/**
 	 * Constructor
	 * @param os OutputStreamWriter the stream the socket will write the serialized commands to.
	 * @param gui the gui that the controller will get the commands from.
	 * @param playerID the players identification string so the server knows from who the command came from.
	 */
	public GUIController(GUI gui, OutputStreamWriter os, String playerID){
		this.os = os;
		this.playerID = playerID;
		this.gui = gui;
		
		gson = GsonWithInterface.getGson();
	}
	

	/**
	 * Writes the serialized JSON Command to the OutputStream then flushes it to be sure the message has been sent. 
	 * @param message the message that will be written by the OutputStreamWriter
	 */
    public void send(String message) throws IOException {
        os.write(message+"\n");
        os.flush();
    }

	/**
	 * Parses the command from the GUI elements, serializes it, then sends it to the server. 
	 * @param e the ActionEvent that activated this method.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
		   CommandHolder ch = new CommandHolder(gui.getControlBoard().getCommand(), playerID);
			
		   String json = gson.toJson(ch);
		   send(json);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE, "context", e);
		}
		
	}
	
	/**
	 * Writes a AddPlayerCommand to the server without waiting for the user's input.
	 * @param the bonusTile that the player chose.
	 */
	public void sendJoinCommand(String bonusTile){

		try {
			CommandHolder ch = new CommandHolder(new AddPlayerCommand(bonusTile), playerID);
			String json = gson.toJson(ch);
			send(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE, "context", e);
		}
	}



	/**
	 * Writes a ReconnectCommand to the server without waiting for the user's input.
	 */
	@Override
	public void sendReconnectCommand() {
		try {
			CommandHolder ch = new CommandHolder(new ReconnectCommand(), playerID);
			String json = gson.toJson(ch);
			send(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE, "context", e);
		}
		
	}
	
}
