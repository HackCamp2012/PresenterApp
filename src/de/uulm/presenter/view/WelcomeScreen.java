package de.uulm.presenter.view;

import java.util.Vector;

import javax.bluetooth.BluetoothStateException;

import com.sun.lwuit.*;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;

import de.uulm.presenter.controler.Main;
import de.uulm.presenter.device.RemoteDevice;
import de.uulm.presenter.view.style.MainStyle;

public class WelcomeScreen extends MainStyle implements ActionListener, Runnable{
	
	private final Label title;
	private final TextArea instr;
	private final Command exit;
	private final Command start;
	private final Main m;
	private SearchingDialog sd;
	private Vector devices;
	
	public WelcomeScreen(Main m){
		this.m = m;
		setMainStyle();
		String text = "blabla";
		exit = new Command("Exit");
		start = new Command("Start");
		title = new Label("Welcome");
		title.getStyle().setBgTransparency(0);
		instr = new TextArea(text, 10, 20, TextArea.UNEDITABLE);
		instr.getStyle().setBgTransparency(0);
		
		addComponent(title);
		addComponent(instr);
		addCommand(exit);
		addCommand(start);
		addCommandListener(this);
	}

	public void actionPerformed(ActionEvent evt) {
		
		if(evt.getCommand().equals(start)){
			Thread t = new Thread(this);
			t.start();
			sd = new SearchingDialog();
			sd.show();
			
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			DiscoveryScreen d = new DiscoveryScreen(m, devices);
			d.show();
		}
		if(evt.getCommand().equals(exit)){
			m.exitApp();
		}
	}

	public void run() {
		try {
			devices=RemoteDevice.getInstance().getDevices();
			sd.dispose();
		} catch (BluetoothStateException e) {
			e.printStackTrace();
			//TODO BT error dialog!
		}
		
	}

	
}
