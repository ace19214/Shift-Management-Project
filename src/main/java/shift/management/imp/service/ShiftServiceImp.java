package shift.management.imp.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shift.management.entity.Schedule;
import shift.management.entity.Shift;
import shift.management.model.ShiftModel;
import shift.management.model.UpdateShiftModel;
import shift.management.repository.ScheduleRepository;
import shift.management.repository.ShiftRepository;
import shift.management.response.ScheduleShiftResponse;
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
    public List<ScheduleShiftResponse> createShift(List<ShiftModel> list) throws Exception {
        logger.info(Constant.BEGIN_SERVICE + "createShift");
        try {

            Schedule schedule = scheduleService.createSchedule(list.get(0).getDate(), list.get(0).getBonusRate());
            Schedule schedule1 = scheduleRepository.findByDate(schedule.getDate());
            for(int i =0 ; i < list.size(); i++){
                Shift shift = new Shift(0, Time.valueOf(list.get(i).getStart()), Time.valueOf(list.get(i).getFinish()),list.get(i).getSlot() ,schedule1.getId());
                shiftRepository.save(shift);
            }
            List<Shift> shiftList = shiftRepository.getAllShiftByScheduleID(schedule1.getId());
            List<ScheduleShiftResponse> scheduuleShiftRespons = new ArrayList<>();
            for(int i = 0; i < shiftList.size(); i++){
                ScheduleShiftResponse shiftResponse = new ScheduleShiftResponse();
                shiftResponse.setShiftID(shiftList.get(i).getId());
                shiftResponse.setBonusRate(schedule1.getBonusRate());
                shiftResponse.setDate(schedule1.getDate());
                shiftResponse.setScheduleID(shiftList.get(i).getScheduleID());
                shiftResponse.setStart(shiftList.get(i).getStart().toString());
                shiftResponse.setFinish(shiftList.get(i).getFinish().toString());
                shiftResponse.setSlot(shiftList.get(i).getSlot());

                scheduuleShiftRespons.add(shiftResponse);
            }
            return scheduuleShiftRespons;
        }finally {
            logger.info(Constant.END_SERVICE + "createShift");
        }
    }

    @Override
    public List<ScheduleShiftResponse> updateShift(List<UpdateShiftModel> list) throws Exception {
        logger.info(Constant.BEGIN_SERVICE + "updateShift");
        try {
            if(list.isEmpty()){
                throw new Exception(Message.LIST_UPDATE_SCHEDULE_SHIFT_EMPTY);
            }
            Schedule schedule = scheduleService.updateSchedule(list.get(0).getScheduleID(), list.get(0).getBonusRate());
            List<ScheduleShiftResponse> responses = new ArrayList<>();
            for(int i = 0; i < list.size(); i++){
                Shift shift = shiftRepository.findById(list.get(i).getShiftID());
                shift.setStart(Time.valueOf(list.get(i).getStart()));
                shift.setFinish(Time.valueOf(list.get(i).getFinish()));
                shift.setSlot(list.get(i).getSlot());

                shiftRepository.save(shift);

                ScheduleShiftResponse dto = new ScheduleShiftResponse();
                dto.setScheduleID(schedule.getId());
                dto.setBonusRate(schedule.getBonusRate());
                dto.setDate(schedule.getDate());
                dto.setShiftID(shift.getId());
                dto.setStart(shift.getStart().toString());
                dto.setFinish(shift.getFinish().toString());
                dto.setSlot(shift.getSlot());

                responses.add(dto);

            }
            return responses;
        }finally {
            logger.info(Constant.END_SERVICE + "updateShift");
        }
    }

    @Override
    public List<Shift> getListShiftBySchedule(Date date) throws Exception {
        logger.info(Constant.BEGIN_SERVICE + "getListShiftBySchedule");
        try {
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            List<Shift> result = new ArrayList<>();
            Schedule schedule = scheduleRepository.findByDate(sqlDate);
            if(schedule == null){
                throw new Exception(Message.SCHEDULE_NOT_FOUND);
            }
            result = shiftRepository.findAllByScheduleID(schedule.getId());
            return result;
        }finally {
            logger.info(Constant.END_SERVICE + "getListShiftBySchedule");
        }
    }
}
