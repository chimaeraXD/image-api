package com.example.image_api.client.imagaa.model;

import java.net.URI;
import java.util.Objects;
import com.example.image_api.client.imagaa.model.ImageStatus;
import com.example.image_api.client.imagaa.model.ImageTag;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * ImageTagResult
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-05-30T21:59:48.295358700-05:00[America/Chicago]", comments = "Generator version: 7.6.0")
public class ImageTagResult {

  @Valid
  private List<@Valid ImageTag> tags = new ArrayList<>();

  private ImageStatus status;

  public ImageTagResult tags(List<@Valid ImageTag> tags) {
    this.tags = tags;
    return this;
  }

  public ImageTagResult addTagsItem(ImageTag tagsItem) {
    if (this.tags == null) {
      this.tags = new ArrayList<>();
    }
    this.tags.add(tagsItem);
    return this;
  }

  /**
   * Get tags
   * @return tags
  */
  @Valid 
  @Schema(name = "tags", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("tags")
  public List<@Valid ImageTag> getTags() {
    return tags;
  }

  public void setTags(List<@Valid ImageTag> tags) {
    this.tags = tags;
  }

  public ImageTagResult status(ImageStatus status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
  */
  @Valid 
  @Schema(name = "status", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("status")
  public ImageStatus getStatus() {
    return status;
  }

  public void setStatus(ImageStatus status) {
    this.status = status;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ImageTagResult imageTagResult = (ImageTagResult) o;
    return Objects.equals(this.tags, imageTagResult.tags) &&
        Objects.equals(this.status, imageTagResult.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tags, status);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ImageTagResult {\n");
    sb.append("    tags: ").append(toIndentedString(tags)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
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

