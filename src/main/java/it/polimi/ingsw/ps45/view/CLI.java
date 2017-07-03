package it.polimi.ingsw.ps45.view;


import com.google.gson.Gson;

import it.polimi.ingsw.ps45.gson.GsonWithInterface;
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
import it.polimi.ingsw.ps45.model.cards.Character;
import it.polimi.ingsw.ps45.model.cards.Territory;
import it.polimi.ingsw.ps45.model.cards.Venture;
import it.polimi.ingsw.ps45.model.game.Game;
import it.polimi.ingsw.ps45.model.player.PawnType;
import it.polimi.ingsw.ps45.model.player.Player;


/**
 * CLI View. Prints out the state of the game in the terminal.
 */
public class CLI extends View{
	
	private String playerID;
	
	/**
 	 * Constructor
 	 * @param playerID the ID of the player who "owns" the view.
	 */
	public CLI(String playerID){
		this.playerID = playerID;
	}


	/**
	 * Updates the view with the new game data.
 	 * @param gameJSON The new object of the game serialized as a JSON string.
	 */
	@Override
	public void updateView(String gameJSON) {
		Gson gson = GsonWithInterface.getGson();
		Game g = gson.fromJson(gameJSON, Game.class);
		
		System.out.println("\n\n\n||||||||||  " + g.getStatus() + "  ||||||||||");
		System.out.println("\n" + playerID);
		System.out.println("Current ERA: " + g.getEras()[g.getCurrentEra()] + "  || Current ROUND: " + g.getRoundNumber());
		System.out.println("CURRENT PLAYER: " + g.getCurrentRound().getCurrentPlayer().getPlayerID() + "  \n");
		System.out.println("\n ---------------- PLAYERS ----------------");
		
		for(Player p: g.getPlayers()){
			System.out.println("\n   --- " + p.getPlayerID() + " ---   ");
			System.out.println("State: " + p.getStatus());
			System.out.println(p.getResourceSet().getResources());
			printPawns(p);
			printTerritories(p);
			printCharacters(p);
			printBuildings(p);
			printVentures(p);
		}
		
		System.out.println("\n ---------------- BOARD ----------------");
		printBoard(g.getBoard());
		
		
	}
	
	/**
	 * Prints out strings representing all the territories a player owns.
 	 * @param p the player whose territories will be printed.
	 */
	private void printTerritories(Player p){
		StringBuilder sb = new StringBuilder();
		sb.append("Territories: ");
		for(Territory t: p.getResourceSet().getTerritoryList()){
			sb.append(t.getName() + " | ");
		}
		System.out.println(sb.toString());
	}
	
	/**
	 * Prints out strings representing all the pawns a player owns.
 	 * @param p the player whose pawns will be printed.
	 */
	private void printPawns(Player p){
		StringBuilder sb = new StringBuilder();
		sb.append("PAWNS: \n");
		
		if(p.getResourceSet().getPawn(PawnType.WHITE).isAvailable())sb.append("White: " + p.getResourceSet().getPawn(PawnType.WHITE).getValue() + "  ||  ");
		if(p.getResourceSet().getPawn(PawnType.BLACK).isAvailable())sb.append("Black: " + p.getResourceSet().getPawn(PawnType.BLACK).getValue()+ "  ||  ");
		if(p.getResourceSet().getPawn(PawnType.ORANGE).isAvailable())sb.append("Orange: " + p.getResourceSet().getPawn(PawnType.ORANGE).getValue()+ "  ||  ");
		if(p.getResourceSet().getPawn(PawnType.NEUTRAL).isAvailable())sb.append("Neutral: " + p.getResourceSet().getPawn(PawnType.NEUTRAL).getValue()+ "  ||  ");
		System.out.println(sb.toString());
	}
	
	/**
	 * Prints out strings representing all the characters a player owns.
 	 * @param p the player whose characters will be printed.
	 */
	private void printCharacters(Player p){
		StringBuilder sb = new StringBuilder();
		sb.append("Characters: ");
		for(Character t: p.getResourceSet().getCharacterList()){
			sb.append(t.getName() + " | ");
		}
		System.out.println(sb.toString());
	}	
	
