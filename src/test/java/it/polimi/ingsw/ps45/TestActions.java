package it.polimi.ingsw.ps45;


import org.junit.Before;

import it.polimi.ingsw.ps45.controller.SocketObserver;
import it.polimi.ingsw.ps45.exceptions.ActionNotAllowedException;
import it.polimi.ingsw.ps45.exceptions.PlayerExistanceException;
import it.polimi.ingsw.ps45.model.actions.state.HarvestState;
import it.polimi.ingsw.ps45.model.actions.state.PawnActionState;
import it.polimi.ingsw.ps45.model.actions.state.TakeBuildingState;
import it.polimi.ingsw.ps45.model.actions.state.TakeCharacterState;
import it.polimi.ingsw.ps45.model.actions.state.TakeTerritoryState;
import it.polimi.ingsw.ps45.model.actions.state.TakeVentureState;
import it.polimi.ingsw.ps45.model.cards.VentureMode;
import it.polimi.ingsw.ps45.model.game.Game;
import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.player.PawnType;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class TestActions extends TestCase{
	
	Game g;
	 @Before
	  public void setUp() throws ActionNotAllowedException, PlayerExistanceException {
		g = new Game();
		
		
		g.addPlayer("player1", "2", new SocketObserver(null));
		g.addPlayer("player2", "3", new SocketObserver(null));
		
		g.getPlayerByID("player1").getResourceSet().getResources().setCoins(200);
		g.getPlayerByID("player1").getResourceSet().getResources().setWood(200);
		g.getPlayerByID("player1").getResourceSet().getResources().setStone(200);
		g.getPlayerByID("player1").getResourceSet().getResources().setServants(200);
		g.getPlayerByID("player1").getResourceSet().getResources().setMilitaryPoins(200);
		
		g.addPlayer("player3", "3", new SocketObserver(null));
		g.addPlayer("player4", "2", new SocketObserver(null));
		
	  }
	 
	 public void testPawnTerritory() throws ActionNotAllowedException, PlayerExistanceException{
		 g.getPlayerByID("player1").getActionBuilder().placePawnTerritory(g.getBoard().getTerritoryTower().getGroundFloor(), PawnType.WHITE, 0);
		 assertEquals(1, g.getPlayerByID("player1").getResourceSet().getTerritoryList().size());
		 assertTrue(!g.getPlayerByID("player1").getResourceSet().getPawnSet().get(PawnType.WHITE).isAvailable());
	 }
	 
	 public void testPawnCharacter() throws ActionNotAllowedException, PlayerExistanceException{
		 g.getPlayerByID("player1").getActionBuilder().placePawnCharacter(g.getBoard().getCharacterTower().getGroundFloor(), PawnType.WHITE, 0);
		 assertEquals(1, g.getPlayerByID("player1").getResourceSet().getCharacterList().size());
		 assertTrue(!g.getPlayerByID("player1").getResourceSet().getPawnSet().get(PawnType.WHITE).isAvailable());
	 }
	 
	 public void testPawnBuilding() throws ActionNotAllowedException, PlayerExistanceException{
		 g.getPlayerByID("player1").getActionBuilder().placePawnBuilding(g.getBoard().getBuildingTower().getGroundFloor(), PawnType.WHITE, 0);
		 assertEquals(1, g.getPlayerByID("player1").getResourceSet().getBuildingList().size());
		 assertTrue(!g.getPlayerByID("player1").getResourceSet().getPawnSet().get(PawnType.WHITE).isAvailable());
	 }
	 
	 public void testPawnVenture() throws ActionNotAllowedException, PlayerExistanceException{
		 System.out.println(g.hasStarted());
		 
		 g.getPlayerByID("player1").getActionBuilder().placePawnVenture(g.getBoard().getVentureTower().getGroundFloor(), PawnType.WHITE, 0, VentureMode.FIRST);
		 assertEquals(1, g.getPlayerByID("player1").getResourceSet().getVentureList().size());
		 assertTrue(!g.getPlayerByID("player1").getResourceSet().getPawnSet().get(PawnType.WHITE).isAvailable());
	 }
	 
	 public void testNoPawnTerritory() throws ActionNotAllowedException, PlayerExistanceException{
		 g.getPlayerByID("player1").getActionBuilder().setState(new TakeTerritoryState(7, new ConsumableSet()));
		 g.getPlayerByID("player1").getActionBuilder().NoPawnTerritory(g.getBoard().getTerritoryTower().getGroundFloor(), 0);
		 assertEquals(1, g.getPlayerByID("player1").getResourceSet().getTerritoryList().size());
	 }
	 
	 public void testNoPawnCharacter() throws ActionNotAllowedException, PlayerExistanceException{
		 g.getPlayerByID("player1").getActionBuilder().setState(new TakeCharacterState(7, new ConsumableSet()));
		 g.getPlayerByID("player1").getActionBuilder().NoPawnCharacter(g.getBoard().getCharacterTower().getGroundFloor(), 0);
		 assertEquals(1, g.getPlayerByID("player1").getResourceSet().getCharacterList().size());
	 }
	 
	 public void testNoPawnBuilding() throws ActionNotAllowedException, PlayerExistanceException{
		 g.getPlayerByID("player1").getActionBuilder().setState(new TakeBuildingState(7, new ConsumableSet()));
		 g.getPlayerByID("player1").getActionBuilder().NoPawnBuilding(g.getBoard().getBuildingTower().getGroundFloor(), 0);
		 assertEquals(1, g.getPlayerByID("player1").getResourceSet().getBuildingList().size());
	 }
	 
	 public void testNoPawnVenture() throws ActionNotAllowedException, PlayerExistanceException{
		 g.getPlayerByID("player1").getActionBuilder().setState(new TakeVentureState(7, new ConsumableSet()));
		 g.getPlayerByID("player1").getActionBuilder().NoPawnVenture(g.getBoard().getVentureTower().getGroundFloor(), 0, VentureMode.FIRST);
		 assertEquals(1, g.getPlayerByID("player1").getResourceSet().getVentureList().size());
	 }
	 
	 public void testHarvest() throws PlayerExistanceException, ActionNotAllowedException{
		 g.getPlayerByID("player1").getActionBuilder().setState(new TakeTerritoryState(7, new ConsumableSet()));
		 g.getPlayerByID("player1").getActionBuilder().NoPawnTerritory(g.getBoard().getTerritoryTower().getGroundFloor(), 0);
		 g.getPlayerByID("player1").getActionBuilder().setState(new TakeTerritoryState(7, new ConsumableSet()));
		 g.getPlayerByID("player1").getActionBuilder().NoPawnTerritory(g.getBoard().getTerritoryTower().getFirstFloor(), 0);
		 g.getPlayerByID("player1").getActionBuilder().setState(new TakeTerritoryState(7, new ConsumableSet()));
		 g.getPlayerByID("player1").getActionBuilder().NoPawnTerritory(g.getBoard().getTerritoryTower().getSecondFloor(), 0);
		 g.getPlayerByID("player1").getActionBuilder().setState(new TakeTerritoryState(7, new ConsumableSet()));
		 g.getPlayerByID("player1").getActionBuilder().NoPawnTerritory(g.getBoard().getTerritoryTower().getThirdFloor(), 0);
		 
		 int wood = g.getPlayerByID("player1").getResourceSet().getResources().getWood();
		 
		 g.getPlayerByID("player1").getActionBuilder().setState(new HarvestState(6));
		 g.getPlayerByID("player1").getActionBuilder().harvest();
		 System.out.println(wood);
		 System.out.println(g.getPlayerByID("player1").getResourceSet().getResources().getWood());
		 assertTrue(wood <= g.getPlayerByID("player1").getResourceSet().getResources().getWood());
	 }
	 
	 
	
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public TestActions( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( TestActions.class );
    }
}
