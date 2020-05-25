package shift.management.imp.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import shift.management.entity.User;
import shift.management.repository.UserRepository;
import shift.management.service.UserService;
import shift.management.util.Constant;
import shift.management.util.DateUtil;
import shift.management.util.Message;

import java.util.Date;

@Service
public class UserServiceImp implements UserService {
    private static final Logger logger = Logger.getLogger(UserServiceImp.class);
    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User createAccount(User user) throws Exception {
        logger.info(Constant.BEGIN_SERVICE + "createAccount");
        try {
            if(userRepository.findById(user.getUsername()).isPresent()){
                throw new Exception(Message.ACCOUNT_EXIST);
            }else if(user.getRole().equals(Constant.MANAGER)){
                throw new Exception(Message.ROLE_INVALID);
            }
            else{
                user.setStatus(Constant.ACTIVE);
                Date date = new Date();
                user.setDate(DateUtil.convertUtilToSql(date));
                user.setWeight(1);
                user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
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

}
