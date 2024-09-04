package com.donesvad.scenario;

import static com.donesvad.action.CarsAction.getManufacturer;

import com.donesvad.rest.dto.GetCarManufacturersResponse;
import com.donesvad.rest.dto.Manufacturer;
import java.math.BigInteger;
import lombok.extern.apachecommons.CommonsLog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;

@CommonsLog
public class CarManufacturerTest extends BaseTest {

  @Value("${car.manufacturer.id}")
  private BigInteger manufacturerId;

  @Value("${car.manufacturer.name}")
  private String manufacturerName;

  @Value("${car.manufacturer.common-name}")
  private String manufacturerCommonName;

  @Value("${car.manufacturer.country}")
  private String manufacturerCountry;

  @Test
  void getCarManufacturersResponseTest() {
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
}
