package com.donesvad.scenario;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

import com.donesvad.action.CarsAction;
import com.donesvad.configuration.SpringConfiguration;
import com.donesvad.mock.CarsMock;
import com.donesvad.util.TestContext;
import com.github.tomakehurst.wiremock.common.ConsoleNotifier;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.Filter;
import io.restassured.filter.log.ResponseLoggingFilter;
import java.util.LinkedList;
import java.util.List;
import lombok.extern.apachecommons.CommonsLog;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

@CommonsLog
@SpringBootTest(classes = SpringConfiguration.class)
public abstract class BaseTest {

  @RegisterExtension
  protected static WireMockExtension wme =
      WireMockExtension.newInstance()
          .options(wireMockConfig().notifier(new ConsoleNotifier(false)))
          .proxyMode(true)
          .build();

  @Autowired protected CarsAction carsAction;
  @Autowired protected CarsMock carsMock;
  @Autowired protected TestContext testContext;

  @BeforeAll
  public static void setup(@Autowired Environment env) {
    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    List<Filter> filters = new LinkedList<>();
    filters.add(new AllureRestAssured());
    if (Boolean.parseBoolean(env.getProperty("log.rest-assured-responses", "false"))) {
      filters.add(new ResponseLoggingFilter());
    }
    RestAssured.filters(filters);
    RestAssured.useRelaxedHTTPSValidation();
  }

  @AfterAll
  public static void tearDown() {
    wme.shutdownServer();
  }

  @BeforeEach
  public void init() {}

  @AfterEach
  public void cleanUp() {
    wme.resetMappings();
  }
}
