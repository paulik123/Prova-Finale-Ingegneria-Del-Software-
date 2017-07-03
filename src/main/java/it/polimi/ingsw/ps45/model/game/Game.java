package it.polimi.ingsw.ps45.model.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Random;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import it.polimi.ingsw.ps45.gson.GsonWithInterface;
import it.polimi.ingsw.ps45.model.actions.state.PawnActionState;
import it.polimi.ingsw.ps45.model.actions.state.VaticanChoiceState;
import it.polimi.ingsw.ps45.model.area.Board;
import it.polimi.ingsw.ps45.model.area.PlayerPawnPair;
import it.polimi.ingsw.ps45.model.cards.CardDealer;
import it.polimi.ingsw.ps45.model.cards.Era;
import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.player.PawnType;
import it.polimi.ingsw.ps45.model.player.Player;
import it.polimi.ingsw.ps45.model.vatican.Vatican;

/**
 * Game contains all the data the model of the game needs. It has observers and notifies them it's state has changed.
 */
public class Game implements Observer{
	private static final int turnsPerRound = 4;
	private static final int MAX_NUM_OF_PLAYERS = 4;
	
	private int numberOfPlayers;
	
	private transient Era[] eras = {Era.I, Era.II, Era.III};
	private ArrayList<Player> players;
	private transient HashMap<Observer, String> observers;
	private Board board;
	private Vatican vatican;
	private transient Player[] turns; 
	private String[] colorTurns;
	private Round currentRound;
	private int currentEra;
	private int roundNumber;
	private boolean gameStarted;
	private transient CardDealer cardDealer;
	private String status;
	private HashMap<PawnType, Integer> dices;
	
	
	/**
 	 * Constructor
 	 * Initializes all the data that the model of the game needs.
 	 * The game is created as "not started" and will start only when the start() method will be called.
	 */
	public Game(){
		numberOfPlayers = 0;
		gameStarted = false;
		roundNumber = 0;
		currentEra = 0;
		players = new ArrayList<Player>();
		observers = new HashMap<Observer, String>();
		dices = new HashMap<PawnType, Integer>();

		vatican = new Vatican();
		
		
		try {
			cardDealer = new CardDealer();
		} catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		status = "Game not started";
	}
	
	/**
	 * Starts the game. A new board is created. The turn-orders of the players are calculated and the game is marked as started so no other new players can join it.
	 * @throws Exception if the game has already started.
	 */
	public void start() throws Exception{
		if(gameStarted) throw new Exception("Game already started");
		
		board = new Board(players.size());
		updateActionBuildersBoard();
		
		turns = new Player[turnsPerRound * numberOfPlayers];
		colorTurns = new String[numberOfPlayers];
		calculateTurnsStart();
		
		currentRound = new Round(turns, this);
		
		roundNumber++;
		gameStarted = true;
		cardDealer.updateBoard(board, eras[currentEra]);
		setPawns();
		currentRound.getCurrentPlayer().getActionBuilder().setState(new PawnActionState());
		
		setStatus("Game has started");
		System.out.println("Game Started");
		
		notifyObservers();
	}
	
	/**
	 * Ends the turn of the current player and allows the next player to make moves.
	 * If the current round has ended a new round is created.
	 * @param playerID the ID of the player that says his turn is over.
	 * @throws Exception the player with the id that called this method is not the current player(it's not his turn to end).
	 */
	public void nextTurn(String playerID) throws Exception{
		if(!playerID.equals(currentRound.getCurrentPlayer().getPlayerID())) throw new Exception("It's not your turn to end");
		
		
		currentRound.nextTurn();
		if(currentRound.roundEnded()){
			newRound();
			return;
		}
	}
	
	/**
     * Checks the round that has ended was the second round of the era. If it was, a vatican round is created. If not a simple round is created.
	 * @throws Exception if the new round is a vatican round and the game can't read the excommunication card of that era from file.
	 */
	public void newRound() throws Exception{
		if(roundNumber % 2 == 1){
			roundNumber++;
			board = new Board(players.size());
			updateActionBuildersBoard();
			cardDealer.updateBoard(board, eras[currentEra]);
			setPawns();
			calculateTurns();
			currentRound = new Round(turns, this);
			currentRound.getCurrentPlayer().getActionBuilder().setState(new PawnActionState());
		}
		else{
			vaticanTurn();
		}
	}
	
