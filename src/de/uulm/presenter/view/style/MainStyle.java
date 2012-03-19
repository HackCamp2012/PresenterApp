package de.uulm.presenter.view.style;

import java.io.IOException;

import com.sun.lwuit.*;
import com.sun.lwuit.plaf.Style;

import de.uulm.presenter.util.Log;

public class MainStyle extends Form{

	protected Image bgImage;
	protected Font f;
	protected Style style;
	protected int width;
	protected int height;
	
	public void setMainStyle(){
		style = this.getStyle();
		f = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_LARGE);
		width = Display.getInstance().getDisplayWidth();
		height = Display.getInstance().getDisplayHeight();
		
		try {
			//bgImage = Image.createImage("/images/background_red.png");
			//bgImage = Image.createImage("/images/background_blue.jpg");
			bgImage = Image.createImage("/images/background_greenblue.jpg");
		} catch (IOException e) {
			Log.log("Image not found!", this.getClass(), "setMainStyle");
			e.printStackTrace();
		}
		style.setBgImage(bgImage);
	}
}
