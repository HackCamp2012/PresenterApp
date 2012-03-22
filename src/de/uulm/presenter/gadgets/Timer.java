package de.uulm.presenter.gadgets;

import java.util.Vector;



public class Timer implements Runnable{
	private long startTime;
	private long stopTime;
	private boolean isStopped=false;
	private long elapsedTime;
	private long endTime;
	private Vector timeUpdateListeners;
	private boolean isRunning=false;
	private boolean kill=false;
	public Timer(long endTime) {
		this();
		setEndTime(endTime);
		
	}
	public Timer() {
		elapsedTime=0;
		timeUpdateListeners = new Vector();
		
		Thread t = new Thread(this);
		t.start();
	}
	public void setEndTime(long endTime){
		this.endTime=endTime;
	}
	public boolean playPause(){
		if (isStopped){
			startTime=0;
			elapsedTime=0;
			isStopped=false;
		}
		
		if (isRunning){
			//pause
			stopTime=System.currentTimeMillis();
			elapsedTime += stopTime-startTime;
		}else{
			//play
			startTime = System.currentTimeMillis();
		}
		isRunning=!isRunning;
		return isRunning;
		
	}
	public void stop(){
		if (isStopped) return;
		if (isRunning){
			stopTime=System.currentTimeMillis();
			elapsedTime += stopTime-startTime;
		}
		
		isRunning=false;
		isStopped=true;
		
		
	}
	
	public void reset(){
		startTime=0;
		elapsedTime=0;
	}

	public void run() {
		while (!kill){
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized(this){
				for (int i = 0;i<timeUpdateListeners.size();i++){
					
					TimeUpdateListener l = (TimeUpdateListener) timeUpdateListeners.elementAt(i);
					TimeEvent evt = new TimeEvent(getElapsedTime(), endTime);
					l.timeUpdated(evt);
				}	
			}
				
		}
		
		
	}
	private long getElapsedTime(){
		
		if (isRunning){
			return (System.currentTimeMillis()-startTime)+elapsedTime;	
		}else{
			return elapsedTime;
		}
		
	}
	
	public synchronized void addTimeUpdateListener(TimeUpdateListener l){
		
		timeUpdateListeners.addElement(l);
	}
	public synchronized  void removeTimeUpdateListener(TimeUpdateListener l){
		timeUpdateListeners.removeElement(l);
	}
	
	
	
}


