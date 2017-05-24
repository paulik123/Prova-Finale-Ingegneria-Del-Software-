package it.polimi.ingsw.ps45.model.game;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import it.polimi.ingsw.ps45.model.actions.state.PawnActionState;
import it.polimi.ingsw.ps45.model.actions.state.VaticanChoiceState;
import it.polimi.ingsw.ps45.model.area.Board;
import it.polimi.ingsw.ps45.model.area.PlayerPawnPair;
import it.polimi.ingsw.ps45.model.cards.CardDealer;
import it.polimi.ingsw.ps45.model.cards.Era;
import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.player.Player;
import it.polimi.ingsw.ps45.model.vatican.Vatican;

public class Game {
	private static int turnsPerRound = 4;
	
	private int numberOfPlayers;
	public String gameID;
	
	private Era[] eras = {Era.I, Era.II, Era.III};
	private ArrayList<Player> players;
	private Board board;
	private Vatican vatican;
	private Player[] turns; 
	private Round currentRound;
	private int currentEra;
	private int roundNumber;
	private boolean gameStarted;
	private CardDealer cardDealer;
	
	public Game(String gameID){
		this.gameID = gameID;
		
		numberOfPlayers = 0;
		gameStarted = false;
		roundNumber = 0;
		currentEra = 0;
		players = new ArrayList<Player>();
		board = new Board();
		vatican = new Vatican();
		
		
		try {
			cardDealer = new CardDealer();
		} catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void start() throws Exception{
		if(gameStarted) throw new Exception("Game already started");
		turns = new Player[turnsPerRound * numberOfPlayers];
		calculateTurnsStart();
		currentRound = new Round(turns);
		roundNumber++;
		gameStarted = true;
	}
	
	public void nextTurn(String playerID) throws Exception{
		if(!playerID.equals(currentRound.getCurrentPlayer().getPlayerID())) throw new Exception("It's not your turn to end");
		if(currentRound.nextTurn() && currentEra != 3) newRound();
		if(currentRound.nextTurn() && currentEra == 3); //TODO END GAME CALCULATION
	}
	
	public void newRound() throws Exception{
		if(roundNumber % 2 == 1){
			roundNumber++;
			board = new Board();
			updateActionBuildersBoard();
			cardDealer.updateBoard(board, eras[currentEra]);
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
			if(p.getResourceSet().getResources().getFaithPoints() < currentEra+2) p.getActionBuilder().refuseVatican();
			else {
				p.getActionBuilder().setState(new VaticanChoiceState(vatican.getCard(eras[currentEra])));
			}
		}
		//TODO TIMEOUT FOR SOME TIME TO WAIT FOR RESPONSES THEN RUN nextEra
	}
	
	public void nextEra() throws Exception{
		for(Player p:players){
			if(p.hasAnsweredVatican()){
				p.setAnsweredVatican(false);
				continue;
			}
			else{
				 p.getActionBuilder().refuseVatican();
				 p.setAnsweredVatican(false);
			}
		}
		
		currentEra++;
		roundNumber = 1;
		board = new Board();
		updateActionBuildersBoard();
		cardDealer.updateBoard(board, eras[currentEra]);
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
				turns[m] = temp[m%turnsPerRound];
			}
		
	}

	private void calculateTurnsStart(){
		for(int i=0; i<numberOfPlayers*turnsPerRound;i++){
			turns[i] = players.get(i%turnsPerRound);
		}
	}
	
	public void addPlayer(String playerID) throws Exception{
		if(canAddPlayer(playerID)){
			ConsumableSet cs = new ConsumableSet();
			cs.setWood(Player.defaultWood);
			cs.setStone(Player.defaultStone);
			cs.setServants(Player.defaultServants);
			cs.setCoins(Player.defaultCoins + players.size());
			
			Player p = new Player(playerID, board, cs);
			players.add(p);
			numberOfPlayers++;
		}
	}
	
	private boolean canAddPlayer(String playerID) throws Exception{
		if(playerIDExists(playerID)) throw new Exception("PlayerID already exists");
		if(players.size() >= 4) throw new Exception("Game is full");
		return true;
	}
	
	private boolean playerIDExists(String playerID){
		for(Player p:players){
			if(p.getPlayerID().equals(playerID)) return true;
		}
		return false;
	}
}
