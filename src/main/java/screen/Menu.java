package screen;

import entity.Driver;
import entity.Trip;
import org.apache.commons.lang3.StringUtils;
import payload.Response;
import service.DriverService;
import service.FareService;
import service.impl.DriverServiceImpl;
import service.impl.FareServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    String ch;
    Scanner sc = new Scanner(System.in);

    public void displayMenu(){
        System.out.println("========================================================");
        System.out.println("1. Registration          ");
        System.out.println("2. Driver List           ");
        System.out.println("3. Check Fare (Cheapest) ");
        System.out.println("4. Fare Summary          ");
        System.out.println("5. Exit                  ");
        System.out.println("========================================================");
        System.out.println();
    }

    public boolean selectAction()
    {
        DriverService driverService = new DriverServiceImpl();
        FareService fareService = new FareServiceImpl();

        DriversFare driversFare = new DriversFare();
        Response response = new Response();

        List<Driver> driverList;

        System.out.println("Please Enter Your Selection : ");
        ch = sc.nextLine();

        if(!checkValidInput(ch)){
            ch = "0";
        }

        switch (Integer.parseInt(ch)){
            case 1:
                System.out.println("Redirecting to Registration...");
                System.out.println();

                DriversRegistration driversRegistration = new DriversRegistration();
                response = driversRegistration.registrationForm();

                if(response.isSuccess()){
                    System.out.println("Driver Registered Successfully!");
                } else {
                    System.out.println(response.getErrorMessage());
                }

                return true;
            case 2:
                System.out.println("Redirecting to Driver List...");
                System.out.println();

                DriversProfile driversProfile = new DriversProfile();
                Driver driver;

                driverList = driverService.getDriverList();
                driversProfile.displayDriverList(driverList);
                driver = driversProfile.chooseDriver(driverList);
                driversProfile.displayDriverInfo(driver);
                driversProfile.selectAction(driver);

                return true;
            case 3:
                driverList = driverService.getDriverList();
                Trip trip = fareService.getTrip();
                driverList = fareService.calculateDriversFare(driverList, trip);
                driversFare.displayCheapestFare(driverList);

                return true;
            case 4:
                List<Driver> driverWithFareList = new ArrayList<>();
                driverWithFareList = fareService.getFareSummary();
                driversFare.displayFareSummary(driverWithFareList);

                return true;
            case 5:
                System.out.println("Bye bye...");

                return false;
            default:
                System.out.println("Invalid Selection!");
                selectAction();

                return true;
        }
    }

    public boolean checkValidInput(String input){

        return !StringUtils.isBlank(input) && StringUtils.isNumeric(input);
    }
}
