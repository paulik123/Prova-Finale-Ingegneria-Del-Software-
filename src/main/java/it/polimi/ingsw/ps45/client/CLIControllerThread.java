package it.polimi.ingsw.ps45.client;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import it.polimi.ingsw.ps45.controller.command.AddPlayerCommand;
import it.polimi.ingsw.ps45.controller.command.Command;
import it.polimi.ingsw.ps45.controller.command.CommandHolder;
import it.polimi.ingsw.ps45.controller.command.ReconnectCommand;
import it.polimi.ingsw.ps45.gson.GsonWithInterface;
import it.polimi.ingsw.ps45.gson.PropertyBasedInterfaceMarshal;
import it.polimi.ingsw.ps45.model.effects.Effect;

public class CLIControllerThread extends Thread implements ClientController{
	private OutputStreamWriter os;
	private Scanner scanner;
	private String playerID;
	private CommandParser cp;
	private Gson gson;
	
	public CLIControllerThread(OutputStreamWriter os, Scanner scanner, String playerID){
		this.os = os;
		this.scanner = scanner;
		this.playerID = playerID;
		cp = new CommandParser();
		gson = GsonWithInterface.getGson();
	}
	
	public void run(){
        while(true){
        	String c = scanner.nextLine();

			try {
				CommandHolder ch = new CommandHolder(cp.parse(c), playerID);

	     	   String json = gson.toJson(ch);
	     	   send(json);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

        }
    }

	
    public void send(String message) throws IOException {
        os.write(message+"\n");
        os.flush();
    }
    
	public void sendJoinCommand(String bonusTile){

		try {
			CommandHolder ch = new CommandHolder(new AddPlayerCommand(bonusTile), playerID);
			String json = gson.toJson(ch);
			send(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void sendReconnectCommand() {
		try {
			CommandHolder ch = new CommandHolder(new ReconnectCommand(), playerID);
			String json = gson.toJson(ch);
			send(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
