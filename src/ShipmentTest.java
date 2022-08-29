import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShipmentTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testNegativeLoadException() {
		
		try {
			new Plane(10245, -2, "Cairo", 5, "Lucas", 7);
		} catch(NegativeLoadException nl) {
			return;
		} catch(NegativeETAException ne) {
			fail("Negative eta exception where we should have a negative load exception");
		} catch(Exception e) {
			fail("Generic exception where we should have a negative load exception");
		} 
	
		fail("No exception thrown for negative load.");
	}
	
	@Test
	void testNegativeETAException() {
		
		try {
			new Plane(11258, 3, "Madrid", -2, "John", 5);;
		} catch(NegativeLoadException nl) {
			fail("Negative load exception where we should have a negative ETA exception");
		} catch(NegativeETAException ne) {
			return;
		} catch(Exception e) {
			fail("Generic exception where we should have a negative ETA exception");
		}
		fail("No exception thrown for negative ETA.");
	}

	@Test
	void testValidInstance() {
		try {
			new Truck(10256, 5, "London", 3, "Tom" );
		} catch(NegativeLoadException nl) { //executes when you have a throw new NegativeLoadException
			fail("Negative load exception for valid instance");
		} catch(NegativeETAException ne) {
			fail("Negative eta exception for valid instance");
		} catch(Exception e) {
			fail("Exception for valid instance");
		} 
	}
}

