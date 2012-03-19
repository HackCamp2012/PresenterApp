package de.uulm.presenter.device;

import java.io.IOException;
import java.util.Vector;

import javax.bluetooth.BluetoothStateException;

import org.json.me.JSONException;
import org.json.me.JSONObject;

import de.uulm.presenter.connection.protocol.MessageConstants;
import de.uulm.presenter.connection.protocol.RemoteProtocol;

public class RemoteDeviceMessenger {
	protected final RemoteProtocol client;
	protected final CommandMapping cmds;
	public RemoteDeviceMessenger() {
		cmds = CommandMapping.getDefaultMapping();
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
	
	protected void processKeys(Key[] k) {
		for (int i = 0;i<k.length;i++){
			JSONObject o = new JSONObject();
			try {
				o.put("type", MessageConstants.KEY);
				o.put("event", k[i].getAction());
				o.put("keycode", k[i].getKeycode());
				this.client.sendMessage( o.toString() );
				
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}

}
