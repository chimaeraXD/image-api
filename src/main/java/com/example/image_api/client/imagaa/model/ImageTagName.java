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
 * ImageTagName
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-05-30T21:59:48.295358700-05:00[America/Chicago]", comments = "Generator version: 7.6.0")
public class ImageTagName {

  private String en;

  public ImageTagName en(String en) {
    this.en = en;
    return this;
  }

  /**
   * the tag itself which could be an object, concept, color, etc
   * @return en
  */
  
  @Schema(name = "en", example = "banana", description = "the tag itself which could be an object, concept, color, etc", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("en")
  public String getEn() {
    return en;
  }

  public void setEn(String en) {
    this.en = en;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ImageTagName imageTagName = (ImageTagName) o;
    return Objects.equals(this.en, imageTagName.en);
  }

  @Override
  public int hashCode() {
    return Objects.hash(en);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ImageTagName {\n");
    sb.append("    en: ").append(toIndentedString(en)).append("\n");
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

