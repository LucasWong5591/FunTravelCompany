package service;

import java.util.List;

import entity.Driver;
import payload.Response;

public interface DriverService {

    Response registration(Driver driver);

    List<Driver> getDriverList();

    Response deleteDriver(Driver driver);

    Response editDriver(Driver driver, String indiNumber);
}
