package it.polimi.ingsw.ps45.view.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import it.polimi.ingsw.ps45.model.area.HasDictionary;
import it.polimi.ingsw.ps45.model.game.Game;
import it.polimi.ingsw.ps45.model.player.PawnType;
import it.polimi.ingsw.ps45.model.player.Player;

/**
 * Class that listens to the GUI's combo boxes and buttons and updates their contents as necessary to make the game easier for the user. 
 */



@SuppressWarnings("rawtypes")
public class CommandComboBoxListener implements ActionListener{
	
	private static final Logger LOGGER = Logger.getLogger( CommandComboBoxListener.class.getName());
	
	private JComboBox areas;
	private JComboBox pawns;
	private JComboBox servants;
	private Game g;
	private Player p;
	
	private String[] none = {"---"};

	
	/**
 	 * Constructor
 	 * @param g the game the view is currently "viewing".
 	 * @param p the ID of the player controlling the view.
 	 * @param areas a JComboBox in which the possible areas are displayed.
 	 * @param pawns a JComboBox in which the possible pawns are displayed.
 	 * @param servants a JComboBox in which the possible servants that can be added are displayed.
	 */
	public CommandComboBoxListener(Game g, Player p,  JComboBox areas, JComboBox pawns, JComboBox servants){
		this.areas = areas;
		this.pawns = pawns;
		this.servants = servants;
		this.g = g;
		this.p = p;
	}
	
	/**
 	 * Updates this with a new game object/player.
 	 * @param g the game the view is currently "viewing".
 	 * @param p the ID of the player controlling the view.
	 */
	public void update(Game g, Player p){
		this.g = g;
		this.p = p;
	}
	
	/**
 	 * Gets all areas from an object that implements HasDictionary as strings.
 	 * @param hs the object which implements HasDictionary.
 	 * @return an array of string that represent the areas in the object.
	 */
	private String[] getAvailableAreas(HasDictionary hs){
		ArrayList<String> available = new ArrayList<String>();
		for(String key: hs.getDictionary().keySet()){
			try {
				if(hs.getAreaFromString(key).isAvailable()) available.add(key);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				LOGGER.log(Level.SEVERE, "context", e);
			}
		}

		return available.toArray(new String[available.size()]);
	}
	
	/**
 	 * @return a list containing all the player's pawns that are currently available.
	 */
	public String[] getAvailablePawns(){
		ArrayList<String> available = new ArrayList<String>();
		for(PawnType pt: p.getResourceSet().getPawnSet().getPawnMap().keySet()){
			if(p.getResourceSet().getPawnSet().getPawnMap().get(pt).isAvailable()) available.add(pt.getColor());
		}
		return available.toArray(new String[available.size()]);
	}
	
	/**
 	 * @return a list containing all possible numbers of servants the player could add to an action.
	 */
	public String[] getAvailableServants(){
		ArrayList<String> available = new ArrayList<String>();
		int servants = p.getResourceSet().getResources().getServants();
		for(int i=0; i <= servants; i++){
			available.add(String.valueOf(i));
		}
		return available.toArray(new String[available.size()]);
	}
	
	/**
 	 * @return a list containing the indexes of the leader cards the player currently owns.
	 */
	public String[] getAvailableLeaderCards(){
		ArrayList<String> available = new ArrayList<String>();
		int size = p.getResourceSet().getLeaderCardList().size();
		for(int i=0; i <= size-1; i++){
			available.add(String.valueOf(i));
		}
		return available.toArray(new String[available.size()]);
	}
	
	/**
 	 * @return a list containing the indexes of the activated leader cards the player currently owns.
	 */
	public String[] getAvailableActivatedLeaderCards(){
		ArrayList<String> available = new ArrayList<String>();
		int size = p.getResourceSet().getActivatedLeaderCardList().size();
		for(int i=0; i <= size-1; i++){
			available.add(String.valueOf(i));
		}
		return available.toArray(new String[available.size()]);
	}

