package shift.management.service;

import shift.management.entity.UserShift;
import shift.management.response.UserOnShiftResponse;
import shift.management.response.UserShiftResponse;

import java.util.Date;
import java.util.List;

public interface UserShiftService {
    UserShift insertApproveShift(String username, int shiftID, int registerShiftID)throws Exception;

    boolean takeAttendance(String username, int shiftID, String startWork)throws Exception;

    UserShift finishShiftAndComputeSalary(String username, int scheduleID, int shiftID, String finishWork) throws Exception;

    List<UserShiftResponse> findUserShiftByDate(Date date);

    boolean takeAttendance2(int userShiftId)throws Exception;

    List<UserOnShiftResponse> getAllUserByShiftId(int shiftId)throws Exception;
}
