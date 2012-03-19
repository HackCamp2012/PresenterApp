package de.uulm.presenter.view.style;

import com.sun.lwuit.Component;
import com.sun.lwuit.Font;
import com.sun.lwuit.plaf.Style;

public class PresenterStyle{

	public PresenterStyle(){
		
	}
	
	public static Style getArrowStyle(){
		Style s = new Style();
		
		s.setBgTransparency(0);
		s.setAlignment(Component.CENTER);
		s.setMargin(0, 0, 0, 0);
		//s.setBackgroundType(Style.BACKGROUND_IMAGE_ALIGNED_CENTER);
		
		return s;
	}
	
	public static Style getTimerStyle(Font f){
		Style s = new Style();
		
		s.setBgTransparency(0);
		s.setFont(f);
		s.setFgColor(0xffffff);
		s.setAlignment(Component.CENTER);
		s.setMargin(0, 0, 0, 0);
		
		return s;
	}
	
	public static Style getRadioStyle(Font f){
		Style s = new Style();
		
		s.setFont(f);
		s.setFgColor(0xffffff);
		s.setPadding(10, 10, 5, 0);
		
		return s;
	}
}
