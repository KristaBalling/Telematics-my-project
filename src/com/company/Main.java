package com.company;


import java.util.Scanner;

public class Main {

    private static Scanner inputScanner;

    public static void main(String[] args) {
        // write your code here
        Scanner in = new Scanner(System.in);

        // make a new VehicleInfo object
        VehicleInfo vh = new VehicleInfo();
        vh.setVin(12345);
        // for each field in VehicleInfo, use the
        // scanner to read in values. and populate.
        // the object.
        System.out.println("Enter VIN # here:");
        vh.setVin(Integer.parseInt(in.nextLine()));

        System.out.println("Enter odometer reading here:");
        vh.setOdometer(Double.parseDouble(in.nextLine()));

        System.out.println("Enter Gas used in gallons here:");
        vh.setConsumption(Double.parseDouble(in.nextLine()));

        System.out.println("Enter odometer reading at last Oil change here:");
        vh.setLastOilChange(Double.parseDouble(in.nextLine()));

        System.out.println("Enter engine size in liters here:");
        vh.setEngineSize(Double.parseDouble(in.nextLine()));



        TelematicsService.report(vh);
    }
}

