package com.donesvad.rest.service;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;

import com.donesvad.rest.dto.GetCarManufacturersResponse;
import com.donesvad.rest.dto.GetMakesOfCarsResponse;
import com.donesvad.rest.enums.ResponseFormat;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.ParamConfig;
import io.restassured.config.ParamConfig.UpdateStrategy;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import jakarta.annotation.PostConstruct;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CarsService {

  public static final String getAllMakesPath = "/getallmakes";
  public static final String getAllManufacturers = "/getallmanufacturers";
  public static final String formatParam = "format";

  private RequestSpecification requestSpec;

  @Value("${cars-service-rest.host}")
  private String host;

  @Value("${cars-service-rest.port:#{null}}")
  private Integer port;

  @Value("${cars-service-rest.protocol}")
  private String protocol;

  @Value("${cars-service-rest.base-path}")
  private String basePath;

  @PostConstruct
  public void init() {
    String baseUri = protocol + "://" + host;
    if (port != null) {
      baseUri += ":" + port;
    }
    requestSpec =
        new RequestSpecBuilder()
            .log(LogDetail.URI)
            .log(LogDetail.METHOD)
            .log(LogDetail.BODY)
            .setContentType(ContentType.JSON)
            .setAccept(ContentType.JSON)
            .setBaseUri(baseUri)
            .setBasePath(basePath)
            .setConfig(
                config()
                    .paramConfig(
                        ParamConfig.paramConfig()
                            .queryParamsUpdateStrategy(UpdateStrategy.REPLACE)))
            .build();
  }

  public Response getMakesOfCarsResponse(ResponseFormat format) {
    return given(requestSpec).queryParam(formatParam, format).get(getAllMakesPath);
  }

  public GetMakesOfCarsResponse getMakesOfCars(ResponseFormat format) {
    return getMakesOfCarsResponse(format)
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .body()
        .as(GetMakesOfCarsResponse.class);
  }

  public Response getCarManufacturersResponse(ResponseFormat format) {
    return given(requestSpec.queryParam(formatParam, format)).get(getAllManufacturers);
  }

  public GetCarManufacturersResponse getCarManufacturers(ResponseFormat format) {
    return getCarManufacturersResponse(format)
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .body()
        .as(GetCarManufacturersResponse.class);
  }
}
