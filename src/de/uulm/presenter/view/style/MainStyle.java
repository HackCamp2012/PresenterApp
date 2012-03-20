package de.uulm.presenter.view.style;

import java.io.IOException;

import com.sun.lwuit.*;
import com.sun.lwuit.plaf.Style;

import de.uulm.presenter.util.Log;

public class MainStyle extends Form{

	protected Image bgImage;
	protected Font f;
	protected Style style;
	protected final int width = Display.getInstance().getDisplayWidth();
	protected final int height = Display.getInstance().getDisplayHeight();
	public int dialogTop;
	public int dialogBottom;
	
	public void setMainStyle(){
		style = this.getStyle();
		f = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_LARGE);
		
		dialogTop = (int) (height*0.66);
		dialogBottom = (int) (height*0.1); 
		
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
