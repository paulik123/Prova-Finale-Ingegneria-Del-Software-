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

/**
 * Contains all the objects needed to support the communication between the server and client.
 */

public class Connection extends Thread {

    private Socket s;
    private BufferedReader br;
    private OutputStreamWriter os;
    private GameCreator gameCreator;

	/**
 	 * Constructor
	 * @param  socket the socket with which the server communicates with the client.
	 * @param  gameCreator the gameCreator of the server. It's the object that creates and manages existing games.
	 */
    public Connection(Socket socket, GameCreator gameCreator) throws IOException {
        this.s = socket;
        this.gameCreator = gameCreator;
        br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        os = new OutputStreamWriter(socket.getOutputStream());
    }

    
	/**
 	 * Thread that waits for commands from the client.
 	 * br.readLine() is blocking.
 	 * Each new line from the BufferedReader is serialized in a command that is then run.
	 */
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
    
    /**
     * @return A BufferedReader that listens to the socket input stream.
     */
	public BufferedReader getBufferedReader() {
		return br;
	}
	
	
    /**
     * @return A OutputStreamWriter that sends strings to the socket output stream.
     */
	public OutputStreamWriter getOutputStreamWriter(){
		return os;
	}
	
    /**
     * @return The gameCreator that the connection interacts with.
     */
	public GameCreator getGameCreator() {
		return gameCreator;
	}
	
	


}