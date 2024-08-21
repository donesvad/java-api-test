package com.donesvad.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@JsonInclude
@RequiredArgsConstructor
@Getter
@ToString
public class VehicleType {

  @JsonProperty("IsPrimary")
  private final Boolean isPrimary;

  @JsonProperty("Name")
  private final String name;
}
