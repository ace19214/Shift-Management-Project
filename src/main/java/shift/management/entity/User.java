package shift.management.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "account")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    String username;

//    @JsonIgnore
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
    @Transient
    private float totalSalary;

}
