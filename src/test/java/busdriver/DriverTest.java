package busdriver;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DriverTest {

	@Test
	void generateGossip() {
		int routes[][] = new int[][] {
				{3, 1, 3, 3},
				{3, 2, 3, 1},
				{4, 2, 4, 4, 5}
		};
		Driver newDrivers = new Driver(routes);

		System.out.println(newDrivers.sendTheDrivers());
	}
}