	/**
	 * Updates all the JComboBoxes with relevant information depending on which action the user selected.
 	 * @param e The action event which "fired" this method.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JComboBox cb = (JComboBox)e.getSource();
        String s = (String)cb.getSelectedItem();
        String[] servantsAvailable = getAvailableServants();
        
        switch(s){
		case "placepawnnocard":
			changeModel(areas, getAvailableAreas(g.getBoard()));
			changeModel(pawns, getAvailablePawns());
			changeModel(servants, servantsAvailable);
			break;
		case "placepawnharvest":
			changeModel(areas, getAvailableAreas(g.getBoard().getHarvestAreas()));
			changeModel(pawns, getAvailablePawns());
			changeModel(servants, servantsAvailable);
			break;
		case "placepawnproduction":
			changeModel(areas, getAvailableAreas(g.getBoard().getProductionAreas()));
			changeModel(pawns, getAvailablePawns());
			changeModel(servants, servantsAvailable);
			break;
		case "placepawnterritory":
			changeModel(areas, getAvailableAreas(g.getBoard().getTerritoryTower()));
			changeModel(pawns, getAvailablePawns());
			changeModel(servants, servantsAvailable);
			break;
		case "placepawncharacter":
			changeModel(areas, getAvailableAreas(g.getBoard().getCharacterTower()));
			changeModel(pawns, getAvailablePawns());
			changeModel(servants, servantsAvailable);
			break;
		case "placepawnbuilding":
			changeModel(areas, getAvailableAreas(g.getBoard().getBuildingTower()));
			changeModel(pawns, getAvailablePawns());
			changeModel(servants, servantsAvailable);
			break;
		case "placepawnventure":
			changeModel(areas, getAvailableAreas(g.getBoard().getVentureTower()));
			changeModel(pawns, getAvailablePawns());
			changeModel(servants, servantsAvailable);
			break;
		case "nopawnterritory":
			changeModel(areas, getAvailableAreas(g.getBoard().getTerritoryTower()));
			changeModel(pawns, none);
			changeModel(servants, servantsAvailable);
			break;
		case "nopawncharacter":
			changeModel(areas, getAvailableAreas(g.getBoard().getCharacterTower()));
			changeModel(pawns, none);
			changeModel(servants, servantsAvailable);
			break;
		case "nopawnbuilding":
			changeModel(areas, getAvailableAreas(g.getBoard().getBuildingTower()));
			changeModel(pawns, none);
			changeModel(servants, servantsAvailable);
			break;
		case "nopawnventure":
			changeModel(areas, getAvailableAreas(g.getBoard().getVentureTower()));
			changeModel(pawns, none);
			changeModel(servants, servantsAvailable);
			break;
		case "acceptvatican":
			changeModel(areas, none);
			changeModel(pawns, none);
			changeModel(servants, none);
			break;
		case "refusevatican":
			changeModel(areas, none);
			changeModel(pawns, none);
			changeModel(servants, none);
			break;
		case "addservantsharvest":
			changeModel(areas, none);
			changeModel(pawns, none);
			changeModel(servants, servantsAvailable);
			break;
		case "addservantsproduction":
			changeModel(areas, none);
			changeModel(pawns, none);
			changeModel(servants, servantsAvailable);
			break;
		case "cp1":
			changeModel(areas, none);
			changeModel(pawns, none);
			changeModel(servants, none);
			break;
		case "cp2":
			changeModel(areas, none);
			changeModel(pawns, none);
			changeModel(servants, none);
			break;
		case "cp3":
			changeModel(areas, none);
			changeModel(pawns, none);
			changeModel(servants, none);
			break;
		case "harvest":
			changeModel(areas, none);
			changeModel(pawns, none);
			changeModel(servants, none);
			break;
		case "production":
			changeModel(areas, none);
			changeModel(pawns, none);
			changeModel(servants, none);
			break;
		case "activateleader":
			changeModel(areas, getAvailableLeaderCards());
			changeModel(pawns, none);
			changeModel(servants, none);
			break;
		case "useleader":
			changeModel(areas, getAvailableActivatedLeaderCards());
			changeModel(pawns, none);
			changeModel(servants, none);
			break;
		default: 			
			changeModel(areas, none);
			changeModel(pawns, none);
			changeModel(servants, none);
			break;
		}
		
	}
	/**
	 * Changes the model of a JComboBox with a new one containing the string in the argument s.
 	 * @param box the box whose model will be changed.
 	 * @param s the new options of that box.
	 */
	@SuppressWarnings("unchecked")
	public void changeModel(JComboBox box, String[] s){
		DefaultComboBoxModel model = new DefaultComboBoxModel(s);
		box.setModel(model);
	}

}
