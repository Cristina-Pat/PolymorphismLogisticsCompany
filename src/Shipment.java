
public class Shipment {
	
	private int load;
	private String destination;
	private int ETA;
	
	public Shipment(int aLoad, String aDestination, int anETA){
		load = aLoad;
		destination = aDestination;
		ETA = anETA;
	}
	
	public String getReportPart() {
		return "Shipment";
	}
	

}
