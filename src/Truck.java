
public class Truck extends Shipment{

	private String driver;
	
	public Truck(int aLoad, String aDestination, int anETA, String aDriver) {
		super(aLoad, aDestination, anETA);
		driver = aDriver;
	}
}
