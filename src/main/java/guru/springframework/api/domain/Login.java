package guru.springframework.api.domain;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Login implements Serializable {

    private static final long serialVersionUID = 772368935L;

    String username;
    String password;
    String md5;
    String sha1;
    String sha256;

}
