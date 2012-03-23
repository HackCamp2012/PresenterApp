package de.uulm.presenter.view;

import java.io.IOException;
import java.util.Vector;

import javax.bluetooth.BluetoothStateException;

import com.sun.lwuit.Command;
import com.sun.lwuit.Image;
import com.sun.lwuit.Label;
import com.sun.lwuit.TextArea;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.layouts.BoxLayout;

import de.uulm.presenter.controler.Main;
import de.uulm.presenter.device.RemoteDevice;
import de.uulm.presenter.view.style.MainStyle;

public class WelcomeScreen extends MainStyle implements ActionListener, Runnable{
	
	private final Label title;
	private final TextArea instr;
	private final Label icon;
	private final Command exit;
	private final Command start;
	private SearchingDialog sd;
	private Vector devices;
	
	public WelcomeScreen(){
		
		setMainStyle();
		style.setAlignment(CENTER);
		setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		
		Image i = null;
		try {
			i = Image.createImage("/images/iconBig.png").scaledHeight((int) (height*0.4));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String text = "Please start DesktopApp,\nthen click \"Start\"";
		
		exit = new Command("Exit");
		start = new Command("Start");
		
		title = new Label("Welcome");
		title.getStyle().setBgTransparency(0);
		title.getStyle().setFont(f);
		title.getStyle().setAlignment(CENTER);
		title.getStyle().setFgColor(0xdddddd);
		title.getStyle().setPadding(10, 10, 0, 0);
		
		icon = new Label(i);
		icon.getStyle().setAlignment(CENTER);
		icon.getStyle().setBgTransparency(0);
		icon.getStyle().setPadding(20, 30, 0, 0);
		
		instr = new TextArea(text);
		instr.setEditable(false);
		instr.getSelectedStyle().setBgTransparency(0);
		instr.getSelectedStyle().setFgColor(0xdddddd);
		instr.getSelectedStyle().setBorder(null);
		
		addComponent(title);
		addComponent(icon);
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
			DiscoveryScreen d = new DiscoveryScreen(devices);
			d.show();
			
		}
		if(evt.getCommand().equals(exit)){
			Main.getInstance().exitApp();
		}
	}

	public void run() {
		try {
			devices = RemoteDevice.getInstance().getDevices();
			sd.dispose();
		} catch (BluetoothStateException e) {
			ErrorScreen.getInstance().showError(e.getMessage());
			e.printStackTrace();
		}
		
	}

	
}
