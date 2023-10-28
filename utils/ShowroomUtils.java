package utils;

import java.util.ArrayList;
import java.util.List;

import com.iacsd.core.Color;
import com.iacsd.core.Vehicle;

import custom_exceptions.InvalidInputException;
import static java.time.LocalDate.parse;

public class ShowroomUtils {
//add a method to return found vehicle ref or throw exc
	public static Vehicle findByChasisNo(String chNo, List<Vehicle> list) throws InvalidInputException {
		Vehicle vehicle = new Vehicle(chNo);
		int index = list.indexOf(vehicle);
		if (index == -1)
			throw new InvalidInputException("Invalid ch no , Vehicle not found !!!!");
		System.out.println(list.get(index));
		return vehicle;
	}

	// add a static method to return a populate list of vehicles , with sample data
	public static List<Vehicle> populateShowroom() {
		List<Vehicle> vehicles = new ArrayList<>();
		vehicles.add(new Vehicle("abc-1233", Color.WHITE, 456789, parse("2023-05-25"), "Honda"));
		vehicles.add(new Vehicle("abc-1235", Color.RED, 556787, parse("2022-10-20"), "Maruti"));
		vehicles.add(new Vehicle("abc-1238", Color.WHITE, 956789, parse("2023-05-25"), "Mahindra"));
		vehicles.add(new Vehicle("abc-1239", Color.BLACK, 576787, parse("2023-10-21"), "Honda"));
		vehicles.add(new Vehicle("abc-1231", Color.WHITE, 496789, parse("2022-08-20"), "Honda"));
		vehicles.add(new Vehicle("abc-1234", Color.BLUE, 706787, parse("2023-05-25"), "Tata"));
		vehicles.add(new Vehicle("abc-1230", Color.WHITE, 416789, parse("2022-08-20"), "Honda"));
		vehicles.add(new Vehicle("abc-1236", Color.BLUE, 356787, parse("2023-05-25"), "Tata"));
	
		return vehicles;
	}
}
