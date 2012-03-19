package de.uulm.presenter.view;


import java.util.Vector;

import javax.bluetooth.BluetoothConnectionException;
import javax.bluetooth.BluetoothStateException;

import com.sun.lwuit.Dialog;
import com.sun.lwuit.Label;

import de.uulm.presenter.device.RemoteDevice;

public class SearchingDialog extends Dialog{
	
	public SearchingDialog(){
		Label searching = new Label("searching...");
	
		addComponent(searching);
		
		
	}
	
	

	
}