	/**
     * Checks the round that has ended was the second round of the era. If it was, a vatican round is created. If not a simple round is created.
	 * @throws Exception if nextEra() can't read the excommunication card of that era from file.
	 */
	public void vaticanTurn() throws Exception{
		for(Player p:players){
			//currentEra+2 is the faith points requirement
			if(p.getResourceSet().getResources().getFaithPoints() < currentEra+2) {
				p.getActionBuilder().setState(new VaticanChoiceState(vatican.getCard(eras[currentEra])));
				p.getActionBuilder().refuseVatican();
			}
			else {
				p.getActionBuilder().setState(new VaticanChoiceState(vatican.getCard(eras[currentEra])));
			}
		}
		//TODO TIMEOUT FOR SOME TIME TO WAIT FOR RESPONSES THEN RUN nextEra
		nextEra();
	}
	
	/**
     * If the era that has just ended is the last one it proceeds to calculate the winner. Else a new era is created.
	 * @throws Exception if it can't get the current excommunication card from vatican.
	 */
	public void nextEra() throws Exception{
		// Check if it's the last era so the game will end.
		if(currentEra == 3){
			for(Player p:players){
					 p.getActionBuilder().setState(new VaticanChoiceState(vatican.getCard(eras[currentEra])));
					 p.getActionBuilder().refuseVatican();
			}
			calculateWinner();
			return;
		} 
		
		for(Player p:players){
			if(p.hasAnsweredVatican()){
				p.setAnsweredVatican(false);
				continue;
			}
			else{
				 p.getActionBuilder().setState(new VaticanChoiceState(vatican.getCard(eras[currentEra])));
				 p.getActionBuilder().refuseVatican();
				 p.setAnsweredVatican(false);
			}
		}
		
		currentEra++;
		roundNumber = 1;
		board = new Board(players.size());
		updateActionBuildersBoard();
		cardDealer.updateBoard(board, eras[currentEra]);
		setPawns();
		calculateTurns();
		currentRound = new Round(turns, this);
		currentRound.getCurrentPlayer().getActionBuilder().setState(new PawnActionState());
	}
	
	/**
	 * Updates the action builder of each player with the game's current board.
	 */
	public void updateActionBuildersBoard(){
		for(Player p:players){
			p.getActionBuilder().setBoard(board);
		}
	}
	
	/**
	 * Changes the player's old observer with the new observer.
	 * @throws Exception if the player with the given playerID is not found.
	 */
	public void reconnect(String playerID, Observer o) throws Exception{
		observers.put(o, playerID);
		Player player = getPlayerByID(playerID);
		player.changeObserver(o);
		player.setDisconnected(false);
		
		System.out.println("SERVER: reconnected player: "+ playerID);
		notifyObservers();
	}
	
	/**
	 * Calculates the turn based on the order in the list of occupants in the CouncilPalaceArea of the game.
	 */
	private void calculateTurns(){
		
			Player[] temp = new Player[numberOfPlayers];
			int i = 0;
			ArrayList<String> added = new ArrayList<String>();
			for(PlayerPawnPair ppp:board.getCouncilPalaceArea().getOccupants()){
				String playerID = ppp.getPlayer().getPlayerID();
				if(!added.contains(playerID)){
					added.add(playerID);
					temp[i] = ppp.getPlayer();
					i++;
				}
			}
			for(int j=0; j < numberOfPlayers; j++){
				String playerID = turns[j].getPlayerID();
				if(!added.contains(playerID)){
					added.add(playerID);
					temp[i] = turns[j];
					i++;
				}
			}
			for(int m=0; m<numberOfPlayers*turnsPerRound;m++){
				//TODO No first turn effect
				turns[m] = temp[m%numberOfPlayers];
			}
			updateColorTurns();
	}
	
	/**
	 * Updates the colors based on the current turns of the players.
	 */
	private void updateColorTurns(){
		for(int j=0; j < numberOfPlayers; j++){
			colorTurns[j] = turns[j].getColor();
		}
	}

	/**
	 * Calculates the turn based on the order the players joined the game.
	 */
	private void calculateTurnsStart(){
		for(int i=0; i<numberOfPlayers*turnsPerRound;i++){
			turns[i] = players.get(i%numberOfPlayers);
		}
		updateColorTurns();
	}
	
