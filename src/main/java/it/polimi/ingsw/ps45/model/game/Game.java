package it.polimi.ingsw.ps45.model.game;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import it.polimi.ingsw.ps45.controller.command.Command;
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

public class Game {
	private static final int turnsPerRound = 4;
	private static final int MAX_NUM_OF_PLAYERS = 2;
	
	private int numberOfPlayers;
	
	private transient Era[] eras = {Era.I, Era.II, Era.III};
	private ArrayList<Player> players;
	private transient ArrayList<Observer> observers;
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
	
	public Game(){
		numberOfPlayers = 0;
		gameStarted = false;
		roundNumber = 0;
		currentEra = 0;
		players = new ArrayList<Player>();
		observers = new ArrayList<Observer>();
		dices = new HashMap<PawnType, Integer>();
		board = new Board();
		vatican = new Vatican();
		
		
		try {
			cardDealer = new CardDealer();
		} catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		status = "Game not started";
	}
	
	public void start() throws Exception{
		if(gameStarted) throw new Exception("Game already started");
		turns = new Player[turnsPerRound * numberOfPlayers];
		colorTurns = new String[numberOfPlayers];
		calculateTurnsStart();
		currentRound = new Round(turns);
		roundNumber++;
		gameStarted = true;
		cardDealer.updateBoard(board, eras[currentEra]);
		setPawns();
		currentRound.getCurrentPlayer().getActionBuilder().setState(new PawnActionState());
		
		setStatus("Game has started");
		System.out.println("Game Started");
		
		notifyObservers();
	}
	
	public void nextTurn(String playerID) throws Exception{
		if(!playerID.equals(currentRound.getCurrentPlayer().getPlayerID())) throw new Exception("It's not your turn to end");
		currentRound.nextTurn();
		if(currentRound.roundEnded()){
			newRound();
			return;
		}
	}
	
	public void newRound() throws Exception{
		if(roundNumber % 2 == 1){
			roundNumber++;
			board = new Board();
			updateActionBuildersBoard();
			cardDealer.updateBoard(board, eras[currentEra]);
			setPawns();
			calculateTurns();
			currentRound = new Round(turns);
			currentRound.getCurrentPlayer().getActionBuilder().setState(new PawnActionState());
		}
		else{
			vaticanTurn();
		}
	}
	
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
	
	public void nextEra() throws Exception{
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
		board = new Board();
		updateActionBuildersBoard();
		cardDealer.updateBoard(board, eras[currentEra]);
		setPawns();
		calculateTurns();
		currentRound = new Round(turns);
		currentRound.getCurrentPlayer().getActionBuilder().setState(new PawnActionState());
	}
	
	public void updateActionBuildersBoard(){
		for(Player p:players){
			p.getActionBuilder().setBoard(board);
		}
	}
	
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
	
	private void updateColorTurns(){
		for(int j=0; j < numberOfPlayers; j++){
			colorTurns[j] = turns[j].getColor();
		}
	}

	private void calculateTurnsStart(){
		for(int i=0; i<numberOfPlayers*turnsPerRound;i++){
			turns[i] = players.get(i%numberOfPlayers);
		}
		updateColorTurns();
	}
	
	public void addPlayer(String playerID, Observer observer) throws Exception{
		if(canAddPlayer(playerID)){
			ConsumableSet cs = new ConsumableSet();
			cs.setWood(Player.defaultWood);
			cs.setStone(Player.defaultStone);
			cs.setServants(Player.defaultServants);
			cs.setCoins(Player.defaultCoins + players.size());
			
			Player p = new Player(playerID, ColorFromInt.getColor(numberOfPlayers), board, cs, observer);
			players.add(p);
			registerObserver(observer);
			numberOfPlayers++;
			
			System.out.println("SERVER: added player: "+ playerID);
			
			if(players.size() == MAX_NUM_OF_PLAYERS) start();
			
		}
	}
	
	private boolean canAddPlayer(String playerID) throws Exception{
		if(playerIDExists(playerID)) throw new Exception("PlayerID already exists");
		if(players.size() >= MAX_NUM_OF_PLAYERS) throw new Exception("Game is full");
		return true;
	}
	
	private boolean playerIDExists(String playerID){
		for(Player p:players){
			if(p.getPlayerID().equals(playerID)) return true;
		}
		return false;
	}
	
	public Player getPlayerByID(String ID) throws Exception{
		for(Player p: players){
			if(p.getPlayerID().equals(ID)){
				return p;
			}
		}
		
		throw new Exception("No such player");
	}

	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}
	
	public void registerObserver(Observer o){
		observers.add(o);
	}
	
	
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
		
		GameUpdateNotifier n = new GameUpdateNotifier(observers, game);
		n.start();
	}
	
	public void setPawns(){
		Random r = new Random();
		setSinglePawn(r.nextInt(6)+1, PawnType.BLACK);
		setSinglePawn(r.nextInt(6)+1, PawnType.WHITE);
		setSinglePawn(r.nextInt(6)+1, PawnType.ORANGE);
		setSinglePawn(0, PawnType.NEUTRAL);
	}
	
	public void setSinglePawn(int value, PawnType pt){
		dices.put(pt, value);
		
		for(Player p:players){
			p.getResourceSet().setPawn(pt, value, true);
		}
	}
	
	public void updatePlayerStatus(){
		for(Player p: players){
			p.setStatus(p.getActionBuilder().getState().message());
			p.setAvailableCommands(p.getActionBuilder().getState().commands());
		}
	}
	
	public ArrayList<Player> getPlayers(){
		return players;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean hasStarted() {
		return gameStarted;
	}

	public Board getBoard() {
		return board;
	}

	public int getRoundNumber(){
		return roundNumber;
	}

	public int getCurrentEra() {
		return currentEra;
	}

	public Era[] getEras() {
		return eras;
	}

	public Round getCurrentRound() {
		return currentRound;
	}

	public Vatican getVatican() {
		return vatican;
	}
	
	public String[] getColorTurns(){
		return colorTurns;
	}
	
	public HashMap<PawnType, Integer> getDices(){
		return dices;
	}
	
	
	
	
	
	
	

	
}
