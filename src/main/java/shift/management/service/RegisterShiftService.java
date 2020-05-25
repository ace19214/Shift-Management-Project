package shift.management.service;
import shift.management.response.RegisterShiftResponse;
import shift.management.response.RequestShiftReponse;

import java.util.List;

public interface RegisterShiftService {

    RegisterShiftResponse register(String username, int shiftID) throws Exception;

    List<RequestShiftReponse> listRequest(int shiftID)throws Exception;

    boolean disapproveRequest(String username, int shiftID)throws Exception;
}
