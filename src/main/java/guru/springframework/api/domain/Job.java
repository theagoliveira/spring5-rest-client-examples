package guru.springframework.api.domain;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Job implements Serializable {

    private static final long serialVersionUID = 157914361L;

    String title;
    String company;

}
