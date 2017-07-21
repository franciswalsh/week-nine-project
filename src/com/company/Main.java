package com.company;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
	// write your code here
        Scanner scanner = new Scanner(System.in);
        System.out.println("What is the vehicle's Identification Number?");
        int newVin = Integer.parseInt(scanner.nextLine());
        System.out.println("How many miles are currently on the Vehicle's Odometer?");
        Double newOdometer = Double.parseDouble(scanner.nextLine());
        System.out.println("How many gallons of gas has your vehicle consumed?");
        Double newConsumption = Double.parseDouble(scanner.nextLine());
        System.out.println("How many miles were on your Vehicle's odometer at your Vehicle's last oil change?");
        Double newOdometerAtLastOilChange = Double.parseDouble(scanner.nextLine());
        System.out.println("What is your Vehicle's engine size in Liters?");
        Double newEngineSize = Double.parseDouble(scanner.nextLine());

        VehicleInfo vehicleCreated = new VehicleInfo(newVin, newOdometer, newConsumption,
                                                     newOdometerAtLastOilChange, newEngineSize);

        TelematicsService telematicsService = new TelematicsService();

        telematicsService.report(vehicleCreated);

        int numberOfFiles = 0;
        File fileReadCounter = new File(".");
        for (File f: fileReadCounter.listFiles()){
            if (f.getName().endsWith(".json")) {
                numberOfFiles++;
            }
        }

        VehicleInfo[] allVehicles = new VehicleInfo[numberOfFiles];
        int counter = 0;
        File fileRead = new File(".");
        for (File f : fileRead.listFiles()) {

            if (f.getName().endsWith(".json")) {
                File file = new File(String.valueOf(f));
                try {
                    Scanner fileScanner = new Scanner(file);
                    ObjectMapper mapper = new ObjectMapper();
                    VehicleInfo vi = mapper.readValue(file, VehicleInfo.class);
                    allVehicles[counter] = vi;
                }
                catch (IOException ex){
                    System.out.println("Could not find file *" + String.valueOf(f) + "*");
                    ex.printStackTrace();
                }
                counter++;
            }
        }
        System.out.println(allVehicles[4].getVin());
    }
}
