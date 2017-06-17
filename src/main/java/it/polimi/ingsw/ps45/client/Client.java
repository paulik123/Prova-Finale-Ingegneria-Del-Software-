package it.polimi.ingsw.ps45.client;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

import it.polimi.ingsw.ps45.view.CLI;
import it.polimi.ingsw.ps45.view.View;
import it.polimi.ingsw.ps45.view.gui.GUI;
import it.polimi.ingsw.ps45.view.gui.GUIController;

public class Client {

    private Socket socket;
    private ObserverThread observerThread;

    private String playerID;
    Scanner scanner;


    private static final int PORTNUMBER = 12345;
    public Client(String host) throws IOException {
    	scanner = new Scanner(System.in);
    	System.out.println("Enter your username:");
    	playerID = scanner.nextLine();
    	
    	System.out.println("\nHello " + playerID + ". FOR GUI enter \"gui\" as next command. Else the game will use CLI.\n");
    	if(scanner.nextLine().toLowerCase().equals("gui")){
    		socket = new Socket(host, PORTNUMBER);
    		GUI gui = new GUI(playerID);
    		GUIController controller = new GUIController(gui, new OutputStreamWriter(socket.getOutputStream()), playerID);
    		observerThread = new ObserverThread(new BufferedReader(new InputStreamReader(socket.getInputStream())), gui);
    		observerThread.start();
    		controller.sendJoinCommand();
    		gui.addController(controller);
    	}
    	else{
            socket = new Socket(host, PORTNUMBER);

            CLI cli = new CLI(scanner, playerID);
            CLIControllerThread controllerThread = new CLIControllerThread(new OutputStreamWriter(socket.getOutputStream()), scanner, playerID);
            observerThread = new ObserverThread(new BufferedReader(new InputStreamReader(socket.getInputStream())), cli);
            observerThread.start();
            controllerThread.start();
    	}
    }

    public static void main(String[] args) throws IOException{
    	Client c = null;
        try {
           c = new Client("127.0.0.1");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}