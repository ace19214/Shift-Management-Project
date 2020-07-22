package shift.management.service;

import shift.management.entity.Schedule;

import java.sql.Date;
import java.util.List;

public interface ScheduleService {

    public Schedule createSchedule(Date date, float bonusRate)throws Exception;
    
    public Schedule updateSchedule(int id, float bonusRate)throws Exception;

    public List<Schedule> getListSchedule()throws Exception;
}
