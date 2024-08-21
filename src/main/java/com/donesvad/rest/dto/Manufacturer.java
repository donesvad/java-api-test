package com.donesvad.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigInteger;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@JsonInclude
@RequiredArgsConstructor
@Getter
@ToString
public class Manufacturer {

  @JsonProperty("Country")
  private final String country;

  @JsonProperty("Mfr_CommonName")
  private final String manufacturerCommonName;

  @JsonProperty("Mfr_ID")
  private final BigInteger manufacturerId;

  @JsonProperty("Mfr_Name")
  private final String manufacturerName;

  @JsonProperty("VehicleTypes")
  private final List<VehicleType> vehicleTypes;
}
