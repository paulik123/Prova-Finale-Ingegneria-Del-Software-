package it.polimi.ingsw.ps45;


import org.junit.Before;

import it.polimi.ingsw.ps45.exceptions.AreaNotAvailableException;
import it.polimi.ingsw.ps45.model.area.Board;
import it.polimi.ingsw.ps45.model.area.cardarea.BuildingTower;
import it.polimi.ingsw.ps45.model.area.cardarea.CharacterTower;
import it.polimi.ingsw.ps45.model.area.cardarea.TerritoryTower;
import it.polimi.ingsw.ps45.model.area.cardarea.VentureTower;
import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class TestBoard extends TestCase{
	
	Board b;
	 @Before
	  public void setUp() {
		b = new Board(4);
	  }
	 
	 public void testAreas() throws AreaNotAvailableException{

		 assertTrue(b.getCoinsMarketArea() != null);
		 assertTrue(b.getServantsMarketArea() != null);
		 assertTrue(b.getMilitaryAndCoinArea() != null);
		 assertTrue(b.getCouncilPrivilegeMarketArea() != null);
	 }
	 
	 
	 public void testTowers(){
		 assertTrue(b.getBuildingTower() != null);
		 assertTrue(b.getCharacterTower() != null);
		 assertTrue(b.getTerritoryTower() != null);
		 assertTrue(b.getVentureTower() != null);
	 }
	 
	 public void testTerritories(){
		 TerritoryTower t = new TerritoryTower();
		 assertTrue(t.getGroundFloor() != null);
		 assertTrue(t.getFirstFloor() != null);
		 assertTrue(t.getSecondFloor() != null);
		 assertTrue(t.getThirdFloor() != null);
	 }
	 
	 public void testCharacters(){
		 CharacterTower t = new CharacterTower();
		 assertTrue(t.getGroundFloor() != null);
		 assertTrue(t.getFirstFloor() != null);
		 assertTrue(t.getSecondFloor() != null);
		 assertTrue(t.getThirdFloor() != null);
	 }
	 
	 public void testBuildings(){
		 BuildingTower t = new BuildingTower();
		 assertTrue(t.getGroundFloor() != null);
		 assertTrue(t.getFirstFloor() != null);
		 assertTrue(t.getSecondFloor() != null);
		 assertTrue(t.getThirdFloor() != null);
	 }
	 
	 public void testVentures(){
		 VentureTower t = new VentureTower();
		 assertTrue(t.getGroundFloor() != null);
		 assertTrue(t.getFirstFloor() != null);
		 assertTrue(t.getSecondFloor() != null);
		 assertTrue(t.getThirdFloor() != null);
	 }
	
	
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public TestBoard( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( TestBoard.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
}
