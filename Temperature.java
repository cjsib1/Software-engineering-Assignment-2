package melbourneweather2;

import java.rmi.RemoteException;

import melbourneweather2.MelbourneWeather2Stub.GetTemperature ;
import melbourneweather2.MelbourneWeather2Stub.GetTemperatureResponse;

public class Temperature {

	public static String location;
	public static String[] Temperature;
	public static String timeStamp = "",time;
	public static String temp,temperature,temperatureLoc,temperatureTime;
	
	
	
	public Temperature(String Location){
		this.location = Location;
		try {
			getTemperatureFromServer();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExceptionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	public Temperature(String Location, String Temperature,String time){
		this.location = Location;
		this.temp = Temperature;
		this.timeStamp = time;
		
	}
	
	public static String getTemperature(){
		temperature = Temperature[1].toString(); 
		return temperature;
	}
	
	public static String getTemperatureTime(){
		temperatureTime = Temperature[0].toString();
		return temperatureTime;
	}
	
	public static String getTemperatureLoc(){
		temperatureLoc = location;
		return temperatureLoc;
	}
	
	public static String[] getTemperatureFromServer() throws RemoteException, ExceptionException{
		
		final MelbourneWeather2Stub MelbourneWeatherSation = new MelbourneWeather2Stub();
		GetTemperature  TemperatureRequest = new GetTemperature ();
		TemperatureRequest.setLocation(location);
		GetTemperatureResponse TemperatureResponse = MelbourneWeatherSation.getTemperature (TemperatureRequest);
		Temperature = TemperatureResponse.get_return();
		
		System.out.println("THis is the size" + Temperature.length);
		for (int i = 0;i < Temperature.length;i++){
			System.out.print("Temperature output " + Temperature[i]);
		}
		return Temperature;
	}
	
	public static String [] getTemperatureArray(){
		String [] locationTempArray = null;
		try {
			locationTempArray = getTemperatureFromServer();
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
		return "The temperature is " + getTemperature() + "The time is:\t " + getTemperatureTime();
	}
}


