package shift.management.imp.service;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shift.management.entity.*;
import shift.management.repository.*;
import shift.management.response.UserOnShiftResponse;
import shift.management.response.UserShiftResponse;
import shift.management.service.UserShiftService;
import shift.management.util.Constant;
import shift.management.util.Message;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class UserShiftServiceImp implements UserShiftService{
    private static final Logger logger = Logger.getLogger(UserShiftServiceImp.class);

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

    @Autowired
    SalaryRepository salaryRepository;


    @Override
    public UserShift insertApproveShift(String username, int shiftID, int registerShiftID) throws Exception {
        logger.info(Constant.BEGIN_SERVICE + "insertApproveShift");
        try {
            User user = userRepository.findByUsername(username);
            if(user == null){
                throw new Exception(Message.ACCOUNT_NOT_FOUND);
            }
            Salary salary = salaryRepository.findByRole(user.getRole());
            if(salary == null){
                throw new Exception(Message.ROLE_INVALID);
            }
            UserShift userShift = new UserShift();
            userShift.setUserID(username);
            userShift.setSalaryID(salary.getId());
            userShift.setShiftID(shiftID);
            userShift.setStatus(Constant.NOT_YET);

            userShiftRepository.save(userShift);

            //update status register shift
            RegisterShift registerShift = registerShiftRepository.findById(registerShiftID);
            if(registerShift == null){
                throw new Exception(Message.REGISTER_SHIFT_NULL);
            }
            registerShift.setStatus(Constant.DISABLE);
            registerShiftRepository.save(registerShift);

            return userShift;

        }finally {
            logger.info(Constant.END_SERVICE + "insertApproveShift");
        }
    }
    //để dành mốt có làm cái "đi trễ về sớm" thì gọi nha
    @Override
    public boolean takeAttendance(String username, int shiftID, String startWork) throws Exception {
        logger.info(Constant.BEGIN_SERVICE + "takeAttendance");
        try {
            UserShift userShift = userShiftRepository.findByUserIDAndShiftID(username, shiftID);
            if(userShift == null){
                throw new Exception(Message.USER_SHIFT_NOT_FOUND);
            }
            userShift.setStatus(Constant.PRESENT);
            userShift.setStartWork(Time.valueOf(startWork));
            userShiftRepository.save(userShift);

            return true;
        }finally{
            logger.info(Constant.END_SERVICE + "takeAttendance");
        }
    }

    @Override
    public UserShift finishShiftAndComputeSalary(String username,int scheduleID, int shiftID, String finishWork) throws Exception{
        logger.info(Constant.BEGIN_SERVICE + "finishShiftAndComputeSalary");
        try {
            User user = userRepository.findByUsername(username);
            if(user == null){
                throw new Exception(Message.ACCOUNT_NOT_FOUND);
            }
            Shift shift = shiftRepository.findById(shiftID);
            if(shift == null){
                throw new Exception(Message.SHIFT_NOT_FOUND);
            }
            UserShift userShift = userShiftRepository.findByUserIDAndShiftID(username, shiftID);
            if(userShift == null){
                throw new Exception(Message.USER_SHIFT_NOT_FOUND);
            }
            userShift.setStartWork(shift.getStart());
            userShift.setFinishWork(Time.valueOf(finishWork));
            Schedule schedule = scheduleRepository.findById(scheduleID);
            if(schedule == null){
                throw new Exception(Message.SCHEDULE_NOT_FOUND);
            }
            Salary salary = salaryRepository.findById(userShift.getSalaryID());
            if(salary == null){
                throw new Exception(Message.SALARY_NOT_FOUND);
            }
            int hour = shift.getFinish().getHours() - shift.getStart().getHours();
            int minutes = shift.getFinish().getMinutes() - shift.getStart().getMinutes();

            float wagesHour = hour * salary.getSalary();
            float wagesMinutes = (salary.getSalary() / 60) * minutes;

            float wages = (wagesHour + wagesMinutes) * user.getWeight() * schedule.getBonusRate();
            userShift.setWages(Math.round(wages));
            userShiftRepository.save(userShift);
            return userShift;
        }finally{
            logger.info(Constant.END_SERVICE + "finishShiftAndComputeSalary");
        }
    }

    @Override
    public List<UserShiftResponse> findUserShiftByDate(Date date) {
        logger.info(Constant.BEGIN_SERVICE + "findUserShiftByDate");
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        Schedule schedule = scheduleRepository.findByDate(sqlDate);
        try{
            if(Objects.nonNull(schedule)){
                List<Shift> shiftList = shiftRepository.findAllByScheduleID(schedule.getId());
                if(ObjectUtils.isNotEmpty(shiftList)){
                    List<UserShiftResponse> userShiftsRes = new ArrayList<>();
                    for (Shift shift : shiftList) {
                        List<UserShift> userShifts= userShiftRepository.findAllByShiftID(shift.getId());
                        for (UserShift userShift : userShifts){
                            userShiftsRes.add(parseToUserShiftResponse(userShift));
                        }
                    }
                    return userShiftsRes;
                }
            }
            return null;
        }finally {
            logger.info(Constant.END_SERVICE + "takeAttendance");
        }
    }

    @Override
    public boolean takeAttendance2(int userShiftId) throws Exception{
        logger.info(Constant.BEGIN_SERVICE + "takeAttendance2");
        try {
            UserShift userShift = userShiftRepository.findById(userShiftId)
                    .orElseThrow(Exception::new);

            userShift.setStatus(Constant.PRESENT);
            userShiftRepository.save(userShift);

            return true;
        }finally{
            logger.info(Constant.END_SERVICE + "takeAttendance2");
        }
    }

    @Override
    public List<UserOnShiftResponse> getAllUserByShiftId(int shiftId) throws Exception {
        logger.info(Constant.BEGIN_SERVICE + "getAllUserByShiftId");
        try {
            Shift shift = shiftRepository.findById(shiftId);
            if(shift == null){
                throw new Exception(Message.SHIFT_NOT_FOUND);
            }
            List<UserOnShiftResponse> response = new ArrayList<>();
            List<UserShift> userShiftList = userShiftRepository.findAllByShiftID(shiftId);
            if(!userShiftList.isEmpty()){
                for(int i = 0; i < userShiftList.size(); i++){
                    UserOnShiftResponse dto = new UserOnShiftResponse();
                    dto.setUserShift(userShiftList.get(i));
                    User user = userRepository.findByUsername(userShiftList.get(i).getUserID());
                    dto.setUser(user);
                    response.add(dto);
                }
            }
            return response;
        }finally{
            logger.info(Constant.END_SERVICE + "getAllUserByShiftId");
        }
    }

    private UserShiftResponse parseToUserShiftResponse(UserShift userShift){
        UserShiftResponse userShiftResponse = new UserShiftResponse();
        userShiftResponse.setId(userShift.getId());
        userShiftResponse.setFinishWork(userShift.getFinishWork());
        userShiftResponse.setSalaryID(userShift.getSalaryID());
        userShiftResponse.setStatus(userShift.getStatus());
        userShiftResponse.setStartWork(userShift.getStartWork());
        userShiftResponse.setUser(userRepository.findByUsername(userShift.getUserID()));
        userShiftResponse.setShift(shiftRepository.findById(userShift.getShiftID()));
        userShiftResponse.setTimeOur(userShift.getTimeOur());
        userShiftResponse.setWages(userShift.getWages());

        return userShiftResponse;
    }
}
