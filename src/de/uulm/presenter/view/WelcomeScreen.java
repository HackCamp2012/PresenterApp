package de.uulm.presenter.view;

import java.util.Vector;

import javax.bluetooth.BluetoothStateException;

import com.sun.lwuit.*;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.layouts.BoxLayout;

import de.uulm.presenter.controler.Main;
import de.uulm.presenter.device.RemoteDevice;
import de.uulm.presenter.view.style.MainStyle;

public class WelcomeScreen extends MainStyle implements ActionListener, Runnable{
	
	private final Label title;
	private final Label instr1;
	private final Label instr2;
	private final Command exit;
	private final Command start;
	private final Main m;
	private SearchingDialog sd;
	private Vector devices;
	
	public WelcomeScreen(Main m){
		this.m = m;
		setMainStyle();
		style.setAlignment(CENTER);
		setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		
		String text1 = "Please start DesktopApp,";
		String text2 = "then click \"start\"";
		
		exit = new Command("Exit");
		start = new Command("Start");
		
		title = new Label("Welcome");
		title.getStyle().setBgTransparency(0);
		title.getStyle().setFont(f);
		title.getStyle().setAlignment(CENTER);
		title.getStyle().setFgColor(0xdddddd);
		title.getStyle().setPadding(10, 10, 0, 0);
		
		instr1 = new Label(text1);
		instr1.getStyle().setBgTransparency(0);
		instr1.getStyle().setFgColor(0xdddddd);
		instr1.setWidth(width);
		instr1.setHeight(50);
		
		instr2 = new Label(text2);
		instr2.getStyle().setBgTransparency(0);
		instr2.getStyle().setFgColor(0xdddddd);
		instr2.setWidth(width);
		instr2.setHeight(50);
		
		addComponent(title);
		addComponent(instr1);
		addComponent(instr2);
		addCommand(exit);
		addCommand(start);
		addCommandListener(this);
	}

	public void actionPerformed(ActionEvent evt) {
		
		if(evt.getCommand().equals(start)){
			Thread t = new Thread(this);
			t.start();
			sd = new SearchingDialog();
			sd.show(dialogTop, dialogBottom, 10, 10, false);
			
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
			devices = RemoteDevice.getInstance().getDevices();
			sd.dispose();
		} catch (BluetoothStateException e) {
			e.printStackTrace();
			//TODO BT error dialog!
		}
		
	}

	
}
