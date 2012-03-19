package de.uulm.presenter.controler;

import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import com.sun.lwuit.Display;

import de.uulm.presenter.view.WelcomeScreen;

public class Main extends MIDlet {

	private WelcomeScreen w;
	
	public Main() {
	}

	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
		notifyDestroyed();
	}

	protected void pauseApp() {
	}

	protected void startApp() throws MIDletStateChangeException {
		
		Display.init(this);
		w = new WelcomeScreen(this);
		w.show();
	}
	
	public void exitApp(){
		notifyDestroyed();
	}

}
