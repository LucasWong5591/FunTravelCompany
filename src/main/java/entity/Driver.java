package entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Driver extends Fare{

    private String indiNumber;
    private String name;
    private String surname;
    private String email;
    private String vehicleType;
    private String baseFarePrice;
    private String baseFareDistance;

}
