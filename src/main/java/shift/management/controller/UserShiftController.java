package shift.management.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shift.management.service.UserShiftService;
import shift.management.util.Constant;
import shift.management.util.URL;

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
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }finally {
            logger.info(Constant.END_CONTROLLER + "insertApproveRequest");
        }
    }
}
