package de.uulm.presenter.device;

import de.uulm.presenter.connection.protocol.MessageConstants;



public class Key implements MessageConstants{
	private final String action;
	private final int keycode;
	
	public Key(int keycode, String action) {
		this.keycode = keycode;
		this.action=action;
	}

	public String getAction() {
		return action;
	}

	public int getKeycode() {
		return keycode;
	}
	
	
	
	
	

}
