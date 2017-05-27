package it.polimi.ingsw.ps45.controller;


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

public class Client {

    Socket socket;
    OutputStreamWriter os;
    BufferedReader br;
    public static Client c;


    private static final int PORTNUMBER = 12345;
    public Client(String host) throws IOException {
        socket = new Socket(host, PORTNUMBER);
        os = new OutputStreamWriter(socket.getOutputStream());
        br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void send(String message) throws IOException {
        os.write(message+"\n");
        os.flush();
    }

    public static void main(String[] args) throws IOException{


        try {
            c = new Client("127.0.0.1");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
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
    }

}