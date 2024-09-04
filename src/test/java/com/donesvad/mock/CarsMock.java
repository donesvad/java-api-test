package com.donesvad.mock;

import static com.donesvad.rest.service.CarsService.formatParam;
import static com.donesvad.rest.service.CarsService.getAllMakesPath;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;

import com.donesvad.rest.enums.ResponseFormat;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CarsMock {

  @Value("${cars-service-rest.base-path}")
  private String basePath;

  public void stubGetAllMakers(
      WireMockExtension wireMockServer, ResponseFormat format, int status) {
    wireMockServer.stubFor(
        get(urlPathEqualTo(basePath + getAllMakesPath))
            .withQueryParam(formatParam, WireMock.equalTo(format.toString()))
            .willReturn(aResponse().withStatus(status)));
  }
}
