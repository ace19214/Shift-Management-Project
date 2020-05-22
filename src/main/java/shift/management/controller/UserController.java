package shift.management.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shift.management.entity.User;
import shift.management.service.UserService;
import shift.management.util.Constant;
import shift.management.util.URL;

@RestController
@RequestMapping(URL.API)
public class UserController {

    private static final Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    UserService userService;

    //create account
    @PostMapping(URL.CREATE_ACCOUNT)
    public ResponseEntity createAccount (@RequestBody User user){
        logger.info(Constant.BEGIN_CONTROLLER + "createAccount");
        try {

            return new ResponseEntity(userService.createAccount(user), HttpStatus.OK);
        }catch (Exception ex){
            logger.error(ex);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }finally {
            logger.info(Constant.END_CONTROLLER + "createAccount");
        }
    }

}
