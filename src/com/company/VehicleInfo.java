package com.company;
import java.util.List;

/**
 * Created by franciswalsh on 7/21/17.
 */
public class VehicleInfo {

    private int vin;                        //Vehicle Identification Number
    private double odometer;                //miles traveled
    private double consumption;             //gallons of gas consumed
    private double odometerAtLastOilChange; //odometer reading for last oil change
    private double engineSize;              //in liters (e.g. 2.0 4.5)

    public VehicleInfo(int vin, double odometer, double consumption, double odometerAtLastOilChange, double engineSize){
        this.vin = vin;
        this.odometer = odometer;
        this.consumption = consumption;
        this.odometerAtLastOilChange = odometerAtLastOilChange;
        this.engineSize = engineSize;
    }

    public VehicleInfo() {
    }

    public int getVin() {
        return this.vin;
    }

    public void setVin(int vin) {
        this.vin = vin;
    }

    public double getOdometer() {
        return this.odometer;
    }

    public void setOdometer(double odometer) {
        this.odometer = odometer;
    }

    public double getConsumption() {
        return this.consumption;
    }

    public void setConsumption(double consumption) {
        this.consumption = consumption;
    }

    public double getOdometerAtLastOilChange() {
        return this.odometerAtLastOilChange;
    }

    public void setOdometerAtLastOilChange(double odometerAtLastOilChange) {
        this.odometerAtLastOilChange = odometerAtLastOilChange;
    }

    public double getEngineSize() {
        return this.engineSize;
    }

    public void setEngineSize(double engineSize) {
        this.engineSize = engineSize;
    }

//    public double getMilesPerGallon(){
//        return Math.round((this.getOdometer() / this.getConsumption())*10.0)/10.0;
//    }
}
