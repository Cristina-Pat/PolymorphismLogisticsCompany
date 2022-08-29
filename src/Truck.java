
public class Truck extends Shipment {

	private String driver;

	public Truck(int anID, int aLoad, String aDestination, int anETA, String aDriver) {
		super(anID, aLoad, aDestination, anETA);
		driver = aDriver;
	}
	
	public String getDriverName() {
		return driver;
	}

	public String getReportPart() {
		String report = String.format("Truck driven by %s", driver);
		return report;
	}
}
