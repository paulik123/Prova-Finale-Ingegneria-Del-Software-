package it.polimi.ingsw.ps45.client;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

import it.polimi.ingsw.ps45.view.CLI;
import it.polimi.ingsw.ps45.view.View;

public class Client {

    private Socket socket;
    private CLIControllerThread controllerThread;
    private ObserverThread observerThread;
    private View view;
    private String playerID;
    Scanner scanner;


    private static final int PORTNUMBER = 12345;
    public Client(String host) throws IOException {
    	scanner = new Scanner(System.in);
    	System.out.println("Enter your username:");
    	playerID = scanner.nextLine();
    	
    	
        socket = new Socket(host, PORTNUMBER);

        view = new CLI(scanner, playerID);
        System.out.println("After view");
        controllerThread = new CLIControllerThread(new OutputStreamWriter(socket.getOutputStream()), scanner, playerID);
        System.out.println("After CLICONTROLLER new");
        observerThread = new ObserverThread(new BufferedReader(new InputStreamReader(socket.getInputStream())), view);
        System.out.println("After observerthread new");
        observerThread.start();
        System.out.println("After observerthread start");
        controllerThread.start();
        System.out.println("After controllerthread start");
        
    }

    public static void main(String[] args) throws IOException{
    	Client c = null;
        try {
           c = new Client("127.0.0.1");
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
        Scanner scanner = new Scanner(System.in);

       while(true){
    	   
    	   AddPlayerCommand command = new AddPlayerCommand(scanner.nextLine());
    	   CommandHolder ch = new CommandHolder(command);
    	   
    	   Gson gson = new GsonBuilder()
                   .registerTypeAdapter(Effect.class,
                           new PropertyBasedInterfaceMarshal())
                   .registerTypeAdapter(Command.class,
                           new PropertyBasedInterfaceMarshal()).create();
    	   
    	   String json = gson.toJson(ch);
    	   
    	   c.send(json);
       }
       */
    }

}