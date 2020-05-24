package shift.management.imp.service;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shift.management.entity.Schedule;
import shift.management.entity.Shift;
import shift.management.model.ShiftModel;
import shift.management.repository.ScheduleRepository;
import shift.management.repository.ShiftRepository;
import shift.management.response.CreateShiftResponse;
import shift.management.service.ScheduleService;
import shift.management.service.ShiftService;
import shift.management.util.Constant;
import shift.management.util.DateUtil;
import shift.management.util.Message;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ShiftServiceImp implements ShiftService {
    private static final Logger logger = Logger.getLogger(ShiftServiceImp.class);

    @Autowired
    ShiftRepository shiftRepository;
    @Autowired
    ScheduleService scheduleService;
    @Autowired
    ScheduleRepository scheduleRepository;


    @Override
    public List<CreateShiftResponse> createShift(List<ShiftModel> list) throws Exception {
        logger.info(Constant.BEGIN_SERVICE + "createShift");
        try {
//            DateTime dateTime = DateUtil.getCurrentDateTime();
//            Date date = new Date();

            Schedule schedule = scheduleService.createSchedule(list.get(0).getDate(), list.get(0).getBonusRate());
            Schedule schedule1 = scheduleRepository.findByDate(DateUtil.convertUtilToSql(schedule.getDate()));
            for(int i =0 ; i < list.size(); i++){
                Shift shift = new Shift(0, Time.valueOf(list.get(i).getStart()), Time.valueOf(list.get(i).getFinish()),list.get(i).getSlot() ,schedule1.getId());
                shiftRepository.save(shift);
            }
            List<Shift> shiftList = shiftRepository.getAllShiftByScheduleID(schedule1.getId());
            List<CreateShiftResponse> createShiftResponses = new ArrayList<>();
            for(int i = 0; i < shiftList.size(); i++){
                CreateShiftResponse shiftResponse = new CreateShiftResponse();
                shiftResponse.setShiftID(shiftList.get(i).getId());
                shiftResponse.setBonusRate(schedule1.getBonusRate());
                shiftResponse.setDate(DateUtil.DateFormatterTime(schedule1.getDate()));
                shiftResponse.setScheduleID(shiftList.get(i).getScheduleID());
                shiftResponse.setStart(shiftList.get(i).getStart().toString());
                shiftResponse.setFinish(shiftList.get(i).getFinish().toString());
                shiftResponse.setSlot(shiftList.get(i).getSlot());

                createShiftResponses.add(shiftResponse);
            }
            return createShiftResponses;
        }finally {
            logger.info(Constant.END_SERVICE + "createShift");
        }
    }
}
