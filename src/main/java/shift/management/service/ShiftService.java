package shift.management.service;

import shift.management.entity.Shift;
import shift.management.model.ShiftModel;
import shift.management.model.UpdateShiftModel;
import shift.management.response.ScheduleShiftResponse;

import java.util.Date;
import java.util.List;

public interface ShiftService {

    public List<ScheduleShiftResponse> createShift(List<ShiftModel> list) throws Exception;

    public List<ScheduleShiftResponse> updateShift(List<UpdateShiftModel> list) throws Exception;

    public List<Shift> getListShiftBySchedule(Date date) throws Exception;
}
