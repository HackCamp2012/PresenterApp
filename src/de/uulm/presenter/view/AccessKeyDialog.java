package de.uulm.presenter.view;

import com.sun.lwuit.*;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;

import de.uulm.presenter.connection.protocol.MessageListener;
import de.uulm.presenter.device.RemoteDevice;
import de.uulm.presenter.util.Log;

public class AccessKeyDialog extends Dialog implements ActionListener, MessageListener{

	private final TextArea status;
	private final Command cancel;
	
	public AccessKeyDialog(){
		super("Authentication");
		getStyle().setBgTransparency(200);
		getStyle().setAlignment(CENTER);
		
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
			dispose();
		}
	}
	
	public void aMessage(String s) {
		Log.log(s, this.getClass(), "aMessage");
		//status.setText(s);
		if ("AUTHOK".equals(s)){
			RemoteDevice.getInstance().removeMessageListener(this);
			dispose();
		}else if ("AUTHREJECT".equals(s)){
			status.setText("Authentication rejected");
			addCommand(cancel);
		}
		
	}

	public void errorOccured() {
		//pass
		Log.log("connection lost", this.getClass(), "errorOccured");
		//status.setText("error");
		
	}
	
}
