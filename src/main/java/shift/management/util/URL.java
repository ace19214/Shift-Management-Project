package shift.management.util;

public class URL {

    public static final String API = "/api";

    //account controller
    public static final String USER = "/user";
    public static final String CREATE_ACCOUNT = USER + "/create";
    public static final String UPDATE_ACCOUNT = USER + "/update";
    public static final String SEARCH_ACCOUNT = USER + "/search";
    public static final String LIST_ACCOUNT = USER + "/list-account";
    public static final String TOTAL_SALARY = USER + "/total-salary";
    public static final String TOTAL_SALARY_USERS = USER + "/total-salary-users";

    //shift controller
    public static final String SHIFT = "/shift";
    public static final String CREATE_SCHEDULE_SHIFT = SHIFT + "/create";
    public static final String UPDATE_SCHEDULE_SHIFT = SHIFT + "/update";
    public static final String GET_LIST_SHIFT = SHIFT + "/list-shift";

    //registerShift controller
    public static final String REGISTER_SHIFT = "/register";
    public static final String REGISTER = REGISTER_SHIFT + "/shift";
    public static final String LIST_REQUEST = REGISTER_SHIFT + "/list-request";
    public static final String DISAPPRORVE_REQUEST = REGISTER_SHIFT + "/disapprove-request";

    //user shift controller
    public static final String USER_SHIFT = "/shift-request";
    public static final String INSERT_APPROVE = USER_SHIFT + "/approve";
    public static final String TAKE_ATTENDANCE = USER_SHIFT + "/take-attendance";
    public static final String FINISH_SHIFT_COMPUTE_SALARY = USER_SHIFT + "/compute-salary";
    public static final String USER_SHIFT_DATE = USER_SHIFT + "/user-shift-date";
    public static final String TAKE_ATTENDANCE2 = USER_SHIFT + "/take-attendance2";
    public static final String LIST_USER_SHIFT = USER_SHIFT + "/list-user-shift";

    //schedule controller
    public static final String SCHEDULE = "/schedule";
    public static final String GET_LIST_SCHEDULE = SCHEDULE + "/list-schedule";

}
