package it.polimi.ingsw.ps45.model.game;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.ps45.model.actions.state.PawnActionState;
import it.polimi.ingsw.ps45.model.area.Board;
import it.polimi.ingsw.ps45.model.area.PlayerPawnPair;
import it.polimi.ingsw.ps45.model.cards.Era;
import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.player.Player;

public class Game {
	private static int turnsPerRound = 4;
	
	private int numberOfPlayers;
	public String gameID;
	
	private Era[] eras = {Era.I, Era.II, Era.III};
	private ArrayList<Player> players;
	private Board board;
	private Player[] turns; 
	private Round currentRound;
	private int currentEra;
	private int roundNumber;
	private boolean gameStarted;
	
	public Game(String gameID){
		this.gameID = gameID;
		
		numberOfPlayers = 0;
		gameStarted = false;
		roundNumber = 0;
		currentEra = 0;
		players = new ArrayList<Player>();
		board = new Board();
		turns = new Player[turnsPerRound * numberOfPlayers];
	}
	
	public void start() throws Exception{
		if(players.size() < numberOfPlayers) throw new Exception("Not enough players");
		if(gameStarted) throw new Exception("Game already started");
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
	
	public void newRound(){
		if(roundNumber % 2 == 1){
			roundNumber++;
			board = new Board();
			updateActionBuildersBoard();
			calculateTurns();
			currentRound = new Round(turns);
			currentRound.getCurrentPlayer().getActionBuilder().setState(new PawnActionState());
		}
		else{
			//TODO vatican
			currentEra++;
			roundNumber = 1;
			
			board = new Board();
			updateActionBuildersBoard();
			calculateTurns();
			currentRound = new Round(turns);
			currentRound.getCurrentPlayer().getActionBuilder().setState(new PawnActionState());
		}
	}
	
	public void updateActionBuildersBoard(){
		for(Player p:players){
			p.getActionBuilder().setBoard(board);
		}
	}
	
	private void calculateTurns(){
		if(board.getCouncilPalaceArea().getOccupants().size() == 0){
			return;
		}else{
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
