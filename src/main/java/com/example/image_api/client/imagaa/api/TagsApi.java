/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (7.6.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.example.image_api.client.imagaa.api;

import com.example.image_api.client.imagaa.model.ImageTagResponse;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-05-30T21:59:48.295358700-05:00[America/Chicago]", comments = "Generator version: 7.6.0")
@Validated
@Tag(name = "imagaa", description = "Detect and analyze the objects in image")
public interface TagsApi {

    /**
     * GET /tags : Get detected objects in an image
     * By sending an image URL to the /tags endpoint you can get a list of many automatically suggested textual tags
     *
     * @param imageUrl The image internet url to be analyzed (optional)
     * @return successful operation (status code 200)
     *         or Invalid value (status code 400)
     */
    @Operation(
        operationId = "getImageTags",
        summary = "Get detected objects in an image",
        description = "By sending an image URL to the /tags endpoint you can get a list of many automatically suggested textual tags",
        tags = { "imagaa" },
        responses = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ImageTagResponse.class))
            }),
            @ApiResponse(responseCode = "400", description = "Invalid value")
        },
        security = {
            @SecurityRequirement(name = "basicAuth")
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/tags",
        produces = { "application/json" }
    )
    
    ResponseEntity<ImageTagResponse> getImageTags(
        @Parameter(name = "image_url", description = "The image internet url to be analyzed", in = ParameterIn.QUERY) @Valid @RequestParam(value = "image_url", required = false) String imageUrl
    );

}
