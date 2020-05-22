package shift.management.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "account")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    String username;

    String password;

    String name;
    @Column(name = "phone_number")
    String phoneNumber;

    String email;

    boolean sex;

    int age;

    String role;

    String status;

    Date date;

    float weight;


}
