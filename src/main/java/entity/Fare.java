package entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Fare {

    private BigDecimal totalCost;
    private double distanceTraveled;
    private double traveledUnit;
    private BigDecimal costPerDistanceTraveled;

}
