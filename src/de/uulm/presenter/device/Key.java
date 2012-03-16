package de.uulm.presenter.device;

import de.uulm.presenter.connection.protocol.MessageConstants;



public class Key implements MessageConstants{
	private final boolean isUp;
	private final int keycode;
	
	public Key(int keycode, boolean isUp) {
		this.keycode = keycode;
		this.isUp=isUp;
	}
	
	
	
	
	

}
