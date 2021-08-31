package guru.springframework.api.domain;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Geo implements Serializable {

    private static final long serialVersionUID = 101226737L;

    String lat;
    String lng;

}
