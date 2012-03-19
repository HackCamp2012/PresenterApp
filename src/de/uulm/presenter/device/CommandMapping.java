package de.uulm.presenter.device;

public class CommandMapping {
	private KeyStroke nextSlide;
	private KeyStroke prevSlide;
	
	public KeyStroke getNextSlide() {
		return nextSlide;
	}
	public void setNextSlide(KeyStroke nextSlide) {
		this.nextSlide = nextSlide;
	}
	public KeyStroke getPrevSlide() {
		return prevSlide;
	}
	public void setPrevSlide(KeyStroke prevSlide) {
		this.prevSlide = prevSlide;
	}
	
	
	public static CommandMapping getDefaultMapping(){
		CommandMapping ret = new CommandMapping();
		ret.setNextSlide((new KeyStroke()).press(39));
		ret.setPrevSlide((new KeyStroke()).press(37));
		return ret;
	}
}
