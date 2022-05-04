public class Plane extends Shipment {
	
	private String captain;
	private int crew;
	
	public Plane(int aLoad, String aDestination, int anETA, String aCaptain, int aCrew) {
		super(aLoad, aDestination, anETA);
		captain = aCaptain;
		crew = aCrew; 
	}
}