package it.polimi.ingsw.ps45.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import it.polimi.ingsw.ps45.controller.command.AddPlayerCommand;
import it.polimi.ingsw.ps45.controller.command.Command;
import it.polimi.ingsw.ps45.controller.command.CommandHolder;
import it.polimi.ingsw.ps45.gson.PropertyBasedInterfaceMarshal;
import it.polimi.ingsw.ps45.model.effects.Effect;
import it.polimi.ingsw.ps45.view.View;

public class CLIControllerThread extends Thread{
	private OutputStreamWriter os;
	private Scanner scanner;
	private String playerID;
	boolean x;
	private CommandParser cp;
	
	public CLIControllerThread(OutputStreamWriter os, Scanner scanner, String playerID){
		this.os = os;
		this.scanner = scanner;
		this.playerID = playerID;
		cp = new CommandParser();
	}
	
	public void run(){
        while(true){
        	String c = scanner.nextLine();

			try {
				CommandHolder ch = new CommandHolder(cp.parse(c), playerID);
	        	Gson gson = new GsonBuilder()
	                    .registerTypeAdapter(Effect.class,
	                            new PropertyBasedInterfaceMarshal())
	                    .registerTypeAdapter(Command.class,
	                            new PropertyBasedInterfaceMarshal()).create();
	     	   
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
	
}
