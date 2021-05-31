package guru.springframework.api.domain;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExpirationDate implements Serializable {

    private static final long serialVersionUID = 621359296L;

    String date;
    Integer timezoneType;
    String timezone;

}
