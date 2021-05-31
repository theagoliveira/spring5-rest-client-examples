package guru.springframework.api.domain;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User implements Serializable {

    private static final long serialVersionUID = 952278064L;

    String gender;
    Name name;
    Location location;
    String email;
    Login login;
    String phone;
    Job job;
    Billing billing;
    String language;
    String currency;

}
