package it.polimi.ingsw.ps45.client;

import java.io.BufferedReader;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import it.polimi.ingsw.ps45.controller.command.Command;
import it.polimi.ingsw.ps45.controller.command.CommandHolder;
import it.polimi.ingsw.ps45.gson.PropertyBasedInterfaceMarshal;
import it.polimi.ingsw.ps45.model.effects.Effect;
import it.polimi.ingsw.ps45.view.View;

public class ObserverThread extends Thread{
	private BufferedReader br;
	private View view;
	
	public ObserverThread(BufferedReader br, View view){
		this.br = br;
		this.view = view;
	}
	
	public void run(){
        String fromClient = "";
      

        System.out.println("ObserverThread waiting");
        try {
            while((fromClient = br.readLine()) != null) {
            		System.out.println("This is ObserverThread. Json: ");
            		System.out.println(fromClient);
            		view.updateView(fromClient);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	public BufferedReader getBufferedReader() {
		return br;
	}
	
}
