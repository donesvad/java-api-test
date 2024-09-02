package com.donesvad.scenario;

import com.donesvad.action.CarsAction;
import com.donesvad.configuration.SpringConfiguration;
import com.donesvad.util.TestContext;
import io.restassured.RestAssured;
import lombok.extern.apachecommons.CommonsLog;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@CommonsLog
@SpringBootTest(classes = SpringConfiguration.class)
public abstract class BaseTest {

  @Autowired protected CarsAction carsAction;
  @Autowired protected TestContext testContext;

  @BeforeAll
  public static void setup() {
    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    // RestAssured.filters( new ResponseLoggingFilter()); // log response
  }

  @BeforeEach
  public void init() {}

  @AfterEach
  public void cleanUp() {}
}
