package it.polimi.ingsw.ps45;


import org.junit.Before;

import it.polimi.ingsw.ps45.controller.SocketObserver;
import it.polimi.ingsw.ps45.exceptions.ActionNotAllowedException;
import it.polimi.ingsw.ps45.exceptions.PlayerExistanceException;
import it.polimi.ingsw.ps45.model.cards.Era;
import it.polimi.ingsw.ps45.model.game.Game;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class TestGameSetUp extends TestCase{
	
	Game g;
	 @Before
	  public void setUp() throws ActionNotAllowedException {
		g = new Game();
		
		
		g.addPlayer("player1", "2", new SocketObserver(null));
		g.addPlayer("player2", "3", new SocketObserver(null));
		g.addPlayer("player3", "3", new SocketObserver(null));
		g.addPlayer("player4", "2", new SocketObserver(null));
	  }
	 
	 public void testGameStarted(){
		 assertTrue(g.hasStarted());
	 }
	 
	 public void testResources() throws PlayerExistanceException{
		 assertEquals(2, g.getPlayerByID("player1").getResourceSet().getResources().getWood());
		 assertEquals(2, g.getPlayerByID("player2").getResourceSet().getResources().getStone());
		 assertTrue(!g.getPlayerByID("player1").getResourceSet().getPermanentEffects().isAddingServantsPenalty());
		 assertEquals(4 ,g.getPlayerByID("player2").getResourceSet().getLeaderCardList().size());
	 }
	 
	 public void testExcom(){
		 assertTrue(g.getVatican().getCard(Era.I) != null);
		 assertTrue(g.getVatican().getCard(Era.II) != null);
		 assertTrue(g.getVatican().getCard(Era.III) != null);
	 }
	 
	 public void testNextTurn() throws ActionNotAllowedException{
		 g.nextTurn("player1");
		 assertTrue(g.getCurrentRound().getCurrentPlayer().getPlayerID().equals("player2"));
	 }
	 
	 public void testNextEra() throws ActionNotAllowedException{
		 g.nextEra();
		 assertEquals(1 ,g.getCurrentEra());
		 assertTrue(g.getBoard().getTerritoryTower().getGroundFloor().getTerritory().getEra().equals(Era.II));
	 }
	
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public TestGameSetUp( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( TestGameSetUp.class );
    }
}