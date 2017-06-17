package it.polimi.ingsw.ps45.view.gui;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class GUIWindowListener implements WindowListener{
	
	private boolean ready;
	
	public GUIWindowListener(){
		this.ready = false;
	}
	
	public boolean isReady(){
		return ready;
	}

	@Override
	public void windowActivated(WindowEvent e) {

	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		ready = true;
		System.out.println("Window ready");
	}

}
