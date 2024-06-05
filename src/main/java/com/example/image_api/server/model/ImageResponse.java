package com.example.image_api.server.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * ImageResponse
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ImageResponse  implements Serializable {

  private static final long serialVersionUID = 1L;

  @JsonProperty("id")
  private Integer id;

  @JsonProperty("label")
  private String label;

  @JsonProperty("imageMetadata")
  private String imageMetadata;

  @JsonProperty("detectedObjects")
  @Valid
  private List<String> detectedObjects = null;

  public ImageResponse id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  
  @Schema(name = "id", example = "100000", required = false)
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public ImageResponse label(String label) {
    this.label = label;
    return this;
  }

  /**
   * Get label
   * @return label
  */
  
  @Schema(name = "label", example = "zoo", required = false)
  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public ImageResponse imageMetadata(String imageMetadata) {
    this.imageMetadata = imageMetadata;
    return this;
  }

  /**
   * Get imageMetadata
   * @return imageMetadata
  */
  
  @Schema(name = "imageMetadata", example = "TBD", required = false)
  public String getImageMetadata() {
    return imageMetadata;
  }

  public void setImageMetadata(String imageMetadata) {
    this.imageMetadata = imageMetadata;
  }

  public ImageResponse detectedObjects(List<String> detectedObjects) {
    this.detectedObjects = detectedObjects;
    return this;
  }

  public ImageResponse addDetectedObjectsItem(String detectedObjectsItem) {
    if (this.detectedObjects == null) {
      this.detectedObjects = new ArrayList<>();
    }
    this.detectedObjects.add(detectedObjectsItem);
    return this;
  }

  /**
   * Get detectedObjects
   * @return detectedObjects
  */
  
  @Schema(name = "detectedObjects", required = false)
  public List<String> getDetectedObjects() {
    return detectedObjects;
  }

  public void setDetectedObjects(List<String> detectedObjects) {
    this.detectedObjects = detectedObjects;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ImageResponse imageResponse = (ImageResponse) o;
    return Objects.equals(this.id, imageResponse.id) &&
        Objects.equals(this.label, imageResponse.label) &&
        Objects.equals(this.imageMetadata, imageResponse.imageMetadata) &&
        Objects.equals(this.detectedObjects, imageResponse.detectedObjects);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, label, imageMetadata, detectedObjects);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ImageResponse {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    label: ").append(toIndentedString(label)).append("\n");
    sb.append("    imageMetadata: ").append(toIndentedString(imageMetadata)).append("\n");
    sb.append("    detectedObjects: ").append(toIndentedString(detectedObjects)).append("\n");
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

