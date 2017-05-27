package it.polimi.ingsw.ps45.client;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import it.polimi.ingsw.ps45.controller.command.AddPlayerCommand;
import it.polimi.ingsw.ps45.controller.command.Command;
import it.polimi.ingsw.ps45.controller.command.CommandHolder;
import it.polimi.ingsw.ps45.gson.PropertyBasedInterfaceMarshal;
import it.polimi.ingsw.ps45.model.effects.Effect;
import it.polimi.ingsw.ps45.view.View;

public class Client {

    private Socket socket;
    private ControllerThread controllerThread;
    private ObserverThread observerThread;
    private View view;
    private String playerID;


    private static final int PORTNUMBER = 12345;
    public Client(String host) throws IOException {
        socket = new Socket(host, PORTNUMBER);
        //TODO HANDLE VIEW CREATION
        controllerThread = new ControllerThread(new OutputStreamWriter(socket.getOutputStream()), view, playerID);
        observerThread = new ObserverThread(new BufferedReader(new InputStreamReader(socket.getInputStream())), view);
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