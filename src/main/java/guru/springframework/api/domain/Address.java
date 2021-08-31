package guru.springframework.api.domain;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address implements Serializable {

    private static final long serialVersionUID = 100328337L;

    String street;
    String suite;
    String city;
    String zipcode;
    Geo geo;

}
