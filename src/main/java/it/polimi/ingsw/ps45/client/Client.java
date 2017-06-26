package it.polimi.ingsw.ps45.client;

import java.awt.EventQueue;

public class Client {
	
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
