package shift.management.service;

import shift.management.response.UserShiftResponse;

import java.util.Date;
import java.util.List;

public interface UserShiftService {
    boolean insertApproveShift(String username, int shiftID, int registerShiftID)throws Exception;

    boolean takeAttendance(String username, int shiftID, String startWork)throws Exception;

    boolean finishShiftAndComputeSalary(String username,int scheduleID, int shiftID, String finishWork) throws Exception;

    List<UserShiftResponse> findUserShiftByDate(Date date);

    boolean takeAttendance2(int userShiftId)throws Exception;
}
