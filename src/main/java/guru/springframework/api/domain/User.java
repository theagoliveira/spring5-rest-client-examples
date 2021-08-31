package guru.springframework.api.domain;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User implements Serializable {

    private static final long serialVersionUID = 952278064L;

    Long id;
    String name;
    String username;
    String email;
    Address address;
    String phone;
    String website;
    Company company;

}
