package com.donesvad.rest.service;

import static com.donesvad.rest.enums.ResponseFormat.JSON;
import static io.restassured.RestAssured.given;

import com.donesvad.rest.dto.GetCarManufacturersResponse;
import com.donesvad.rest.dto.GetMakesOfCarsResponse;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CarsService {

  private static final String getAllMakesPath = "/getallmakes";
  private static final String getAllManufacturers = "/getallmanufacturers";
  private static final String formatParam = "format=%s";
  private RequestSpecification requestSpec;

  @Value("${cars-service-rest.host}")
  private String host;

  @Value("${cars-service-rest.port}")
  private Integer port;

  @Value("${cars-service-rest.protocol}")
  private String protocol;

  @Value("${cars-service-rest.context-path}")
  private String contextPath;

  @PostConstruct
  public void init() {
    HashMap<String, String> headers = new HashMap<>();
    headers.put("Content-Type", "application/json");
    headers.put("Accept", "application/json");
    String baseUri = protocol + "://" + host;
    if (port != null) {
      baseUri += ":" + port;
    }
    requestSpec =
        new RequestSpecBuilder()
            .setBaseUri(baseUri)
            .log(LogDetail.URI)
            .log(LogDetail.METHOD)
            .log(LogDetail.BODY)
            .addHeaders(headers)
            .build();
  }

  public GetMakesOfCarsResponse getMakesOfCars() {
    return given(requestSpec)
        .get(contextPath + getAllMakesPath + "?" + String.format(formatParam, JSON))
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .body()
        .as(GetMakesOfCarsResponse.class);
  }

  public GetCarManufacturersResponse getCarManufacturers() {
    return given(requestSpec)
        .get(contextPath + getAllManufacturers + "?" + String.format(formatParam, JSON))
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .body()
        .as(GetCarManufacturersResponse.class);
  }
}
