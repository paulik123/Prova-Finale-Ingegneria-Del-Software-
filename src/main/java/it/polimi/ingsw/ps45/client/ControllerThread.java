package it.polimi.ingsw.ps45.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import it.polimi.ingsw.ps45.controller.command.Command;
import it.polimi.ingsw.ps45.controller.command.CommandHolder;
import it.polimi.ingsw.ps45.gson.PropertyBasedInterfaceMarshal;
import it.polimi.ingsw.ps45.model.effects.Effect;
import it.polimi.ingsw.ps45.view.View;

public class ControllerThread extends Thread{
	private OutputStreamWriter os;
	private View view;
	
	public ControllerThread(OutputStreamWriter os, View view){
		this.os = os;
		this.view = view;
	}
	
	public void run(){
        while(view != null){
        	String c = view.getCommand();
        	//parse the string and send the command 
        }
    }

	
    public void send(String message) throws IOException {
        os.write(message+"\n");
        os.flush();
    }
	
}
