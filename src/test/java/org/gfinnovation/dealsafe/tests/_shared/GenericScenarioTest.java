package org.gfinnovation.dealsafe.tests._shared;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.slf4j.MDC;

import java.util.UUID;

public abstract class GenericScenarioTest {

    @BeforeEach
    public void setUp() {
        MDC.put("userId", UUID.randomUUID().toString());
        MDC.put("whitelabelId", UUID.randomUUID().toString());
        MDC.put("requestId", UUID.randomUUID().toString());
    }

    @AfterEach
    public void setDown() {
        MDC.clear();
    }
}