	/**
	 * Creates a new player with the correct consumables depending on his turn when he joined the game. It also registers it's observer so the player
	 * will be notified when the state of the game changes.
	 * If the game reached it's maximum player capacity the game is started.
	 * @throws Exception if the game is full and can't add any more players or a player with that ID already exists in the game.
	 * @param playerID the ID of the player.
	 * @param bonusTile the index of the bonus tile the player wants to use, so the correct bonus tile can be read from file.
	 * @param observer the observer that gets notified when the game changes it's state.
	 */
	public void addPlayer(String playerID, String bonusTile, Observer observer) throws Exception{
		if(canAddPlayer(playerID)){
			ConsumableSet cs = new ConsumableSet();
			cs.setWood(Player.defaultWood);
			cs.setStone(Player.defaultStone);
			cs.setServants(Player.defaultServants);
			cs.setCoins(Player.defaultCoins + players.size());
			
			Player p = new Player(playerID, bonusTile, ColorFromInt.getColor(numberOfPlayers), board, cs, cardDealer.getFourLeaders(), observer);
			players.add(p);
			registerObserver(playerID, observer);
			numberOfPlayers++;
			
			System.out.println("SERVER: added player: "+ playerID);
			
			if(players.size() == MAX_NUM_OF_PLAYERS) start();
			
		}
	}
	
	/**
	 * @throws Exception if the a player with that ID already exists in the game or if the game is full.
	 * @param playerID the ID of the player.
	 * @return true if the game can add one more player.
	 */
	private boolean canAddPlayer(String playerID) throws Exception{
		if(playerIDExists(playerID)) throw new Exception("PlayerID already exists");
		if(players.size() >= MAX_NUM_OF_PLAYERS) throw new Exception("Game is full");
		return true;
	}
	
	/**
	 * @param playerID the ID of the player.
	 * @return true if the player with the given ID exists in the game.
	 */
	private boolean playerIDExists(String playerID){
		for(Player p:players){
			if(p.getPlayerID().equals(playerID)) return true;
		}
		return false;
	}
	
	/**
	 * @param playerID the ID of the player.
	 * @return the player that has an ID equal to the given ID.
	 * @throws Exception if no player with the given ID exists.
	 */
	public Player getPlayerByID(String ID) throws Exception{
		for(Player p: players){
			if(p.getPlayerID().equals(ID)){
				return p;
			}
		}
		
		throw new Exception("No such player");
	}

