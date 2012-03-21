package de.uulm.presenter.view;

import com.sun.lwuit.Display;

public class PreventScreenSaver{
	private Thread flash;
	private Runnable flashTrigger;
	
	public PreventScreenSaver() {
		flashTrigger= new Runnable() {
			
			public void run() {
				while(true){
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
					
						e.printStackTrace();
					}
					Display.getInstance().flashBacklight(0);
					
				}
				
			}
		};
		flash = new Thread(flashTrigger);
		flash.start();
		
	}
	
	 

}
