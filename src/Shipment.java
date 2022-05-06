
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
	

	public String getReport() {
		if(ETA < 4) {
			String outputString = String.format("%c %s with load %dt is heading to %s will arrive in %dh.", 
					 '*', getReportPart(), load, destination, ETA);
			return outputString;
		}else {
			String outputString = String.format("%s with load %dt is heading to %s will arrive in %dh.", 
					getReportPart(), load, destination, ETA);
			return outputString;
		}
	}
}
