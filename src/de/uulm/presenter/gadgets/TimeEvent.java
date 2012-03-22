package de.uulm.presenter.gadgets;




public class TimeEvent {
	private final long elapsed;
	private final long endTime;
	public TimeEvent(long timestamp) {
		this(timestamp,0);
	}
	
	public TimeEvent(long elapsed,long endTime) {
		this.elapsed=elapsed;
		this.endTime=endTime;
	}
	
	public long getElapsedTimestamp(){
		return elapsed;
	}
	
	public long getEndTimestamp(){
		return endTime;
	}
	
	public long getLeftTimestamp(){
		return endTime-elapsed;
	}
	
	public static String getFormattedTime(long timestamp){
		timestamp=timestamp/1000;
		
		
		long hours = timestamp / (60*60);
		timestamp %= 60*60;
		
		long mins = timestamp / 60;
		timestamp %= 60;
		
		long secs = timestamp;
		
		
		return TimeEvent.getZeroedString(hours)+":"+TimeEvent.getZeroedString(mins)+":"+TimeEvent.getZeroedString(secs);
	}
	
	private static String getZeroedString(long i){
		return i>9?i+"":"0"+i;
	}
}
