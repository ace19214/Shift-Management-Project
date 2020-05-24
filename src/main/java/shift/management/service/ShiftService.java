package shift.management.service;

import shift.management.model.ShiftModel;
import shift.management.response.CreateShiftResponse;

import java.util.List;

public interface ShiftService {

    public List<CreateShiftResponse> createShift(List<ShiftModel> list) throws Exception;
}
