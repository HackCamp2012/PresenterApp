	package de.uulm.presenter.view;

import com.sun.lwuit.Dialog;
import com.sun.lwuit.Font;
import com.sun.lwuit.Label;

public class SearchingDialog extends Dialog implements Runnable{
	
	private Label searching;
	private Label instr;
	
	private final Font f = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_LARGE); 
	private final int numberOfDots = 5;
	
	public SearchingDialog(){
		searching = new Label("searching.");
		searching.getStyle().setBgTransparency(0);
		searching.getStyle().setFont(f);
		animateLabel();
		
		instr = new Label("please be patient");
		instr.getStyle().setBgTransparency(0);
		
		getDialogStyle().setAlignment(CENTER);
		
		addComponent(searching);
		addComponent(instr);
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
			
			e.printStackTrace();
		}
	}

	
}
