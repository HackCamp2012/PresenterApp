package de.uulm.presenter.view;

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
	
	public DiscoveryScreen(Main m){
		this.m = m;
		setMainStyle();
		init();
	}
	
	public void init(){
		exit = new Command("Exit");
		
		connect = new Command("Connect");
		
		RadioButton rb;
		Vector devices = RemoteDevice.getInstance().getDevices();
		ButtonGroup group = new ButtonGroup();
		
		setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		
		Label title = new Label("Select a device: ");
		title.getStyle().setBgTransparency(0);
		title.getStyle().setFgColor(0xffffff);
		title.getStyle().setFont(f);
		title.getStyle().setAlignment(CENTER);
		title.getStyle().setPadding(8, 8, 0, 0);
		
		addComponent(title);
		
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
			AccessKeyDialog dialog = new AccessKeyDialog();
			dialog.show();
			//PresenterScreen p = new PresenterScreen();
			//p.show();
		}
		
	}
	
}
