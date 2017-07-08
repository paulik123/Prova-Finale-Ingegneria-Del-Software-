package it.polimi.ingsw.ps45.view.gui;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import it.polimi.ingsw.ps45.controller.command.AcceptVaticanCommand;
import it.polimi.ingsw.ps45.controller.command.ActivateLeaderCardCommand;
import it.polimi.ingsw.ps45.controller.command.AddServantsToHarvestCommand;
import it.polimi.ingsw.ps45.controller.command.AddServantsToProductionCommand;
import it.polimi.ingsw.ps45.controller.command.Command;
import it.polimi.ingsw.ps45.controller.command.EndTurnCommand;
import it.polimi.ingsw.ps45.controller.command.ExchangeCouncilPrivilegeOneCommand;
import it.polimi.ingsw.ps45.controller.command.ExchangeCouncilPrivilegeThreeCommand;
import it.polimi.ingsw.ps45.controller.command.ExchangeCouncilPrivilegeTwoCommand;
import it.polimi.ingsw.ps45.controller.command.HarvestCommand;
import it.polimi.ingsw.ps45.controller.command.NoPawnBuildingCommand;
import it.polimi.ingsw.ps45.controller.command.NoPawnCharacterCommand;
import it.polimi.ingsw.ps45.controller.command.NoPawnTerritoryCommand;
import it.polimi.ingsw.ps45.controller.command.NoPawnVentureCommand;
import it.polimi.ingsw.ps45.controller.command.PlacePawnBuildingCommand;
import it.polimi.ingsw.ps45.controller.command.PlacePawnCharacterCommand;
import it.polimi.ingsw.ps45.controller.command.PlacePawnHarvestCommand;
import it.polimi.ingsw.ps45.controller.command.PlacePawnNoCardCommand;
import it.polimi.ingsw.ps45.controller.command.PlacePawnProductionCommand;
import it.polimi.ingsw.ps45.controller.command.PlacePawnTerritoryCommand;
import it.polimi.ingsw.ps45.controller.command.PlacePawnVentureCommand;
import it.polimi.ingsw.ps45.controller.command.ProductionCommand;
import it.polimi.ingsw.ps45.controller.command.RefuseVaticanCommand;
import it.polimi.ingsw.ps45.controller.command.UseLeaderCardCommand;
import it.polimi.ingsw.ps45.exceptions.BadCommandException;

/**
 * Adapter class that takes data from the GUI's elements and outputs commands. 
 * Used to make the "translation" between user input and actual command objects.
 */
public class GUICommandParser {
	
	private JComboBox command;
	private JComboBox area;
	private JComboBox pawn;
	private JComboBox servants;
	private JTextField modes;
	
	
	/**
 	 * Constructor
 	 * @param command the JComboBox that contains commands as strings.
 	 * @param area the JComboBox that contains areas as strings.
 	 * @param pawn the JComboBox that contains pawn types as strings.
 	 * @param servants the JComboBox that contains numbers of servants as strings.
 	 * @param modes JTextField in which the user can input strings for advanced commands.
	 */
	public GUICommandParser(JComboBox command, JComboBox area, JComboBox pawn, JComboBox servants, JTextField modes){
		this.command = command;
		this.area = area;
		this.pawn = pawn;
		this.servants = servants;
		this.modes = modes;
	}
	
