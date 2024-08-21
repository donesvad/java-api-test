package com.donesvad.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigInteger;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@JsonInclude
@RequiredArgsConstructor
@Getter
@ToString
public class Make {

  @JsonProperty("Make_ID")
  private final BigInteger makeId;

  @JsonProperty("Make_Name")
  private final String makeName;
}
