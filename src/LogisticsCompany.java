
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.TreeSet;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class LogisticsCompany  {
	private TreeSet<Shipment> shipments;
	private String name;
	
	private int countTruck = 0;
	private int countPlane = 0;
	private int countShip = 0;


	public LogisticsCompany(String aName) {
		shipments = new TreeSet<>();
		name = aName;
	}

	public void addMember(Shipment vehicle) {
		Boolean added = shipments.add(vehicle);
		if(!added) {
			System.out.println("Shipment " + vehicle.getID() + " could not be added because it already exists");
		}
	}

	public void removeMember(Shipment vehicle) {
		shipments.remove(vehicle);
	}
	
	public void updateMember(Shipment vehicle, int newETA) {
		vehicle.setETA(newETA);
	}
	
	public Shipment selectMember(String destination) {
		for(Shipment s: shipments) {
			if(s.getDestination().equals(destination)) {
				return s;
			}
		}
		return null;
	}

	public void printMembers() {
		for(Shipment s : shipments) {
			System.out.println(s);
		}
		System.out.println();
	}


	public void countType() {
		for (Shipment n : shipments) {
			if (n instanceof Truck) {
				countTruck++;
			} else if (n instanceof Plane) {
				countPlane++;
			} else if (n instanceof Ship) {
				countShip++;
			}
		}
	}

	
	@Override
	public String toString() {
		String s = "";
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		s += name + "\n";
		s += dtf.format(now) + "\n";
		countType();
		s += countTruck + " - trucks, " + countPlane + " - planes, " + countShip + " - ships\n";
		s += "========================\n\n";
		
		if (shipments.size() < 1) {
			s += "No shipments.\n";
		} else {
			for (Shipment n : shipments) {
				s += n.toString() + "\n";
			}
		}
		return s;
	}
	
	public void printReport() {
		System.out.println(this.toString());
	}

	public TreeSet<Shipment> getShipments() {
		return shipments;
	}

	public void clear() {
		shipments.clear();
	}

	public void populate() {
		addMember(new Truck(10256, 5, "London", 3, "Tom" ));
		addMember(new Ship(12478, 115, "Constanta", 3, 2, "St Helen"));
		addMember(new Plane(10245, 2, "Cairo", 5, "Lucas", 7));
		addMember(new Ship(10789, 200, "Calais", 115, 7, "Claire"));
		addMember(new Plane(11258, 3, "Madrid", 2, "John", 5));
	}
	
	/**
	 * Create a CSV file for ship instances.
	 * @return
	 */
	private FileWriter createShipCsvFile(String filename) {
		FileWriter shipTable = null;
		try {
			shipTable = new FileWriter(filename + "ShipTable.csv");
			//shipTable.append("ShipmentID, Load, Destination, ETA, Sailors, Ship name \n");
			for(Shipment element: shipments) {
				if(element instanceof Ship) {
					Ship s = (Ship) element;
					shipTable.append(element.getID() + "," + element.getLoad() + "," + element.getDestination() + "," + element.getETA()
								+ "," + s.getSailorCount() + "," + s.getName() + "\n");
				}
			}
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				shipTable.flush();
				shipTable.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		return shipTable;
	}
	
	/**
	 * Create a CSV file for plane instances.
	 * @return
	 */
	private FileWriter createPlaneCsvFile(String filename) {
		FileWriter planeTable = null;
		try {
			planeTable = new FileWriter(filename + "PlaneTable.csv");
			for(Shipment element: shipments) {
				if(element instanceof Plane) {
					Plane s = (Plane) element;
					planeTable.append(element.getID() + "," + element.getLoad() + "," + element.getDestination() + "," + element.getETA()
								+ "," + s.getCaptainName() + "," + s.getCrewCount() + "\n");
				}
			}
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				planeTable.flush();
				planeTable.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		return planeTable;
	}
	
	/**
	 * Create a CSV file for truck instances.
	 * @return
	 */
	private FileWriter createTruckCsvFile(String filename) {
		FileWriter truckTable = null;
		try {
			truckTable = new FileWriter(filename + "TruckTable.csv");
			for(Shipment element: shipments) {
				if(element instanceof Truck) {
					Truck s = (Truck) element;
					truckTable.append(element.getID() + "," + element.getLoad() + "," + element.getDestination() + "," + element.getETA()
								+ "," + s.getDriverName() + "\n");
				}
			}
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				truckTable.flush();
				truckTable.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		return truckTable;
	}
	
	public void createCSVFile(String filename) {
		createPlaneCsvFile(filename);
		createShipCsvFile(filename);
		createTruckCsvFile(filename);
	}
	
	/**
	 * Read a CSV file for ship instances.
	 * @return
	 */
	private void readShipCsvFile(String filename) throws InvalidInputFileException, IOException {
		// open the CSV file from a path
		
		String line = "";
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filename + "ShipTable.csv"));
			while((line = br.readLine())!=null) {
				String[] strShipment = line.split(",");
				int size = strShipment.length;
				if(size != 6) { //6 is number of fields
					throw new InvalidInputFileException();
				}
				int ID = Integer.parseInt(strShipment[0]); 
				int load = Integer.parseInt(strShipment[1]);
				String destination = strShipment[2];
				int ETA= Integer.parseInt(strShipment[3]);
				int sailors = Integer.parseInt(strShipment[4]);
				String name = strShipment[5];
				
				Ship s = new Ship (ID, load, destination, ETA, sailors, name);
				addMember(s);
				
			}
			
		} catch (NumberFormatException ne) {
			throw new InvalidInputFileException();
			
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
		/**
		 * Read a CSV file for plane instances.
		 * @return
		 */
		private void readPlaneCsvFile(String filename) throws InvalidInputFileException, IOException {
			// open the CSV file from a path
			
			String line = "";
			BufferedReader br = null;
			try {
				br = new BufferedReader(new FileReader(filename + "PlaneTable.csv"));
				while((line = br.readLine())!=null) {
					String[] strShipment = line.split(",");
					int size = strShipment.length;
					if(size != 6) { //6 is number of fields
						throw new InvalidInputFileException();
					}
					int ID = Integer.parseInt(strShipment[0]); 
					int load = Integer.parseInt(strShipment[1]);
					String destination = strShipment[2];
					int ETA= Integer.parseInt(strShipment[3]);
					String captain = strShipment[4];
					int crew = Integer.parseInt(strShipment[5]);
					
					
					Plane p = new Plane (ID, load, destination, ETA, captain, crew);
					addMember(p);
					
				}
				
			} catch (NumberFormatException ne) {
				throw new InvalidInputFileException();
				
			} finally {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		/**
		 * Read a CSV file for truck instances.
		 * @return
		 */
		private void readTruckCsvFile(String filename) throws InvalidInputFileException, IOException {
			// open the CSV file from a path
			
			String line = "";
			BufferedReader br = null;
			try {
				br = new BufferedReader(new FileReader(filename + "TruckTable.csv"));
				while((line = br.readLine())!=null) {
					Scanner scan = new Scanner(line);
					scan.useDelimiter(",");
					
					int ID = Integer.parseInt(scan.next()); 
					int load = Integer.parseInt(scan.next());
					String destination = scan.next();
					int ETA= Integer.parseInt(scan.next());
					String driver = scan.next();
					
					
					Truck t = new Truck (ID, load, destination, ETA, driver);
					addMember(t);
					
					scan.close();
				}				
			} catch (NumberFormatException ne) {
				throw new InvalidInputFileException();
			} finally {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		public void readCSVFile(String filename) throws InvalidInputFileException, IOException {
			readPlaneCsvFile(filename);
			readShipCsvFile(filename);
			readTruckCsvFile(filename);
		}

	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(obj == null || this.getClass() != obj.getClass()) {
			return false;
		}
		LogisticsCompany company = (LogisticsCompany) obj;
		return shipments.equals(company.shipments) && name.equals(company.name);
	}
}
