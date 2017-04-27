package melbourneweather2;
import java.util.List;
import java.awt.SystemColor;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.*;

import melbourneweather2.MelbourneWeather2Stub.*;

public class Rainfall {
	public static String locationToFind;
	public static String[] Rainfall;
	public static String timeStamp = "";
	public static List<Rainfall> rainfallObject = new ArrayList<Rainfall>();
	public static String rainfallTime = "";
	public static String time = "";
	public static String rainfall = "";
	public static String rainfallLoc;
	public static String [] RainfallThis;
	
	public Rainfall(String Location){
		locationToFind = Location;
		try {
			getRainfallFromServer();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExceptionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	public Rainfall(String Location, String rainfall,String time){
		this.locationToFind = Location;
		rainfallTime = rainfall;
		this.time = time;
		
	}
	
	public static String getRainfall(){
		rainfall = Rainfall[1].toString(); 
		return rainfall;
	}
	
	public static String getRainfallTime(){
		rainfallTime = Rainfall[0].toString();
		return rainfallTime;
	}
	
	public static String getRainfallLoc(){
		rainfallLoc = locationToFind;
		return rainfallLoc;
	}
	
	public static String[] getRainfallFromServer() throws RemoteException, ExceptionException{
		
		final MelbourneWeather2Stub MelbourneWeatherSation = new MelbourneWeather2Stub();
		GetRainfall RainfallRequest = new GetRainfall();
		RainfallRequest.setLocation(locationToFind);
		GetRainfallResponse RainfallResponse = MelbourneWeatherSation.getRainfall(RainfallRequest);
		Rainfall = RainfallResponse.get_return();
		System.out.println("THis is the size" + Rainfall.length);
		for (int i = 0;i < Rainfall.length;i++){
			System.out.print("LEts " + Rainfall[i]);
		}
		
		return Rainfall;
	}
	
	public static String [] getRainfallArray(){
		String [] locationTempArray = null;
		try {
			locationTempArray = getRainfallFromServer();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExceptionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return locationTempArray;
	}
	
	public String toString(){
		return "The rainfall is " + getRainfall() + "The time is:\t " + getRainfallTime();
	}
}

