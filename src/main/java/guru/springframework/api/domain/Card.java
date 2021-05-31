package guru.springframework.api.domain;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Card implements Serializable {

    private static final long serialVersionUID = 624606390L;

    String type;
    String number;
    ExpirationDate expirationDate;
    String iban;
    String swift;

}
