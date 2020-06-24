package shift.management.response;

import lombok.Data;
import shift.management.entity.Shift;
import shift.management.entity.User;

import java.io.Serializable;
import java.sql.Time;

@Data
public class UserShiftResponse implements Serializable {
    private int id;
    private int timeOur;
    private String status;
    private float wages;
    private Time startWork;
    private Time finishWork;
    private int salaryID;
    private User user;
    private Shift shift;
}
