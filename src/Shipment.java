public class Shipment implements Comparable<Shipment> {
	
	private int load;
	private String destination;
	private int ETA;
	private int ID;
	
	public Shipment(int anID, int aLoad, String aDestination, int anETA) {
		
		if(aLoad < 0) {
			throw new NegativeLoadException();
		}
		if(anETA < 0) {
			throw new NegativeETAException();
		}
		
		ID = anID;
		load = aLoad;
		destination = aDestination;
		ETA = anETA;
	}
	
	@Override
	public int compareTo(Shipment s) {
		// return negative int if the compared elements is less, 0 if is equal and pozitive int if is grater
		if(this.equals(s)) {
			return 0;
		}
		
		if(this.getETA() < s.getETA()) {
			return -1;
		}
		if(this.getETA() == s.getETA()) {
			if(this.getID() > s.getID()) {
				return 1;
			}
			if(this.getID() < s.getID()) {
				return -1;
			}
			if(this.getID() == s.getID()) {
				return 0;
			}
		}
		return 1;
	}
	
	public int getID() {
		return ID;
	}
	
	public int getLoad() {
		return load;
	}
	
	public String getDestination() {
		return destination;
	}
	
	public int getETA() {
		return ETA;
	}
	
	public String getReportPart() {
		return "Shipment";
	}
	
	@Override
	public String toString() {
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
	
	@Override
	public boolean equals(Object obj) {

	      if(obj instanceof Shipment) {
	    	  Shipment s = (Shipment) obj;
	    	  return ID == s.ID;
	      }
	    return false;
	}
	
	@Override
	public int hashCode() {
		return ID;
	}

	public void setETA(int newETA) {
		ETA = newETA;		
	}
	
}
