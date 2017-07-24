package com.company;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
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
        int numberOfVehicles = allVehicles.length;
        double totalOdometer = 0;
        double totalConsumption = 0;
        double totalOdometerAtLastOilChange = 0;
        double totalEngineSize = 0;

        for (VehicleInfo vehicle : allVehicles){
            totalOdometer += vehicle.getOdometer();
            totalConsumption += vehicle.getConsumption();
            totalOdometerAtLastOilChange += vehicle.getOdometerAtLastOilChange();
            totalEngineSize += vehicle.getEngineSize();
        }

        double averageOdometer = Math.round((totalOdometer / numberOfVehicles)*10.0)/10.0;
        double averageConsumption = Math.round((totalConsumption / numberOfVehicles)*10.0)/10.0;
        double averageOdometerAtLastOilChange = Math.round((totalOdometerAtLastOilChange / numberOfVehicles)*10.0)/10.0;
        double averageEngineSize = Math.round((totalEngineSize / numberOfVehicles)*10.0)/10.0;


        try {
            File htmlFile = new File("dashboard.html");
            FileWriter htmlFileWriter = new FileWriter(htmlFile);

              htmlFileWriter.write("<html>\n" +
                      "  <title>Vehicle Telematics Dashboard</title>\n" +
                      "  <body>");
              // need number of vehicles
              htmlFileWriter.write("<h1 align=\"center\">Averages for " +
                      allVehicles.length +
                      " vehicles</h1>");
              htmlFileWriter.write("<table align=\"center\">\n" +
                      "        <tr>\n" +
                      "            <th>Odometer (miles) |</th><th>Consumption (gallons) |</th><th>Last Oil Change |</th><th>Engine Size (liters)</th>\n" +
                      "        </tr>");
              //need averages
              htmlFileWriter.write("<tr>\n" +
                      "            <td align=\"center\">" +
                      averageOdometer +
                      "</td><td align=\"center\">" +
                      averageConsumption +
                      "</td><td align=\"center\">" +
                      averageOdometerAtLastOilChange +
                      "</td align=\"center\"><td align=\"center\">" +
                      averageEngineSize +
                      "</td>\n" +
                      "        </tr>");
              htmlFileWriter.write("</table>");
              htmlFileWriter.write("<h1 align=\"center\">History</h1>\n" +
                      "    <table align=\"center\" border=\"1\">\n" +
                      "        <tr>\n" +
                      "            <th>VIN</th><th>Odometer (miles)</th><th>Consumption (gallons)</th><th>Last Oil Change</th><th>Engine Size (liters)</th>\n" +
                      "        </tr>");
              //need to loop through all vehicles and print info
            for(VehicleInfo vehicle : allVehicles){
                htmlFileWriter.write("<tr>\n" +
                        "            <td align=\"center\">" +
                        vehicle.getVin() +
                        "</td><td align=\"center\">" +
                        Math.round(vehicle.getOdometer()*10.0)/10.0 +
                        "</td><td align=\"center\">" +
                        Math.round(vehicle.getConsumption()*10.0)/10.0 +
                        "</td><td align=\"center\">" +
                        Math.round(vehicle.getOdometerAtLastOilChange()*10.0)/10.0 +
                        "</td align=\"center\"><td align=\"center\">" +
                        Math.round(vehicle.getEngineSize()*10.0)/10.0 +
                        "</td>\n" +
                        "        </tr>");
            }
            htmlFileWriter.write(" </table>\n" +
                    "  </body>\n" +
                    "</html>");
            htmlFileWriter.close();
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
