package com.donesvad.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@JsonInclude
@RequiredArgsConstructor
@Getter
@ToString
public class GetMakesOfCarsResponse {

  @JsonProperty("Count")
  private final Integer count;

  @JsonProperty("Message")
  private final String message;

  @JsonProperty("SearchCriteria")
  private final String searchCriteria;

  @JsonProperty("Results")
  private final List<Make> results;
}
