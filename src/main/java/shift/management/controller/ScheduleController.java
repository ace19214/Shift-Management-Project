package shift.management.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shift.management.service.ScheduleService;
import shift.management.util.Constant;
import shift.management.util.URL;

@RestController
@RequestMapping(URL.API)
public class ScheduleController {
    private static final Logger logger = Logger.getLogger(ScheduleController.class);

    @Autowired
    ScheduleService scheduleService;

    //get list schedule
    @GetMapping(URL.GET_LIST_SCHEDULE)
    public ResponseEntity getListSchedule (){
        logger.info(Constant.BEGIN_CONTROLLER + "getListSchedule");
        try {

            return new ResponseEntity(scheduleService.getListSchedule(), HttpStatus.OK);
        }catch (Exception ex){
            logger.error(ex);
            return new ResponseEntity(ex.getMessage(), HttpStatus.OK);
        }finally {
            logger.info(Constant.END_CONTROLLER + "getListSchedule");
        }
    }
}