	/**
	 * @return the current number of players in the game.
	 */
	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}
	
	/**
	 * @param playerID the ID of the player.
	 * @param o the new observer of the player with the given playersID.
	 */
	private void registerObserver(String playerID, Observer o){
		observers.put(o, playerID);
	}
	
	
	/**
	 * Serializes itself as a JSON string the notifies each observers.
	 */
	public  void notifyObservers(){
		updatePlayerStatus();
		
		Gson gson = GsonWithInterface.getGson(); 
        String game = gson.toJson(this);
        
        Writer writer;
		try {
			writer = new FileWriter("game.json");
			gson.toJson(this, writer);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		GameUpdateNotifier n = new GameUpdateNotifier(observersList(), game, this);
		n.start();
	}
	
	/**
	 * Updates the available pawns of each player every time a round is started.
	 */
	public void setPawns(){
		Random r = new Random();
		setSinglePawn(r.nextInt(6)+1, PawnType.BLACK);
		setSinglePawn(r.nextInt(6)+1, PawnType.WHITE);
		setSinglePawn(r.nextInt(6)+1, PawnType.ORANGE);
		setNeutralPawn();
		
		for(Player p:players){
			p.getResourceSet().setSmallestToPawnSix();
		}
	}
	
	/**
	 * Sets the pawn with the given Pawn type with the new given value, also makes it available.
	 * @param value the value of the new pawn.
	 * @param pt the color of the new pawn.
	 */
	public void setSinglePawn(int value, PawnType pt){
		dices.put(pt, value);
		
		for(Player p:players){
			p.getResourceSet().setPawn(pt, value, true);
		}
	}
	
	/**
	 * Sets the neutral pawn of each player as available and sets it's value as the player's default neutral pawn value.
	 */
	public void setNeutralPawn(){
		for(Player p:players){
			p.getResourceSet().setPawn(PawnType.NEUTRAL, p.getResourceSet().getPawnSet().getDefaultNeutralValue(), true);
		}
	}
	
	/**
	 * Updates the status of the player/ the list of the available commands he can make as strings(useful for the views).
	 */
	public void updatePlayerStatus(){
		for(Player p: players){
			p.setStatus(p.getActionBuilder().getState().message());
			p.setAvailableCommands(p.getActionBuilder().getState().commands());
		}
	}
	
	/**
	 * @return the list of all the players in the game.
	 */
	public ArrayList<Player> getPlayers(){
		return players;
	}

	/**
	 * @return the current status of the game.
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the current status of the game.
	 * @param status the new status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return true if the game is markes as started.
	 */
	public boolean hasStarted() {
		return gameStarted;
	}

	/**
	 * @return the current board of the game.
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * @return the index of the current era's current round.
	 */
	public int getRoundNumber(){
		return roundNumber;
	}

	/**
	 * @return the index of the current era.
	 */
	public int getCurrentEra() {
		return currentEra;
	}

	/**
	 * @return an array with all the game's eras.
	 */
	public Era[] getEras() {
		return eras;
	}

	/**
	 * @return the current round of the game.
	 */
	public Round getCurrentRound() {
		return currentRound;
	}

	/**
	 * @return the game's vatican object.
	 */
	public Vatican getVatican() {
		return vatican;
	}
	
	/**
	 * @return the players color's ordered by turns.
	 */
	public String[] getColorTurns(){
		return colorTurns;
	}
	
	/**
	 * @return a hashmap containing the game's current dice values.
	 */
	public HashMap<PawnType, Integer> getDices(){
		return dices;
	}
	
	/**
	 * Calculates the end-game victoryPoints of each player then notifies the players 
	 */
	private void calculateWinner(){
		StringBuilder sb = new StringBuilder();
		
		 endGameMilitaryVictoryPoints();
		
		for(Player p:players){
			sb.append(p.getPlayerID() + ": " + endGamePlayerVictoryPoints(p) + " victory points.");
		}
		
		ResultsNotifier n = new ResultsNotifier(observersList(), sb.toString());
		n.start();
	}
	
	/**
	 * Calculates the winner of victory points regarding who has the most military points at the end of the game. 
	 */
	private void endGameMilitaryVictoryPoints(){
		int first = 0;
		int second = 0;
		
		for(Player p:players){
			int points = p.getResourceSet().getResources().getMilitaryPoins();
			if(points > first){
				second = first;
				first = points;
			}else if(points > second){
				second = points;
			}
		}
		for(Player p:players){
			int points = p.getResourceSet().getResources().getMilitaryPoins();
			if(points == first){
				ConsumableSet cs = new ConsumableSet();
				cs.setVictoryPoints(5);
				p.getResourceSet().collect(cs);
			}else if(points == second){
				ConsumableSet cs = new ConsumableSet();
				cs.setVictoryPoints(2);
				p.getResourceSet().collect(cs);
			}
		}
	}
	
	/**
	 * Calculates the player's victory points at the end of the game.
	 * @param p the player whose victory points will be calculated.
	 * @return the player's total number of victory points. 
	 */
	private int endGamePlayerVictoryPoints(Player p){
		VictoryPointsConverter vpc = new VictoryPointsConverter(p);
		
		
		vpc.characterVictoryPoints();
		vpc.applyVentureVictoryPoints();
		vpc.territoryVictoryPoints();
		vpc.vaticanVictoryPoints();
		vpc.resourceVictoryPoints();
		vpc.aFifthVictoryPointsPenalty();
		vpc.militaryPointsPenalty();
		vpc.resourcePointsPenalty();
		vpc.buildingPointsPenalty();
		
		return p.getResourceSet().getResources().getVictoryPoints();
	}
	
	/**
	 * @return a list with all the observers that are currently observing the game.
	 */
	public ArrayList<Observer>  observersList(){
		ArrayList<Observer> list = new ArrayList<Observer>();
		
		for(Observer o:observers.keySet()){
			list.add(o);
		}
		return list;
	}
	

	/**
	 * The method in which the game get's notified when it's current round changed the current turn so it can notify it's observers. 
	 */
	@Override
	public synchronized void notify(String json) {
		notifyObservers();
	}
	
	/**
	 * Removes a player's observer and marks the player as disconnected so his turn will be skipped.
	 */
	public void removeObserver(Observer o){
		try {
			getPlayerByID(observers.get(o)).setDisconnected(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		observers.remove(o);
	}

	
}
