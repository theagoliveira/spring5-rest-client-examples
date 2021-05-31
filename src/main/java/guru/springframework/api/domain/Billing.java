package guru.springframework.api.domain;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Billing implements Serializable {

    private static final long serialVersionUID = 764660578L;

    Card card;

}
