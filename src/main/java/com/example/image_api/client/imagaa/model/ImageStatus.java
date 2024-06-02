package com.example.image_api.client.imagaa.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * ImageStatus
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-05-30T21:59:48.295358700-05:00[America/Chicago]", comments = "Generator version: 7.6.0")
public class ImageStatus {

  private String type;

  private String text;

  public ImageStatus type(String type) {
    this.type = type;
    return this;
  }

  /**
   * success / error depending on whether the request was processed successfully
   * @return type
  */
  
  @Schema(name = "type", example = "success", description = "success / error depending on whether the request was processed successfully", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("type")
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public ImageStatus text(String text) {
    this.text = text;
    return this;
  }

  /**
   * human-readable reason why the processing was unsuccessful
   * @return text
  */
  
  @Schema(name = "text", description = "human-readable reason why the processing was unsuccessful", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("text")
  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ImageStatus imageStatus = (ImageStatus) o;
    return Objects.equals(this.type, imageStatus.type) &&
        Objects.equals(this.text, imageStatus.text);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, text);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ImageStatus {\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    text: ").append(toIndentedString(text)).append("\n");
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

