package de.uulm.presenter.view;

import java.io.IOException;
import java.util.Vector;

import javax.bluetooth.BluetoothStateException;

import com.sun.lwuit.*;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.layouts.BoxLayout;

import de.uulm.presenter.connection.protocol.MessageListener;
import de.uulm.presenter.device.RemoteDevice;
import de.uulm.presenter.view.style.MainStyle;
import de.uulm.presenter.view.style.PresenterStyle;

public class DiscoveryScreen extends MainStyle implements ActionListener, Runnable, MessageListener{

	private Command back;
	private Command connect;
	private Command reconnect;
	private Vector devices;
	private ButtonGroup group; 
	private SearchingDialog sd;

	public DiscoveryScreen(Vector devices){
		setMainStyle();
		this.devices = devices;
		init();
	}

	public void init(){

		back = new Command("Back");
		connect = new Command("Connect");
		reconnect = new Command("Search again");

		RadioButton rb;
		group = new ButtonGroup();

		setLayout(new BoxLayout(BoxLayout.Y_AXIS));

		Label title = new Label("Select a device: ");
		title.getStyle().setBgTransparency(0);
		title.getStyle().setFgColor(0xdddddd);
		title.getStyle().setFont(f);
		title.getStyle().setAlignment(CENTER);
		title.getStyle().setPadding(8, 8, 0, 0);

		addComponent(title);

		if(devices.size()==0){
			Label noDevice = new Label("No devices Found");
			noDevice.getStyle().setBgTransparency(0);
			noDevice.getStyle().setFgColor(0xdddddd);
			noDevice.getStyle().setFont(f);
			addComponent(noDevice);
			addCommand(back);
			addCommand(reconnect);
		}
		else{
			for(int i=0; i<devices.size(); i++){
				rb = new RadioButton(devices.elementAt(i).toString());
				rb.setPressedStyle(PresenterStyle.getRadioStyle(f));
				rb.getPressedStyle().setBgTransparency(100);

				rb.setSelectedStyle(PresenterStyle.getRadioStyle(f));
				rb.getSelectedStyle().setBgTransparency(50);

				rb.getStyle().setBgTransparency(0);
				rb.getStyle().setFgColor(0xffffff);
				rb.getStyle().setFont(f);
				rb.getStyle().setPadding(10, 10, 5, 0);
				group.add(rb);
				addComponent(rb);
			}

			group.setSelected(0);
			
			addCommand(back);
			addCommand(connect);
		}

		addCommandListener(this);
		RemoteDevice.getInstance().addMessageListener(this);
	}

	public void actionPerformed(ActionEvent evt) {
		RemoteDevice.getInstance().removeMessageListener(this);
		if(evt.getCommand().equals(back)){
			WelcomeScreen w = new WelcomeScreen();
			w.show();
		}
		if(evt.getCommand().equals(connect)){
			
				connect.setEnabled(false);
				back.setEnabled(false);
			
				AccessKeyDialog dialog = new AccessKeyDialog(this);
				dialog.show((int)(height*0.33), dialogBottom, 10, 10, false);
				
				if(dialog.hasAccess){
					PresenterScreen p = new PresenterScreen();
					p.show();
				}
				else{
					WelcomeScreen w = new WelcomeScreen();
					w.show();
				}
			
		}
		if(evt.getCommand().equals(reconnect)){
			Thread t = new Thread(this);
			t.start();
			sd = new SearchingDialog();
			sd.show(dialogTop, dialogBottom, 10, 10, false);

			try {
				t.join();
			} catch (InterruptedException e) {
				ErrorScreen.getInstance().showError(e.getMessage());
				e.printStackTrace();
			}
			DiscoveryScreen d = new DiscoveryScreen(devices);
			d.show();
		}
	}
	
	public void connect() throws IOException{
		RemoteDevice.getInstance().connect(group.getSelectedIndex());
	}
	
	//TODO tastensperre
	
	public void run() {
		try {
			devices=RemoteDevice.getInstance().getDevices();
			sd.dispose();
		} catch (BluetoothStateException e) {
			ErrorScreen.getInstance().showError(e.getMessage());
			e.printStackTrace();
		}

	}

	public void aMessage(String s) {
		// TODO Auto-generated method stub
	}

	public void errorOccured() {
		ErrorScreen.getInstance().showError("Connection lost");
	}

}
