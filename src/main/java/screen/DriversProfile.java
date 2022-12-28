package screen;

import entity.Driver;
import org.apache.commons.lang3.StringUtils;
import payload.Response;
import service.DriverService;
import service.impl.DriverServiceImpl;

import java.util.List;
import java.util.Scanner;

public class DriversProfile {

    String ch;
    Scanner sc = new Scanner(System.in);

    public void displayDriverList(List<Driver> driverList){

        System.out.println("========================================================");

        for(int i = 0; i < driverList.size(); i ++){
            System.out.println(i+1 + ". " + driverList.get(i).getName() + " " + driverList.get(i).getSurname());
        }
        System.out.println("========================================================");
    }

    public Driver chooseDriver(List<Driver> driverList) {

        int tempInput = 0;
        boolean isValid = false;

        do {
            System.out.println("Please Select Driver : ");

            ch = sc.nextLine();
            isValid = checkValidInput(ch);

            if (isValid) {
                tempInput = Integer.parseInt(ch);
                if (tempInput > driverList.size() || tempInput == 0) {
                    System.out.println("Invalid Selection!");
                }

            } else {
                System.out.println("Invalid Selection!");
            }

        } while (tempInput > driverList.size() || tempInput == 0 || !isValid);

        return driverList.get(tempInput - 1);
    }

    public void displayDriverInfo(Driver driver){

        System.out.println("========================================================");
        System.out.println("Individual Number : "  + driver.getIndiNumber());
        System.out.println("Name : "  + driver.getName());
        System.out.println("Surname : "  + driver.getSurname());
        System.out.println("Email : "  + driver.getEmail());
        System.out.println("Vehicle Type : "  + driver.getVehicleType());
        System.out.println("Base Fare Price : "  + driver.getBaseFarePrice());
        System.out.println("Base Dare Distance : "  + driver.getBaseFareDistance());
        System.out.println("========================================================");
        System.out.println("1. Edit          ");
        System.out.println("2. Delete        ");
        System.out.println("3. Exit          ");
        System.out.println("========================================================");
    }

    public void selectAction(Driver driver)
    {
        DriverService driverService = new DriverServiceImpl();

        Response response = new Response();

        System.out.println("Please Enter Your Selection : ");
        ch = sc.nextLine();

        if(!checkValidInput(ch)){
            ch = "0";
        }

        switch (Integer.parseInt(ch)){
            case 1:
                System.out.println("Redirecting to Edit...");
                DriversEdit driversEdit = new DriversEdit();
                response = driversEdit.editForm(driver);

                if(response.isSuccess()){
                    System.out.println("Driver's Details Updated Successfully!");
                } else {
                    System.out.println(response.getErrorMessage());
                }
                break;
            case 2:
                System.out.println("Are you sure you wanna delete driver : " + driver.getName() + "?");
                System.out.println("Enter Y to proceed, N to cancel. ");

                String confirmation = "";
                int index = 0;

                do {

                    confirmation = sc.nextLine();

                    if(index > 0){
                        if(!confirmation.equalsIgnoreCase("Y") && !confirmation.equalsIgnoreCase("N")){
                            System.out.println("Invalid Input!");
                        } else {

                            if(confirmation.equalsIgnoreCase("Y")){
                                response = driverService.deleteDriver(driver);

                                if(response.isSuccess()){
                                    System.out.println("Driver Deleted Successfully!");
                                } else {
                                    System.out.println(response.getErrorMessage());
                                }
                            }

                        }
                    }

                    index++;
                } while (!confirmation.equalsIgnoreCase("Y") && !confirmation.equalsIgnoreCase("N"));

                break;
            case 3:
                System.out.println("Redirecting...");
                break;
            default:
                System.out.println("Invalid Selection!");
                selectAction(driver);
        }
    }

    public boolean checkValidInput(String input){

        return !StringUtils.isBlank(input) && StringUtils.isNumeric(input);
    }
}
