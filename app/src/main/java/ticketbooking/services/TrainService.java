package ticketbooking.services;

import java.io.File;
import java.util.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import ticketbooking.entities.Train;

public class TrainService {

  public ObjectMapper objectMapper = new ObjectMapper();
  String Trainpath = "./src/main/java/ticketbooking/localDB/trains.json";

  private List<Train> trainList;

  public TrainService() throws Exception {
    try {
      File TrainFile = new File(Trainpath);
      trainList = objectMapper.readValue(TrainFile, new TypeReference<List<Train>>() {
      });
    } catch (Exception e) {
      System.out.println("Error loading train data");
    }
  }

  public void printTrainlist() {
    for (Train train : trainList) {
      System.out.println(train.getTrainInfo());
    }
  }
}
