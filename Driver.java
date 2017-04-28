package melbourneweather2;
import java.lang.Exception;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Driver implements Monitor {
	
	public static String[] locations;
	
	/************Main driver********************/
	public static void main(String[] args) throws Exception{
	locations = Monitor.getlocations();
	
	/*******Testing**************/
	//System.out.println(locations.length);
	
	/***********Creates the GUI*****************/
	GUI.CreateGUI(locations);
	
	/**********Runs the update after 5 minutes***************/
	ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
	executor.scheduleAtFixedRate(GUI.Update(), 0, 300, TimeUnit.SECONDS);
	}
}




