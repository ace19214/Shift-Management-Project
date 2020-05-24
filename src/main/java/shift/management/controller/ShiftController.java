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
import shift.management.model.ShiftModel;
import shift.management.service.ShiftService;
import shift.management.service.UserService;
import shift.management.util.Constant;
import shift.management.util.URL;

import java.util.List;

@RestController
@RequestMapping(URL.API)
public class ShiftController {
    private static final Logger logger = Logger.getLogger(ShiftController.class);

    @Autowired
    ShiftService shiftService;

    //create schedule and shift
    @PostMapping(URL.CREATE_SCHEDULE_SHIFT)
    public ResponseEntity createScheduleAndShift (@RequestBody List<ShiftModel> list){
        logger.info(Constant.BEGIN_CONTROLLER + "createScheduleAndShift");
        try {

            return new ResponseEntity(shiftService.createShift(list), HttpStatus.OK);
        }catch (Exception ex){
            logger.error(ex);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }finally {
            logger.info(Constant.END_CONTROLLER + "createScheduleAndShift");
        }
    }
}
