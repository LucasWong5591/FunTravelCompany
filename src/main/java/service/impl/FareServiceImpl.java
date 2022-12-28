package service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import constant.DataSource;
import entity.Driver;
import entity.Trip;
import service.FareService;
import utilities.FileUtil;

import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FareServiceImpl implements FareService {

    FileUtil fileUtil = new FileUtil();

    public Trip getTrip (){

        Trip trip = new Trip();

        String tripsData = "";

        tripsData = fileUtil.read(DataSource.TRIP_RECORD);

        if(!tripsData.isEmpty()){

            List<String> rawList = Arrays.asList(tripsData.split(","));

            trip.setDistanceTraveled(Double.parseDouble(rawList.get(0)));
            trip.setTraveledUnit(Double.parseDouble(rawList.get(1)));
            trip.setCostPerDistanceTraveled(new BigDecimal(rawList.get(2)));
        }

        return trip;
    }

    public BigDecimal calculateFare (Trip trip, BigDecimal baseFare, Double baseDistance){

        BigDecimal totalCost = baseFare;
        BigDecimal extraDistanceCost = new BigDecimal(0);
        double extraDistance = (double) 0;

        extraDistance = trip.getDistanceTraveled() - baseDistance;

        if(extraDistance > 0){

            BigDecimal multiplicand = new BigDecimal(extraDistance/trip.getTraveledUnit());

            extraDistanceCost = extraDistanceCost.add(trip.getCostPerDistanceTraveled().multiply(multiplicand));

            totalCost = totalCost.add(extraDistanceCost);
        }

        return totalCost;
    }

    public List<Driver> calculateDriversFare (List<Driver> driverList, Trip trip){

        for (Driver driver : driverList) {

            BigDecimal baseFare = new BigDecimal(driver.getBaseFarePrice());
            double baseDistance = Double.parseDouble(driver.getBaseFareDistance());

            driver.setTotalCost(calculateFare(trip, baseFare, baseDistance));
            driver.setDistanceTraveled(trip.getDistanceTraveled());
            driver.setTraveledUnit(trip.getTraveledUnit());
            driver.setCostPerDistanceTraveled(trip.getCostPerDistanceTraveled());
        }

        try{

            final StringWriter sw =new StringWriter();
            final ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(sw, driverList);

            fileUtil.write(DataSource.DRIVERS_RECORD, sw.toString());

        } catch (IOException e){
            e.printStackTrace();
        }

        return driverList;
    }

    public List<Driver> getFareSummary(){

        ObjectMapper objectMapper = new ObjectMapper();

        List<Driver> driverWithFareList = new ArrayList<>();
        String driversData = "";

        FileUtil fileUtil = new FileUtil();
        driversData = fileUtil.read(DataSource.FARE_SUMMARY);

        if(!driversData.isEmpty()){

            try {

                driverWithFareList = objectMapper.readValue(driversData, new TypeReference<List<Driver>>() {});

            } catch (JsonProcessingException e){
                e.printStackTrace();
            }

        }

        return driverWithFareList;

    }

    public void updateFareSummary(List<Driver> driverList, Trip trip){

        List<Driver> updatedDriverList = new ArrayList<>();
        updatedDriverList = calculateDriversFare(driverList, trip);

        saveSummary(driverList);

    }

    public Boolean saveSummary (List<Driver> driverList){

        try{

            final StringWriter sw =new StringWriter();
            final ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(sw, driverList);

            fileUtil.write(DataSource.FARE_SUMMARY, sw.toString());

        } catch (IOException e){
            e.printStackTrace();
        }

        return true;
    }
}
