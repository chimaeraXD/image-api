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
 * ImageRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.6.0")
public class ImageRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  private String imageUrl;

  private String label;

  private Boolean objectDetectionEnable;

  public ImageRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ImageRequest(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public ImageRequest imageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
    return this;
  }

  /**
   * Get imageUrl
   * @return imageUrl
  */
  @NotNull 
  @Schema(name = "imageUrl", example = "https://zoo.jpg", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("imageUrl")
  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public ImageRequest label(String label) {
    this.label = label;
    return this;
  }

  /**
   * Get label
   * @return label
  */
  
  @Schema(name = "label", example = "zoo", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("label")
  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public ImageRequest objectDetectionEnable(Boolean objectDetectionEnable) {
    this.objectDetectionEnable = objectDetectionEnable;
    return this;
  }

  /**
   * Get objectDetectionEnable
   * @return objectDetectionEnable
  */
  
  @Schema(name = "objectDetectionEnable", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("objectDetectionEnable")
  public Boolean getObjectDetectionEnable() {
    return objectDetectionEnable;
  }

  public void setObjectDetectionEnable(Boolean objectDetectionEnable) {
    this.objectDetectionEnable = objectDetectionEnable;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ImageRequest imageRequest = (ImageRequest) o;
    return Objects.equals(this.imageUrl, imageRequest.imageUrl) &&
        Objects.equals(this.label, imageRequest.label) &&
        Objects.equals(this.objectDetectionEnable, imageRequest.objectDetectionEnable);
  }

  @Override
  public int hashCode() {
    return Objects.hash(imageUrl, label, objectDetectionEnable);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ImageRequest {\n");
    sb.append("    imageUrl: ").append(toIndentedString(imageUrl)).append("\n");
    sb.append("    label: ").append(toIndentedString(label)).append("\n");
    sb.append("    objectDetectionEnable: ").append(toIndentedString(objectDetectionEnable)).append("\n");
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

