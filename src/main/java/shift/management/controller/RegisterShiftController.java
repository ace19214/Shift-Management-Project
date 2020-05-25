package shift.management.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shift.management.model.ShiftModel;
import shift.management.service.RegisterShiftService;
import shift.management.util.Constant;
import shift.management.util.URL;

import java.util.List;

@RestController
@RequestMapping(URL.API)
public class RegisterShiftController {
    private static final Logger logger = Logger.getLogger(RegisterShiftController.class);

    @Autowired
    RegisterShiftService registerShiftService;

    //register shift
    @PostMapping(URL.REGISTER)
    public ResponseEntity registerShift (@RequestParam String username, @RequestParam int shiftID){
        logger.info(Constant.BEGIN_CONTROLLER + "registerShift");
        try {

            return new ResponseEntity(registerShiftService.register(username, shiftID), HttpStatus.OK);
        }catch (Exception ex){
            logger.error(ex);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }finally {
            logger.info(Constant.END_CONTROLLER + "registerShift");
        }
    }

    //get list request
    @GetMapping(URL.LIST_REQUEST)
    public ResponseEntity getListRequest (@RequestParam int shiftID){
        logger.info(Constant.BEGIN_CONTROLLER + "getListRequest");
        try {

            return new ResponseEntity(registerShiftService.listRequest(shiftID), HttpStatus.OK);
        }catch (Exception ex){
            logger.error(ex);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }finally {
            logger.info(Constant.END_CONTROLLER + "getListRequest");
        }
    }
}
