package com.donesvad.scenario;

import static com.donesvad.action.CarsAction.getMake;
import static com.donesvad.action.CarsAction.getManufacturer;

import com.donesvad.rest.dto.GetCarManufacturersResponse;
import com.donesvad.rest.dto.GetMakesOfCarsResponse;
import com.donesvad.rest.dto.Make;
import com.donesvad.rest.dto.Manufacturer;
import java.math.BigInteger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;

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
    GetMakesOfCarsResponse makesOfCarsResponse = carsAction.getMakesOfCarsResponse();
    carsAction.assertMakesOfCars(makesOfCarsResponse);
  }

  @Test
  void getSpecificMakeOfCarsTest() {
    Make expectedMake = new Make(makeId, makeName);
    GetMakesOfCarsResponse makesOfCarsResponse = carsAction.getMakesOfCarsResponse();
    Make actualMake = getMake(makesOfCarsResponse, expectedMake.getMakeId());
    carsAction.assertMakeOfCars(actualMake, expectedMake);
  }

  @Test
  void getCarsManufacturersResponseTest() {
    GetCarManufacturersResponse carManufacturersResponse = carsAction.getCarManufacturersResponse();
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
    GetCarManufacturersResponse carManufacturersResponse = carsAction.getCarManufacturersResponse();
    Manufacturer actualManufacturer =
        getManufacturer(carManufacturersResponse, expectedManufacturer.getManufacturerId());
    carsAction.assertCarManufacturer(actualManufacturer, expectedManufacturer);
  }
}
