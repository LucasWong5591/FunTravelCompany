package service;

import entity.Driver;
import entity.Trip;

import java.math.BigDecimal;
import java.util.List;

public interface FareService {

    Trip getTrip();

    BigDecimal calculateFare(Trip trip, BigDecimal baseFare, Double baseDistance);

    List<Driver> calculateDriversFare(List<Driver> driverList, Trip trip);

    List<Driver> getFareSummary();

    void updateFareSummary(List<Driver> driverList, Trip trip);
}
