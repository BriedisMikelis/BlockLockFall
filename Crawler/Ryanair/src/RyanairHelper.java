import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RyanairHelper {
	public void writeToFile(String theString) {
		try {

			File file = new File("results.csv");

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(theString);
			bw.write("\n");
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Map<String, Object> getMapOFAllRoutesFromJsonFile(String fileName){
		File file = new File(fileName);
		FileInputStream fis;
		Map<String, Object> response = null;
		ObjectMapper mapper = new ObjectMapper();
		try  {
			fis = new FileInputStream(file);
			response = mapper.readValue(fis, Map.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}
	
	//Creates hash map from map that was gotten from json file with all tht roates available.
	public ArrayList<String> createArrayListOfRoatesToRequest(Map<String, Object> jsonMap){
		ArrayList<String> results = new ArrayList<String>();
		ArrayList<Object> airports = (ArrayList<Object>) jsonMap.get("airports");
		for (int i = 0; i < airports.size(); i++) {
			String from = ((Map<String, Object>)airports.get(i)).get("iataCode").toString();
			ArrayList<String> routes = (ArrayList<String>) ((Map<String, Object>)airports.get(i)).get("routes");
			for (int j = 0; j < routes.size(); j++) {
				results.add(from + "-" + routes.get(j));
			}
		}
		return results;
	}

}
