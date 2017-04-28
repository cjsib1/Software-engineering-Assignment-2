package melbourneweather2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;


public class GUI  {
	
		static List<String> LocationUpdate= new ArrayList <String>(); 
		static List<Boolean> TemperatureBoo= new ArrayList <Boolean>(); 
		static List<Boolean> RainfallBoo= new ArrayList <Boolean>();
		static List<Object> Both= new ArrayList <Object>(); 
		static List<Object> Temperature= new ArrayList <Object>(); 
		static List<Object> Rainfall= new ArrayList <Object>(); 
		static Object objectTemp;	
		static JComboBox<String> locationCombo;
		static JFrame mainFrame;
		static Container pane;
		static JButton viewB, deleteB, addB;
		static JRadioButton rainfall, temp; 
		static JLabel allLocationsLabel, addLabel, rainfallL, tempL,output1,output2,output3,output4,output;
		static JTable allLoctaions;
		static JPanel fstPan, secPan, thirPan, fourPan, fifthPan; 
		static boolean Temp, rain = false; //return for buttons
		static String locationSelected = "0",rainSend ="",TempSend = ""; //locations to monitor
		static boolean Idle = false;
		static int clickCount = 0;
		static int selectedLocation;
		static boolean currentRain = false,currentTemp = false,currentBoth = false;

