package shift.management.service;
import shift.management.response.RegisterShiftResponse;

public interface RegisterShiftService {

    RegisterShiftResponse register(String username, int shiftID) throws Exception;
}
