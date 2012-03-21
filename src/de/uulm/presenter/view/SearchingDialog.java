	package de.uulm.presenter.view;

import com.sun.lwuit.Dialog;
import com.sun.lwuit.Font;
import com.sun.lwuit.Label;

import de.uulm.presenter.util.Log;


public class SearchingDialog extends Dialog implements Runnable{
	
	private Label searching;
	
	private final Font f = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_LARGE); 
	private final int numberOfDots = 5;
	
	public SearchingDialog(){
		searching = new Label("searching.");
		searching.getStyle().setBgTransparency(0);
		searching.getStyle().setFont(f);
		
		animateLabel();
		
		getDialogStyle().setAlignment(CENTER);
		getDialogStyle().setBgTransparency(200);
		
		addComponent(searching);
	}
	
	
	private void animateLabel(){
		Thread t = new Thread(this);
		t.start();
		
	}


	public void run() {
		int dots = 0;
		try {
			while (dots < numberOfDots){
				
				Thread.sleep(800);
				
				if(dots == numberOfDots-1){
					searching.setText("searching.");
					repaint();
					dots = 0;
					continue;
				}
				else{
					searching.setText(searching.getText()+".");
					repaint();
					dots++;
				}
			}
		
		} catch (InterruptedException e) {
			Log.log("InterruptedException", this.getClass(), "run");
			e.printStackTrace();
		}
	}

	
}
