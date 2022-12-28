package entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Trip {

    private double distanceTraveled;
    private double traveledUnit;
    private BigDecimal costPerDistanceTraveled;

}
