package shift.management.service;

import shift.management.entity.UserShift;
import shift.management.response.ApproveRequestResponse;
import shift.management.response.RequestShiftReponse;

import java.util.List;

public interface UserShiftService {
    boolean insertApproveShift(String username, int shiftID, int registerShiftID)throws Exception;
}
