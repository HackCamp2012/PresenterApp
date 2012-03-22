package de.uulm.presenter.connection.protocol;

import java.util.Vector;

import de.uulm.presenter.connection.bt.BTClient;

public class MessageProtocol extends BTClient{
	private final char SEPARATOR = '|';
	private final StringBuffer message;
	private final Vector remoteDevices;
	
	public MessageProtocol(){
		message = new StringBuffer();
		remoteDevices = new Vector();
	}
	
	public void recv(byte[] b,int cnt) {
		if (b==null && cnt == -1){
			while (remoteDevices.elements().hasMoreElements()){
				MessageListener l = (MessageListener) remoteDevices.elements().nextElement();
				l.errorOccured();
			}
			return;
		}
		String s = new String(b,0,cnt);
		this.recvString(s);		
	}

	private void recvString(String s){
		int msgSeparatorAt=s.indexOf(SEPARATOR);
		
		if (msgSeparatorAt==-1){
			//no separator found = message not yet done
			message.append(s);
			return;
		}
		
		message.append(s.toCharArray(),0,msgSeparatorAt);
		this.aMessage(message.toString());
		message.setLength(0);
		recvString(s.substring(msgSeparatorAt+1));
	}
	
	public void aMessage(String s){
		while (remoteDevices.elements().hasMoreElements()){
			MessageListener l = (MessageListener) remoteDevices.elements().nextElement();
			l.aMessage(s);
		}
	}
	
	
	public void sendMessage(String s) {
		s = s+"|";
		send(s.getBytes());		
	}
	
	
	
	public void addMessageListener(MessageListener r) {
		remoteDevices.addElement(r);
		
	}

	
	public void removeMessageListener(MessageListener r) {
		remoteDevices.removeElement(r);
		
	}
}




