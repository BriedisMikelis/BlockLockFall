import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Map;

public class Crawler {

	public static void main(String[] args) throws IOException {
		makeRequest("RIX", "RYG");
//		RyanairHelper helper = new RyanairHelper();
//		ArrayList<String> roatesToRequest;
//		roatesToRequest = helper
//				.createArrayListOfRoatesToRequest(helper.getMapOFAllRoutesFromJsonFile("allAirpourtRoutes.json"));
//		makeApiCallsForTheseRoates(roatesToRequest);
		
	}

	private static void makeApiCallsForTheseRoates(ArrayList<String> roatesToRequest) throws MalformedURLException, IOException {
		while (!roatesToRequest.isEmpty()) {
			String roate = roatesToRequest.get(0);
			String from = roate.split("-")[0];
			String to = roate.split("-")[1];
			if (makeRequest(from, to)) {
				roatesToRequest.remove(0);
				System.out.println(from + "-" + to);
				String opositeRoate = to + "-" + from;
				if (roatesToRequest.contains(opositeRoate)) {
					roatesToRequest.remove(roatesToRequest.indexOf(opositeRoate));
					System.out.println(opositeRoate);
				}
			} else {
				break;
			}

		}
	}

	private static boolean makeRequest(String from, String to) throws MalformedURLException, IOException {
		RyanairApiCallParameters apiParams = new RyanairApiCallParameters(from, to);
		ApiUtilities apiUtils = new ApiUtilities();
		String query = apiUtils.buildApiRequestQuery(apiParams);
		Map<String, Object> jsonMap = apiUtils.getApiResponse(apiParams, query);
		new ResponseParser().parseJsonGetTripsWriteToFile(jsonMap);
		return true;
	}
}
