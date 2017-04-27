package melbourneweather2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;


public class GUI  {
	
		static String [] locations; //sent from driver from monitor
		static String [] results;
		static JFrame mainFrame;
		static Container pane;
		static JButton viewB, deleteB, addB;
		static JComboBox<String> locationCombo; 
		static Insets insets;
		static JLabel allLocationsLabel, addLabel, rainfallL, tempL,output1,output2,output3,output4,output;
		static JTable allLoctaions;
		static JPanel fstPan, secPan, thirPan, fourPan; 
		static JRadioButton rainfall, temp;
		static boolean Temp, rain = false; //return for buttons
		static String locationSelected = "0",rainSend ="",TempSend = ""; //locations to monitor
		ItemSelectable item;
		static boolean Idle = false;
		static int clickCount = 0;
		static int selectedLocation;
		static List<Object> Both= new ArrayList <Object>(); 
		static List<Object> Temperature= new ArrayList <Object>(); 
		static List<Object> Rainfall= new ArrayList <Object>(); 
		static Object objectTemp;
		static boolean currentRain = false,currentTemp = false,currentBoth = false;
		static String rainCheck;
		static String tempCheck;
		static String bothCheck;

		public static void  CreateGUI  (String [] locations){
			
			locationSelected = locations[0];
			//Main frame of GUI
			mainFrame = new JFrame ("The Weather Monitor");
			mainFrame.setSize(500,250);

		
			//First Panel 
			fstPan = new JPanel();
			fstPan.setLayout(new BorderLayout());
			addLabel = new JLabel("Add a Location");
			fstPan.add(addLabel, BorderLayout.SOUTH);
			
			//RadioButton rainfall
			rainfall = new JRadioButton("Rainfall");
			fstPan.add(rainfall, BorderLayout.CENTER);
			ButtonGroup rain_temp = new ButtonGroup();
			rain_temp.add(rainfall);
			
			//RadioButton temperature
			temp = new JRadioButton("Temperature");
			fstPan.add(temp, BorderLayout.SOUTH);
			ButtonGroup Temp_temp = new ButtonGroup();
			Temp_temp.add(temp);
			

			
			
			//locationCombo = new JComboBox<String>(LocationsArray);
			locationCombo = new JComboBox<String>(locations);
			fstPan.add(locationCombo, BorderLayout.NORTH);
			System.out.println("length " + locations.length);
			locationCombo.addActionListener(new ActionListener(){
				
				@Override
				public void actionPerformed(ActionEvent e) {
					locationCombo = (JComboBox)e.getSource();
					locationSelected = (String) locationCombo.getSelectedItem();
					System.out.println(locationSelected);
					for(int i = 0;i <= locations.length;i++){
						if(locations[i].equals(locationSelected)){
							selectedLocation = i--;
							i = locations.length;
						}
					}
					//testing	
					//selectedLocation =   CheckLocation(locationSelected);
					System.out.println("" + selectedLocation);

				}
				
			});
			
			
			/*boolean objectFound = false;
			for(int i = 0;i <= locations.length;i++){
				if(locations[i].contains(locationSelected)){
					selectedLocation = i--;
					objectFound = true;
				}
			}*/
			
			
			//rainfall action 
			
			rainfall.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(rainfall.isSelected()){
						rain = true;
					/*}else{
						rain = false;*/
					}
					if (++clickCount % 2 == 0) {

						rain_temp.clearSelection();
						rain = false;
					}
				}
			});
			
			//temperature action
			temp.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(temp.isSelected() == true){
						Temp = true;
					/*}else{
						Temp = false;*/
					}
					if (++clickCount % 2 == 0) {

						Temp_temp.clearSelection();
						Temp = false;
					}
				}
			});
			
			//SecondPanel 
			secPan = new JPanel();
			addB = new JButton("add");{
			addB.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							{
								System.out.println(" RainCheck " + rain + locationSelected + "Temperature " + Temp);
								//rain_temp.clearSelection();
								//Temp_temp.clearSelection();
								if(rain == true & Temp == false){
									Rainfall rainObject = new Rainfall(locationSelected);
									Rainfall.add(rainObject);
									currentRain = true;
									System.out.print("This is rainfall object 1 " + Rainfall.get(0).toString());
									}
								
									if(Temp == true & rain == false){
									Temperature tempObject = new Temperature(locationSelected);
									Temperature.add(tempObject);
									System.out.print("This is temperature object 1 " + Temperature.get(0).toString());
									currentTemp = true;
									}
									if (Temp == true & rain == true){
										Rainfall rainObject1 = new Rainfall(locationSelected);
										Temperature tempObject1 = new Temperature(locationSelected);
										Both.add(rainObject1);
										Both.add(tempObject1);
										System.out.print("This is both object 1 " + Both.get(0).toString());
										currentBoth = true;
									}
									}
										rain_temp.clearSelection();
										Temp_temp.clearSelection();
										resetButtons();
									}
								
								/*System.out.print(Rainfall.get(0).toString());
								System.out.print(Temperature.get(0).toString());
								System.out.print(Both.get(0).toString());*/
							}
						);
					}
			
			
			output1 = new JLabel();
			output2 = new JLabel();
			output3 = new JLabel();
			output4 = new JLabel();
			output =  new JLabel();
			
			secPan.add(addB);
			//JPanel panelDisplay = new JPanel();
			//panelDisplay.setLayout(new BorderLayout());
			
			//Third Panel
			thirPan = new JPanel();
			secPan.add(addB);
			//JPanel panelDisplay = new JPanel();
			//panelDisplay.setLayout(new BorderLayout());
		
			allLocationsLabel = new JLabel("Locations");
			thirPan.add(allLocationsLabel);
			viewB = new JButton("view");
			thirPan.add(viewB);
			viewB.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					createForms();
				}
					/*if(Both.size() >= 1 & currentBoth == true){
						for(int i = 0;i < 1;i++){
							JPanel panelDisplay = new JPanel();
							JFrame parent = new JFrame(locationSelected);
							panelDisplay.setLayout(new FlowLayout());
							objectTemp = (Both.get(Both.size()-1));
							System.out.println("I DONT KNOW " + objectTemp.toString());
							
							output = new JLabel ("The rainfall is: \t" + melbourneweather2.Rainfall.getRainfall() + "MM");
							output1 = new JLabel ("The temperature is: \t" + melbourneweather2.Temperature.getTemperature() + "degrees Celsuis");
							output2 = new JLabel ("The time is: \t" + melbourneweather2.Temperature.getTemperatureTime());
							output3 = new JLabel ("The location is: " + melbourneweather2.Temperature.getTemperatureLoc());
							panelDisplay.add(output3, BorderLayout.NORTH);
							panelDisplay.add(output2, BorderLayout.CENTER);
							panelDisplay.add(output1, BorderLayout.WEST);
							panelDisplay.add(output, BorderLayout.EAST);
							panelDisplay.setSize(500,500);
							parent.add(panelDisplay);
							parent.setSize(350, 150);
							parent.setVisible(true);
							parent.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
							currentBoth = false;
						}
					}
					if(Rainfall.size() >= 1 & currentRain == true){
						for(int i = 0;i < 1;i++){
							JPanel panelDisplay = new JPanel();
							JFrame parent = new JFrame(locationSelected);
							panelDisplay.setLayout(new FlowLayout());
							objectTemp = (Rainfall.get(Rainfall.size()-1));
							System.out.println("I DONT KNOW " + objectTemp.toString());
							output1 = new JLabel ("The rainfall is: \t" + melbourneweather2.Rainfall.getRainfall() + "MM");
							output2 = new JLabel ("The time is: \t" + melbourneweather2.Rainfall.getRainfallTime());
							output3 = new JLabel ("The location is: " + melbourneweather2.Rainfall.getRainfallLoc());
							panelDisplay.add(output3, BorderLayout.NORTH);
							panelDisplay.add(output2, BorderLayout.CENTER);
							panelDisplay.add(output1, BorderLayout.SOUTH);
							panelDisplay.setSize(500,500);
							parent.add(panelDisplay);
							parent.setSize(250, 100);
							parent.setVisible(true);
							parent.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
							currentRain = false;
						}
					}
					if(Temperature.size() >= 1 & currentTemp == true){
						for (int i = 0;i < 1;i++){
							JPanel panelDisplay = new JPanel();
							JFrame parent = new JFrame(locationSelected);
							panelDisplay.setLayout(new FlowLayout());
							objectTemp = (Temperature.get(Temperature.size()-1));
							System.out.println("I DONT KNOW " + objectTemp.toString());
							output1 = new JLabel ("The temperature is: \t" + melbourneweather2.Temperature.getTemperature() + "degrees Celsuis");
							output2 = new JLabel ("The time is: \t" + melbourneweather2.Temperature.getTemperatureTime());
							output3 = new JLabel ("The location is: " + melbourneweather2.Temperature.getTemperatureLoc());
							panelDisplay.add(output3, BorderLayout.NORTH);
							panelDisplay.add(output2, BorderLayout.CENTER);
							panelDisplay.add(output1, BorderLayout.SOUTH);
							panelDisplay.setSize(500,500);
							parent.add(panelDisplay);
							parent.setSize(250, 100);
							parent.setVisible(true);
							parent.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
							currentTemp = false;
						}
					}
					System.out.println("BLAH BLAH");
					System.out.println(Both.size() + " T " + Temperature.size() + " R " + Rainfall.size());
					}*/
					
				});
			
			/*parent.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent HIDE_ON_CLOSE){
					{
						
					}
				}
				});*/
			
			
			
			
			
			
			
			/*deleteB = new JButton("delete");
			deleteB.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				}
			});
			thirPan.add(deleteB);*/
			
			
			
			//table 
			//allLocations = new JTable();
			//adding to pane
			/*pane.add(allLocationsLabel);
			pane.add(locationCombo);
			pane.add(addB);
			pane.add(allLocationsLabel);*/
			
			
			
			//Fourth Panel 
			fourPan = new JPanel();
			fourPan.setLayout(new FlowLayout());
			fourPan.add(fstPan);
			fourPan.add(secPan);
			fourPan.add(thirPan);
			//mainFrame.add(fifthPan, BorderLayout.SOUTH);
			
			
			
			mainFrame.getContentPane().add(fourPan, BorderLayout.CENTER);
			
			mainFrame.setVisible(true);
			mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		//String [] results = {locationSelected, rainSend, TempSend}; 
			}
		
		
		public static boolean Idletest(){
			return Idle;
		}
		
		public static String sendDataLocation(){
			return locationSelected;
		}
		
		public static boolean rainSelected(){
			return rain;
		}
		
		public static boolean TempSelected(){
			return Temp;
		}
		
		public static void resetButtons(){
			
			rain = false;
			Temp = false;
		}
		
		public static int CheckLocation(String locationSelected){
			boolean objectFound = false;
			for(int i = 0;i <= locations.length;i++){
				if(locations[i].contains(locationSelected)){
					selectedLocation = i--;
					objectFound = true;
				}
			}
			return selectedLocation;
		}
		
		/**************Creates an update***************/
		public static Runnable Update(){
					
				Runnable updateMonitor = new Runnable() {
			    public void run() {
			    	if (Rainfall.size() > 0 ){
						rainCheck = Rainfall.get(0).toString();
						//parent.dispose();
						//for(int i = 0;i <= Rainfall.size(); i++){
							createForms();
							createFormsUpdate();
						//}
						
					}
					if (Temperature.size() > 0 ){
						tempCheck = Temperature.get(0).toString();
						//createForms();
						createForms();
					}
					if (Both.size() > 0 ){
						bothCheck = Both.get(0).toString();
						//createForms();
						createForms();
					}
			    	System.out.println("Hello world");
			    	System.out.println("\nThis is current objects in rain " + rainCheck);
			    	System.out.println("\nThis is current objects in Temperature " + tempCheck);
			    	System.out.println("\nThis is current objects in Both " + bothCheck);  	
			    }
			};
			return updateMonitor;
		}
		
		public static void createFormsUpdate(){
			
			if(Both.size() >= 1 & currentBoth == true){
				for(int i = 0; Both.size() <= i;i++){
					String temporay;
					JPanel panelDisplay = new JPanel();
					JFrame parent = new JFrame(locationSelected);
					panelDisplay.setLayout(new FlowLayout());
					objectTemp = (Both.get(i));
					temporay = ((melbourneweather2.Rainfall) objectTemp).getRainfall().toString();
					System.out.println("I DONT KNOW " + objectTemp.toString());
					
					output = new JLabel ("The rainfall is: \t" + melbourneweather2.Rainfall.getRainfall() + "MM");
					output1 = new JLabel ("The temperature is: \t" + melbourneweather2.Temperature.getTemperature() + "degrees Celsuis");
					output2 = new JLabel ("The time is: \t" + melbourneweather2.Temperature.getTemperatureTime());
					output3 = new JLabel ("The location is: " + melbourneweather2.Temperature.getTemperatureLoc());
					panelDisplay.add(output3, BorderLayout.NORTH);
					panelDisplay.add(output2, BorderLayout.CENTER);
					panelDisplay.add(output1, BorderLayout.WEST);
					panelDisplay.add(output, BorderLayout.EAST);
					panelDisplay.setSize(300,300);
					parent.add(panelDisplay);
					parent.setSize(300, 120);
					parent.setVisible(true);
					parent.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
					currentBoth = false;
				}
			}
			if(Rainfall.size() >= 1 & currentRain == true){
				for(int i = 0;i < 1;i++){
					JPanel panelDisplay = new JPanel();
					JFrame parent = new JFrame(locationSelected);
					panelDisplay.setLayout(new FlowLayout());
					objectTemp = (Rainfall.get(Rainfall.size()-1));
					System.out.println("I DONT KNOW " + objectTemp.toString());
					output1 = new JLabel ("The rainfall is: \t" + melbourneweather2.Rainfall.getRainfall() + "MM");
					output2 = new JLabel ("The time is: \t" + melbourneweather2.Rainfall.getRainfallTime());
					output3 = new JLabel ("The location is: " + melbourneweather2.Rainfall.getRainfallLoc());
					panelDisplay.add(output3, BorderLayout.NORTH);
					panelDisplay.add(output2, BorderLayout.CENTER);
					panelDisplay.add(output1, BorderLayout.SOUTH);
					panelDisplay.setSize(500,500);
					parent.add(panelDisplay);
					parent.setSize(250, 110);
					parent.setVisible(true);
					parent.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
					currentRain = false;
				}
			}
			if(Temperature.size() >= 1 & currentTemp == true){
				for (int i = 0;i < 1;i++){
					JPanel panelDisplay = new JPanel();
					JFrame parent = new JFrame(locationSelected);
					panelDisplay.setLayout(new FlowLayout());
					objectTemp = (Temperature.get(Temperature.size()-1));
					System.out.println("I DONT KNOW " + objectTemp.toString());
					output1 = new JLabel ("The temperature is: \t" + melbourneweather2.Temperature.getTemperature() + "degrees Celsuis");
					output2 = new JLabel ("The time is: \t" + melbourneweather2.Temperature.getTemperatureTime());
					output3 = new JLabel ("The location is: " + melbourneweather2.Temperature.getTemperatureLoc());
					panelDisplay.add(output3, BorderLayout.NORTH);
					panelDisplay.add(output2, BorderLayout.CENTER);
					panelDisplay.add(output1, BorderLayout.SOUTH);
					panelDisplay.setSize(500,500);
					parent.add(panelDisplay);
					parent.setSize(250, 110);
					parent.setVisible(true);
					parent.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
					currentTemp = false;
				}
			}
			System.out.println("BLAH BLAH");
			System.out.println(Both.size() + " T " + Temperature.size() + " R " + Rainfall.size());
			}
		
		public static void createForms(){
			if(Both.size() >= 1 & currentBoth == true){
				for(int i = 0;i < 1;i++){
					JPanel panelDisplay = new JPanel();
					JFrame parent = new JFrame(locationSelected);
					panelDisplay.setLayout(new FlowLayout());
					objectTemp = (Both.get(Both.size()-1));
					System.out.println("I DONT KNOW " + objectTemp.toString());
					
					output = new JLabel ("The rainfall is: \t" + melbourneweather2.Rainfall.getRainfall() + "MM");
					output1 = new JLabel ("The temperature is: \t" + melbourneweather2.Temperature.getTemperature() + "degrees Celsuis");
					output2 = new JLabel ("The time is: \t" + melbourneweather2.Temperature.getTemperatureTime());
					output3 = new JLabel ("The location is: " + melbourneweather2.Temperature.getTemperatureLoc());
					panelDisplay.add(output3, BorderLayout.NORTH);
					panelDisplay.add(output2, BorderLayout.CENTER);
					panelDisplay.add(output1, BorderLayout.WEST);
					panelDisplay.add(output, BorderLayout.EAST);
					panelDisplay.setSize(300,300);
					parent.add(panelDisplay);
					parent.setSize(300, 120);
					parent.setVisible(true);
					parent.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
					currentBoth = false;
				}
			}
			if(Rainfall.size() >= 1 & currentRain == true){
				for(int i = 0;i < 1;i++){
					JPanel panelDisplay = new JPanel();
					JFrame parent = new JFrame(locationSelected);
					panelDisplay.setLayout(new FlowLayout());
					objectTemp = (Rainfall.get(Rainfall.size()-1));
					System.out.println("I DONT KNOW " + objectTemp.toString());
					output1 = new JLabel ("The rainfall is: \t" + melbourneweather2.Rainfall.getRainfall() + "MM");
					output2 = new JLabel ("The time is: \t" + melbourneweather2.Rainfall.getRainfallTime());
					output3 = new JLabel ("The location is: " + melbourneweather2.Rainfall.getRainfallLoc());
					panelDisplay.add(output3, BorderLayout.NORTH);
					panelDisplay.add(output2, BorderLayout.CENTER);
					panelDisplay.add(output1, BorderLayout.SOUTH);
					panelDisplay.setSize(500,500);
					parent.add(panelDisplay);
					parent.setSize(250, 110);
					parent.setVisible(true);
					parent.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
					currentRain = false;
				}
			}
			if(Temperature.size() >= 1 & currentTemp == true){
				for (int i = 0;i < 1;i++){
					JPanel panelDisplay = new JPanel();
					JFrame parent = new JFrame(locationSelected);
					panelDisplay.setLayout(new FlowLayout());
					objectTemp = (Temperature.get(Temperature.size()-1));
					System.out.println("I DONT KNOW " + objectTemp.toString());
					output1 = new JLabel ("The temperature is: \t" + melbourneweather2.Temperature.getTemperature() + "degrees Celsuis");
					output2 = new JLabel ("The time is: \t" + melbourneweather2.Temperature.getTemperatureTime());
					output3 = new JLabel ("The location is: " + melbourneweather2.Temperature.getTemperatureLoc());
					panelDisplay.add(output3, BorderLayout.NORTH);
					panelDisplay.add(output2, BorderLayout.CENTER);
					panelDisplay.add(output1, BorderLayout.SOUTH);
					panelDisplay.setSize(500,500);
					parent.add(panelDisplay);
					parent.setSize(250, 110);
					parent.setVisible(true);
					parent.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
					currentTemp = false;
				}
			}
			System.out.println("BLAH BLAH");
			System.out.println(Both.size() + " T " + Temperature.size() + " R " + Rainfall.size());
			}
		
		
}
		/*public static void main (String args []){
			
			new GUI(); 
		
		}*/
			
			
		