	/**
	 * Prints out strings representing all the buildings a player owns.
 	 * @param p the player whose buildings will be printed.
	 */
	private void printBuildings(Player p){
		StringBuilder sb = new StringBuilder();
		sb.append("Buildings: ");
		for(Building t: p.getResourceSet().getBuildingList()){
			sb.append(t.getName() + " | ");
		}
		System.out.println(sb.toString());
	}	
	
	/**
	 * Prints out strings representing all the ventures a player owns.
 	 * @param p the player whose ventures will be printed.
	 */
	private void printVentures(Player p){
		StringBuilder sb = new StringBuilder();
		sb.append("Ventures: ");
		for(Venture t: p.getResourceSet().getVentureList()){
			sb.append(t.getName() + " | ");
		}
		System.out.println(sb.toString());
	}	
	
	
	/**
	 * Prints out strings representing the current state of the game's board.
 	 * @param b the board whose state will be printed.
	 */
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
	
	/**
	 * Prints out the name of an area and it's occupants.
 	 * @param a the area whose occupants will be printed.
	 */
	private void printArea(Area a){
		System.out.println(a.getName() + ": ");
		for(PlayerPawnPair ppp: a.getOccupants()){
			System.out.println("Player: " + ppp.getPlayer().getPlayerID() + "   Pawn: " + ppp.getType());
		}
	}
	
	/**
	 * Prints out strings representing the territory tower and it's occupants.
 	 * @param t the tower whose state will be printed.
	 */
	private void printTerritoryTower(TerritoryTower t){
		System.out.println("");
		for(TerritoryCardArea a: t.getAreas()){
			if(a.getTerritory() != null) System.out.println(a.getName() + " | Territory: " + a.getTerritory().getName());
			for(PlayerPawnPair ppp: a.getOccupants()){
				System.out.println("Player: " + ppp.getPlayer().getPlayerID() + "   Pawn: " + ppp.getType());
			}
		}
		System.out.println("---");
	}
	
	/**
	 * Prints out strings representing the character tower and it's occupants.
 	 * @param t the tower whose state will be printed.
	 */
	private void printCharacterTower(CharacterTower t){
		for(CharacterCardArea a: t.getAreas()){
			if(a.getCharacter() != null)System.out.println(a.getName() + " | Character: " + a.getCharacter().getName());
			for(PlayerPawnPair ppp: a.getOccupants()){
				System.out.println("Player: " + ppp.getPlayer().getPlayerID() + "   Pawn: " + ppp.getType());
			}
		}
		System.out.println("---");
	}
	
	/**
	 * Prints out strings representing the building tower and it's occupants.
 	 * @param t the tower whose state will be printed.
	 */
	private void printBuildingTower(BuildingTower t){
		for(BuildingCardArea a: t.getAreas()){
			if(a.getBuilding() != null)System.out.println(a.getName() + " | Building: " + a.getBuilding().getName());
			for(PlayerPawnPair ppp: a.getOccupants()){
				System.out.println("Player: " + ppp.getPlayer().getPlayerID() + "   Pawn: " + ppp.getType());
			}
		}
		System.out.println("---");
	}
	
	/**
	 * Prints out strings representing the venture tower and it's occupants.
 	 * @param t the tower whose state will be printed.
	 */
	private void printVentureTower(VentureTower t){
		for(VentureCardArea a: t.getAreas()){
			if(a.getVenture() != null) System.out.println(a.getName() + " | Venture: " + a.getVenture().getName());
			for(PlayerPawnPair ppp: a.getOccupants()){
				System.out.println("Player: " + ppp.getPlayer().getPlayerID() + "   Pawn: " + ppp.getType());
			}
		}
		System.out.println("---");
	}



	/**
	 * Prints out an error message received from the server.
 	 * @param error the error message that will be printed.
	 */
	@Override
	public void showError(String error) {
		System.out.println(error);
		
	}



	/**
	 * Prints out the end game results received from the server.
 	 * @param error the error message that will be printed.
	 */
	@Override
	public void showResults(String results) {
		System.out.println("\n\n\n\n\n End Game Results: \n");
		System.out.println(results);	
	}

}
