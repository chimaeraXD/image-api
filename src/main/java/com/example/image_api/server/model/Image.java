package com.example.image_api.server.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.io.Serializable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * Image
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.6.0")
public class Image implements Serializable {

  private static final long serialVersionUID = 1L;

  private String metaData;

  public Image metaData(String metaData) {
    this.metaData = metaData;
    return this;
  }

  /**
   * Get metaData
   * @return metaData
  */
  
  @Schema(name = "metaData", example = "image metadata", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("metaData")
  public String getMetaData() {
    return metaData;
  }

  public void setMetaData(String metaData) {
    this.metaData = metaData;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Image image = (Image) o;
    return Objects.equals(this.metaData, image.metaData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(metaData);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Image {\n");
    sb.append("    metaData: ").append(toIndentedString(metaData)).append("\n");
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

