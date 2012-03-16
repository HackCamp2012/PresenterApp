package de.uulm.presenter.connection.protocol;

public class RemoteProtocol extends MessageProtocol {
	private static final String TYPE_KEY = "k";
	private static final String TYPE_MOUSE = "m";
	
	private static final String NONE = "";
	
	public void sendKey(int keyCode,String action){
		//TODO implement
	}
	 
	public void sendKey(int keyCode){
		//TODO implement		
	}
	
	public void sendMouse(double x, double y, String action){
		//TODO implement		
	}
	public void sendMouse(double x, double y){
		//TODO implement		
	}
}
