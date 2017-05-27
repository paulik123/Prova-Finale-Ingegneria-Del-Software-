package it.polimi.ingsw.ps45.view;

import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import it.polimi.ingsw.ps45.controller.command.Command;
import it.polimi.ingsw.ps45.gson.GsonWithInterface;
import it.polimi.ingsw.ps45.gson.PropertyBasedInterfaceMarshal;
import it.polimi.ingsw.ps45.model.effects.Effect;
import it.polimi.ingsw.ps45.model.game.Game;
import it.polimi.ingsw.ps45.model.game.GameData;
import it.polimi.ingsw.ps45.model.player.BonusTile;
import it.polimi.ingsw.ps45.model.player.Player;

public class CLI extends View{
	
	Scanner scanner;
	
	public CLI(Scanner scanner){
		this.scanner = scanner;
	}

	@Override
	public String getCommand() {
		
		String out = scanner.nextLine();
		return out;
	}

	@Override
	public void updateView(String gameJSON) {
		Gson gson = GsonWithInterface.getGson();
		Game g = gson.fromJson(gameJSON, Game.class);
		
		System.out.println(g.getStatus());
		
		
		for(Player p: g.getPlayers()){
			System.out.println(p.getPlayerID());
			System.out.println(p.getResourceSet().getResources());
		}
		
		
	}

}
