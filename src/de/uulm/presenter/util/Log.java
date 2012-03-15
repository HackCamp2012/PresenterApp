package de.uulm.presenter.util;

public class Log {
	/**
	 * 
	 * @param log Log String
	 * @param class_ Use TheClass.class 
	 * @param method The method that calls log 
	 */
	public static void log(String log,Class class_,String method){
				System.out.println("log: "+log+" \n Class: "+class_.toString()+"\n Method: "+method);
	}
	
	
	
}



