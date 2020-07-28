package shift.management.model;

import lombok.Data;
import org.joda.time.DateTime;

import java.sql.Date;
@Data

public class ShiftModel {
    private String start;
    private String finish;
    private int slot;
    private float bonusRate;
    private Date date;
}
