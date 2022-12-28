package screen;

import entity.Driver;
import payload.Response;
import service.DriverService;
import service.impl.DriverServiceImpl;

import java.util.Scanner;

public class DriversRegistration {

    String ch;
    Scanner sc = new Scanner(System.in);

    public Response registrationForm(){

        Driver driver = new Driver();

        System.out.println("========================================================");
        System.out.print("Please enter driver's Individual Number : ");

        ch = sc.nextLine();
        driver.setIndiNumber(ch);

        System.out.println("Individual Number : " + ch);
        System.out.println(">");
        System.out.print("Please enter driver's Name : ");

        ch = sc.nextLine();
        driver.setName(ch);

        System.out.println("Name : " + ch);
        System.out.println(">");
        System.out.print("Please enter driver's Surname : ");

        ch = sc.nextLine();
        driver.setSurname(ch);

        System.out.println("Surname : " + ch);
        System.out.println(">");
        System.out.print("Please enter driver's Email : ");

        ch = sc.nextLine();
        driver.setEmail(ch);

        System.out.println("Email : " + ch);
        System.out.println(">");
        System.out.print("Please enter driver's Vehicle Type : ");

        ch = sc.nextLine();
        driver.setVehicleType(ch);

        System.out.println("Vehicle Type : " + ch);
        System.out.println(">");
        System.out.print("Please enter driver's Base Fare Price : ");

        ch = sc.nextLine();
        driver.setBaseFarePrice(ch);

        System.out.println("Base Fare Price : " + ch);
        System.out.println(">");
        System.out.print("Please enter driver's Base Fare Distance : ");

        ch = sc.nextLine();
        driver.setBaseFareDistance(ch);

        System.out.println("Base Fare Distance : " + ch);
        System.out.println(">");
        System.out.print("Processing...");

        DriverService driverService = new DriverServiceImpl();
        return driverService.registration(driver);

    }

}
