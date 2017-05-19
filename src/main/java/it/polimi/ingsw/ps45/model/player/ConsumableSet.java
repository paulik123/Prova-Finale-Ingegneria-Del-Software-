package it.polimi.ingsw.ps45.model.player;

public class ConsumableSet {
	
	
	
	private int wood;
	private int stone;
	private int coins;
	private int servants;
	private int victoryPoints;
	private int faithPoints;
	private int militaryPoints;
	

	public ConsumableSet() {
		this.wood = 0;
		this.stone = 0;
		this.coins = 0;
		this.servants = 0;
		this.victoryPoints = 0;
		this.faithPoints = 0;
		this.militaryPoints = 0;
	}
	public ConsumableSet(int wood, int stone, int servants, int coins) {
		this.wood = wood;
		this.stone = stone;
		this.servants = servants;
		this.coins = coins;
	}
	
	

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
	
	public void pay(ConsumableSet c){
		if(!hasConsumablesAvailable(c)){
			//TODO: ASK PLAYER FOR A NEW COMMAND
			return;
		}
		this.wood = this.wood - c.wood;
		this.stone = this.stone - c.stone;
		this.servants = this.servants - c.servants;
		this.coins = this.coins - c.coins;
		this.victoryPoints = this.coins - c.coins;
		this.faithPoints = this.faithPoints - c.faithPoints;
		this.militaryPoints = this.militaryPoints - c.faithPoints;
	}
	
	
	
	public void collect(ConsumableSet c){
		
		this.wood = this.wood + c.wood;
		this.stone = this.stone + c.stone;
		this.servants = this.servants + c.servants;
		this.coins = this.coins + c.coins;
		this.victoryPoints = this.victoryPoints + c.victoryPoints;
		this.faithPoints = this.faithPoints + c.faithPoints;
		this.militaryPoints = this.militaryPoints + c.militaryPoints;
	}
	
	public void makeDiscount(ConsumableSet c){
		subtractWithZeroLimit(this.wood, c.wood);
		subtractWithZeroLimit(this.stone, c.stone);
		subtractWithZeroLimit(this.servants, c.servants);
		subtractWithZeroLimit(this.coins, c.coins);
		subtractWithZeroLimit(this.victoryPoints, c.victoryPoints);
		subtractWithZeroLimit(this.faithPoints, c.faithPoints);
		subtractWithZeroLimit(this.militaryPoints, c.militaryPoints);
	}
	
	public int getWood() {
		return wood;
	}
	public void setWood(int wood) {
		this.wood = wood;
	}
	public int getStone() {
		return stone;
	}
	public void setStone(int stone) {
		this.stone = stone;
	}
	public int getCoins() {
		return coins;
	}
	public void setCoins(int coins) {
		this.coins = coins;
	}
	public int getServants() {
		return servants;
	}
	public void setServants(int servants) {
		this.servants = servants;
	}
	public int getVictoryPoints() {
		return victoryPoints;
	}
	public void setVictoryPoints(int victoryPoints) {
		this.victoryPoints = victoryPoints;
	}
	public int getFaithPoints() {
		return faithPoints;
	}
	public void setFaithPoints(int faithPoints) {
		this.faithPoints = faithPoints;
	}
	public int getMilitaryPoins() {
		return militaryPoints;
	}
	public void setMilitaryPoins(int militaryPoints) {
		this.militaryPoints = militaryPoints;
	}
	
	public int subtractWithZeroLimit(int a, int b){
		int result = a - b;
		if(result >= 0) return result;
		else return 0;
	}
	
	
	
}
