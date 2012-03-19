package de.uulm.presenter.device;

import java.util.Vector;

public class RemoteDevice {
	
	private static final RemoteDevice r = new RemoteDevice();

	public static RemoteDevice getInstance(){
		return r;
	}
	
	public Vector getDevices(){
		Vector v = new Vector(3);
		v.addElement("Device 1");
		v.addElement("Device 2");
		v.addElement("Device 3");
		return v;
	}
}
