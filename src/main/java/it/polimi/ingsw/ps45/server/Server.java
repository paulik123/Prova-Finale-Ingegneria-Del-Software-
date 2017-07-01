package it.polimi.ingsw.ps45.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import it.polimi.ingsw.ps45.controller.Connection;
import it.polimi.ingsw.ps45.controller.GameCreator;

/**
 *
 * The server class that waits for new connections.
 *
 */

public class Server {
	private static int PORTNUMBER = 12345;
	
	ServerSocket server;
	GameCreator gameCreator;
	
	
	/**
 	 * Constructor
	 * @param  gameCreator the gameCreator of the server. It's the object that creates and manages existing games.
	 */
	public Server(GameCreator gameCreator){
		try {
			server = new ServerSocket(PORTNUMBER);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.gameCreator = gameCreator;
	}
	
	
	/**
 	 * The server waits for new connections in the while loop.
 	 * server.accept() is blocking.
 	 * A new connection is made for every sockets that connects.
	 */
    public void run(){
    	System.out.println("Server running");
        while(true){
            Socket socket;
            try {
                socket = server.accept();
                new Connection(socket, gameCreator).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
	/**
	 * Main function of the server.
	 */
    public static void main(String[] args){
    	GameCreator gc = new GameCreator();
    	Server s = new Server(gc);
    	s.run();
    }
    
    
}
