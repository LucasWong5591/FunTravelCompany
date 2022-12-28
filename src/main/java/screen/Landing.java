package screen;

import entity.Driver;
import entity.Trip;
import org.apache.commons.lang3.StringUtils;
import service.DriverService;
import service.FareService;
import service.impl.DriverServiceImpl;
import service.impl.FareServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class Landing {

    public void startApplication(){

        System.out.println("*******************Fun Travel Company*******************");
        System.out.println();

        initialiseFare();

        boolean restart = true;

        Menu menu = new Menu();

        menu.displayMenu();
        restart = menu.selectAction();

        if(restart){
            startApplication();
        }
    }

    public void initialiseFare(){

        FareService fareService = new FareServiceImpl();
        DriverService driverService = new DriverServiceImpl();

        Trip trip = fareService.getTrip();
        List<Driver> driverList = driverService.getDriverList();

        fareService.updateFareSummary(driverList, trip);
    }

}