		public static void  CreateGUI  (String [] locations){
			
			/*********Locations from monitor**************/
			locationSelected = locations[0];
			
			/*************Main frame of GUI***************/
			mainFrame = new JFrame ("The Weather Monitor");
			mainFrame.setSize(750,550);
			mainFrame.setLayout(new BorderLayout());
		
			/**************First Panel*********************/ 
			fstPan = new JPanel();
			fstPan.setLayout(new BorderLayout());
			addLabel = new JLabel("Add a Location");
			fstPan.add(addLabel, BorderLayout.SOUTH);
			
			/****************RadioButton rainfall***********/
			rainfall = new JRadioButton("Rainfall");
			fstPan.add(rainfall, BorderLayout.CENTER);
			ButtonGroup rain_temp = new ButtonGroup();
			rain_temp.add(rainfall);
			
			/*************RadioButton temperature***********/
			temp = new JRadioButton("Temperature");
			fstPan.add(temp, BorderLayout.SOUTH);
			ButtonGroup Temp_temp = new ButtonGroup();
			Temp_temp.add(temp);
			
			/***********Panel five**************************/
			fifthPan = new JPanel();
			fifthPan.setLayout(new FlowLayout());
			fifthPan.setSize(300,150);
			fifthPan.setVisible(true);
			
			/****Drop down list for locations****/
			locationCombo = new JComboBox<String>(locations);
			fstPan.add(locationCombo, BorderLayout.NORTH);
			System.out.println("length " + locations.length);
			locationCombo.addActionListener(new ActionListener(){
				
				/*********Action listener for the drop down box*****************/
				@Override
				public void actionPerformed(ActionEvent e) {
					locationCombo = (JComboBox)e.getSource();
					locationSelected = (String) locationCombo.getSelectedItem();
					
					/***********Testing************/
					//System.out.println(locationSelected);
					for(int i = 0;i <= locations.length;i++){
						if(locations[i].equals(locationSelected)){
							selectedLocation = i--;
							i = locations.length;
						}
					}
					/*************testing********************/	
					//selectedLocation =   CheckLocation(locationSelected);
					//System.out.println("" + selectedLocation);

				}
				
			});
			
			/**********rainfall action listener******************/
			
			rainfall.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(rainfall.isSelected()){
						rain = true;
					}
					//unchecks the radio button
					if (++clickCount % 2 == 0) {

						rain_temp.clearSelection();
						rain = false;
					}
				}
			});
			
			/***********temperature action***************/
			temp.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(temp.isSelected() == true){
						Temp = true;
					}
					if (++clickCount % 2 == 0) {

						Temp_temp.clearSelection();
						Temp = false;
					}
				}
			});
			
			/***************SecondPanel**********************/ 
			secPan = new JPanel();
			addB = new JButton("add");{
			addB.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							{
								/************testing********************/
								//System.out.println(" RainCheck " + rain + "Location " + locationSelected + "Temperature " + Temp);
								LocationUpdate.add(locationSelected);
								RainfallBoo.add(rain);
								TemperatureBoo.add(Temp);
								/*************Checks if the boolean for temperature and rainfall is checked******************/
								if(rain == true & Temp == false){
									Rainfall rainObject = new Rainfall(locationSelected);
									Rainfall.add(rainObject);
									currentRain = true;
									
									/****************Testing********************/
									//System.out.print("This is rainfall object 1 " + Rainfall.get(0).toString());
									}
								
									if(Temp == true & rain == false){
									Temperature tempObject = new Temperature(locationSelected);
									Temperature.add(tempObject);
									
									/****************Testing********************/
									//System.out.print("This is temperature object 1 " + Temperature.get(0).toString());
									currentTemp = true;
									}
									if (Temp == true & rain == true){
										Rainfall rainObject1 = new Rainfall(locationSelected);
										Temperature tempObject1 = new Temperature(locationSelected);
										Both.add(rainObject1);
										Both.add(tempObject1);
										
										/****************Testing********************/
										//System.out.print("This is both object 1 " + Both.get(0).toString());
										currentBoth = true;
									}
									}
										//Clears the radio buttons
										rain_temp.clearSelection();
										Temp_temp.clearSelection();
										resetButtons();
									}
							}
						);
					}
			
			/*****************Labels used for output panels********************/
			output1 = new JLabel();
			output2 = new JLabel();
			output3 = new JLabel();
			output4 = new JLabel();
			output =  new JLabel();
			
			secPan.add(addB);
			
			/*******************Third Panel**********************/
			thirPan = new JPanel();
			secPan.add(addB);
		
			/********************View button listener************/
			allLocationsLabel = new JLabel("Locations");
			thirPan.add(allLocationsLabel);
			viewB = new JButton("view");
			thirPan.add(viewB);
			viewB.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					createForms();
				}
			});
				
			/************Fourth Panel*******************/ 
			fourPan = new JPanel();
			fourPan.setLayout(new FlowLayout());
			fourPan.add(fstPan);
			fourPan.add(secPan);
			fourPan.add(thirPan);
			
			/************Main Frame*******************/ 
			mainFrame.add(fourPan, BorderLayout.NORTH);
			mainFrame.setVisible(true);
			mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}

		/*******Restsets the buttons**************/
		public static void resetButtons(){
			
			rain = false;
			Temp = false;
		}
		
		/**************Creates an update***************/
		public static Runnable Update(){
					
				Runnable updateMonitor = new Runnable() {
			    public void run() {
			    	fifthPan.removeAll();
			    	if (Rainfall.size() > 0 ){
						createUpdate();						
					}
					if (Temperature.size() > 0 ){
						createUpdate();
					}
					if (Both.size() > 0 ){
						createUpdate();
					}
					/***********Testing the update runs***********/
			    	//System.out.println("\nHello world");
			    }
			};
			return updateMonitor;
		}
	
		/************Update of the User interface*********************/
		public static void createUpdate(){
				for(int i = 0;i <= LocationUpdate.size();i++){
					locationSelected = LocationUpdate.get(i);
					rain = RainfallBoo.get(i);
					Temp = TemperatureBoo.get(i);
				if(rain == true & Temp == false){
					Rainfall rainObject = new Rainfall(locationSelected);
					Rainfall.add(rainObject);
					currentRain = true;
					System.out.print("This is rainfall object 1 " + Rainfall.get(0).toString());
					createForms();
					}
				if(Temp == true & rain == false){
					Temperature tempObject = new Temperature(locationSelected);
					Temperature.add(tempObject);
					System.out.print("This is temperature object 1 " + Temperature.get(0).toString());
					currentTemp = true;
					createForms();
					}
				if (Temp == true & rain == true){
					Rainfall rainObject1 = new Rainfall(locationSelected);
					Temperature tempObject1 = new Temperature(locationSelected);
					Both.add(rainObject1);
					Both.add(tempObject1);
					System.out.print("This is both object 1 " + Both.get(0).toString());
					currentBoth = true;
					createForms();
				}
				}
		}		
		
		/*************Creates the panels that are used to display data to user******************/
		public static void createForms(){ 
			if(Both.size() >= 1 & currentBoth == true){
				for(int i = 0;i < 1;i++){
					JPanel panelDisplay = new JPanel();
					JInternalFrame parent = new JInternalFrame(locationSelected, true, true, true);
					panelDisplay.setLayout(new BorderLayout());
					objectTemp = (Both.get(Both.size()-1));
					
					/***************Testing*************/
					//System.out.println("Object values " + objectTemp.toString());
					
					/***************Change labels to output************/
					output = new JLabel ("The rainfall is: " + melbourneweather2.Rainfall.getRainfall() + " MM");
					output1 = new JLabel ("The temperature is: " + melbourneweather2.Temperature.getTemperature() + " °C");
					output2 = new JLabel ("The time is:" + melbourneweather2.Temperature.getTemperatureTime());
					
					/************panel formating*************/
					panelDisplay.add(output2, BorderLayout.NORTH);
					panelDisplay.add(output1, BorderLayout.CENTER);
					panelDisplay.add(output, BorderLayout.SOUTH);
					panelDisplay.setSize(300,300);
					panelDisplay.revalidate();
					parent.add(panelDisplay);
					parent.setSize(300, 300);
					parent.setVisible(true);
					parent.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
					parent.revalidate();
					fifthPan.add(parent);
					currentBoth = false;
				}
			}
			if(Rainfall.size() >= 1 & currentRain == true){
				for(int i = 0;i < 1;i++){
					JPanel panelDisplay = new JPanel();
					JInternalFrame parent = new JInternalFrame(locationSelected, true, true, true);
					panelDisplay.setLayout(new BorderLayout());
					objectTemp = (Rainfall.get(Rainfall.size()-1));
					
					/***************Testing*************/
					//System.out.println("Object values " + objectTemp.toString());
					
					/***************Change labels to output************/
					output1 = new JLabel ("The rainfall is: \t" + melbourneweather2.Rainfall.getRainfall() + " MM");
					output2 = new JLabel ("The time is: \t" + melbourneweather2.Rainfall.getRainfallTime());
					output3 = new JLabel ("The location is: " + melbourneweather2.Rainfall.getRainfallLoc());
					
					/************panel formating*************/
					panelDisplay.add(output3, BorderLayout.NORTH);
					panelDisplay.add(output2, BorderLayout.CENTER);
					panelDisplay.add(output1, BorderLayout.SOUTH);
					panelDisplay.setSize(300,300);
					panelDisplay.revalidate();
					parent.add(panelDisplay);
					parent.setSize(300, 300);
					parent.setVisible(true);
					parent.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
					parent.revalidate();
					fifthPan.add(parent);
					currentRain = false;
				}
			}
			if(Temperature.size() >= 1 & currentTemp == true){
				for (int i = 0;i < 1;i++){
					JPanel panelDisplay = new JPanel();
					JInternalFrame parent = new JInternalFrame(locationSelected, true, true, true);
					panelDisplay.setLayout(new BorderLayout());
					objectTemp = (Temperature.get(Temperature.size()-1));
					
					/***************Testing*************/
					//System.out.println("Object values " + objectTemp.toString());
					
					/***************Change labels to output************/
					output1 = new JLabel ("The temperature is: \t" + melbourneweather2.Temperature.getTemperature() + " °C");
					output2 = new JLabel ("The time is: \t" + melbourneweather2.Temperature.getTemperatureTime());
					output3 = new JLabel ("The location is: " + melbourneweather2.Temperature.getTemperatureLoc());
					
					/************panel formating*************/
					panelDisplay.add(output3, BorderLayout.NORTH);
					panelDisplay.add(output2, BorderLayout.CENTER);
					panelDisplay.add(output1, BorderLayout.SOUTH);
					panelDisplay.setSize(300,300);
					panelDisplay.revalidate();
					parent.add(panelDisplay);
					parent.setSize(300, 300);
					parent.setVisible(true);
					parent.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
					parent.revalidate();
					fifthPan.add(parent);
					currentTemp = false;
				}
			}
			fifthPan.setVisible(true);
			mainFrame.add(fifthPan, BorderLayout.CENTER);
			
			/**************Testing*****************/
			//System.out.println("BLAH BLAH");
			//System.out.println(Both.size() + " T " + Temperature.size() + " R " + Rainfall.size());
			//Created by Wayde and CHi CHI
			}
		
		
}

			
			
		
