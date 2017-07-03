package it.polimi.ingsw.ps45.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import it.polimi.ingsw.ps45.client.ServerResponseVisitor;
import it.polimi.ingsw.ps45.controller.command.Command;
import it.polimi.ingsw.ps45.model.effects.Effect;
import it.polimi.ingsw.ps45.model.game.ServerResponse;
import it.polimi.ingsw.ps45.model.player.PersonalBonusTile;

public class GsonWithInterface {
	public static Gson getGson(){
		Gson gson = new GsonBuilder()
                .registerTypeAdapter(Effect.class,
                        new PropertyBasedInterfaceMarshal())
                .registerTypeAdapter(Command.class,
                        new PropertyBasedInterfaceMarshal())
                .registerTypeAdapter(ServerResponse.class,
                        new PropertyBasedInterfaceMarshal())
                .registerTypeAdapter(ServerResponseVisitor.class,
                        new PropertyBasedInterfaceMarshal()).create();
		return gson;
	}
}
