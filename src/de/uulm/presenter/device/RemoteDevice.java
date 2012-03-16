package de.uulm.presenter.device;

import java.io.IOException;
import java.util.Vector;

import javax.bluetooth.BluetoothStateException;

import de.uulm.presenter.connection.protocol.RemoteProtocol;

public class RemoteDevice {
	private final RemoteProtocol client;
	private static final RemoteDevice instance = new RemoteDevice(); 
	private final CommandMapping cmds;
	
	public static final RemoteDevice getInstance(){
		return instance;
	}
	
	private RemoteDevice() {
		cmds = new CommandMapping();
		client = new RemoteProtocol();
	}
	
	public CommandMapping getCommandMapping(){
		return cmds;
	}
	
	public Vector getDevices(){
		try {
			return client.getDevices();
		} catch (BluetoothStateException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void connect(int index) throws IOException{
		client.connect(index);
	}
	
	public void nextSlide(){
		
	}
}
