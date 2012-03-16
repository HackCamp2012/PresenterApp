package de.uulm.presenter.controler;

import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import com.sun.lwuit.Display;

import de.uulm.presenter.view.PresenterScreen;

public class Main extends MIDlet {

	PresenterScreen p;
	
	public Main() {
		// TODO Auto-generated constructor stub
	}

	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
		// TODO Auto-generated method stub

	}

	protected void pauseApp() {
		// TODO Auto-generated method stub

	}

	protected void startApp() throws MIDletStateChangeException {
		
		Display.init(this);
		//Display d = Display.getInstance();
		p = new PresenterScreen();
		p.init();
	}

}
