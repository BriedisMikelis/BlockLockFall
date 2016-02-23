
public class RyanairApiCallParameters {
	String url = "https://desktopapps.ryanair.com/en-ie/availability";
	String charset = "UTF-8";
	String ADT = "1";
	String CHD = "0";
	String DateIn;
	String DateOut;
	String Destination;
	String FlexDaysIn;
	String FlexDaysOut;
	String INF = "0";
	String Origin;
	String RoundTrip;
	String TEEN = "0";
	
	public RyanairApiCallParameters() {
		this("2016-03-23", "2016-02-24", "BRI", "6", "6", "TRS", "true");
	}
	
	public RyanairApiCallParameters(String DateIn, String DateOut, String Destination, String FlexDaysIn, String FlexDaysOut, String Origin, String RoundTrip) {
		this.DateIn = DateIn; 
		this.DateOut = DateOut; 
		this.Destination = Destination; 
		this.FlexDaysIn = FlexDaysIn; 
		this.FlexDaysOut = FlexDaysOut; 
		this.Origin = Origin; 
		this.RoundTrip = RoundTrip; 
	}
	
	public RyanairApiCallParameters(String Origin, String Destination) {
		this("2016-07-15", "2016-07-08", Destination, "3", "3", Origin, "true");
	}
}
