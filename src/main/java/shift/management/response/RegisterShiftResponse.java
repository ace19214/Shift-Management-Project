package shift.management.response;

public class RegisterShiftResponse {
    private int registerID;
    private String username;
    private String name;
    private String dateRegister;
    private String start;
    private String finish;
    private String dateSchedule;


    public RegisterShiftResponse() {
    }

    public RegisterShiftResponse(int registerID, String username, String name, String dateRegister, String start, String finish, String dateSchedule) {
        this.registerID = registerID;
        this.username = username;
        this.name = name;
        this.dateRegister = dateRegister;
        this.start = start;
        this.finish = finish;
        this.dateSchedule = dateSchedule;
    }

    public int getRegisterID() {
        return registerID;
    }

    public void setRegisterID(int registerID) {
        this.registerID = registerID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateRegister() {
        return dateRegister;
    }

    public void setDateRegister(String dateRegister) {
        this.dateRegister = dateRegister;
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

    public String getDateSchedule() {
        return dateSchedule;
    }

    public void setDateSchedule(String dateSchedule) {
        this.dateSchedule = dateSchedule;
    }
}
