package shift.management.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@AllArgsConstructor
@Data
public class LoginRequest implements Serializable {
    private String username;
    private String password;
}
