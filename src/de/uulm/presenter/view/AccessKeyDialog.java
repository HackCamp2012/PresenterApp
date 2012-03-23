package de.uulm.presenter.view;

import com.sun.lwuit.*;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.plaf.Border;
import com.sun.lwuit.plaf.Style;

import de.uulm.presenter.connection.protocol.MessageListener;
import de.uulm.presenter.controler.Main;
import de.uulm.presenter.device.RemoteDevice;
 
public class AccessKeyDialog extends Dialog implements ActionListener, MessageListener{

	private final Label title;
	private final TextArea status;
	private final Command cancel;
	public boolean hasAccess = false;
	private final Font f = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_LARGE); 
	
	public AccessKeyDialog(){
		
		getStyle().setBgTransparency(200);
		getStyle().setAlignment(CENTER);
		
		cancel = new Command("Cancel");
		
		title = new Label("Authentication");
		title.getStyle().setBgTransparency(0);
		title.getStyle().setFont(f);
		
		status = new TextArea("Waiting for authentication");
		status.setEditable(false);
		status.getSelectedStyle().setBgTransparency(0);
		status.getSelectedStyle().setBorder(null);
		
		getDialogStyle().setBackgroundType(Style.BACKGROUND_GRADIENT_LINEAR_VERTICAL);
		getDialogStyle().setBackgroundGradientStartColor(0xffffff);
		getDialogStyle().setBackgroundGradientEndColor(0xbbbbbb);
		getDialogStyle().setBorder(Border.createOutsetBorder(2, 0xaaaaaa));
		
		addComponent(title);
		addComponent(status);
		addCommandListener(this);
		RemoteDevice.getInstance().addMessageListener(this);
	}
	
	public void actionPerformed(ActionEvent evt) {
		if(evt.getCommand().equals(cancel)){
			Main.getInstance().exitApp();
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
