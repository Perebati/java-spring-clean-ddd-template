package org.gfinnovation.dealsafe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/*
TODO: Encapsulate UserID and CompanyID on o single authentication object to pass to repository layer: UPDATE: DONE!
TODO: Create proper error objects that inherits one another. UPDATE: DONE!
TODO: Documentation.
 */

@SpringBootApplication
@EnableAsync
public class DealSafeApplication {

    public static void main(String[] args) {
        SpringApplication.run(DealSafeApplication.class, args);
    }

}
