package it.polimi.ingsw.ps45.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import it.polimi.ingsw.ps45.view.CLI;

public class CLIClient extends Thread{
	
    private Socket socket;
    private ObserverThread observerThread;
    private Scanner scanner;
    private String ip;
    private String playerID;
    private String bonusTile;
    
    private static final int PORTNUMBER = 12345;
    
    public CLIClient(){
    	scanner = new Scanner(System.in);
    }
    
    public void run(){
    	getData();
    	
    	try {
			connectOrReconnect();
		} catch (IOException e) {
			System.out.println("Cannot connect to the server. The entered ip may be wrong or the server isn't online.");
		}
    }
    
    private void connectOrReconnect() throws UnknownHostException, IOException{
    	String command = "";
    	do{
        	System.out.println("Do you want to connect or reconnect? -> ['connect','reconnect']");
        	command = scanner.nextLine();
    	}while(!(command.equals("connect") || command.equals("reconnect")));
    	if(command.equals("connect")){
    		connect();
    	}else{
    		reconnect();
    	}

    	
    }
    
    private void reconnect() throws UnknownHostException, IOException{
		socket = new Socket(ip, PORTNUMBER);
        CLI cli = new CLI(scanner, playerID);
        CLIControllerThread controllerThread = new CLIControllerThread(new OutputStreamWriter(socket.getOutputStream()), scanner, playerID);
        observerThread = new ObserverThread(new BufferedReader(new InputStreamReader(socket.getInputStream())), cli);
        observerThread.start();
        controllerThread.start();
        controllerThread.sendReconnectCommand();
    }
    private void connect() throws UnknownHostException, IOException{
    	bonusTile = "";
    	do{
    		System.out.println("Enter your preferred Personal Bonus Tile: [1,2,3,4,5]");
    		bonusTile = scanner.nextLine();
    		if(!(bonusTile.equals("1") || bonusTile.equals("2") || bonusTile.equals("3") || bonusTile.equals("4") || bonusTile.equals("5"))){
    			System.out.println("Wrong bonus tile. Available ones are: [1,2,3,4,5]");
    		}
    	}while(!(bonusTile.equals("1") || bonusTile.equals("2") || bonusTile.equals("3") || bonusTile.equals("4") || bonusTile.equals("5")));
    	
		socket = new Socket(ip, PORTNUMBER);
        CLI cli = new CLI(scanner, playerID);
        CLIControllerThread controllerThread = new CLIControllerThread(new OutputStreamWriter(socket.getOutputStream()), scanner, playerID);
        observerThread = new ObserverThread(new BufferedReader(new InputStreamReader(socket.getInputStream())), cli);
        observerThread.start();
        controllerThread.start();
        controllerThread.sendJoinCommand((String) bonusTile);
    }
    
    private void getData(){
    	System.out.println("Lorenzo il Magnifico - CLI");
    	System.out.println("Enter the server IP address:");
    	ip = scanner.nextLine();
    	System.out.println("Enter your name:");
    	playerID = scanner.nextLine();
    }
	


}
