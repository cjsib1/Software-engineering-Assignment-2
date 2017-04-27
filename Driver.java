package melbourneweather2;
import java.lang.Exception;
import java.rmi.RemoteException;

import melbourneweather2.MelbourneWeather2Stub.*;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Driver implements Monitor {
	
	public static String[] locations;
	public static int userSelection;
	public static String selectForLocation;
	public static List<String> monitors = new ArrayList<String>();
	public static boolean rainfall,temperature,both = false;
	public static String [] rainOutPut = null;
	public static String [] tempOutPut = null;
	
	/************Main driver********************/
	public static void main(String[] args) throws Exception{
	
	//final MelbourneWeather2Stub MelbourneWeatherSation = new MelbourneWeather2Stub();
	//locations = MelbourneWeatherSation.getLocations().get_return();
	locations = Monitor.getlocations();
	System.out.println(locations.length);
	GUI.CreateGUI(locations);
	
	/*******************/
	/*Runnable updateMonitor = new Runnable() {
	    public void run() {
	        System.out.println("Hello world");
	    }
	};*/

	ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
	executor.scheduleAtFixedRate(GUI.Update(), 0, 300, TimeUnit.SECONDS);
	}
	//getUserInput();
	
	//GUI.createTable(tempOutPut,rainOutPut);
	//getUserInput();
	/*for(int i = 0;i < tempOutPut.length;i++){
		System.out.println(tempOutPut[i].toString());
	}
	for(int i = 0;i < rainOutPut.length;i++){
		System.out.println(rainOutPut[i].toString());
	}
	for(int i = 0;i < monitors.size();i++){
		System.out.println(monitors.get(i).toString());
	}
	//System.out.println(Rainfall.rainfallObject.get(0).getRainfall());
	}

	/****************Displays output to user for temperature*************************/
	public static void displayTemperature(){
		//String [] tempOutPut = null;
		tempOutPut = Temperature.getTemperatureArray();
		System.out.println("The monitor for the location is: " + "\t" + tempOutPut[0] +
				"\n------------------------------------------");
		System.out.println("Time:" +"\t\t" +tempOutPut[1]);
		System.out.println("Temperature:" +"\t" +tempOutPut[2] + " degrees celsuis");
		System.out.println("\n--------------------------------------");
	}
	
	/****************Displays output to user for Rainfall*************************/
	public static void displayRainfall(){
		//String [] rainOutPut = null;
		rainOutPut = Rainfall.getRainfallArray();
		System.out.println("The monitor for the location is: " + "\t" + rainOutPut[0] +
				"\n------------------------------------------");
		System.out.println("Time:" +"\t\t" +rainOutPut[1]);
		System.out.println("Rainfall:" +"\t" +rainOutPut[2] + " mm");
		System.out.println("\n--------------------------------------");
	}
	
	/****************Displays output to user for both*************************/
	public static void displayBoth(){
		//String [] tempOutPut = null;
		tempOutPut = Temperature.getTemperatureArray();
		rainOutPut = Rainfall.getRainfallArray();
		System.out.println("The monitor for the location is: " + "\t" + rainOutPut[0].toString() +
				"\n------------------------------------------");
		System.out.println("Time:" +"\t\t" +rainOutPut[1]);
		System.out.println("Rainfall:" +"\t" +rainOutPut[2] + " mm");
		System.out.println("Temperature:" +"\t" +tempOutPut[2] + " degrees celsuis");
	}
	
	public static void removeMonitor(){
		int userselectionToRemove = 0;
		Scanner scan = new Scanner(System.in);
		
		System.out.println("please select a monitor to remove");
		userselectionToRemove = scan.nextInt();
		
		monitors.remove(locations[userselectionToRemove]);
	}
	
	/**********Obtains the users selection for the monitors***********/
	public static void getUserInput(){ 
		 Scanner Scan = new Scanner(System.in);
		 int index = 1, userSelection = 1,option = 0;
		 int Sentinel = 1;
		 
		 //print out locations to screen
		 for(int i=0;i < locations.length;i++){				
				System.out.println(index + ") " + locations[i]);
				index++;
			}
		 while(userSelection <= 22 && userSelection >= 0 && Sentinel != -1){
				System.out.println("Please select a location to monitor enter 0 to exist");
				userSelection = Scan.nextInt();
				
				if (userSelection == 0){
					Sentinel = -1;
				}else{
					userSelection--;
					//add all the user input into array list 
					monitors.add(locations[userSelection]);
					}
		 		}
					for(int i = 0;i < monitors.size();i++){
						System.out.println("Active monitor: " + monitors.get(i).toString());
						System.out.println("-----------------------------------------" +
								"\nSelect either rainfall, temperature or both for current monitor" +
								"\n1) Rainfall" + "\n2) Temperature" + "\n3) Both");
						option = Scan.nextInt();
						if (option == 1){
							Rainfall rainMonitor1 = new Rainfall(monitors.get(i).toString());
							rainfall = true;
							displayRainfall();
						}else if (option == 2){
							Temperature temperaturemonitor = new Temperature(monitors.get(i).toString());
							temperature = true;
							displayTemperature();
						}
						else if (option == 3){
							Rainfall rainMonitor = new Rainfall(monitors.get(i).toString());
							Temperature temperaturemonitor = new Temperature(monitors.get(i).toString());
							both = true;
							displayBoth();
						} 
						
					}
				}
		
	public static void getvalues() throws RemoteException, ExceptionException{
		System.out.print(Rainfall.getRainfallFromServer().toString());
	}
		}



