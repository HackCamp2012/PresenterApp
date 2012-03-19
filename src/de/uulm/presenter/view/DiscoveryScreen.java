package de.uulm.presenter.view;

import java.io.IOException;
import java.util.Vector;

import com.sun.lwuit.*;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.layouts.BoxLayout;

import de.uulm.presenter.controler.Main;
import de.uulm.presenter.device.RemoteDevice;
import de.uulm.presenter.view.style.MainStyle;
import de.uulm.presenter.view.style.PresenterStyle;

public class DiscoveryScreen extends MainStyle implements ActionListener{
	
	private Command exit;
	private Command connect;
	private Main m;
	private Vector devices;
	private ButtonGroup group; 
	
	public DiscoveryScreen(Main m, Vector devices){
		this.m = m;
		setMainStyle();
		this.devices = devices;
		init();
	}
	
	public void init(){
		exit = new Command("Exit");
		connect = new Command("Connect");
		
		RadioButton rb;
		group = new ButtonGroup();
		
		setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		
		Label title = new Label("Select a device: ");
		title.getStyle().setBgTransparency(0);
		title.getStyle().setFgColor(0xffffff);
		title.getStyle().setFont(f);
		title.getStyle().setAlignment(CENTER);
		title.getStyle().setPadding(8, 8, 0, 0);
		
		addComponent(title);
		
		if(devices.size()==0){
			Label noDevice = new Label("No devices Found");
			addComponent(noDevice);
		}
		
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
		
		addCommand(exit);
		addCommand(connect);
		addCommandListener(this);
	}

	public void actionPerformed(ActionEvent evt) {
		if(evt.getCommand().equals(exit)){
			m.exitApp();
		}
		if(evt.getCommand().equals(connect)){
			try {
				RemoteDevice.getInstance().connect(group.getSelectedIndex());
				AccessKeyDialog dialog = new AccessKeyDialog();
				dialog.show();
				int accessKey = dialog.getValue();
				RemoteDevice.getInstance().sendAuthString(""+accessKey);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			//PresenterScreen p = new PresenterScreen();
			//p.show();
		}
		
	}
	
}
