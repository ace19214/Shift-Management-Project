package shift.management.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shift.management.entity.UserShift;
import shift.management.service.UserShiftService;
import shift.management.util.Constant;
import shift.management.util.URL;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(URL.API)
public class UserShiftController {
    private static final Logger logger = Logger.getLogger(UserShiftController.class);

    @Autowired
    UserShiftService userShiftService;

    //get list request
    @PostMapping(URL.INSERT_APPROVE)
    public ResponseEntity insertApproveRequest (@RequestParam String username,@RequestParam int shiftID, @RequestParam int registerID){
        logger.info(Constant.BEGIN_CONTROLLER + "insertApproveRequest");
        try {

            return new ResponseEntity(userShiftService.insertApproveShift(username,shiftID, registerID), HttpStatus.OK);
        }catch (Exception ex){
            logger.error(ex);
            return new ResponseEntity(ex.getMessage(), HttpStatus.OK);
        }finally {
            logger.info(Constant.END_CONTROLLER + "insertApproveRequest");
        }
    }

    //take attendance
    @PutMapping(URL.TAKE_ATTENDANCE)
    public ResponseEntity takeAttendance (@RequestParam String username,@RequestParam int shiftID, @RequestParam String startWork){
        logger.info(Constant.BEGIN_CONTROLLER + "takeAttendance");
        try {

            return new ResponseEntity(userShiftService.takeAttendance(username,shiftID, startWork), HttpStatus.OK);
        }catch (Exception ex){
            logger.error(ex);
            return new ResponseEntity(ex.getMessage(), HttpStatus.OK);
        }finally {
            logger.info(Constant.END_CONTROLLER + "takeAttendance");
        }
    }

    //take attendance
    @PostMapping(URL.FINISH_SHIFT_COMPUTE_SALARY)
    public ResponseEntity finishShiftAndComputeSalary (@RequestParam String username, @RequestParam int scheduleID,@RequestParam int shiftID, @RequestParam String finishWork){
        logger.info(Constant.BEGIN_CONTROLLER + "finishShiftAndComputeSalary");
        try {
            return new ResponseEntity(userShiftService.finishShiftAndComputeSalary(username,scheduleID, shiftID, finishWork), HttpStatus.OK);
        }catch (Exception ex){
            logger.error(ex);
            return new ResponseEntity(ex.getMessage(), HttpStatus.OK);
        }finally {
            logger.info(Constant.END_CONTROLLER + "finishShiftAndComputeSalary");
        }
    }

    //find user_shift by date
    @GetMapping(URL.USER_SHIFT_DATE)
    public ResponseEntity<?> findUserShiftByDate(@RequestParam String dateStr){
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
            return ResponseEntity.ok(userShiftService.findUserShiftByDate(date));
        } catch (Exception ex) {
            logger.error(ex);
            return new ResponseEntity(ex.getMessage(), HttpStatus.OK);
        }finally {
            logger.info(Constant.END_CONTROLLER + "findUserShiftByDate");
        }
    }

    @PutMapping(URL.TAKE_ATTENDANCE2)
    public ResponseEntity<?> takeAttendance2 (@RequestParam int userShiftId){
        logger.info(Constant.BEGIN_CONTROLLER + "takeAttendance2");
        try {

            return ResponseEntity.ok(userShiftService.takeAttendance2(userShiftId));
        }catch (Exception ex){
            logger.error(ex);
            return new ResponseEntity(ex.getMessage(), HttpStatus.OK);
        }finally {
            logger.info(Constant.END_CONTROLLER + "takeAttendance2");
        }
    }


    @GetMapping(URL.LIST_USER_SHIFT)
    public ResponseEntity<?> getListUserShift (@RequestParam int shiftId){
        logger.info(Constant.BEGIN_CONTROLLER + "getListUserShift");
        try {

            return ResponseEntity.ok(userShiftService.getAllUserByShiftId(shiftId));
        }catch (Exception ex){
            logger.error(ex);
            return new ResponseEntity(ex.getMessage(), HttpStatus.OK);
        }finally {
            logger.info(Constant.END_CONTROLLER + "getListUserShift");
        }
    }
}
