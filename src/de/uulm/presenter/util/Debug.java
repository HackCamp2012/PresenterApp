package de.uulm.presenter.util;

public class Debug {
	public void test(){
		Log.log("test", this.getClass(), "test");
	}
	
	public static void main(String[] args) {
		Debug d = new Debug();
		d.test();
	}

}
