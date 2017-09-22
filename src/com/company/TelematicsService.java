package com.company;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TelematicsService {
    static void report(VehicleInfo vehicleInfo) {
        // Write the VehicleInfo to a file as json
        // using the VIN as the name of the file and
        // a "json" extension (e.g. "234235435.json").
        // The file will overwrite any existing files for the same VIN.
        File f = new File(vehicleInfo.getVin() + ".json");
        try {
            // write to a new file
            FileWriter fw = new FileWriter(f);

            // convert our vehicleInfo to json
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(vehicleInfo);

            fw.write(json);
            fw.close();


            // GOAL: Build an arraylist of VehicleInfo objects
            // we'll get our vehicle info info from all the json files
            // in the current directory
            ArrayList<VehicleInfo> vehicleInfos = new ArrayList<>();

            // read from all json files
            File file = new File(".");
            for (File currentFile : file.listFiles()) {
                if (currentFile.getName().endsWith(".json")) {
                    // get the json from the file
                    String fileJson = getDataFromFile(currentFile);


                    // convert that json to a VehicleInfo object
                    ObjectMapper om = new ObjectMapper();
                    VehicleInfo vi = om.readValue(fileJson, VehicleInfo.class);


                    // what do we do with vi?
                    // put it inside of an arraylist.
                    vehicleInfos.add(vi);

                }
            }

            double odometer = 0;
            double consumption = 0;
            double oilChange = 0;
            double engineSize = 0;
            for( int i = 0; i < vehicleInfos.size(); i++){
                odometer += vehicleInfos.get(i).getOdometer();
                consumption += vehicleInfos.get(i).getConsumption();
                oilChange += vehicleInfos.get(i).getLastOilChange();
                engineSize += vehicleInfos.get(i).getEngineSize();

            }
            System.out.println("Odometer Total:" + odometer);
            System.out.println("Odometer Average:" + (odometer/vehicleInfos.size()));

            System.out.println("Consumption Total:" + consumption);
            System.out.println("Consumption Average:" + (consumption/vehicleInfos.size()));

            System.out.println("Total of Last Oil Change:" + oilChange);
            System.out.println("Average Oil Change:" + (oilChange/vehicleInfos.size()));

            System.out.println("Total Engine Size:" + engineSize);
            System.out.println("Average Engine Size:" + (engineSize/vehicleInfos.size()));

            System.out.println("The average MPG is  :" + (consumption/odometer));

            // when we get to this point, we should have a populated arraylist of VehicleInfos

            // we can use this list to write values to our html

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static String getDataFromFile(File f) throws FileNotFoundException {
        Scanner fileScanner = new Scanner(f);

        String results = "";
        String answer =fileScanner.nextLine();
        results += answer;

        // while fileScanner hasNext(),
        // add the next line from the file
        // to results

        return results;
    }

    // TODO: maybe write a "writeDataToFile method??????"
}

