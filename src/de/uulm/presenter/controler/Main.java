package de.uulm.presenter.controler;

import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import com.sun.lwuit.Display;

import de.uulm.presenter.view.PresenterScreen;
import de.uulm.presenter.view.PreventScreenSaver;
import de.uulm.presenter.view.WelcomeScreen;

public class Main extends MIDlet {

	private WelcomeScreen w;
	private static Main instance;
	private PreventScreenSaver ps;
	public Main() {
		instance = this;
		ps = new PreventScreenSaver();
	}
	
	public static Main getInstance(){
		return instance;
	}

	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
		notifyDestroyed();
	}

	protected void pauseApp() {
	}

	protected void startApp() throws MIDletStateChangeException {
		
		Display.init(this);
		//w = new WelcomeScreen();
		//w.show();
		PresenterScreen p = new PresenterScreen();
		p.show();
	}
	
	public void exitApp(){
		notifyDestroyed();
	}

}