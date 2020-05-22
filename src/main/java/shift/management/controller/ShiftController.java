package shift.management.controller;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shift.management.util.URL;

@RestController
@RequestMapping(URL.API)
public class ShiftController {
    private static final Logger logger = Logger.getLogger(ShiftController.class);
}
