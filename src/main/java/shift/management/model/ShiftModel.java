package shift.management.model;

import org.joda.time.DateTime;

import java.util.Date;

public class ShiftModel {
    private String start;
    private String finish;
    private int slot;
    private float bonusRate;
    private String date;

    public ShiftModel() {
    }

    public ShiftModel(String start, String finish, int slot, float bonusRate) {
        this.start = start;
        this.finish = finish;
        this.slot = slot;
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

    public float getBonusRate() {
        return bonusRate;
    }

    public void setBonusRate(float bonusRate) {
        this.bonusRate = bonusRate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
