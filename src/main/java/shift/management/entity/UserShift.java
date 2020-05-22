package shift.management.entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user_shift")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserShift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "user_id")
    String userID;

    @Column(name = "shift_id")
    int shiftID;

    @Column(name = "time_our")
    int timeOur;

    String status;

    float wages;
}
