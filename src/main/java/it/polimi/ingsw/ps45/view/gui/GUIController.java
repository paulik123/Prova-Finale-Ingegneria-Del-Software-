package it.polimi.ingsw.ps45.view.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import it.polimi.ingsw.ps45.client.CommandParser;
import it.polimi.ingsw.ps45.controller.command.Command;
import it.polimi.ingsw.ps45.controller.command.CommandHolder;
import it.polimi.ingsw.ps45.gson.PropertyBasedInterfaceMarshal;
import it.polimi.ingsw.ps45.model.effects.Effect;

public class GUIController implements ActionListener{
	private OutputStreamWriter os;
	private String playerID;
	private Gson gson;
	private GUI gui;
	
	public GUIController(GUI gui, OutputStreamWriter os, String playerID){
		this.os = os;
		this.playerID = playerID;
		this.gui = gui;
		
		gson = new GsonBuilder()
                .registerTypeAdapter(Effect.class,
                        new PropertyBasedInterfaceMarshal())
                .registerTypeAdapter(Command.class,
                        new PropertyBasedInterfaceMarshal()).create();
	}
	

	
    public void send(String message) throws IOException {
        os.write(message+"\n");
        os.flush();
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
		   CommandHolder ch = new CommandHolder(gui.getControlBoard().getCommand(), playerID);
			
		   String json = gson.toJson(ch);
		   send(json);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
}
