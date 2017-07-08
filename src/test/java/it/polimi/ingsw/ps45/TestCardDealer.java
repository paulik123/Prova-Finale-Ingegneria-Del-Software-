package it.polimi.ingsw.ps45;


import java.io.FileNotFoundException;

import org.junit.Before;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import it.polimi.ingsw.ps45.exceptions.AreaNotAvailableException;
import it.polimi.ingsw.ps45.model.area.Board;
import it.polimi.ingsw.ps45.model.area.cardarea.BuildingTower;
import it.polimi.ingsw.ps45.model.area.cardarea.CharacterTower;
import it.polimi.ingsw.ps45.model.area.cardarea.TerritoryTower;
import it.polimi.ingsw.ps45.model.area.cardarea.VentureTower;
import it.polimi.ingsw.ps45.model.cards.CardDealer;
import it.polimi.ingsw.ps45.model.cards.Era;
import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class TestCardDealer extends TestCase{
	
	CardDealer cd;
	Board b;
	 @Before
	  public void setUp() {
		try {
			cd = new CardDealer();
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		b = new Board(4);
	  }
	 
	 public void testTerritory() throws JsonSyntaxException, JsonIOException, FileNotFoundException{
		 assertEquals(8, cd.getTerritoryFromEra(Era.I).size());
		 assertEquals(8, cd.getTerritoryFromEra(Era.II).size());
		 assertEquals(8, cd.getTerritoryFromEra(Era.III).size());
	 }
	 public void testCharacters() throws JsonSyntaxException, JsonIOException, FileNotFoundException{
		 assertEquals(8, cd.getCharacterFromEra(Era.I).size());
		 assertEquals(8, cd.getCharacterFromEra(Era.II).size());
		 assertEquals(8, cd.getCharacterFromEra(Era.III).size());
	 }
	 
	 public void testBuildings() throws JsonSyntaxException, JsonIOException, FileNotFoundException{
		 assertEquals(8, cd.getBuildingFromEra(Era.I).size());
		 assertEquals(8, cd.getBuildingFromEra(Era.II).size());
		 assertEquals(8, cd.getBuildingFromEra(Era.III).size());
	 }
	 public void testVentures() throws JsonSyntaxException, JsonIOException, FileNotFoundException{
		 assertEquals(8, cd.getVentureFromEra(Era.I).size());
		 assertEquals(8, cd.getVentureFromEra(Era.II).size());
		 assertEquals(8, cd.getVentureFromEra(Era.III).size());
	 }
	 
	 public void testUpdateBoard(){
		 cd.updateBoard(b, Era.I);
		 //assertTrue(b.getTerritoryTower().getGroundFloor().getTerritory().getEra().equals(Era.I));
		 assertTrue(b.getBuildingTower().getGroundFloor().getBuilding().getEra().equals(Era.I));
		 assertTrue(b.getCharacterTower().getGroundFloor().getCharacter().getEra().equals(Era.I));
		 assertTrue(b.getVentureTower().getGroundFloor().getVenture().getEra().equals(Era.I));
	 }
	
	
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public TestCardDealer( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( TestCardDealer.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
}
