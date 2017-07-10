package it.polimi.ingsw.ps45;


import org.junit.Before;

import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestResourceSet extends TestCase{
	
	private ConsumableSet cs;
	private ConsumableSet collectSet;
	  @Before
	  public void setUp() {
	    cs = new ConsumableSet();
	    
	    collectSet = new ConsumableSet();
    	collectSet.setWood(10);
    	collectSet.setCoins(10);
    	collectSet.setFaithPoints(10);
    	collectSet.setStone(10);
    	collectSet.setMilitaryPoins(10);
    	collectSet.setServants(10);
    	collectSet.setVictoryPoints(10);
	    
	  }
	  
	    
	    public void testEmpty() {
	    	assertEquals(0, cs.getWood());
	    	assertEquals(0, cs.getCoins());
	    	assertEquals(0, cs.getFaithPoints());
	    	assertEquals(0, cs.getStone());
	    	assertEquals(0, cs.getMilitaryPoins());
	    	assertEquals(0, cs.getServants());
	    	assertEquals(0, cs.getVictoryPoints());
	    }
	    
	    
	    public void testPay() {
	    	cs.setWood(15);
	    	cs.setCoins(15);
	    	cs.setFaithPoints(15);
	    	cs.setStone(10);
	    	cs.setMilitaryPoins(10);
	    	cs.setServants(10);
	    	cs.setVictoryPoints(10);
	    	
	    	cs.pay(collectSet);
	    	
	    	assertEquals(5, cs.getWood());
	    	assertEquals(5, cs.getCoins());
	    	assertEquals(5, cs.getFaithPoints());
	    	assertEquals(0, cs.getStone());
	    	assertEquals(0, cs.getMilitaryPoins());
	    	assertEquals(0, cs.getServants());
	    	assertEquals(0, cs.getVictoryPoints());
	    }
	    
	    
	   
	    public void testDiscount() {
	    	cs.setWood(15);
	    	cs.setCoins(15);
	    	cs.setFaithPoints(15);
	    	cs.setStone(5);
	    	cs.setMilitaryPoins(5);
	    	cs.setServants(5);
	    	cs.setVictoryPoints(5);
	    	
	    	cs.makeDiscount(collectSet);
	    	
	    	assertEquals(5, cs.getWood());
	    	assertEquals(5, cs.getCoins());
	    	assertEquals(5, cs.getFaithPoints());
	    	assertEquals(0, cs.getStone());
	    	assertEquals(0, cs.getMilitaryPoins());
	    	assertEquals(0, cs.getServants());
	    	assertEquals(0, cs.getVictoryPoints());
	    }
	    
	    
	    
	    public void testAvailable() {
	    	cs.setWood(15);
	    	cs.setCoins(15);
	    	cs.setFaithPoints(15);
	    	cs.setStone(5);
	    	cs.setMilitaryPoins(5);
	    	cs.setServants(5);
	    	cs.setVictoryPoints(5);
	    	
	    	assertTrue(!cs.hasConsumablesAvailable(collectSet));
	    }
	    
	    public void testCollect() {

	    	
	    	cs.collect(collectSet);
	    	
	    	assertEquals(10, cs.getWood());
	    	assertEquals(10, cs.getCoins());
	    	assertEquals(10, cs.getFaithPoints());
	    	assertEquals(10, cs.getStone());
	    	assertEquals(10, cs.getMilitaryPoins());
	    	assertEquals(10, cs.getServants());
	    	assertEquals(10, cs.getVictoryPoints());
	    }
	    
	    
	  
	    
	    public void testOne() {
	    	cs.setWood(1);
	    	cs.setCoins(1);
	    	cs.setFaithPoints(1);
	    	cs.setStone(1);
	    	cs.setMilitaryPoins(1);
	    	cs.setServants(1);
	    	cs.setVictoryPoints(1);
	    	assertEquals(1, cs.getWood());
	    	assertEquals(1, cs.getCoins());
	    	assertEquals(1, cs.getFaithPoints());
	    	assertEquals(1, cs.getStone());
	    	assertEquals(1, cs.getMilitaryPoins());
	    	assertEquals(1, cs.getServants());
	    	assertEquals(1, cs.getVictoryPoints());
	    }
	    
	    /**
	     * Create the test case
	     *
	     * @param testName name of the test case
	     */
	    public TestResourceSet( String testName )
	    {
	        super( testName );
	    }

	    /**
	     * @return the suite of tests being tested
	     */
	    public static Test suite()
	    {
	        return new TestSuite( TestResourceSet.class );
	    }

}
