package screen;

import entity.Driver;
import payload.Response;
import service.DriverService;
import service.impl.DriverServiceImpl;

import java.util.Scanner;

public class DriversEdit {

    String ch;
    Scanner sc = new Scanner(System.in);

    public Response editForm(Driver driver){

        DriverService driverService = new DriverServiceImpl();

        Driver tempDriver = new Driver();

        System.out.println("========================================================");
        System.out.println("Individual Number : " + driver.getIndiNumber());
        System.out.println();
        System.out.println("Current driver's Name : " + driver.getName());
        System.out.print("Please enter driver's Name : ");

        ch = sc.nextLine();
        tempDriver.setName(ch);

        System.out.println("Name : " + driver.getName() + " -> " + ch);
        System.out.println(">");
        System.out.println("Current driver's Surname : " + driver.getSurname());
        System.out.print("Please enter driver's Surname : ");

        ch = sc.nextLine();
        tempDriver.setSurname(ch);

        System.out.println("Surname : " + driver.getSurname() + " -> " + ch);
        System.out.println(">");
        System.out.println("Current driver's Email : " + driver.getEmail());
        System.out.print("Please enter driver's Email : ");

        ch = sc.nextLine();
        tempDriver.setEmail(ch);

        System.out.println("Email : " + driver.getEmail() + " -> " + ch);
        System.out.println(">");
        System.out.println("Current driver's Vehicle Type : " + driver.getVehicleType());
        System.out.print("Please enter driver's Vehicle Type : ");

        ch = sc.nextLine();
        tempDriver.setVehicleType(ch);

        System.out.println("Vehicle Type : " + driver.getVehicleType() + " -> " + ch);
        System.out.println(">");
        System.out.println("Current driver's Base Fare Price : " + driver.getBaseFarePrice());
        System.out.print("Please enter driver's Base Fare Price : ");

        ch = sc.nextLine();
        tempDriver.setBaseFarePrice(ch);

        System.out.println("Base Fare Price : " + driver.getBaseFarePrice() + " -> " + ch);
        System.out.println(">");
        System.out.println("Current driver's Base Fare Distance : " + driver.getBaseFareDistance());
        System.out.print("Please enter driver's Base Fare Distance : ");

        ch = sc.nextLine();
        tempDriver.setBaseFareDistance(ch);

        System.out.println("Base Fare Distance : " + driver.getBaseFareDistance() + " -> " + ch);
        System.out.println(">");
        System.out.println("Processing...");

        return driverService.editDriver(tempDriver, driver.getIndiNumber());

    }

}
