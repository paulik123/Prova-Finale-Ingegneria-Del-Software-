package it.polimi.ingsw.ps45.client;

import java.awt.EventQueue;
import java.net.Socket;

import it.polimi.ingsw.ps45.view.View;


/**
 * Contains the main method of the client. Does nothing but make the user choose between the CLI client or the GUI client.
 */
public class Client {
	
	
	protected transient Socket socket;
	protected transient ObserverThread observerThread;
	protected transient ClientController controller;
	protected transient View view;
	protected static final int PORTNUMBER = 12345;
	
	/**
	 * Main method of the client.
	 */
	public static void main(String[] args){
		if(args.length == 0){
			System.out.println("You have to enter the view mode as first argument.");
			System.out.println("Currently available views are: 'gui' and 'cli'.");
			System.exit(0);
		}
		String view = args[0];
		
		if( view.equals("gui") ){
			EventQueue.invokeLater(new Runnable(){

				@Override
				public void run() {
					GUIClient c = new GUIClient();
				}
				
			});
		}else if(view.equals("cli")){
			
			Runnable r = new Runnable(){

				@Override
				public void run() {
					CLIClient c = new CLIClient();
				}
				
			};
			new Thread(r).start();

		}else{
			System.out.println("Wrong arguments. Available views are 'gui' and 'cli'.");
		}
		
	}

}
