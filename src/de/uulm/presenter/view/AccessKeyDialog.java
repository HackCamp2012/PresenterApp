package de.uulm.presenter.view;

import java.io.IOException;

import com.sun.lwuit.*;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;

import de.uulm.presenter.connection.protocol.MessageListener;
import de.uulm.presenter.controler.Main;
import de.uulm.presenter.device.RemoteDevice;
 
public class AccessKeyDialog extends Dialog implements ActionListener, MessageListener{

	private final TextArea status;
	private final Command cancel;
	public boolean hasAccess = false;
	private DiscoveryScreen dscreen;
	
	public AccessKeyDialog(DiscoveryScreen screen){
		super("Authentication");
		getStyle().setBgTransparency(200);
		getStyle().setAlignment(CENTER);
		dscreen = screen;
		cancel = new Command("Cancel");
		
		status = new TextArea("Waiting for authentication");
		status.setEditable(false);
		status.getSelectedStyle().setBgTransparency(0);
		status.getSelectedStyle().setBorder(null);
		
		RemoteDevice.getInstance().addMessageListener(this);
		
		addComponent(status);
		addCommandListener(this);
		
	}
	
	public void actionPerformed(ActionEvent evt) {
		if(evt.getCommand().equals(cancel)){
			Main.getInstance().exitApp();
		}
	}
	
	protected void onShowCompleted() {
		try {
			dscreen.connect();
		} catch (IOException e) {
			ErrorScreen.getInstance().showError("Failed to connect. Please restart the App.");
		}
	}
	public void aMessage(String s) {
		
		//status.setText(s);
		if ("AUTHOK".equals(s)){
			RemoteDevice.getInstance().removeMessageListener(this);
			hasAccess = true;
			dispose();
		}else if ("AUTHREJECT".equals(s)){
			status.setText("Authentication rejected");
			addCommand(cancel);
		}
		
	}

	public void errorOccured() {
		ErrorScreen.getInstance().showError("Connection lost");
		
	}
	
}
