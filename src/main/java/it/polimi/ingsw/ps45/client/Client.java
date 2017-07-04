package it.polimi.ingsw.ps45.client;

import java.awt.EventQueue;


/**
 * Contains the main method of the client. Does nothing but make the user choose between the CLI client or the GUI client.
 */
public class Client {
	
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
					ClientFrame c = new ClientFrame();
					c.setVisible(true);
				}
				
			});
		}else if(view.equals("cli")){
			CLIClient c = new CLIClient();
			c.start();
		}else{
			System.out.println("Wrong arguments. Available views are 'gui' and 'cli'.");
		}
		
	}

}
