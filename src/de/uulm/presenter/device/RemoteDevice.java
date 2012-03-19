package de.uulm.presenter.device;



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
	

}
