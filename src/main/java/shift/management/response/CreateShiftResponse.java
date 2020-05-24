package shift.management.response;

import org.joda.time.DateTime;

import java.util.Date;

public class CreateShiftResponse {
    private int scheduleID;
    private int shiftID;
    private String date;
    private float bonusRate;
    private String start;
    private String finish;
    private int slot;

    public CreateShiftResponse() {
    }

    public CreateShiftResponse(int scheduleID, int shiftID, String date, float bonusRate, String start, String finish, int slot) {
        this.scheduleID = scheduleID;
        this.shiftID = shiftID;
        this.date = date;
        this.bonusRate = bonusRate;
        this.start = start;
        this.finish = finish;
        this.slot = slot;
    }

    public int getScheduleID() {
        return scheduleID;
    }

    public void setScheduleID(int scheduleID) {
        this.scheduleID = scheduleID;
    }

    public int getShiftID() {
        return shiftID;
    }

    public void setShiftID(int shiftID) {
        this.shiftID = shiftID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getBonusRate() {
        return bonusRate;
    }

    public void setBonusRate(float bonusRate) {
        this.bonusRate = bonusRate;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getFinish() {
        return finish;
    }

    public void setFinish(String finish) {
        this.finish = finish;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }
}
