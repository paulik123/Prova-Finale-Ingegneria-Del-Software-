package it.polimi.ingsw.ps45.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;

import it.polimi.ingsw.ps45.gson.GsonWithInterface;
import it.polimi.ingsw.ps45.model.game.ServerResponseWrapper;
import it.polimi.ingsw.ps45.view.View;


/**
 * Thread that continuously listens to the socket's input stream.
 */
public class ObserverThread extends Thread{
	
	private static final Logger LOGGER = Logger.getLogger( ObserverThread.class.getName() );
	private BufferedReader br;
	private ClientServerResponseVisitor serverResponseVisitor;
	private Gson gson;
	
	
	/**
 	 * Constructor
	 * @param br A buffered reader that listens to the socket's input stream.
	 * @param view the client's view so the the thread know where to send the messages from the server to.
	 */
	public ObserverThread(BufferedReader br, View view){
		this.br = br;
		serverResponseVisitor = new ClientServerResponseVisitor(view);
		gson = GsonWithInterface.getGson();
	}
	
	/**
	 * Contains a loop that continuously listens for responses from the server.
	 * br.readLine() is blocking.
	 * 
	 * Uses the visitor pattern so it can know which type of response the server sent.
	 */
	public void run(){
        String fromServer = "";
      

        try {
            while((fromServer = br.readLine()) != null) {
            	
            	ServerResponseWrapper respWrapper = gson.fromJson(fromServer, ServerResponseWrapper.class);
				respWrapper.getResponse().accept(serverResponseVisitor);	
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "context", e);
        }
    }

	/**
	 * @return the thread's buffered reader.
	 */
	public BufferedReader getBufferedReader() {
		return br;
	}
	
}
