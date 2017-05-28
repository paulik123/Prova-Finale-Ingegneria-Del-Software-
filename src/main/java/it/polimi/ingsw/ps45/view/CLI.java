package it.polimi.ingsw.ps45.view;

import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import it.polimi.ingsw.ps45.controller.command.Command;
import it.polimi.ingsw.ps45.gson.GsonWithInterface;
import it.polimi.ingsw.ps45.gson.PropertyBasedInterfaceMarshal;
import it.polimi.ingsw.ps45.model.area.Area;
import it.polimi.ingsw.ps45.model.area.Board;
import it.polimi.ingsw.ps45.model.area.PlayerPawnPair;
import it.polimi.ingsw.ps45.model.area.cardarea.BuildingCardArea;
import it.polimi.ingsw.ps45.model.area.cardarea.BuildingTower;
import it.polimi.ingsw.ps45.model.area.cardarea.CharacterCardArea;
import it.polimi.ingsw.ps45.model.area.cardarea.CharacterTower;
import it.polimi.ingsw.ps45.model.area.cardarea.TerritoryCardArea;
import it.polimi.ingsw.ps45.model.area.cardarea.TerritoryTower;
import it.polimi.ingsw.ps45.model.area.cardarea.VentureCardArea;
import it.polimi.ingsw.ps45.model.area.cardarea.VentureTower;
import it.polimi.ingsw.ps45.model.cards.Building;
import it.polimi.ingsw.ps45.model.cards.Territory;
import it.polimi.ingsw.ps45.model.cards.Venture;
import it.polimi.ingsw.ps45.model.cards.Character;
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
		
		System.out.println("\n\n\n||||||||||  " + g.getStatus() + "  ||||||||||");
		System.out.println("Current ERA: " + g.getEras()[g.getCurrentEra()] + "  || Current ROUND: " + g.getRoundNumber());
		System.out.println("\n ---------------- PLAYERS ----------------");
		
		for(Player p: g.getPlayers()){
			System.out.println("\n   --- " + p.getPlayerID() + " ---   ");
			System.out.println("STATUS: " + p.getStatus());
			System.out.println(p.getResourceSet().getResources());
			printTerritories(p);
			printCharacters(p);
			printBuildings(p);
			printVentures(p);
		}
		
		System.out.println("\n ---------------- BOARD ----------------");
		printBoard(g.getBoard());
		
		
	}
	
	private void printTerritories(Player p){
		StringBuilder sb = new StringBuilder();
		sb.append("Territories: ");
		for(Territory t: p.getResourceSet().getTerritoryList()){
			sb.append(t.getName() + " | ");
		}
		System.out.println(sb.toString());
	}	
	private void printCharacters(Player p){
		StringBuilder sb = new StringBuilder();
		sb.append("Characters: ");
		for(Character t: p.getResourceSet().getCharacterList()){
			sb.append(t.getName() + " | ");
		}
		System.out.println(sb.toString());
	}	
	private void printBuildings(Player p){
		StringBuilder sb = new StringBuilder();
		sb.append("Buildings: ");
		for(Building t: p.getResourceSet().getBuildingList()){
			sb.append(t.getName() + " | ");
		}
		System.out.println(sb.toString());
	}	
	private void printVentures(Player p){
		StringBuilder sb = new StringBuilder();
		sb.append("Ventures: ");
		for(Venture t: p.getResourceSet().getVentureList()){
			sb.append(t.getName() + " | ");
		}
		System.out.println(sb.toString());
	}	
	
	private void printBoard(Board b){
		printArea(b.getCoinsMarketArea());
		printArea(b.getServantsMarketArea());
		printArea(b.getMilitaryAndCoinArea());
		printArea(b.getCouncilPrivilegeMarketArea());
		
		printArea(b.getCouncilPalaceArea());
		
		printArea(b.getProductionAreas().getSmall());
		printArea(b.getProductionAreas().getBig());
		
		printArea(b.getHarvestAreas().getSmall());
		printArea(b.getHarvestAreas().getBig());
		System.out.println("---");
		
		printTerritoryTower(b.getTerritoryTower());
		printCharacterTower(b.getCharacterTower());
		printBuildingTower(b.getBuildingTower());
		printVentureTower(b.getVentureTower());
	}
	
	private void printArea(Area a){
		System.out.println(a.getName() + ": ");
		for(PlayerPawnPair ppp: a.getOccupants()){
			System.out.println("Player: " + ppp.getPlayer().getPlayerID() + "   Pawn: " + ppp.getType());
		}
	}
	
	private void printTerritoryTower(TerritoryTower t){
		System.out.println("");
		for(TerritoryCardArea a: t.getAreas()){
			System.out.println(a.getName() + " | Territory: " + a.getTerritory().getName());
			for(PlayerPawnPair ppp: a.getOccupants()){
				System.out.println("Player: " + ppp.getPlayer().getPlayerID() + "   Pawn: " + ppp.getType());
			}
		}
		System.out.println("---");
	}
	private void printCharacterTower(CharacterTower t){
		for(CharacterCardArea a: t.getAreas()){
			System.out.println(a.getName() + " | Character: " + a.getCharacter().getName());
			for(PlayerPawnPair ppp: a.getOccupants()){
				System.out.println("Player: " + ppp.getPlayer().getPlayerID() + "   Pawn: " + ppp.getType());
			}
		}
		System.out.println("---");
	}
	private void printBuildingTower(BuildingTower t){
		for(BuildingCardArea a: t.getAreas()){
			System.out.println(a.getName() + " | Building: " + a.getBuilding().getName());
			for(PlayerPawnPair ppp: a.getOccupants()){
				System.out.println("Player: " + ppp.getPlayer().getPlayerID() + "   Pawn: " + ppp.getType());
			}
		}
		System.out.println("---");
	}
	private void printVentureTower(VentureTower t){
		for(VentureCardArea a: t.getAreas()){
			System.out.println(a.getName() + " | Venture: " + a.getVenture().getName());
			for(PlayerPawnPair ppp: a.getOccupants()){
				System.out.println("Player: " + ppp.getPlayer().getPlayerID() + "   Pawn: " + ppp.getType());
			}
		}
		System.out.println("---");
	}

}
