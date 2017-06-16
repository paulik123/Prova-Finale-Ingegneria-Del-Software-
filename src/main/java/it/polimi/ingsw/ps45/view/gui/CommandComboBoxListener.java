package it.polimi.ingsw.ps45.view.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import it.polimi.ingsw.ps45.model.area.HasDictionary;
import it.polimi.ingsw.ps45.model.game.Game;
import it.polimi.ingsw.ps45.model.player.Pawn;
import it.polimi.ingsw.ps45.model.player.PawnType;
import it.polimi.ingsw.ps45.model.player.Player;
@SuppressWarnings("rawtypes")
public class CommandComboBoxListener implements ActionListener{
	

	private JComboBox areas;
	private JComboBox pawns;
	private JComboBox servants;
	private Game g;
	private Player p;
	
	private String[] none = {"---"};

	
	public CommandComboBoxListener(Game g, Player p,  JComboBox areas, JComboBox pawns, JComboBox servants){
		this.areas = areas;
		this.pawns = pawns;
		this.servants = servants;
		this.g = g;
		this.p = p;
	}
	
	public void update(Game g, Player p){
		this.g = g;
		this.p = p;
	}
	
	private String[] getAvailableAreas(HasDictionary hs){
		ArrayList<String> available = new ArrayList<String>();
		for(String key: hs.getDictionary().keySet()){
			try {
				if(hs.getAreaFromDictionary(key).isAvailable()) available.add(key);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return available.toArray(new String[available.size()]);
	}
	
	public String[] getAvailablePawns(){
		ArrayList<String> available = new ArrayList<String>();
		for(PawnType pt: p.getResourceSet().getPawnSet().getPawnMap().keySet()){
			if(p.getResourceSet().getPawnSet().getPawnMap().get(pt).isAvailable()) available.add(pt.getColor());
		}
		return available.toArray(new String[available.size()]);
	}
	
	public String[] getAvailableServants(){
		ArrayList<String> available = new ArrayList<String>();
		int servants = p.getResourceSet().getResources().getServants();
		for(int i=0; i <= servants; i++){
			available.add(String.valueOf(i));
		}
		return available.toArray(new String[available.size()]);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(g == null) return;
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
		default: 			
			changeModel(areas, none);
			changeModel(pawns, none);
			changeModel(servants, none);
			break;
		}
		
	}
	@SuppressWarnings("unchecked")
	public void changeModel(JComboBox box, String[] s){
		DefaultComboBoxModel model = new DefaultComboBoxModel(s);
		box.setModel(model);
	}

}
