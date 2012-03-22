package de.uulm.presenter.view;

import java.io.IOException;

import com.sun.lwuit.*;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.plaf.Style;
import com.sun.lwuit.table.TableLayout;


import de.uulm.presenter.connection.protocol.MessageListener;
import de.uulm.presenter.device.RemoteDevice;

import de.uulm.presenter.gadgets.TimeEvent;
import de.uulm.presenter.gadgets.TimeUpdateListener;
import de.uulm.presenter.gadgets.Timer;

import de.uulm.presenter.view.style.MainStyle;
import de.uulm.presenter.view.style.PresenterStyle;



public class PresenterScreen extends MainStyle implements ActionListener, TimeUpdateListener, MessageListener{

	private Image arrowRight = null;
	private Image arrowLeft = null;
	private final Button next; 
	private final Button back;
	private final Button time;
	private final Timer timer;
	
	private final int timerHeight = (int)(height*0.2);
	private final int arrowRightHeight = (int)(height*0.5);
	private final int arrowLeftHeight = (int) (height*0.3);
	private final int arrowWidth =  width-2;
	
	private long lastTimerClick=0; 
	
	public PresenterScreen(){
		setMainStyle();
		loadArrows();
		
		next = new Button(arrowRight);
		next.setSelectedStyle(PresenterStyle.getArrowStyle());
		next.setPressedStyle(PresenterStyle.getArrowStyle());
		next.getPressedStyle().setBgTransparency(50);
		next.getStyle().setBgTransparency(0);
		next.getStyle().setAlignment(CENTER);
		next.getStyle().setMargin(0, 0, 0, 0);
		next.getStyle().setBackgroundType(Style.BACKGROUND_IMAGE_ALIGNED_CENTER);
		next.addActionListener(this);
		
		
		back = new Button(arrowLeft);
		back.setSelectedStyle(PresenterStyle.getArrowStyle());
		back.setPressedStyle(PresenterStyle.getArrowStyle());
		back.getPressedStyle().setBgTransparency(50);
		back.getStyle().setBgTransparency(0);
		back.getStyle().setAlignment(CENTER);
		back.getStyle().setMargin(0, 0, 0, 0);
		back.addActionListener(this);
		
		
		time = new Button("00:00");
		time.setHeight(timerHeight);
		time.setSelectedStyle(PresenterStyle.getTimerStyle(f));
		
		time.setPressedStyle(PresenterStyle.getTimerStyle(f));
		time.getPressedStyle().setBgTransparency(120);
		
		time.getStyle().setBgTransparency(0);
		time.getStyle().setFont(f);
		time.getStyle().setFgColor(0xFFFFFF);
		time.getStyle().setAlignment(CENTER);
		time.getStyle().setMargin(0, 0, 0, 0);
		time.addActionListener(this);
		
		timer = new Timer(); 
		timer.addTimeUpdateListener(this);
		
		
		TableLayout table = new TableLayout(3, 1);
		setLayout(table);
		
		addComponent(next);
		addComponent(back);
		addComponent(time);
		RemoteDevice.getInstance().addMessageListener(this);
	}
	
	
	private void loadArrows(){
		try {
			arrowRight = Image.createImage("/images/arrow_right.png").scaled(arrowWidth, arrowRightHeight);
			arrowLeft = Image.createImage("/images/arrow_left.png").scaled(arrowWidth, arrowLeftHeight);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}


	public void actionPerformed(ActionEvent evt) {
		if (evt.getSource() == back){
			RemoteDevice.getInstance().prevSlide();
		}else if (evt.getSource() == next){
			RemoteDevice.getInstance().nextSlide();	
		}else if (evt.getSource() == time){
			if (timeDblClick()){
				timer.stop();
				time.setText("00:00");
				//timer.playPause();
			}else{
				timer.playPause();
			}
		}
	}

	public synchronized void timeUpdated(TimeEvent t) {
		long eta = t.getElapsedTimestamp();
		String etaFormattedString = TimeEvent.getFormattedTime(eta);
		if (eta<60*60*1000){
			etaFormattedString = etaFormattedString.substring(3);
		}
		
		time.setText(etaFormattedString);
		repaint();
	}
	
	
	private boolean timeDblClick(){
		long currentTimerClick = System.currentTimeMillis();
		long offset = currentTimerClick -lastTimerClick;
		lastTimerClick = currentTimerClick;
		return offset < 500;
	}
	
	
	public void aMessage(String s) {
	}

	
	public void errorOccured() {
		RemoteDevice.getInstance().removeMessageListener(this);
		ErrorScreen.getInstance().showError("Connection lost!");
	}
}
