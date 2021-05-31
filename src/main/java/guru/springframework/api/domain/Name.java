package guru.springframework.api.domain;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Name implements Serializable {

    private static final long serialVersionUID = 988219229L;

    String title;
    String first;
    String last;

}
