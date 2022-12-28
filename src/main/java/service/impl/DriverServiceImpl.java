package service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import constant.DataSource;
import constant.Messages;
import entity.Driver;
import org.apache.commons.lang3.StringUtils;
import payload.Response;
import service.DriverService;
import utilities.FileUtil;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class DriverServiceImpl implements DriverService {

    FileUtil fileUtil = new FileUtil();

    public Response registration (Driver tempDriver){

        boolean isDuplicated = false;

        Response response = new Response();

        List<Driver> driverList = getDriverList();

        //Validation
        boolean isValid = checkValid(tempDriver);

        if(isValid){
            for(Driver driver : driverList){
                if(driver.getIndiNumber().equalsIgnoreCase(tempDriver.getIndiNumber())){
                    isDuplicated = true;
                    break;
                }
            }

            if(!isDuplicated){
                driverList.add(tempDriver);
                boolean tempFlag = saveDriver(driverList);

                if(tempFlag){
                    response.setSuccess(true);
                    response.setErrorMessage("");
                } else {
                    response.setSuccess(false);
                    response.setErrorMessage(Messages.GENERAL_ERROR);
                }

            } else {
                response.setSuccess(false);
                response.setErrorMessage(Messages.DUPLICATED);
            }

        } else {
            response.setSuccess(false);
            response.setErrorMessage(Messages.INVALID_INPUT);
        }


        return response;
    }

    public List<Driver> getDriverList (){

        ObjectMapper objectMapper = new ObjectMapper();

        List<Driver> driverList = new ArrayList<>();
        String driversData = "";

        FileUtil fileUtil = new FileUtil();
        driversData = fileUtil.read(DataSource.DRIVERS_RECORD);

        if(!driversData.isEmpty()){

            try {

                driverList = objectMapper.readValue(driversData, new TypeReference<List<Driver>>() {});

            } catch (JsonProcessingException e){
                e.printStackTrace();
            }

        }

        return driverList;
    }

    public Response deleteDriver (Driver driver) {

        Response response = new Response();

        List<Driver> driverList = getDriverList();
        int toRemove = driverList.size() + 1;

        for(int i = 0; i < driverList.size(); i ++){
            if(driver.getIndiNumber().equalsIgnoreCase(driverList.get(i).getIndiNumber())){
                toRemove = i;
                break;
            }
        }

        if(toRemove <= driverList.size()){
            driverList.remove(toRemove);
        }

        boolean tempFlag = saveDriver(driverList);

        if(tempFlag){
            response.setSuccess(true);
            response.setErrorMessage("");
        } else {
            response.setSuccess(false);
            response.setErrorMessage(Messages.GENERAL_ERROR);
        }

        return response;
    }

    public Response editDriver (Driver tempDriver, String indiNumber) {

        Response response = new Response();

        List<Driver> driverList = getDriverList();

        tempDriver.setIndiNumber(indiNumber);

        //Validation
        boolean isValid = checkValid(tempDriver);

        if(isValid){
            for (Driver driver : driverList) {
                //IndiNumber is unique
                if (indiNumber.equalsIgnoreCase(driver.getIndiNumber())) {

                    driver.setName(tempDriver.getName());
                    driver.setSurname(tempDriver.getSurname());
                    driver.setEmail(tempDriver.getEmail());
                    driver.setVehicleType(tempDriver.getVehicleType());
                    driver.setBaseFarePrice(tempDriver.getBaseFarePrice());
                    driver.setBaseFareDistance(tempDriver.getBaseFareDistance());
                    break;
                }
            }

            boolean tempFlag = saveDriver(driverList);

            if(tempFlag){
                response.setSuccess(true);
                response.setErrorMessage("");
            } else {
                response.setSuccess(false);
                response.setErrorMessage(Messages.GENERAL_ERROR);
            }

        } else {
            response.setSuccess(false);
            response.setErrorMessage(Messages.INVALID_INPUT);
        }

        return response;
    }

    public boolean saveDriver (List<Driver> driverList) {

        try{

            final StringWriter sw =new StringWriter();
            final ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(sw, driverList);

            fileUtil.write(DataSource.DRIVERS_RECORD, sw.toString());

        } catch (IOException e){
            e.printStackTrace();
        }

        return true;
    }

    public boolean checkValid(Driver driver){

        if(StringUtils.isBlank(driver.getIndiNumber())){
            System.out.println("getIndiNumber : " + driver.getIndiNumber());
            return false;
        }

        if(StringUtils.isBlank(driver.getName())){
            System.out.println("getName : " + driver.getName());
            return false;
        }

        if(StringUtils.isBlank(driver.getSurname())){
            System.out.println("getSurname : " + driver.getSurname());
            return false;
        }

        if(StringUtils.isBlank(driver.getEmail())){
            System.out.println("getEmail : " + driver.getEmail());
            return false;
        }

        if(StringUtils.isBlank(driver.getVehicleType())){
            System.out.println("getVehicleType : " + driver.getVehicleType());
            return false;
        }

        if(!StringUtils.isNumeric(driver.getBaseFarePrice())){
            System.out.println("Invalid Base Fare Price : " + driver.getBaseFarePrice());
            return false;
        }

        if(!StringUtils.isNumeric(driver.getBaseFareDistance())){
            System.out.println("Invalid Base Fare Distance : " + driver.getBaseFareDistance());
            return false;
        }

        return true;
    }
}
