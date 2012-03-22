package de.uulm.presenter.util;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;


public class Log {
	/**
	 * 
	 * @param log Log String
	 * @param class_ Use TheClass.class 
	 * @param method The method that calls log 
	 */
	public static void log(String log,Class class_,String method){
		print(log, class_, method);		
		//System.out.println("log: "+log+" \n Class: "+class_.toString()+"\n Method: "+method);
	}
	
	
	
	private static void print(String log, Class class_, String me){
		//String path="file://log.txt";
	//	String dir = System.getProperty("user.dir");
		String dir = System.getProperty("fileconn.dir.memorycard");
		try {
			FileConnection fc = (FileConnection)Connector.open(dir+"/log.txt",Connector.READ_WRITE);
			if(!fc.exists()) fc.create();
			
			DataOutputStream dos = fc.openDataOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(dos);
			fc.setWritable(true);
			dos.write(log.getBytes());
			//osw.write(log+"\n", (int) fc.fileSize(), log.length());
			osw.flush();
			dos.close();
			osw.close();
			fc.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}



