package shift.management.imp.service;

import org.apache.log4j.Logger;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shift.management.entity.*;
import shift.management.repository.*;
import shift.management.response.ApproveRequestResponse;
import shift.management.response.RegisterShiftResponse;
import shift.management.response.RequestShiftReponse;
import shift.management.service.RegisterShiftService;
import shift.management.service.UserShiftService;
import shift.management.util.Constant;
import shift.management.util.DateUtil;
import shift.management.util.Message;

import java.sql.Time;
import java.util.Date;
import java.util.List;

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
    public boolean insertApproveShift(String username, int shiftID, int registerShiftID) throws Exception {
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

            return true;

        }finally {
            logger.info(Constant.END_SERVICE + "insertApproveShift");
        }
    }

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
    public boolean finishShiftAndComputeSalary(String username,int scheduleID, int shiftID, String finishWork) throws Exception{
        logger.info(Constant.BEGIN_SERVICE + "finishShiftAndComputeSalary");
        try {
            UserShift userShift = userShiftRepository.findByUserIDAndShiftID(username, shiftID);
            if(userShift == null){
                throw new Exception(Message.USER_SHIFT_NOT_FOUND);
            }
            userShift.setFinishWork(Time.valueOf(finishWork));
            Schedule schedule = scheduleRepository.findById(scheduleID);
            if(schedule == null){
                throw new Exception(Message.SCHEDULE_NOT_FOUND);
            }
            Salary salary = salaryRepository.findById(userShift.getSalaryID());
            if(salary == null){
                throw new Exception(Message.SALARY_NOT_FOUND);
            }
            int hour = userShift.getFinishWork().getHours() - userShift.getStartWork().getHours();
            int minutes = userShift.getFinishWork().getMinutes() - userShift.getStartWork().getMinutes();

            float wagesHour = hour * salary.getSalary();
            float wagesMinutes = (salary.getSalary() / 60) * minutes;

            float wages = wagesHour + wagesMinutes;
            userShift.setWages(Math.round(wages));
            userShiftRepository.save(userShift);
            return true;
        }finally{
            logger.info(Constant.END_SERVICE + "finishShiftAndComputeSalary");
        }
    }
}
