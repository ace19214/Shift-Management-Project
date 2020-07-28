package shift.management.imp.service;

import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import shift.management.entity.Schedule;
import shift.management.entity.Shift;
import shift.management.entity.User;
import shift.management.entity.UserShift;
import shift.management.mapper.UserMapper;
import shift.management.model.CreateAccountRequest;
import shift.management.model.LoginRequest;
import shift.management.repository.ShiftRepository;
import shift.management.repository.UserRepository;
import shift.management.repository.UserShiftRepository;
import shift.management.service.ScheduleService;
import shift.management.service.UserService;
import shift.management.util.Constant;
import shift.management.util.DateUtil;
import shift.management.util.Message;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImp implements UserService {
    private static final Logger logger = Logger.getLogger(UserServiceImp.class);
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ScheduleService scheduleService;
    private final ShiftRepository shiftRepository;
    private final UserShiftRepository userShiftRepository;
    private final UserMapper userMapper;

    @Override
    public User createAccount(CreateAccountRequest account) throws Exception {
        logger.info(Constant.BEGIN_SERVICE + "createAccount");
        try {
            User user = null;
            if(userRepository.findById(account.getUsername()).isPresent()){
                throw new Exception(Message.ACCOUNT_EXIST);
            }else if(account.getRole().equals(Constant.MANAGER)){
                throw new Exception(Message.ROLE_INVALID);
            }
            else{
                user = userMapper.requestToUser(account);
                user.setStatus(Constant.ACTIVE);
                Date date = new Date();
                user.setDate(DateUtil.convertUtilToSql(date));
                user.setWeight(1);
                user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
                user.setRole(user.getRole());
                userRepository.save(user);
            }
            return user;
        }finally {
            logger.info(Constant.END_SERVICE + "createAccount");
        }
    }

    @Override
    public User updateAccount(User user) throws Exception {
        logger.info(Constant.BEGIN_SERVICE + "updateAccount");
        try {
            if(!userRepository.findById(user.getUsername()).isPresent()){
                throw new Exception(Message.ACCOUNT_NOT_FOUND);
            }
            else{
                User accountCompare = userRepository.findByUsername(user.getUsername());
                if(user.getPassword().equals(accountCompare.getPassword())){
                    user.setPassword(accountCompare.getPassword());
                }else if(bCryptPasswordEncoder.matches(user.getPassword(), accountCompare.getPassword())){
                    user.setPassword(accountCompare.getPassword());
                }
                else{
                    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
                }
                user.setDate(accountCompare.getDate());
                userRepository.save(user);
            }
            return user;
        }
        finally {
            logger.info(Constant.END_SERVICE + "updateAccount");
        }
    }

    @Override
    public List<User> searchByUsernameAndName(String keyword) {
        logger.info(Constant.BEGIN_SERVICE + "searchByUsernameAndName");
        try {
            List<User> result = new ArrayList<>();
            return userRepository.findByUsernameContainingOrNameContaining(keyword, keyword);
        }
        finally {
            logger.info(Constant.END_SERVICE + "searchByUsernameAndName");
        }
    }

    @Override
    public List<User> getListAccount() {
        logger.info(Constant.BEGIN_SERVICE + "getListAccount");
        try {
            List<User> result = new ArrayList<>();
            result =  userRepository.findAll();
            return result;
        }
        finally {
            logger.info(Constant.END_SERVICE + "getListAccount");
        }
    }

    @Override
    public User getTotalSalary(Date fromDate, Date toDate, String username) {
        User user = userRepository.findByUsername(username);
        try {
            List<Schedule> scheduleList =  scheduleService.getListSchedule();
            List<Schedule> schedulesFilter = scheduleList.stream().filter(schedule -> schedule.getDate().getTime() >= fromDate.getTime()
                    && schedule.getDate().getTime() <= toDate.getTime()
            ).collect(Collectors.toList());
            List<Shift> shiftList = new ArrayList<>();
            for (Schedule schedule: schedulesFilter) {
                List<Shift> temp = shiftRepository.findAllByScheduleID(schedule.getId());
                shiftList.addAll(temp);
            }
            List<UserShift> userShiftList = new ArrayList<>();
            for (Shift shift :shiftList) {
                List<UserShift> temp = userShiftRepository.findAllByShiftID(shift.getId());
                userShiftList.addAll(temp);
            }
            float total = 0f;
            for (UserShift userShift: userShiftList) {
                if(userShift.getUserID().equals(user.getUsername())){
                    total += userShift.getWages();
                }
            }
            user.setTotalSalary(total);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }

        return user;
    }

    public User checkLogin(LoginRequest loginRequest){
        User user = userRepository.findByUsername(loginRequest.getUsername());
        if(Objects.nonNull(user)
                && bCryptPasswordEncoder.matches(loginRequest.getPassword(),user.getPassword())) {
            return user;
        }
        return null;
    }

    public User checkManagerLogin(LoginRequest loginRequest){
        User user = userRepository.findByUsername(loginRequest.getUsername());
        if(Objects.nonNull(user)
                && bCryptPasswordEncoder.matches(loginRequest.getPassword(),user.getPassword())
                && user.getRole().equals(Constant.MANAGER)){
            return user;
        }
        return null;
    }
}
