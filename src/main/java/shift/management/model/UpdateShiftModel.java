package shift.management.model;

public class UpdateShiftModel {
    private String start;
    private String finish;
    private int slot;
    private float bonusRate;
    private int scheduleID;
    private int shiftID;

    public UpdateShiftModel() {
    }

    public UpdateShiftModel(String start, String finish, int slot, float bonusRate, int scheduleID, int shiftID) {
        this.start = start;
        this.finish = finish;
        this.slot = slot;
        this.bonusRate = bonusRate;
        this.scheduleID = scheduleID;
        this.shiftID = shiftID;
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
}
