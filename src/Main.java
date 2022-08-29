


public class Main {

	public static void main(String[] args) throws Exception {
		
		LogisticsCompany x = new LogisticsCompany("Alpha");
		x.addMember(new Truck(10256, 5, "London", 3, "Tom" ));
		x.addMember(new Ship(12478, 115, "Constanta", 3, 2, "St Helen"));
		x.addMember(new Plane(10245, 2, "Cairo", 5, "Lucas", 7));
		x.addMember(new Ship(10789, 200, "Calais", 115, 7, "Claire"));
		x.addMember(new Plane(11258, 3, "Madrid", 2, "John", 5));
		x.addMember(new Ship(11100, 100, "Peru", 20, 3, "Maria"));
		
		x.createCSVFile("Alpha");
		//x.printReport();
		
		LogisticsCompany z = new LogisticsCompany("Omega");
		z.readCSVFile("Alpha");
		z.printReport();
	}
}