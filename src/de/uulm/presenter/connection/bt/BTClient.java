package de.uulm.presenter.connection.bt;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Vector;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;
import javax.bluetooth.UUID;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;


import de.uulm.presenter.util.Log;


public class BTClient implements RemoteHCIService, Runnable{
	public BTDeviceListener deviceListener;
	private Vector services;
	private Vector serviceDevices;
	private InputStream in;
	private OutputStream out;
	private boolean listening = true;
	private StreamConnection con;
	
	public BTClient() {
		services = new Vector();
		serviceDevices = new Vector();
	}
	
	
	public Vector getDevices() throws BluetoothStateException{
		serviceDevices.setSize(0);
		deviceListener = new BTDeviceListener(this);
		deviceListener.clearDevices();
		LocalDevice local_device = LocalDevice.getLocalDevice();
		
        DiscoveryAgent disc_agent = local_device.getDiscoveryAgent();
        
        local_device.setDiscoverable(DiscoveryAgent.GIAC);
        
		disc_agent.startInquiry(DiscoveryAgent.GIAC, deviceListener);
		
		Vector discDev = deviceListener.getDiscoveredDevices();//warning, this method blocks!
		
		Enumeration devices  = discDev.elements();
		if (discDev.size()==0){
			
			return new Vector(0);
		}else{
			UUID[] u = new UUID[]{new UUID( SERVICEUUID , false )};
	        int attrbs[] = { 0x0100 };
	        
	        while( devices.hasMoreElements() ) {
	        	synchronized (deviceListener) {
	        		RemoteDevice elem = (RemoteDevice)devices.nextElement();
		        	disc_agent.searchServices(attrbs, u, elem, deviceListener);
		
		        	try{
		    			deviceListener.wait();
		    		} catch(InterruptedException e){e.printStackTrace();}
		    		
		        	ServiceRecord r = deviceListener.getService();
		        	if (r != null){
		        		//service found
		        		String friendlyname;
						try {
							friendlyname = elem.getFriendlyName(false);
							serviceDevices.addElement(friendlyname);
						} catch (IOException e) {
							continue;
						}
						services.addElement(r);
		        	}
	        	}
	        }
	        
	        return serviceDevices;
	        
		}
		
	}
	
	public boolean connect(int index) throws IndexOutOfBoundsException, IOException{
		Log.log("connect...",this.getClass(),"connect");
        ServiceRecord record = (ServiceRecord) services.elementAt(index);
        String url;
        url = record.getConnectionURL(0, false);
        con = (StreamConnection) Connector.open(url);
        in = con.openInputStream();
        out = con.openOutputStream();
        Thread t = new Thread(this);
        t.start();
        return true;
	}
	
	
	public void run() {
		byte[] b = new byte[1000];
		
		while (listening) {            
			try {
				int len = in.read(b);
				if (len == -1){
					//exit
					listening=false;
					recv(null, -1); 
					return;
					
				}
			    recv(b, len);	
			} catch (IOException e) {
				
				recv(null, -1);
			}
		}
        
	}
	
	public void recv(byte[] recv, int len){
		
	}
	
    public void send(byte[] b){

    	try {
        	out.write(b);
        	out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

	
	
	
}

class BTDeviceListener implements DiscoveryListener{
	private final Vector discoveredDevices;
	private ServiceRecord service;
	public void clearDevices(){
		discoveredDevices.setSize(0);
		service = null;
	}
	public final synchronized Vector getDiscoveredDevices(){
		try{
			this.wait();
		} catch(InterruptedException e){e.printStackTrace();}
		return discoveredDevices;
	}
	
	public final synchronized ServiceRecord getService(){
		ServiceRecord r=service;
		service = null;
		return r;
	}
	public BTDeviceListener(BTClient client) {
		discoveredDevices = new Vector();
		
	}
	
	public void deviceDiscovered(RemoteDevice btDevice, DeviceClass cod) {
		
        if( ! discoveredDevices.contains( btDevice ) )	{
        	
            try {
				btDevice.getFriendlyName(false);
			} catch (IOException e) {
				return;
			}
            discoveredDevices.addElement( btDevice );
        }
		
	}

	public void inquiryCompleted(int discType) {
		
		synchronized(this){	
        	this.notify();
        }
		
	}

	public void serviceSearchCompleted(int transID, int respCode) {
		
		synchronized(this){	
    		this.notify();
    	}
		
	}

	public void servicesDiscovered(int transID, ServiceRecord[] servRecord) {
		
		this.service=servRecord[0];
	
	}
	
	
}
