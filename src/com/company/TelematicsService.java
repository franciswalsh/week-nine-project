package com.company;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
/**
 * Created by franciswalsh on 7/21/17.
 */
public class TelematicsService {

    public void report(VehicleInfo vehicleInfo){
        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(vehicleInfo);
            File file = new File(vehicleInfo.getVin() + ".json");
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(json);
            fileWriter.close();
            VehicleInfo vi = mapper.readValue(json, VehicleInfo.class);
        }
        catch (JsonProcessingException ex){
            System.out.println("Json processing exception");
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }

}
