package it.polimi.ingsw.ps45.client;


import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import it.polimi.ingsw.ps45.view.gui.GUI;
import it.polimi.ingsw.ps45.view.gui.GUIController;

/**
 * Client that runs the GUI view.
 */

public class ClientFrame extends JFrame{

    private transient Socket socket;
    private transient ObserverThread observerThread;
    private transient GUI gui;
    private transient GUIController controller;
    
	private JPanel contentPane;
	private JTextField nameTextField;
	private JTextField ipTextField;
	private JTextField messageTextField;
	private JComboBox bonusTileComboBox;
	private JComboBox viewComboBox;
	private JButton btnConnect;
	private JButton btnReconnect;
    
    private static final int PORTNUMBER = 12345;
    private static int width = 450;
    private static int height = 300;
    
	/**
 	 * Constructor
	 */
    public ClientFrame(){
    	initializeComponents();
    }
    
	/**
 	 * Constructor
 	 * Initializes the welcome screen in which the player enters the ip of the server, his name, and other details.
	 */
    public void initializeComponents(){
    	setTitle("Lorenzo Il Magnifico");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, width, height);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{75, 200, 75, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblLorenzoIlMagnifico = new JLabel("Lorenzo Il Magnifico");
		lblLorenzoIlMagnifico.setForeground(Color.MAGENTA);
		lblLorenzoIlMagnifico.setFont(new Font("Wolf in the City Light", Font.PLAIN, 35));
		GridBagConstraints gbc_lblLorenzoIlMagnifico = new GridBagConstraints();
		gbc_lblLorenzoIlMagnifico.insets = new Insets(0, 0, 5, 5);
		gbc_lblLorenzoIlMagnifico.gridx = 1;
		gbc_lblLorenzoIlMagnifico.gridy = 0;
		contentPane.add(lblLorenzoIlMagnifico, gbc_lblLorenzoIlMagnifico);
		
		JLabel lblPlayerName = new JLabel("Name:");
		GridBagConstraints gbc_lblPlayerName = new GridBagConstraints();
		gbc_lblPlayerName.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlayerName.anchor = GridBagConstraints.EAST;
		gbc_lblPlayerName.gridx = 0;
		gbc_lblPlayerName.gridy = 1;
		contentPane.add(lblPlayerName, gbc_lblPlayerName);
		
		nameTextField = new JTextField("", 25);
		GridBagConstraints gbc_nameTextField = new GridBagConstraints();
		gbc_nameTextField.insets = new Insets(0, 0, 5, 5);
		gbc_nameTextField.gridx = 1;
		gbc_nameTextField.gridy = 1;
		contentPane.add(nameTextField, gbc_nameTextField);
		
		JLabel lblIp = new JLabel("IP:");
		GridBagConstraints gbc_lblIp = new GridBagConstraints();
		gbc_lblIp.anchor = GridBagConstraints.EAST;
		gbc_lblIp.insets = new Insets(0, 0, 5, 5);
		gbc_lblIp.gridx = 0;
		gbc_lblIp.gridy = 2;
		contentPane.add(lblIp, gbc_lblIp);
		
		ipTextField = new JTextField("127.0.0.1", 25);
		GridBagConstraints gbc_ipTextField = new GridBagConstraints();
		gbc_ipTextField.insets = new Insets(0, 0, 5, 5);
		gbc_ipTextField.gridx = 1;
		gbc_ipTextField.gridy = 2;
		contentPane.add(ipTextField, gbc_ipTextField);

		
		JLabel lblBonustile = new JLabel("Bonus Tile:");
		GridBagConstraints gbc_lblBonustile = new GridBagConstraints();
		gbc_lblBonustile.insets = new Insets(0, 0, 5, 5);
		gbc_lblBonustile.anchor = GridBagConstraints.EAST;
		gbc_lblBonustile.gridx = 0;
		gbc_lblBonustile.gridy = 3;
		contentPane.add(lblBonustile, gbc_lblBonustile);
		
		bonusTileComboBox = new JComboBox();
		bonusTileComboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5"}));
		bonusTileComboBox.setSelectedIndex(0);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 3;
		contentPane.add(bonusTileComboBox, gbc_comboBox);
		
		
		JLabel lblMessage = new JLabel("Message:");
		GridBagConstraints gbc_lblMessage = new GridBagConstraints();
		gbc_lblMessage.insets = new Insets(0, 0, 5, 5);
		gbc_lblMessage.anchor = GridBagConstraints.EAST;
		gbc_lblMessage.gridx = 0;
		gbc_lblMessage.gridy = 5;
		contentPane.add(lblMessage, gbc_lblMessage);
		
		messageTextField = new JTextField("", 30);
		messageTextField.setEditable(false);
		GridBagConstraints gbc_messageTextField = new GridBagConstraints();
		gbc_messageTextField.insets = new Insets(0, 0, 5, 5);
		gbc_messageTextField.gridx = 1;
		gbc_messageTextField.gridy = 5;
		contentPane.add(messageTextField, gbc_messageTextField);
		

		
		btnConnect = new JButton("Connect");
		GridBagConstraints gbc_btnConnect = new GridBagConstraints();
		gbc_btnConnect.insets = new Insets(0, 0, 5, 5);
		gbc_btnConnect.gridx = 1;
		gbc_btnConnect.gridy = 6;
		btnConnect.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
					connectButtonPressed();
			} 
		} );
		contentPane.add(btnConnect, gbc_btnConnect);
		
		btnReconnect = new JButton("Reconnect");
		GridBagConstraints gbc_btnReconnect = new GridBagConstraints();
		gbc_btnReconnect.insets = new Insets(0, 0, 5, 5);
		gbc_btnReconnect.gridx = 1;
		gbc_btnReconnect.gridy = 7;
		btnReconnect.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
					reconnectButtonPressed();
			} 
		} );
		contentPane.add(btnReconnect, gbc_btnReconnect);
		
		pack();
    }
    
	/**
	 * Called when the user clicks the connect button in the welcome screen.
	 * It tries to connect to the server and then it send a AddPlayerCommand to the server.
	 */
    private void connectButtonPressed(){
    	Runnable r = new Runnable(){

			@Override
			public void run() {
		    	try{
		        	String playerID = nameTextField.getText();
		    		socket = new Socket(ipTextField.getText(), PORTNUMBER);
		    		gui = new GUI(playerID);
		    		controller = new GUIController(gui, new OutputStreamWriter(socket.getOutputStream()), playerID);
		    		observerThread = new ObserverThread(new BufferedReader(new InputStreamReader(socket.getInputStream())), gui);
		    		observerThread.start();
		    		controller.sendJoinCommand((String) bonusTileComboBox.getSelectedItem());
		    		gui.addController(controller);
		    	}catch(Exception e){
		    		messageTextField.setText("Error: could not connect.");
		    	}
			}
		};
		new Thread(r).start();
    }
    
    
	/**
	 * Called when the user clicks the reconnect button in the welcome screen.
	 * It tries to reconnect to the server and then it send a ReconnectCommand to the server.
	 */
    private void reconnectButtonPressed(){
    	Runnable r = new Runnable(){

			@Override
			public void run() {
		    	String playerID = nameTextField.getText();
		    	
		    	try{
					socket = new Socket(ipTextField.getText(), PORTNUMBER);
					gui = new GUI(playerID);
					controller = new GUIController(gui, new OutputStreamWriter(socket.getOutputStream()), playerID);
					observerThread = new ObserverThread(new BufferedReader(new InputStreamReader(socket.getInputStream())), gui);
					observerThread.start();
					controller.sendReconnectCommand();
					gui.addController(controller);
		    	}catch(Exception e){
		    		messageTextField.setText("Error: could not reconnect.");
		    	}
			}
		};
		new Thread(r).start();
    }


}