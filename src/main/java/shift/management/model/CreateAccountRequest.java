package shift.management.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@AllArgsConstructor
@Data
public class CreateAccountRequest implements Serializable {
    String username;
    String password;
    String name;
    String phoneNumber;
    String email;
    boolean sex;
    int age;
    String role;
    String status;
    float weight;
}
