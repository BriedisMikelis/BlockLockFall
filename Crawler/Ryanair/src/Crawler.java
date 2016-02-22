import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Crawler {

	public static void main(String[] args) throws IOException {
		{
			
			String url = "https://desktopapps.ryanair.com/en-ie/availability";
			String charset = "UTF-8";
			String ADT = "1";
			String CHD = "0";
			String DateIn = "2016-03-20";
			String DateOut = "2016-02-22";
			String Destination = "RYG";
			String FlexDaysIn = "6";
			String FlexDaysOut = "4";
			String INF = "0";
			String Origin = "RIX";
			String RoundTrip = "true";
			String TEEN = "0";

			String query = String.format(
					"ADT=%s&CHD=%s&DateIn=%s&DateOut=%s&Destination=%s&FlexDaysIn=%s&FlexDaysOut=%s&INF=%s&Origin=%s&RoundTrip=%s&TEEN=%s",
					URLEncoder.encode(ADT, charset),
					URLEncoder.encode(CHD, charset),
					URLEncoder.encode(DateIn, charset),
					URLEncoder.encode(DateOut, charset),
					URLEncoder.encode(Destination, charset),
					URLEncoder.encode(FlexDaysIn, charset),
					URLEncoder.encode(FlexDaysOut, charset),
					URLEncoder.encode(INF, charset),
					URLEncoder.encode(Origin, charset),
					URLEncoder.encode(RoundTrip, charset),
					URLEncoder.encode(TEEN, charset));
//			System.out.println("Making get request to" + url + "?" + query);
			URLConnection connection = new URL(url + "?" + query).openConnection();
			connection.setRequestProperty("Accept-Charset", charset);
			InputStream response = connection.getInputStream();
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> jsonMap = mapper.readValue(response, Map.class);
			
			ArrayList<Object> trips = (ArrayList<Object>) jsonMap.get("trips");
			for (int i = 0; i < trips.size(); i++) {
				LinkedHashMap<String, Object> trip = (LinkedHashMap<String, Object>) trips.get(i);
//				System.out.println(trip.toString());
				System.out.println(trip.get("origin") +" - "+ trip.get("destination"));
				ArrayList<Object> dates = (ArrayList<Object>) trip.get("dates");
//				System.out.println(dates.toString());
				for (int j = 0; j < dates.size(); j++) {
					LinkedHashMap<String, Object> date = (LinkedHashMap<String, Object>) dates.get(j);
					if (((ArrayList<Object>)date.get("flights")).isEmpty()) {
						continue;
					}
//					System.out.println(date.get("flights"));
					ArrayList<Object> flights = (ArrayList<Object>) date.get("flights");
					for (int k = 0; k < flights.size(); k++) {
						LinkedHashMap<String, Object> flight = (LinkedHashMap<String, Object>) flights.get(k);
						System.out.println(flight);
//						if (!flight.containsKey("regularFare")) {
//							continue;
//						}
						System.out.println(((ArrayList<Object>)flight.get("time")).get(0) + " - " + ((ArrayList<Object>)flight.get("time")).get(1));
					}
				}
			}
		}
	}
}
