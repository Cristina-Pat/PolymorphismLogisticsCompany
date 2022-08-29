import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LogisticsCompanyTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testAddShipment() {
		LogisticsCompany x = new LogisticsCompany("Alpha");
		x.addMember(new Truck(10256, 5, "London", 3, "Tom" ));
		x.addMember(new Ship(12478, 115, "Constanta", 3, 2, "St Helen"));
		x.addMember(new Plane(10245, 2, "Cairo", 5, "Lucas", 7));
		x.addMember(new Ship(10789, 200, "Calais", 115, 7, "Claire"));
		x.addMember(new Plane(11258, 3, "Madrid", 2, "John", 5));
		x.addMember(new Ship(11100, 100, "Peru", 20, 3, "Maria"));
		
		assertEquals(6, x.getShipments().size());
		
		//fail("Not yet implemented");
	}

	@Test
	void testWriteReadCSV() throws InvalidInputFileException, IOException {
		LogisticsCompany x = new LogisticsCompany("Alpha");
		x.addMember(new Truck(10256, 5, "London", 3, "Tom" ));
		x.addMember(new Ship(12478, 115, "Constanta", 3, 2, "St Helen"));
		x.addMember(new Plane(10245, 2, "Cairo", 5, "Lucas", 7));
		x.addMember(new Ship(10789, 200, "Calais", 115, 7, "Claire"));
		x.addMember(new Plane(11258, 3, "Madrid", 2, "John", 5));
		x.addMember(new Ship(11100, 100, "Peru", 20, 3, "Maria"));
		
		x.createCSVFile("Alpha");
		
		LogisticsCompany z = new LogisticsCompany("Alpha");
		z.readCSVFile("Alpha");
		z.printReport();
		
		if(!(x.equals(z))) {
			fail("Companies not the same.");
		}
	}
	
	@Test
	void testRemoveVehicle() {
		LogisticsCompany x = new LogisticsCompany("Alpha");
		x.addMember(new Truck(10256, 5, "London", 3, "Tom" ));
		Shipment stHelen = new Ship(12478, 115, "Constanta", 3, 2, "St Helen"); 
		x.addMember(stHelen);
		x.addMember(new Plane(10245, 2, "Cairo", 5, "Lucas", 7));
		x.addMember(new Ship(10789, 200, "Calais", 115, 7, "Claire"));
		
		Shipment toRemove = new Ship(12478, 115, "Constanta", 3, 2, "St Helen");
		
		assertTrue(stHelen.equals(toRemove));
		
		x.removeMember(toRemove);
		
		assertTrue(x.getShipments().size() == 3);
	}
	
	@Test
	void testUpdateVehicle() {
		LogisticsCompany x = new LogisticsCompany("Alpha");
		x.addMember(new Truck(10256, 5, "London", 3, "Tom" ));
		Shipment stHelen = new Ship(12478, 115, "Constanta", 3, 2, "St Helen"); 
		x.addMember(stHelen);
		x.addMember(new Plane(10245, 2, "Cairo", 5, "Lucas", 7));
		x.addMember(new Ship(10789, 200, "Calais", 115, 7, "Claire"));
		
		x.updateMember(stHelen, 10);
		
		assertEquals(10, stHelen.getETA());
		
		x.printReport();
	}
}
