package de.uulm.presenter.view;

import java.io.IOException;

import com.sun.lwuit.*;
import com.sun.lwuit.plaf.Style;
import com.sun.lwuit.table.TableLayout;

import de.uulm.presenter.util.Log;


public class PresenterScreen extends Form{

	Image bgImage = null;
	Image arrowRight = null;
	Image arrowLeft = null;
	int width;
	int height;
	
	public PresenterScreen(){
		this.width = Display.getInstance().getDisplayWidth();
		this.height = Display.getInstance().getDisplayHeight();
	}
	
	public void init(){
		int arrowHeight = (int)(height*0.4);
		
		try {
			bgImage = Image.createImage("/images/background_red.png");
			arrowRight = Image.createImage("/images/arrow_right.png").scaled(width-4, arrowHeight);
			arrowLeft = Image.createImage("/images/arrow_left.png").scaled(width-4, arrowHeight);
		} catch (IOException e) {
			Log.log("Image not found!", this.getClass(), "init");
			e.printStackTrace();
		}
		
		Style style = this.getStyle();
		style.setBgImage(bgImage);
		
		Button next = new Button(arrowRight);
		next.getSelectedStyle().setBgTransparency(0);
		next.getSelectedStyle().setAlignment(CENTER);
		next.getSelectedStyle().setMargin(0, 0, 0, 0);
		
		next.getPressedStyle().setBgTransparency(0);
		next.getPressedStyle().setAlignment(CENTER);
		next.getPressedStyle().setMargin(0, 0, 0, 0);
		next.getPressedStyle().setBackgroundType(Style.BACKGROUND_IMAGE_ALIGNED_CENTER);
		next.getPressedStyle().setBgTransparency(50);
		
		next.getStyle().setBgTransparency(0);
		next.getStyle().setAlignment(CENTER);
		next.getStyle().setMargin(0, 0, 0, 0);
		next.getStyle().setBackgroundType(Style.BACKGROUND_IMAGE_ALIGNED_CENTER);
		
		
		Button back = new Button(arrowLeft);
		back.getSelectedStyle().setBgTransparency(0);
		back.getSelectedStyle().setAlignment(CENTER);
		back.getSelectedStyle().setMargin(0, 0, 0, 0);
		
		back.getPressedStyle().setBgTransparency(0);
		back.getPressedStyle().setAlignment(CENTER);
		back.getPressedStyle().setMargin(0, 0, 0, 0);
		back.getPressedStyle().setBgTransparency(50);
		
		back.getStyle().setBgTransparency(0);
		back.getStyle().setAlignment(CENTER);
		back.getStyle().setMargin(0, 0, 0, 0);
		
		
		Button time = new Button("10 : 53");
		Font timeFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_LARGE);
		
		time.getSelectedStyle().setBgTransparency(0);
		time.getSelectedStyle().setFont(timeFont);
		time.getSelectedStyle().setFgColor(0xffffff);
		time.getSelectedStyle().setAlignment(CENTER);
		time.getSelectedStyle().setMargin(0, 0, 0, 0);
		
		time.getPressedStyle().setBgTransparency(120);
		time.getPressedStyle().setFont(timeFont);
		time.getPressedStyle().setFgColor(0xffffff);
		time.getPressedStyle().setAlignment(CENTER);
		time.getPressedStyle().setMargin(0, 0, 0, 0);
		
		time.getStyle().setBgTransparency(0);
		time.getStyle().setFont(timeFont);
		time.getStyle().setFgColor(0xFFFFFF);
		time.getStyle().setAlignment(CENTER);
		time.getStyle().setMargin(0, 0, 0, 0);
		
		
		TableLayout table = new TableLayout(3, 1);
		setLayout(table);
		
		addComponent(next);
		addComponent(time);
		addComponent(back);
		
		show();
	}
}
