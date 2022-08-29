public class Ship extends Shipment {

	private int sailors;
	private String name;
	
	public Ship(int anID, int aLoad, String aDestination, int anETA, int sailorCount, String aName) {
		super(anID, aLoad, aDestination, anETA);
		sailors = sailorCount;
		name = aName;
		}
	
	public int getSailorCount() {
		return sailors;
	}
	
	public String getName() {
		return name;
	}
	
	public String getReportPart() {
		 String report = String.format("Ship %s with %d sailors and ", 
				 name, sailors);
	     return report;
	}
}