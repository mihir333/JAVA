package tester;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.iacsd.core.Color;
import com.iacsd.core.Vehicle;

import custom_exceptions.InvalidInputException;
import custom_ordering.VehiclePriceComparator;

import static utils.ShowroomValidations.*;
import static utils.ShowroomUtils.*;

public class ShowroomManagement {

	public static void main(String[] args) {
		// typical template for the tester
		// init phase of the app
		try (Scanner sc = new Scanner(System.in)) {
			// create suitable D.S
			List<Vehicle> showroom = populateShowroom();
			// up casting , size=6,initCapa=10
			boolean exit = false;
			while (!exit) {
				System.out.println("Options : 1 . Add a Vehicle\n" + "2. Display all \n" + "3. Get Vehicle Details\n "
						+ "4. Apply discount\n " + "5. Apply discount on old vehicles\n "
						+ "6. Remove specific vehicle \n 7. Remove vehicles by color \n "
						+ "8. Sort vehicles as per ch no \n 9. Sort vehicle details as per price\n"
						+ " 10. Sort vehicle details as per Date n price\n 0.Exit");
				try {
					switch (sc.nextInt()) {
					case 1: // add a vehicle
						System.out.println(
								"Enter vehicle details : chasisNo,  vehicleColor,  price,  manufactureDate,	company");
						Vehicle vehicle = validateAllInputs(sc.next(), sc.next(), sc.nextDouble(), sc.next(), sc.next(),
								showroom);
						// => success
						showroom.add(vehicle);
						System.out.println("vehicle added....");
						break;
					case 2:
						System.out.println("Showroom details");
						for (Vehicle v : showroom)
							System.out.println(v);
						break;
					case 3:
						System.out.println("Enter chasis no , to get vehicle details");
						System.out.println(findByChasisNo(sc.next(), showroom));
						break;
					case 4:
						System.out.println("Enter chasis no n discount");
						vehicle = findByChasisNo(sc.next(), showroom);
						// => vehicle found
						vehicle.setPrice(vehicle.getPrice() - sc.nextDouble());
						System.out.println("Applied discount !");
						break;
					case 5:
						System.out.println("Enter date n discount");

						LocalDate date = parseAndValidateDate(sc.next());
						double discount = sc.nextDouble();
						for (Vehicle v : showroom)
							if (v.getManufactureDate().isBefore(date))
								v.setPrice(v.getPrice() - discount);
						System.out.println("applied discount ....");
						break;

					case 6:
						System.out.println("Enter ch no");
						boolean removed = showroom.remove(new Vehicle(sc.next()));
						if (removed)
							System.out.println("vehicle details removed");
						else
							throw new InvalidInputException("Vehicle details can't be removed : invalid ch no!!!!");
						break;
					case 7:
						System.out.println("Enter color");
						Color c = parseAndValidateColor(sc.next());
//						for (Vehicle v : showroom)
//							if (v.getVehicleColor() == c)
//								showroom.remove(v);
						Iterator<Vehicle> vehicleItr=showroom.iterator();
						while(vehicleItr.hasNext())
						{
							if(vehicleItr.next().getVehicleColor()==c)
								vehicleItr.remove();
						}
						System.out.println("deleted vehicle details");
						break;
					case 8:
						// Collections.sort(List<T> list);
						// JVM : TimSort ---sorting algo will call internally
						// [v1,v2,v3....v100]
						// Vehicle's compareTo --> v1.compareTo(v2) --> <0 or 0 : no shuffling
						// > 0 => shuffle --continues....
						Collections.sort(showroom);
						break;
					case 9:// Sort vehicle details as per price
						Collections.sort(showroom, new VehiclePriceComparator());
						break;
					case 10:// Sort vehicle details as per Date n price
						// new Comparator<Vehicle>() => instance of ano innner class that imple
						// Comparator
						Collections.sort(showroom, new Comparator<Vehicle>() {
							@Override
							public int compare(Vehicle o1, Vehicle o2) {
								System.out.println("in compare : date n price");
								// date based
								int ret = o1.getManufactureDate().compareTo(o2.getManufactureDate());
								if(ret == 0)
								{
									//dates are same , now sort as per price
									if(o1.getPrice()<o2.getPrice())
										return -1;
									if(o1.getPrice()==o2.getPrice())
										return 0;
									return 1;									
								}
								return ret;
							}

						});
						break;

					case 0:
						exit = true;
						break;
					}
				} catch (Exception e) {
			//		if(e instanceof InputMismatchException)
						sc.nextLine();
					e.printStackTrace();
				}
			}
		}

	}

}
