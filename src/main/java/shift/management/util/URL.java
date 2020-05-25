package shift.management.util;

public class URL {

    public static final String API = "/api";

    //account controller
     public static final String USER = "/user";
     public static final String CREATE_ACCOUNT = USER + "/create";
     public static final String UPDATE_ACCOUNT = USER + "/update";

     //shift controller
    public static final String SHIFT = "/shift";
    public static final String CREATE_SCHEDULE_SHIFT = SHIFT + "/create";
    public static final String UPDATE_SCHEDULE_SHIFT = SHIFT + "/update";

    //registerShift controller
    public static final String REGISTER_SHIFT = "/register";
    public static final String REGISTER = REGISTER_SHIFT +  "/shift";
    public static final String LIST_REQUEST = REGISTER_SHIFT + "/list-request";
    public static final String DISAPPRORVE_REQUEST = REGISTER_SHIFT +  "/disapprove-request";

    //user shift controller
    public static final String USER_SHIFT = "/shift-request";
    public static final String INSERT_APPROVE = USER_SHIFT + "/approve";

}
