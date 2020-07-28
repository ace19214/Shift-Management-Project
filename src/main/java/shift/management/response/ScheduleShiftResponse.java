package shift.management.response;

import lombok.Data;
import org.joda.time.DateTime;

import java.sql.Date;

@Data
public class ScheduleShiftResponse {
    private int scheduleID;
    private int shiftID;
    private Date date;
    private float bonusRate;
    private String start;
    private String finish;
    private int slot;
}
