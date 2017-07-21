package com.company;
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


    }
}
