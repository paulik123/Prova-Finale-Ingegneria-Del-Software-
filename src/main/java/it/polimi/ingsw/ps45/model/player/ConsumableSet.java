package it.polimi.ingsw.ps45.model.player;

import java.io.Serializable;
/**
 * Set of consumables that a player can collect or pay with.
 */
public class ConsumableSet{
	
	private int wood;
	private int stone;
	private int coins;
	private int servants;
	private int victoryPoints;
	private int faithPoints;
	private int militaryPoints;
	

	/**
 	 * Constructor
	 * Builds an empty ConsumableSet
	 */
	public ConsumableSet() {
		this.wood = 0;
		this.stone = 0;
		this.coins = 0;
		this.servants = 0;
		this.victoryPoints = 0;
		this.faithPoints = 0;
		this.militaryPoints = 0;
	}
	
	/**
 	 * Constructor
	 * Builds a ConsumableSet with that contains the given consumables.
	 * @param wood the number of wood that the set will contain
	 * @param stone the number of stone that the set will contain
	 * @param servants the number of servants that the set will contain
	 * @param coins the number of coins that the set will contain
	 */
	public ConsumableSet(int wood, int stone, int servants, int coins) {
		this.wood = wood;
		this.stone = stone;
		this.servants = servants;
		this.coins = coins;
	}
	
	
	/**
	 * @param c another ConsumableSet that this will be compared to.
	 * @return true if the number of each consumable in the set is >= than the one in c.
	 */
	public boolean hasConsumablesAvailable(ConsumableSet c){
		return(
				this.wood >= c.wood &&
				this.stone >= c.stone &&
				this.servants >= c.servants &&
				this.coins >= c.coins &&
				this.victoryPoints >= c.victoryPoints &&
				this.faithPoints >= c.faithPoints &&
				this.militaryPoints >= c.militaryPoints
				);
	}
	
	/**
	 * Makes the player "pay". Subtracts the consumables in c from this ConsumableSet. If it doesn't have the enough consumables nothing will happen.
	 * @param c the consumables from c will be subtracted from the consumables in this.
	 */
	public void pay(ConsumableSet c){
		if(!hasConsumablesAvailable(c)){
			return;
		}
		this.wood = this.wood - c.wood;
		this.stone = this.stone - c.stone;
		this.servants = this.servants - c.servants;
		this.coins = this.coins - c.coins;
		this.victoryPoints = this.victoryPoints - c.victoryPoints;
		this.faithPoints = this.faithPoints - c.faithPoints;
		this.militaryPoints = this.militaryPoints - c.faithPoints;
	}
	
	
	
	/**
	 * Makes the player "collect". Adds the consumables in c from this ConsumableSet.
	 * @param c the consumables from c will be added to the consumables in this set.
	 */
	public void collect(ConsumableSet c){
		
		this.wood = this.wood + c.wood;
		this.stone = this.stone + c.stone;
		this.servants = this.servants + c.servants;
		this.coins = this.coins + c.coins;
		this.victoryPoints = this.victoryPoints + c.victoryPoints;
		this.faithPoints = this.faithPoints + c.faithPoints;
		this.militaryPoints = this.militaryPoints + c.militaryPoints;
	}
	
	/**
	 * Makes a discount in this ConsumableSet. Used for calulating costs. Subtracts the consumables in c from this ConsumableSet.
	 * If a result is negative it it will be instead set to 0 as something can't have a negative cost.
	 * @param c a ConsumableSet representing the discount.
	 */
	public void makeDiscount(ConsumableSet c){
		subtractWithZeroLimit(this.wood, c.wood);
		subtractWithZeroLimit(this.stone, c.stone);
		subtractWithZeroLimit(this.servants, c.servants);
		subtractWithZeroLimit(this.coins, c.coins);
		subtractWithZeroLimit(this.victoryPoints, c.victoryPoints);
		subtractWithZeroLimit(this.faithPoints, c.faithPoints);
		subtractWithZeroLimit(this.militaryPoints, c.militaryPoints);
	}
	
	/**
	 * @return true the number of wood in this ConsumableSet.
	 */
	public int getWood() {
		return wood;
	}
	
	/**
	 * Sets the value of wood in this ConsumableSet.
	 * @param wood the new value.
	 */
	public void setWood(int wood) {
		this.wood = wood;
	}
	
	/**
	 * @return true the number of stone in this ConsumableSet.
	 */
	public int getStone() {
		return stone;
	}
	
	/**
	 * Sets the value of stone in this ConsumableSet.
	 * @param wood the new value.
	 */
	public void setStone(int stone) {
		this.stone = stone;
	}
	
	/**
	 * @return true the number of coins in this ConsumableSet.
	 */
	public int getCoins() {
		return coins;
	}
	
	/**
	 * Sets the value of coins in this ConsumableSet.
	 * @param wood the new value.
	 */
	public void setCoins(int coins) {
		this.coins = coins;
	}
	
	/**
	 * @return true the number of servants in this ConsumableSet.
	 */
	public int getServants() {
		return servants;
	}
	
	/**
	 * Sets the value of servants in this ConsumableSet.
	 * @param wood the new value.
	 */
	public void setServants(int servants) {
		this.servants = servants;
	}
	
	/**
	 * @return true the number of victory points in this ConsumableSet.
	 */
	public int getVictoryPoints() {
		return victoryPoints;
	}
	
	/**
	 * Sets the value of victory points in this ConsumableSet.
	 * @param wood the new value.
	 */
	public void setVictoryPoints(int victoryPoints) {
		this.victoryPoints = victoryPoints;
	}
	
	/**
	 * @return true the number of faith points in this ConsumableSet.
	 */
	public int getFaithPoints() {
		return faithPoints;
	}
	
	
	/**
	 * Sets the value of faith points in this ConsumableSet.
	 * @param wood the new value.
	 */
	public void setFaithPoints(int faithPoints) {
		this.faithPoints = faithPoints;
	}
	
	/**
	 * @return true the number of military points in this ConsumableSet.
	 */
	public int getMilitaryPoins() {
		return militaryPoints;
	}
	
	/**
	 * Sets the value of military points in this ConsumableSet.
	 * @param wood the new value.
	 */
	public void setMilitaryPoins(int militaryPoints) {
		this.militaryPoints = militaryPoints;
	}
	
	
	/**
	 * Subtracts b from a. Returns 0 if the result is negative.
	 * @param a the value to be subtracted from.
	 * @param b the value that is subtracted.
	 */
	public int subtractWithZeroLimit(int a, int b){
		int result = a - b;
		if(result >= 0) return result;
		else return 0;
	}
	
	/**
	 * @return a String representation of this set.
	 */
	@Override
	public String toString() {
		return "ConsumableSet [wood=" + wood + ", stone=" + stone + ", coins=" + coins + ", servants=" + servants
				+ ", victoryPoints=" + victoryPoints + ", faithPoints=" + faithPoints + ", militaryPoints="
				+ militaryPoints + "]";
	}
	
	
	
	
	
	
	
}
