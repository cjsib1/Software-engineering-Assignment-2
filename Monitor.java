package melbourneweather2;

import java.rmi.RemoteException;
/************Interface***************/
public interface Monitor {
	
	public static String [] getlocations() throws RemoteException, ExceptionException{
		String [] locations;
		final MelbourneWeather2Stub MelbourneWeatherSation = new MelbourneWeather2Stub();
		locations = MelbourneWeatherSation.getLocations().get_return();
		return locations;//Returns the locations available
	};
	
}
