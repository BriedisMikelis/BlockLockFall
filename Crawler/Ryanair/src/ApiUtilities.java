import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ApiUtilities {
	public Map<String, Object> getApiResponse(RyanairApiCallParameters apiParams, String query) throws MalformedURLException, IOException {
		URLConnection connection = new URL(apiParams.url + "?" + query).openConnection();
		connection.setRequestProperty("Accept-Charset", apiParams.charset);
		InputStream response = connection.getInputStream();
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(response, Map.class);
	}
	
	public String buildApiRequestQuery(RyanairApiCallParameters apiParams) throws UnsupportedEncodingException {
		String charset = apiParams.charset;
		return String.format(
				"ADT=%s&CHD=%s&DateIn=%s&DateOut=%s&Destination=%s&FlexDaysIn=%s&FlexDaysOut=%s&INF=%s&Origin=%s&RoundTrip=%s&TEEN=%s",
				URLEncoder.encode(apiParams.ADT, charset), URLEncoder.encode(apiParams.CHD, charset), URLEncoder.encode(apiParams.DateIn, charset),
				URLEncoder.encode(apiParams.DateOut, charset), URLEncoder.encode(apiParams.Destination, charset),
				URLEncoder.encode(apiParams.FlexDaysIn, charset), URLEncoder.encode(apiParams.FlexDaysOut, charset),
				URLEncoder.encode(apiParams.INF, charset), URLEncoder.encode(apiParams.Origin, charset),
				URLEncoder.encode(apiParams.RoundTrip, charset), URLEncoder.encode(apiParams.TEEN, charset));
	}
}
