package utils;

import java.time.LocalDate;
import java.util.List;

import com.iacsd.core.Color;
import com.iacsd.core.Vehicle;

import custom_exceptions.InvalidInputException;

public class ShowroomValidations {
//add a method to parse n validate color
	public static Color parseAndValidateColor(String clr) throws IllegalArgumentException // optional
	{
		return Color.valueOf(clr.toUpperCase());// throws IllegalArgumentExc
	}

	// add a method to parse n validate manu. date : after 1st Jan 2022
	public static LocalDate parseAndValidateDate(String date) throws InvalidInputException {
		// parsing
		LocalDate manuDate = LocalDate.parse(date);
		// parsing success --> validations
		LocalDate checkDate = LocalDate.of(2022, 1, 1);
		if (manuDate.isBefore(checkDate))
			throw new InvalidInputException("Manu date must be after 1st Jan 2022");
		return manuDate;
	}
	//add a method to check for dups
	//in case of dup vehicle : throw custom exc
	public static void checkForDup(String chNo,List<Vehicle> vehicles) throws InvalidInputException
	{
		//[v1(abc1,....),v2(abc2),v3(abc10),v4(abc5), null,.....null]
		//chNo abc2
		Vehicle newVehicle=new Vehicle(chNo);
		if(vehicles.contains(chNo))
			throw new InvalidInputException("Dup ch no!!!!!");
	}
	

	// add a method to validate all i/ps
	public static Vehicle validateAllInputs(String chasisNo, 
			String vehicleColor, double price, String manufactureDate,
			String company,List<Vehicle> vehicles) throws IllegalArgumentException, InvalidInputException {
		Color color = parseAndValidateColor(vehicleColor);
		LocalDate validatedDate = parseAndValidateDate(manufactureDate);
		checkForDup(chasisNo, vehicles);
		//=> i/ps are valid
		return new Vehicle(chasisNo, color, price, validatedDate, company);
	}
}
