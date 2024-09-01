package com.donesvad.action;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import com.donesvad.rest.dto.GetCarManufacturersResponse;
import com.donesvad.rest.dto.GetMakesOfCarsResponse;
import com.donesvad.rest.dto.Make;
import com.donesvad.rest.dto.Manufacturer;
import com.donesvad.rest.service.CarsService;
import java.math.BigInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarsAction {

  @Autowired protected CarsService carsService;

  public static Make getMake(GetMakesOfCarsResponse makesOfCarsResponse, BigInteger makeId) {
    return makesOfCarsResponse.getResults().stream()
        .filter(make -> make.getMakeId().equals(makeId))
        .findFirst()
        .orElseThrow(() -> new AssertionError("Make not found"));
  }

  public static Manufacturer getManufacturer(
      GetCarManufacturersResponse manufacturersResponse, BigInteger manifacturerId) {
    return manufacturersResponse.getResults().stream()
        .filter(make -> make.getManufacturerId().equals(manifacturerId))
        .findFirst()
        .orElseThrow(() -> new AssertionError("Manufacturer not found"));
  }

  public GetMakesOfCarsResponse getMakesOfCarsResponse() {
    return carsService.getMakesOfCars();
  }

  public GetCarManufacturersResponse getCarManufacturersResponse() {
    return carsService.getCarManufacturers();
  }

  public void assertMakesOfCars(GetMakesOfCarsResponse makesOfCarsResponse) {
    int resultSize = makesOfCarsResponse.getResults().size();
    assertThat("Makes of cars list is empty", resultSize, greaterThan(1));
    assertThat(
        "Count is not equal to the size of the list",
        makesOfCarsResponse.getCount(),
        equalTo(resultSize));
    assertThat(
        "Message is not equal to the expected one",
        makesOfCarsResponse.getMessage(),
        equalTo("Response returned successfully"));
    assertThat("Search criteria is not null", makesOfCarsResponse.getSearchCriteria(), nullValue());
    makesOfCarsResponse
        .getResults()
        .forEach(
            make -> {
              assertThat(make.getMakeId(), greaterThanOrEqualTo(BigInteger.ONE));
              assertThat(make.getMakeName(), not(emptyOrNullString()));
            });
  }

  public void assertMakeOfCars(Make actualMake, Make expectedMake) {
    assertThat(
        "Make ID is not equal to the expected one",
        actualMake.getMakeId(),
        equalTo(expectedMake.getMakeId()));
    assertThat(
        "Make name is not equal to the expected one",
        actualMake.getMakeName(),
        equalTo(expectedMake.getMakeName()));
  }

  public void assertCarManufacturers(GetCarManufacturersResponse carManufacturersResponse) {
    int resultSize = carManufacturersResponse.getResults().size();
    assertThat("Car manufacturers list is empty", resultSize, greaterThan(1));
    assertThat(
        "Count is not equal to the size of the list",
        carManufacturersResponse.getCount(),
        equalTo(resultSize));
    assertThat(
        "Message is not equal to the expected one",
        carManufacturersResponse.getMessage(),
        equalTo("Response returned successfully"));
    assertThat(
        "Search criteria is not equal to the expected one",
        carManufacturersResponse.getSearchCriteria(),
        nullValue());
    carManufacturersResponse
        .getResults()
        .forEach(
            manufacturer -> {
              assertThat(manufacturer.getManufacturerId(), greaterThanOrEqualTo(BigInteger.ONE));
              assertThat(manufacturer.getManufacturerName(), not(emptyOrNullString()));
              assertThat(manufacturer.getCountry(), not(emptyOrNullString()));
            });
  }

  public void assertCarManufacturer(
      Manufacturer actualManufacturer, Manufacturer expectedManufacturer) {
    assertThat(
        "Manufacturer ID is not equal to the expected one",
        actualManufacturer.getManufacturerId(),
        equalTo(expectedManufacturer.getManufacturerId()));
    assertThat(
        "Manufacturer name is not equal to the expected one",
        actualManufacturer.getManufacturerName(),
        equalTo(expectedManufacturer.getManufacturerName()));
    assertThat(
        "Manufacturer common name is not equal to the expected one",
        actualManufacturer.getManufacturerCommonName(),
        equalTo(expectedManufacturer.getManufacturerCommonName()));
    assertThat(
        "Manufacturer country is not equal to the expected one",
        actualManufacturer.getCountry(),
        equalTo(expectedManufacturer.getCountry()));
    assertThat("Vehicle types list is empty", actualManufacturer.getVehicleTypes(), not(empty()));
  }
}
