package shift.management.service;

import shift.management.entity.Schedule;

import java.util.Date;

public interface ScheduleService {

    public Schedule createSchedule(String date, float bonusRate)throws Exception;
}
