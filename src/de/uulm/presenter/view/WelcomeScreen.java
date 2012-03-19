package de.uulm.presenter.view;

import com.sun.lwuit.*;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;

import de.uulm.presenter.controler.Main;
import de.uulm.presenter.view.style.MainStyle;

public class WelcomeScreen extends MainStyle implements ActionListener{
	
	private final Label title;
	private final TextArea instr;
	private final Command exit;
	private final Command start;
	private final Main m;
	
	public WelcomeScreen(Main m){
		this.m = m;
		setMainStyle();
		String text = "blabla";
		exit = new Command("Exit");
		start = new Command("Start");
		title = new Label("Welcome");
		title.getStyle().setBgTransparency(0);
		instr = new TextArea(text, 10, 20, TextArea.UNEDITABLE);
		instr.getStyle().setBgTransparency(0);
		
		addComponent(title);
		addComponent(instr);
		addCommand(exit);
		addCommand(start);
		addCommandListener(this);
	}

	public void actionPerformed(ActionEvent evt) {
		
		if(evt.getCommand().equals(start)){
			DiscoveryScreen d = new DiscoveryScreen(m);
			d.show();
		}
		if(evt.getCommand().equals(exit)){
			m.exitApp();
		}
	}

}
