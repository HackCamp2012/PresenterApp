package de.uulm.presenter.device;

import java.util.Vector;

import de.uulm.presenter.connection.protocol.MessageConstants;

public class KeyStroke {
	private final Vector keys;
	public KeyStroke() {
		keys = new Vector();
	}
	
	public KeyStroke up(int keycode){
		this.keys.addElement(new Key(keycode,MessageConstants.KEYUP));
		return this;
	}
	public KeyStroke down(int keycode){
		this.keys.addElement(new Key(keycode,MessageConstants.KEYDOWN));
		return this;
	}
	
	public KeyStroke press(int keycode){
		this.keys.addElement(new Key(keycode,MessageConstants.KEYPRESS));
		return  this;
	}

	public Key[] getKeys(){
		Key[] ret = new Key[keys.size()];
		for (int i = 0;i<ret.length;i++){
			ret[i]=(Key)keys.elementAt(i);
		}
		return ret;
	}
	
}
