package shift.management.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "register_shift")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class RegisterShift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id")
    private String userID;

    @Column(name = "shift_id")
    private int shiftID;

    private Date date;

    private String status;

}
