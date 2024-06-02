package com.example.image_api.client.imagaa.model;

import java.net.URI;
import java.util.Objects;
import com.example.image_api.client.imagaa.model.ImageTagName;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * ImageTag
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-05-30T21:59:48.295358700-05:00[America/Chicago]", comments = "Generator version: 7.6.0")
public class ImageTag {

  private String confidence;

  private ImageTagName tag;

  public ImageTag confidence(String confidence) {
    this.confidence = confidence;
    return this;
  }

  /**
   * Get confidence
   * @return confidence
  */
  
  @Schema(name = "confidence", example = "61.4116096496582", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("confidence")
  public String getConfidence() {
    return confidence;
  }

  public void setConfidence(String confidence) {
    this.confidence = confidence;
  }

  public ImageTag tag(ImageTagName tag) {
    this.tag = tag;
    return this;
  }

  /**
   * Get tag
   * @return tag
  */
  @Valid 
  @Schema(name = "tag", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("tag")
  public ImageTagName getTag() {
    return tag;
  }

  public void setTag(ImageTagName tag) {
    this.tag = tag;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ImageTag imageTag = (ImageTag) o;
    return Objects.equals(this.confidence, imageTag.confidence) &&
        Objects.equals(this.tag, imageTag.tag);
  }

  @Override
  public int hashCode() {
    return Objects.hash(confidence, tag);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ImageTag {\n");
    sb.append("    confidence: ").append(toIndentedString(confidence)).append("\n");
    sb.append("    tag: ").append(toIndentedString(tag)).append("\n");
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

