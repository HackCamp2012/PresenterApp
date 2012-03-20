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
	private final TextArea instr;
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
		
		String text = "Please start DesktopApp,\nthen click \"start\"";
		
		exit = new Command("Exit");
		start = new Command("Start");
		
		title = new Label("Welcome");
		title.getStyle().setBgTransparency(0);
		title.getStyle().setFont(f);
		title.getStyle().setAlignment(CENTER);
		title.getStyle().setFgColor(0xdddddd);
		title.getStyle().setPadding(10, 10, 0, 0);
		
		instr = new TextArea(text);
		instr.setEditable(false);
		instr.getSelectedStyle().setBgTransparency(0);
		instr.getSelectedStyle().setFgColor(0xdddddd);
		instr.getSelectedStyle().setBorder(null);
		
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
			ErrorScreen err = new ErrorScreen(e.getMessage());
			err.show();
			e.printStackTrace();
		}
		
	}

	
}