	/**
	 * Just a big switch which contains cases for all the commands.
	 * Reads the command string from the command JComboBox.
	 * @throws Exception if the user's input is wrong or badly formatted.
	 */
	public Command parse() throws BadCommandException{
		
		String c = (String)command.getSelectedItem();

		switch(c){
		case "endturn":
			return parseEndTurn();
		case "placepawnnocard":
			return parsePlacePawnNoCard();
		case "placepawnharvest":
			return parsePlacePawnHarvest();
		case "placepawnproduction":
			return parsePlacePawnProduction();
		case "placepawnterritory":
			return parsePlacePawnTerritory();
		case "placepawncharacter":
			return parsePlacePawnCharacter();
		case "placepawnbuilding":
			return parsePlacePawnBuilding();
		case "placepawnventure":
			return parsePlacePawnVenture();
		case "nopawnterritory":
			return parseNoPawnTerritory();
		case "nopawncharacter":
			return parseNoPawnCharacter();
		case "nopawnbuilding":
			return parseNoPawnBuilding();
		case "nopawnventure":
			return parseNoPawnVenture();
		case "acceptvatican":
			return parseAcceptVatican();
		case "refusevatican":
			return parseRefuseVatican();
		case "addservantsharvest":
			return parseAddServantsHarvest();
		case "addservantsproduction":
			return parseAddServantsProduction();
		case "cp1":
			return parseCPOne();
		case "cp2":
			return parseCPTwo();
		case "cp3":
			return parseCPThree();
		case "harvest":
			return parseHarvest();
		case "production":
			return parseProduction();
		case "activateleader":
			return parseActivateLeader();
		case "useleader":
			return parseUseLeader();
		default: throw new BadCommandException("Bad command");
		}
	}
	
	/**
	 * @return the parsed command.
	 */
	public PlacePawnNoCardCommand parsePlacePawnNoCard(){
		return new PlacePawnNoCardCommand((String)area.getSelectedItem(), (String)pawn.getSelectedItem(), Integer.valueOf((String)servants.getSelectedItem()));
	}
	
	/**
	 * @return the parsed command.
	 */
	public PlacePawnHarvestCommand parsePlacePawnHarvest(){
		return new PlacePawnHarvestCommand((String)area.getSelectedItem(), (String)pawn.getSelectedItem(), Integer.valueOf((String)servants.getSelectedItem()));
	}
	
	/**
	 * @return the parsed command.
	 */
	public PlacePawnProductionCommand parsePlacePawnProduction(){
		return new PlacePawnProductionCommand((String)area.getSelectedItem(), (String)pawn.getSelectedItem(), Integer.valueOf((String)servants.getSelectedItem()));
	}
	
	/**
	 * @return the parsed command.
	 */
	public PlacePawnBuildingCommand parsePlacePawnBuilding(){
		return new PlacePawnBuildingCommand((String)area.getSelectedItem(), (String)pawn.getSelectedItem(), Integer.valueOf((String)servants.getSelectedItem()));
	}
	
	/**
	 * @return the parsed command.
	 */
	public PlacePawnCharacterCommand parsePlacePawnCharacter(){
		return new PlacePawnCharacterCommand((String)area.getSelectedItem(), (String)pawn.getSelectedItem(), Integer.valueOf((String)servants.getSelectedItem()));
	}
	
	/**
	 * @return the parsed command.
	 */
	public PlacePawnTerritoryCommand parsePlacePawnTerritory(){
		return new PlacePawnTerritoryCommand((String)area.getSelectedItem(), (String)pawn.getSelectedItem(), Integer.valueOf((String)servants.getSelectedItem()));
	}
	
	/**
	 * @return the parsed command.
	 */
	public PlacePawnVentureCommand parsePlacePawnVenture(){
		return new PlacePawnVentureCommand((String)area.getSelectedItem(), (String)pawn.getSelectedItem(), Integer.valueOf((String)servants.getSelectedItem()), modes.getText());
	}
	
	/**
	 * @return the parsed command.
	 */
	public NoPawnTerritoryCommand parseNoPawnTerritory(){
		return new NoPawnTerritoryCommand((String) area.getSelectedItem(), Integer.valueOf((String)servants.getSelectedItem()));
	}
	
	/**
	 * @return the parsed command.
	 */
	public NoPawnCharacterCommand parseNoPawnCharacter(){
		return new NoPawnCharacterCommand((String)area.getSelectedItem(), Integer.valueOf((String)servants.getSelectedItem()));
	}
	
