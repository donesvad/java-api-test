package com.donesvad.scenario;

import static com.donesvad.action.CarsAction.getMake;
import static com.donesvad.action.CarsAction.getManufacturer;

import com.donesvad.rest.dto.GetCarManufacturersResponse;
import com.donesvad.rest.dto.GetMakesOfCarsResponse;
import com.donesvad.rest.dto.Make;
import com.donesvad.rest.dto.Manufacturer;
import java.math.BigInteger;
import org.junit.jupiter.api.Test;

public class CarsTest extends BaseTest {

  @Test
  void getMakesOfCarsResponseTest() {
    GetMakesOfCarsResponse makesOfCarsResponse = carsAction.getMakesOfCarsResponse();
    carsAction.assertMakesOfCars(makesOfCarsResponse);
  }

  @Test
  void getSpecificMakeOfCarsTest() {
    Make expectedMake = new Make(BigInteger.valueOf(10005), "357 GOLF CARTS");
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
            .manufacturerCommonName("Tesla")
            .manufacturerId(BigInteger.valueOf(955))
            .country("UNITED STATES (USA)")
            .manufacturerName("TESLA, INC.")
            .build();
    GetCarManufacturersResponse carManufacturersResponse = carsAction.getCarManufacturersResponse();
    Manufacturer actualManufacturer =
        getManufacturer(carManufacturersResponse, expectedManufacturer.getManufacturerId());
    carsAction.assertCarManufacturer(actualManufacturer, expectedManufacturer);
  }
}
