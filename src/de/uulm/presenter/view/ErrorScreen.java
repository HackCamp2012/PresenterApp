package de.uulm.presenter.view;

import com.sun.lwuit.*;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.layouts.BoxLayout;

import de.uulm.presenter.view.style.MainStyle;

public class ErrorScreen extends MainStyle implements ActionListener{

	private Label title;
	private static TextArea errMsg;
	private Command back;
	private String errorMessage  = "";
	
	private static ErrorScreen instance;
	
	private ErrorScreen(){
		
		setMainStyle();
		setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		style.setAlignment(CENTER);
		
		back = new Command("Back");
		
		title = new Label("Error");
		title.getStyle().setAlignment(CENTER);
		title.getStyle().setBgTransparency(0);
		title.getStyle().setFont(f);
		title.getStyle().setFgColor(0xdddddd);
		title.getStyle().setPadding(10, 10, 0, 0);
		
		errMsg = new TextArea(errorMessage);
		errMsg.setEditable(false);
		errMsg.getSelectedStyle().setBgTransparency(0);
		errMsg.getSelectedStyle().setFgColor(0xdddddd);
		errMsg.getSelectedStyle().setBorder(null);
		
		addComponent(title);
		addComponent(errMsg);
		addCommand(back);
		addCommandListener(this);
	}
	
	public static ErrorScreen getInstance(){
		if(instance == null){
			instance = new ErrorScreen();
		}
		return instance;
	}
	
	public void showError(String msg){
		errMsg.setText(msg);
		instance.show();
	}
	

	public void actionPerformed(ActionEvent evt) {
		if(evt.getCommand().equals(back)){
			WelcomeScreen w = new WelcomeScreen();
			w.show();
		}
	}
}
