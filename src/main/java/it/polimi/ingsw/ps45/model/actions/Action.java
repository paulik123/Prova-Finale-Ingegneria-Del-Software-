package it.polimi.ingsw.ps45.model.actions;

import it.polimi.ingsw.ps45.model.area.CardArea;
import it.polimi.ingsw.ps45.model.area.HarvestAreaBig;
import it.polimi.ingsw.ps45.model.area.HarvestAreaSmall;
import it.polimi.ingsw.ps45.model.area.MarketArea;
import it.polimi.ingsw.ps45.model.area.ProductionAreaBig;
import it.polimi.ingsw.ps45.model.area.ProductionAreaSmall;
import it.polimi.ingsw.ps45.model.cards.Building;
import it.polimi.ingsw.ps45.model.cards.Character;
import it.polimi.ingsw.ps45.model.cards.Territory;
import it.polimi.ingsw.ps45.model.cards.Venture;
import it.polimi.ingsw.ps45.model.player.Pawn;
import it.polimi.ingsw.ps45.model.player.Player;

public class Action {
	public static void productionSmall(Player p, Pawn pawn, int servantsAdded, ProductionAreaSmall area){
		
	}
	
	public static void productionBig(Player p, Pawn pawn, int servantsAdded, ProductionAreaBig area){
		
	}
	
	public static void harvestSmall(Player p, Pawn pawn, int servantsAdded, HarvestAreaSmall area){
		
	}
	
	public static void harvestBig(Player p, Pawn pawn, int servantsAdded, HarvestAreaBig area){
		
	}
	
	public static void market(Player p, Pawn pawn, int servantsAdded, MarketArea area){
	}
	
	public static void acquireTerritory(Player p,Pawn pawn, int servantsAdded, CardArea<Territory> area){
		
	}
	
	public static void acquireBuilding(Player p, Pawn pawn, int servantsAdded, CardArea<Building> area){
		
	}
	
	public static void acquireCharacter(Player p, Pawn pawn, int servantsAdded, CardArea<Character> area){
		
	}
	
	public static void acquireVenture(Player p, Pawn pawn, int servantsAdded, CardArea<Venture> area){
		
	}
}
