package com.donesvad.scenario;

import static com.donesvad.action.CarsAction.getMake;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

import com.donesvad.rest.dto.GetMakesOfCarsResponse;
import com.donesvad.rest.dto.Make;
import com.donesvad.rest.enums.ResponseFormat;
import com.github.tomakehurst.wiremock.common.ConsoleNotifier;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import java.math.BigInteger;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.api.parallel.ResourceLock;
import org.springframework.beans.factory.annotation.Value;

@CommonsLog
public class MakesOfCarsTest extends BaseTest {

  @RegisterExtension
  private static final WireMockExtension wme =
      WireMockExtension.newInstance()
          .options(
              wireMockConfig()
                  .notifier(new ConsoleNotifier(false))
                  .dynamicPort()
                  .dynamicHttpsPort())
          .proxyMode(true)
          .build();

  @Value("${car.make.id}")
  private BigInteger makeId;

  @Value("${car.make.name}")
  private String makeName;

  @Test
  void getMakesOfCarsResponseTest() {
    GetMakesOfCarsResponse makesOfCarsResponse = carsAction.getMakesOfCars();
    carsAction.assertMakesOfCars(makesOfCarsResponse);
  }

  @Test
  void getSpecificMakeOfCarsTest() {
    Make expectedMake = new Make(makeId, makeName);
    GetMakesOfCarsResponse makesOfCarsResponse = carsAction.getMakesOfCars();
    Make actualMake = getMake(makesOfCarsResponse, expectedMake.getMakeId());
    carsAction.assertMakeOfCars(actualMake, expectedMake);
  }

  @Test
  @ResourceLock(value = "wiremock")
  void getMakesOfCarsXMLMockedResponseTest() {
    carsMock.stubGetAllMakers(wme, ResponseFormat.XML, HttpStatus.SC_INTERNAL_SERVER_ERROR);

    carsAction
        .getMakesOfCarsResponse(ResponseFormat.XML)
        .then()
        .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
    wme.resetMappings();
  }

  @Test
  @ResourceLock(value = "wiremock")
  void getMakesOfCarsXMLResponseTest() {
    carsAction.getMakesOfCarsResponse(ResponseFormat.XML).then().statusCode(HttpStatus.SC_OK);
  }
}
