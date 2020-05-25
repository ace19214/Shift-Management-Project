package shift.management.imp.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shift.management.entity.RegisterShift;
import shift.management.entity.Schedule;
import shift.management.entity.Shift;
import shift.management.entity.User;
import shift.management.repository.*;
import shift.management.response.RegisterShiftResponse;
import shift.management.response.RequestShiftReponse;
import shift.management.service.RegisterShiftService;
import shift.management.util.Constant;
import shift.management.util.DateUtil;
import shift.management.util.Message;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class RegisterShiftServiceImp implements RegisterShiftService{

    private static final Logger logger = Logger.getLogger(RegisterShiftServiceImp.class);

    @Autowired
    RegisterShiftRepository registerShiftRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ShiftRepository shiftRepository;

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    UserShiftRepository userShiftRepository;



    @Override
    public RegisterShiftResponse register(String username, int shiftID) throws Exception {
        logger.info(Constant.BEGIN_SERVICE + "register");
        try {
            User user = userRepository.findByUsername(username);
            if(user == null){
                throw new Exception(Message.ACCOUNT_NOT_FOUND);
            }
            Shift shift = shiftRepository.findById(shiftID);
            if(shift == null){
                throw new Exception(Message.SHIFT_NOT_FOUND);
            }
            Date date = new Date();
            RegisterShift registerShift = new RegisterShift();
            registerShift.setDate(DateUtil.convertUtilToSql(date));
            registerShift.setShiftID(shift.getId());
            registerShift.setUserID(username);
            registerShift.setStatus(Constant.ENABLE);
            registerShiftRepository.save(registerShift);

            RegisterShiftResponse response = new RegisterShiftResponse();
            RegisterShift reShift = registerShiftRepository.findTopByOrderByIdDesc();
            if(reShift == null){
                throw new Exception(Message.SHIFT_NOT_FOUND);
            }
            Schedule schedule = scheduleRepository.findById(shift.getScheduleID());
            if(schedule == null){
                throw new Exception(Message.SCHEDULE_NOT_FOUND);
            }

            response.setDateRegister(DateUtil.DateFormatterTime(registerShift.getDate()));
            response.setDateSchedule(DateUtil.DateFormatterTime(schedule.getDate()));
            response.setUsername(username);
            response.setName(user.getName());
            response.setStart(shift.getStart().toString());
            response.setFinish(shift.getFinish().toString());
            response.setRegisterID(reShift.getId());

            return response;

        }finally {
            logger.info(Constant.END_SERVICE + "register");
        }
    }

    @Override
    public List<RequestShiftReponse> listRequest(int shiftID) throws Exception {
        logger.info(Constant.BEGIN_SERVICE + "listRequest");
        try {
            Shift shift = shiftRepository.findById(shiftID);
            if(shift == null){
                throw new Exception(Message.SHIFT_NOT_FOUND);
            }
            List<RegisterShift> listRegisterShift = registerShiftRepository.findAllByShiftIDAndStatus(shiftID, Constant.ENABLE);
            if(listRegisterShift.isEmpty()){
                List<RequestShiftReponse> response = new ArrayList<>();
                return response;
            }
            List<RequestShiftReponse> response = new ArrayList<>();
            for(int i = 0; i < listRegisterShift.size(); i++){
                RequestShiftReponse dto = new RequestShiftReponse();
                User user = userRepository.findByUsername(listRegisterShift.get(i).getUserID());
                dto.setUsername(user.getUsername());
                dto.setName(user.getName());
                dto.setRole(user.getRole());
                dto.setSlot(shift.getSlot());
                dto.setDateRegister(DateUtil.DateFormatterTime(listRegisterShift.get(i).getDate()));
                dto.setCountSlot(userShiftRepository.countByShiftID(shiftID));
                response.add(dto);
            }
            return response;
        }finally {
            logger.info(Constant.END_SERVICE + "listRequest");
        }
    }

    @Override
    public boolean disapproveRequest(String username,int shiftID) throws Exception {
        logger.info(Constant.BEGIN_SERVICE + "disapproveRequest");
        try {
            RegisterShift registerShift = registerShiftRepository.findByUserIDAndShiftIDAndStatus(username, shiftID, Constant.ENABLE);
            if(registerShift == null){
                throw new Exception(Message.REGISTER_SHIFT_NULL);
            }
            registerShift.setStatus(Constant.DISABLE);
            registerShiftRepository.save(registerShift);
            return true;

        }finally {
            logger.info(Constant.END_SERVICE + "disapproveRequest");
        }
    }



}
