package shift.management.response;

public class ApproveRequestResponse {
    private String username;
    private String name;
    private String role;
    private int slot;
    private int countSlot;
    private String dateRegister;

    public ApproveRequestResponse() {
    }

    public ApproveRequestResponse(String username, String name, String role, int slot, int countSlot, String dateRegister) {
        this.username = username;
        this.name = name;
        this.role = role;
        this.slot = slot;
        this.countSlot = countSlot;
        this.dateRegister = dateRegister;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public int getCountSlot() {
        return countSlot;
    }

    public void setCountSlot(int countSlot) {
        this.countSlot = countSlot;
    }

    public String getDateRegister() {
        return dateRegister;
    }

    public void setDateRegister(String dateRegister) {
        this.dateRegister = dateRegister;
    }
}
