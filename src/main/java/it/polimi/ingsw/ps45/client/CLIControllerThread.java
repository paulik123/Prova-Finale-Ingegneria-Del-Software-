package it.polimi.ingsw.ps45.client;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;

import it.polimi.ingsw.ps45.controller.command.AddPlayerCommand;
import it.polimi.ingsw.ps45.controller.command.CommandHolder;
import it.polimi.ingsw.ps45.controller.command.ReconnectCommand;
import it.polimi.ingsw.ps45.gson.GsonWithInterface;

/**
 * Thread that listens to keyboard input and send commands to the server from the user.
 */
public class CLIControllerThread extends Thread implements ClientController{
	private static final Logger LOGGER = Logger.getLogger( CLIControllerThread.class.getName() );
	
	private OutputStreamWriter os;
	private Scanner scanner;
	private String playerID;
	private CommandParser cp;
	private Gson gson;
	
	/**
 	 * Constructor
	 * @param os OutputStreamWriter the stream the socket will write the serialized commands to.
	 * @param scanner the scanner the controller will take input from.
	 * @param playerID the players identification string so the server knows from who the command came from.
	 */
	public CLIControllerThread(OutputStreamWriter os, Scanner scanner, String playerID){
		this.os = os;
		this.scanner = scanner;
		this.playerID = playerID;
		cp = new CommandParser();
		gson = GsonWithInterface.getGson();
	}
	
	/**
	 * Contains a loop that continuously listens for input from the user.
	 * scanner.nextLine() is blocking.
	 */
	public void run(){
        while(true){
        	String c = scanner.nextLine();

			try {
				CommandHolder ch = new CommandHolder(cp.parse(c), playerID);

	     	   String json = gson.toJson(ch);
	     	   send(json);
			} catch (Exception e1) {
				LOGGER.log(Level.SEVERE, "context", e1);
			}

        }
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
