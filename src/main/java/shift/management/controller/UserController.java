package shift.management.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
            return new ResponseEntity(ex.getMessage(), HttpStatus.OK);
        }finally {
            logger.info(Constant.END_CONTROLLER + "createAccount");
        }
    }

    //update account
    @PutMapping(URL.UPDATE_ACCOUNT)
    public ResponseEntity updateAccount (@RequestBody User user){
        logger.info(Constant.BEGIN_CONTROLLER + "updateAccount");
        try {

            return new ResponseEntity(userService.updateAccount(user), HttpStatus.OK);
        }catch (Exception ex){
            logger.error(ex);
            return new ResponseEntity(ex.getMessage(), HttpStatus.OK);
        }finally {
            logger.info(Constant.END_CONTROLLER + "updateAccount");
        }
    }

    //search by username or name
    @GetMapping(URL.SEARCH_ACCOUNT)
    public ResponseEntity searchByUsernameOrName (@RequestParam String keyword){
        logger.info(Constant.BEGIN_CONTROLLER + "searchByUsernameOrName");
        try {

            return new ResponseEntity(userService.searchByUsernameAndName(keyword), HttpStatus.OK);
        }catch (Exception ex){
            logger.error(ex);
            return new ResponseEntity(ex.getMessage(), HttpStatus.OK);
        }finally {
            logger.info(Constant.END_CONTROLLER + "searchByUsernameOrName");
        }
    }

    @GetMapping(URL.LIST_ACCOUNT)
    public ResponseEntity getListAccount (){
        logger.info(Constant.BEGIN_CONTROLLER + "getListAccount");
        try {

            return new ResponseEntity(userService.getListAccount(), HttpStatus.OK);
        }catch (Exception ex){
            logger.error(ex);
            return new ResponseEntity(ex.getMessage(), HttpStatus.OK);
        }finally {
            logger.info(Constant.END_CONTROLLER + "getListAccount");
        }
    }

}
