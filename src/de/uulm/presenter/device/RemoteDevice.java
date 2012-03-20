package de.uulm.presenter.device;

import de.uulm.presenter.connection.protocol.MessageListener;



public class RemoteDevice extends RemoteDeviceMessenger{

	private static final RemoteDevice instance = new RemoteDevice(); 
	
	
	public static final RemoteDevice getInstance(){
		return instance;
	}
	
	public void nextSlide(){
		processKeys( cmds.getNextSlide().getKeys() );
	}
	public void prevSlide(){
		processKeys( cmds.getPrevSlide().getKeys() );
	}
	
	public void addMessageListener(MessageListener l){
		client.addMessageListener(l);
	}
	
	public void removeMessageListener(MessageListener l){
		client.removeMessageListener(l);
	}

}
