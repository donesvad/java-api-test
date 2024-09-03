package com.donesvad.scenario;

import static com.donesvad.action.CarsAction.getMake;
import static com.donesvad.action.CarsAction.getManufacturer;

import com.donesvad.rest.dto.GetCarManufacturersResponse;
import com.donesvad.rest.dto.GetMakesOfCarsResponse;
import com.donesvad.rest.dto.Make;
import com.donesvad.rest.dto.Manufacturer;
import com.donesvad.rest.enums.ResponseFormat;
import java.math.BigInteger;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;

@CommonsLog
public class CarsTest extends BaseTest {

  @Value("${car.make.id}")
  private BigInteger makeId;

  @Value("${car.make.name}")
  private String makeName;

  @Value("${car.manufacturer.id}")
  private BigInteger manufacturerId;

  @Value("${car.manufacturer.name}")
  private String manufacturerName;

  @Value("${car.manufacturer.common-name}")
  private String manufacturerCommonName;

  @Value("${car.manufacturer.country}")
  private String manufacturerCountry;

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
  void getCarsManufacturersResponseTest() {
    GetCarManufacturersResponse carManufacturersResponse = carsAction.getCarManufacturers();
    carsAction.assertCarManufacturers(carManufacturersResponse);
  }

  @Test
  void getSpecificCarManufacturerTest() {
    Manufacturer expectedManufacturer =
        Manufacturer.builder()
            .manufacturerCommonName(manufacturerCommonName)
            .manufacturerId(manufacturerId)
            .country(manufacturerCountry)
            .manufacturerName(manufacturerName)
            .build();
    GetCarManufacturersResponse carManufacturersResponse = carsAction.getCarManufacturers();
    Manufacturer actualManufacturer =
        getManufacturer(carManufacturersResponse, expectedManufacturer.getManufacturerId());
    carsAction.assertCarManufacturer(actualManufacturer, expectedManufacturer);
  }

  @Test
  void getMakesOfCarsYamlResponseTest() {
    carsMock.stubGetAllMakers(ResponseFormat.XML, HttpStatus.SC_INTERNAL_SERVER_ERROR);

    carsAction
        .getMakesOfCarsResponse(ResponseFormat.XML)
        .then()
        .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
  }
}
