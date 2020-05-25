package shift.management.imp.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shift.management.entity.Schedule;
import shift.management.repository.ScheduleRepository;

import shift.management.service.ScheduleService;
import shift.management.util.Constant;
import shift.management.util.DateUtil;
import shift.management.util.Message;

import java.util.Date;
import java.util.List;

@Service
public class ScheduleServiceImp implements ScheduleService {
    private static final Logger logger = Logger.getLogger(ScheduleServiceImp.class);

    @Autowired
    ScheduleRepository scheduleRepository;


    @Override
    public Schedule createSchedule(String date, float bonusRate) throws Exception {
        logger.info(Constant.BEGIN_SERVICE + "createSchedule");
        try {
            Schedule schedule1 = scheduleRepository.findByDate(DateUtil.convertUtilToSql(DateUtil.convertStringToDate(date)));
            if(schedule1 != null){
                throw new Exception(Message.SCHEDULE_EXIST);
            }
            Schedule schedule = new Schedule(0, DateUtil.convertUtilToSql(DateUtil.convertStringToDate(date)), bonusRate);
            scheduleRepository.save(schedule);
            return schedule;
        }finally {
            logger.info(Constant.END_SERVICE + "createSchedule");
        }
    }

    @Override
    public Schedule updateSchedule(int id, float bonusRate) throws Exception {
        logger.info(Constant.BEGIN_SERVICE + "updateSchedule");
        try {
            Schedule schedule = scheduleRepository.findById(id);
            if(schedule == null){
                throw new Exception(Message.SCHEDULE_NOT_EXIST);
            }
            schedule.setBonusRate(bonusRate);
            scheduleRepository.save(schedule);

            return schedule;
        }finally {
            logger.info(Constant.END_SERVICE + "updateSchedule");
        }
    }

    @Override
    public List<Schedule> getListSchedule() throws Exception {
        logger.info(Constant.BEGIN_SERVICE + "getListSchedule");
        try {
            return scheduleRepository.findAll();
        }finally {
            logger.info(Constant.END_SERVICE + "getListSchedule");
        }
    }
}