	/**
	 * @return the parsed command.
	 */
	public NoPawnBuildingCommand parseNoPawnBuilding(){
		return new NoPawnBuildingCommand((String)area.getSelectedItem(), Integer.valueOf((String)servants.getSelectedItem()));
	}
	
	/**
	 * @return the parsed command.
	 */
	public NoPawnVentureCommand parseNoPawnVenture(){
		return new NoPawnVentureCommand((String)area.getSelectedItem(), Integer.valueOf((String)servants.getSelectedItem()), modes.getText());
	}
	
	/**
	 * @return the parsed command.
	 */
	public AcceptVaticanCommand parseAcceptVatican(){
		return new AcceptVaticanCommand();
	}
	
	/**
	 * @return the parsed command.
	 */
	public RefuseVaticanCommand parseRefuseVatican(){
		return new RefuseVaticanCommand();
	}
	
	/**
	 * @return the parsed command.
	 */
	public AddServantsToHarvestCommand parseAddServantsHarvest(){
		return new AddServantsToHarvestCommand(Integer.valueOf((String)servants.getSelectedItem()));
	}
	
	/**
	 * @return the parsed command.
	 */
	public AddServantsToProductionCommand parseAddServantsProduction(){
		return new AddServantsToProductionCommand(Integer.valueOf((String)servants.getSelectedItem()));
	}
	
	/**
	 * @throws Exception if it can't read input from the GUI elements.
	 * @return the parsed command.
	 */
	public ExchangeCouncilPrivilegeOneCommand parseCPOne() throws BadCommandException{
		String[] s = councilPrivilegeParse(1);
		return new ExchangeCouncilPrivilegeOneCommand(s[0]);
	}
	
	/**
	 * @throws Exception if it can't read input from the GUI elements.
	 * @return the parsed command.
	 */
	public ExchangeCouncilPrivilegeTwoCommand parseCPTwo() throws BadCommandException{
		String[] s = councilPrivilegeParse(2);
		return new ExchangeCouncilPrivilegeTwoCommand(s[0], s[1]);
	}
	
	/**
	 * @return the parsed command.
	 * @throws Exception if the user didn't enter the council privileges correctly in the modes JTextField.
	 */
	public ExchangeCouncilPrivilegeThreeCommand parseCPThree() throws BadCommandException{
		String[] s = councilPrivilegeParse(3);
		return new ExchangeCouncilPrivilegeThreeCommand(s[0], s[1], s[2]);
	}
	
	/**
	 * @return the parsed command.
	 */
	public HarvestCommand parseHarvest(){
		return new HarvestCommand();
	}
	
	/**
	 * @throws Exception if it can't read input from the GUI elements.
	 * @return the parsed command.
	 */
	public ProductionCommand parseProduction(){
		return new ProductionCommand(modes.getText());
	}
	
	/**
	 * @return the parsed command.
	 */
	public EndTurnCommand parseEndTurn(){
		return new EndTurnCommand();
	}
	
	/**
	 * @throws Exception if the user didn't enter the council privileges correctly in the modes JTextField.
	 * @return an array containing the council privileges the user has entered.
	 */
	public String[] councilPrivilegeParse(int count) throws BadCommandException{
		String cp = (String) modes.getText();
		String[] cps = cp.split("-");
		if(cps.length != count) throw new BadCommandException("Bad councilPrivilege command");
		return cps;
	}
	
	/**
	 * @return the parsed command.
	 */
	public  ActivateLeaderCardCommand parseActivateLeader(){
		return new ActivateLeaderCardCommand(Integer.valueOf((String)area.getSelectedItem()));
	}
	
	/**
	 * @return the parsed command.
	 */
	public  UseLeaderCardCommand parseUseLeader(){
		return new UseLeaderCardCommand(Integer.valueOf((String)area.getSelectedItem()));
	}
}
