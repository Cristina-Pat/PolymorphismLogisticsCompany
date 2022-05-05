public class Ship extends Shipment {

	private int sailors;
	private String name;
	
	public Ship(int aLoad, String aDestination, int anETA, int sailorCount, String aName) {
		super(aLoad, aDestination, anETA);
		sailors = sailorCount;
		name = aName;
		}
}