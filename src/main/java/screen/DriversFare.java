package screen;

import entity.Driver;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class DriversFare {

    int ch;
    Scanner sc = new Scanner(System.in);

    public void displayCheapestFare(List<Driver> driverList){

        //Sort by Total Cost
        driverList.sort(Comparator.comparing(Driver::getTotalCost));

        BigDecimal cheapest = driverList.get(0).getTotalCost();

        int count = 0;

        for(Driver driver : driverList){
            if(driver.getTotalCost().compareTo(cheapest) == 0){
                count++;
            }
        }

        System.out.println();
        System.out.println("========================================================");
        System.out.println();
        for (int i = 0; i < count; i ++){
            System.out.println(i+1 + ". Driver : " + driverList.get(i).getName() + " " + driverList.get(i).getSurname() + " ," + "Fare : " + driverList.get(i).getTotalCost());
            System.out.println();
        }
        System.out.println("========================================================");
        System.out.println();
    }

    public void displayFareSummary(List<Driver> driverList){

        if(driverList.size() > 0){

            System.out.println("*********************************************************");
            System.out.println("Distance Traveled : " + driverList.get(0).getDistanceTraveled());
            System.out.println("Traveled Unit : " + driverList.get(0).getTraveledUnit());
            System.out.println("Cost Per Distance Traveled : " + driverList.get(0).getCostPerDistanceTraveled());
            System.out.println("*********************************************************");

            System.out.println("========================================================");
            for(int i = 0; i < driverList.size(); i ++){
                System.out.println("Driver : " + driverList.get(i).getName() + " " + driverList.get(i).getSurname());
                System.out.println("Fare : " + driverList.get(i).getTotalCost());
            }
            System.out.println("========================================================");
            System.out.println();

        } else {
            System.out.println("*********************************************************");
            System.out.println("RECORD NOT FOUND!");
            System.out.println("*********************************************************");
            System.out.println();
        }

    }

}
