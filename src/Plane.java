public class Plane extends Shipment {
	
	private String captain;
	private int crew;
	
	public Plane(int anID, int aLoad, String aDestination, int anETA, String aCaptain, int aCrew) {
		super(anID, aLoad, aDestination, anETA);
		captain = aCaptain;
		crew = aCrew; 
	}
	
	public String getCaptainName() {
		return captain;
	}
	
	public int getCrewCount() {
		return crew;
	}
	
	public String getReportPart() {
		 String report = String.format("Plane piloted by %s with %d crew and ", 
				 captain, crew);
	     return report;
	}
}