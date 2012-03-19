package de.uulm.presenter.view;

import com.sun.lwuit.*;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;

public class AccessKeyDialog extends Dialog implements ActionListener{

	private final TextField keyInput;
	private final Command ok;
	private final Command cancel;
	
	public AccessKeyDialog(){
		super("Access Key: ");
		ok = new Command("OK");
		cancel = new Command("Cancel");
		keyInput = new TextField("", 7);
		keyInput.setConstraint(TextArea.NUMERIC);
		addComponent(keyInput);
		addCommand(cancel);
		addCommand(ok);
		
	}

	public void actionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		
	}
	
}
