import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class ResponseParser {
	public void parseJsonGetTripsWriteToFile(Map<String, Object> jsonMap) {
		ArrayList<Object> trips = (ArrayList<Object>) jsonMap.get("trips");
		for (int i = 0; i < trips.size(); i++) {
			LinkedHashMap<String, Object> trip = (LinkedHashMap<String, Object>) trips.get(i);
			System.out.println(trip.get("origin") + " - " + trip.get("destination"));
			ArrayList<Object> dates = (ArrayList<Object>) trip.get("dates");
			for (int j = 0; j < dates.size(); j++) {
				LinkedHashMap<String, Object> date = (LinkedHashMap<String, Object>) dates.get(j);
				if (((ArrayList<Object>) date.get("flights")).isEmpty()) {
					continue;
				}
				ArrayList<Object> flights = (ArrayList<Object>) date.get("flights");
				for (int k = 0; k < flights.size(); k++) {
					LinkedHashMap<String, Object> flight = (LinkedHashMap<String, Object>) flights.get(k);
					if (!flight.containsKey("regularFare")) {
						continue;
					}
					System.out.println(((ArrayList<Object>) flight.get("time")).get(0) + " - "
							+ ((ArrayList<Object>) flight.get("time")).get(1));
					LinkedHashMap<String, Object> regularFare = ((LinkedHashMap<String, Object>) flight
							.get("regularFare"));
					ArrayList<Object> fares = (ArrayList<Object>) regularFare.get("fares");
					System.out.println(((LinkedHashMap<String, Object>) fares.get(0)).get("amount"));
					String result = trip.get("origin") + ", " + trip.get("destination") + ", "
							+ ((ArrayList<Object>) flight.get("time")).get(0) + ", "
							+ ((ArrayList<Object>) flight.get("time")).get(1) + ", "
							+ ((LinkedHashMap<String, Object>) fares.get(0)).get("amount")+ ", "
							+ jsonMap.get("currency").toString();
					new RyanairHelper().writeToFile(result);
				}
			}
		}
	}
}
