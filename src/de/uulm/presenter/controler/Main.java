package de.uulm.presenter.controler;

import java.util.Enumeration;

import javax.bluetooth.BluetoothStateException;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.TextBox;
import javax.microedition.lcdui.TextField;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;


import de.uulm.presenter.connection.bt.BTClient;
import de.uulm.presenter.util.Log;

public class Main extends MIDlet {
	private TextBox log;
	public static Main main = null;
	public Main() {
		log = new TextBox("log", "", 1024, TextField.UNEDITABLE);
		main=this;
	}

	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
		// TODO Auto-generated method stub

	}

	protected void pauseApp() {
		// TODO Auto-generated method stub

	}

	protected void startApp() throws MIDletStateChangeException {
		
		
		log("Search for remote Servers, be patient...");
		Display.getDisplay(this).setCurrent(log);
		BTClient c = new BTClient();
		Enumeration e;
		try {
			e = c.getDevices().elements();
			
			while (e.hasMoreElements()){
				String s = (String) e.nextElement();
				log("HCI Remote Device:"+s);
			}
		} catch (BluetoothStateException e1) {
			e1.printStackTrace();
		}
		
	}

	public void log(String text){
		log.setString(log.getString()+"\n"+text);
	}
}
