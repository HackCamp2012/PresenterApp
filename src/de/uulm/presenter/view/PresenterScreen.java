package de.uulm.presenter.view;

import java.io.IOException;

import com.sun.lwuit.*;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.plaf.Style;
import com.sun.lwuit.table.TableLayout;

import de.uulm.presenter.device.RemoteDevice;
import de.uulm.presenter.util.Log;
import de.uulm.presenter.view.style.MainStyle;
import de.uulm.presenter.view.style.PresenterStyle;


public class PresenterScreen extends MainStyle{

	private Image arrowRight = null;
	private Image arrowLeft = null;
	
	public PresenterScreen(){
		setMainStyle();
		loadArrows();
		
		Button next = new Button(arrowRight);
		next.setSelectedStyle(PresenterStyle.getArrowStyle());
		next.setPressedStyle(PresenterStyle.getArrowStyle());
		next.getPressedStyle().setBgTransparency(50);
		next.getStyle().setBgTransparency(0);
		next.getStyle().setAlignment(CENTER);
		next.getStyle().setMargin(0, 0, 0, 0);
		next.getStyle().setBackgroundType(Style.BACKGROUND_IMAGE_ALIGNED_CENTER);
		next.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt) {
				RemoteDevice.getInstance().nextSlide();
			}
		});
		
		
		Button back = new Button(arrowLeft);
		back.setSelectedStyle(PresenterStyle.getArrowStyle());
		back.setPressedStyle(PresenterStyle.getArrowStyle());
		back.getPressedStyle().setBgTransparency(50);
		back.getStyle().setBgTransparency(0);
		back.getStyle().setAlignment(CENTER);
		back.getStyle().setMargin(0, 0, 0, 0);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				RemoteDevice.getInstance().prevSlide();
			}
		});
		
		//TODO implement Timer
		Button time = new Button("10 : 53");
		time.setSelectedStyle(PresenterStyle.getTimerStyle(f));
		
		time.setPressedStyle(PresenterStyle.getTimerStyle(f));
		time.getPressedStyle().setBgTransparency(120);
		
		time.getStyle().setBgTransparency(0);
		time.getStyle().setFont(f);
		time.getStyle().setFgColor(0xFFFFFF);
		time.getStyle().setAlignment(CENTER);
		time.getStyle().setMargin(0, 0, 0, 0);
		
		
		TableLayout table = new TableLayout(3, 1);
		setLayout(table);
		
		addComponent(next);
		addComponent(time);
		addComponent(back);
	}
	
	private void loadArrows(){
		int arrowHeight = (int)(height*0.4);
		
		try {
			arrowRight = Image.createImage("/images/arrow_right.png").scaled(width-4, arrowHeight);
			arrowLeft = Image.createImage("/images/arrow_left.png").scaled(width-4, arrowHeight);
		} catch (IOException e) {
			Log.log("Image not found!", this.getClass(), "init");
			e.printStackTrace();
		}
	}
}
