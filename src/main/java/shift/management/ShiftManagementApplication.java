package shift.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.google.common.collect.Lists;
import org.apache.log4j.Logger;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static springfox.documentation.builders.PathSelectors.regex;

@SpringBootApplication
@EntityScan("shift.management.entity")
@EnableAutoConfiguration
public class ShiftManagementApplication {
	public static final Logger logger = Logger.getLogger(ShiftManagementApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(ShiftManagementApplication.class, args);
	}
}
