package de.uulm.presenter.view;

import com.sun.lwuit.*;
import com.sun.lwuit.layouts.BoxLayout;

import de.uulm.presenter.view.style.MainStyle;

public class ErrorScreen extends MainStyle{

	private Label title;
	private TextArea errMsg;
	private Command back;
	
	public ErrorScreen(String errorMessage){
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
	}
}
