package com.example.image_api.client.imagaa.model;

import java.net.URI;
import java.util.Objects;
import com.example.image_api.client.imagaa.model.ImageTagResult;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * ImageTagResponse
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-05-30T21:59:48.295358700-05:00[America/Chicago]", comments = "Generator version: 7.6.0")
public class ImageTagResponse {

  private ImageTagResult result;

  public ImageTagResponse result(ImageTagResult result) {
    this.result = result;
    return this;
  }

  /**
   * Get result
   * @return result
  */
  @Valid 
  @Schema(name = "result", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("result")
  public ImageTagResult getResult() {
    return result;
  }

  public void setResult(ImageTagResult result) {
    this.result = result;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ImageTagResponse imageTagResponse = (ImageTagResponse) o;
    return Objects.equals(this.result, imageTagResponse.result);
  }

  @Override
  public int hashCode() {
    return Objects.hash(result);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ImageTagResponse {\n");
    sb.append("    result: ").append(toIndentedString(result)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

