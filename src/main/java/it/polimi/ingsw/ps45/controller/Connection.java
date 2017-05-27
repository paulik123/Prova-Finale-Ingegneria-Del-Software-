package it.polimi.ingsw.ps45.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import it.polimi.ingsw.ps45.controller.command.Command;
import it.polimi.ingsw.ps45.controller.command.CommandHolder;
import it.polimi.ingsw.ps45.gson.PropertyBasedInterfaceMarshal;
import it.polimi.ingsw.ps45.model.effects.Effect;

public class Connection extends Thread {

    private Socket s;
    private BufferedReader br;
    private OutputStreamWriter os;
    private GameCreator gameCreator;


    public Connection(Socket socket, GameCreator gameCreator) throws IOException {
        this.s = socket;
        this.gameCreator = gameCreator;
        br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        os = new OutputStreamWriter(socket.getOutputStream());
    }

    public void run(){
        String fromClient = "";
        
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Effect.class,
                        new PropertyBasedInterfaceMarshal())
                .registerTypeAdapter(Command.class,
                        new PropertyBasedInterfaceMarshal()).create();

        System.out.println("New connection established");
        try {
            while((fromClient = br.readLine()) != null) {
                CommandHolder c = gson.fromJson(fromClient, CommandHolder.class);
                c.runCommand(this);
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	public BufferedReader getBufferedReader() {
		return br;
	}
	
	public OutputStreamWriter getOutputStreamWriter(){
		return os;
	}

	public GameCreator getGameCreator() {
		return gameCreator;
	}
	
	


}