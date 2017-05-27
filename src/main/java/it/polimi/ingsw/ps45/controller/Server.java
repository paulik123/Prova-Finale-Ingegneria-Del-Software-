package it.polimi.ingsw.ps45.controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private static int PORTNUMBER = 12345;
	
	ServerSocket server;
	GameCreator gameCreator;
	
	public Server(GameCreator gameCreator){
		try {
			server = new ServerSocket(PORTNUMBER);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.gameCreator = gameCreator;
	}
	
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
    
    public static void main(String[] args){
    	GameCreator gc = new GameCreator();
    	Server s = new Server(gc);
    	s.run();
    }
    
    
}
