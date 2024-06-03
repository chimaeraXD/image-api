package com.example.image_api.images;

import com.drew.imaging.ImageMetadataReader;
import com.example.image_api.client.imagaa.model.ImageTag;
import com.example.image_api.client.imagaa.model.ImageTagName;
import com.example.image_api.client.imagaa.model.ImageTagResponse;
import com.example.image_api.client.imagaa.model.ImageTagResult;
import com.example.image_api.imagaa.feign.ImagaaFeignClient;
import com.example.image_api.images.entity.ImageEntity;
import com.example.image_api.server.model.Image;
import com.example.image_api.server.model.ImageRequest;
import com.example.image_api.server.model.ImageResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class ImagesServiceTest {

    @Mock
    private ImagaaFeignClient imagaaClient;

    @Mock
    private ImageRepository imageRepository;

    @Mock
    private ImageMetadataService imageMetadataService;

    @InjectMocks
    private ImagesService imagesService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // ====== Add Image and Detect ======

    @Test
    void addImageAndDetect_withObjectDetectionEnabled_shouldReturnImageResponse() {
        // Arrange
        ImageRequest imageRequest = new ImageRequest();
        imageRequest.setImageUrl("http://example.com/image.jpg");
        imageRequest.setObjectDetectionEnable(true);
        imageRequest.setLabel("Test Image");

        ImageTag tag1 = new ImageTag();
        tag1.setConfidence("60");
        ImageTagName tagName1 = new ImageTagName();
        tagName1.setEn("cat");
        tag1.setTag(tagName1);

        ImageTag tag2 = new ImageTag();
        tag2.setConfidence("40");
        ImageTagName tagName2 = new ImageTagName();
        tagName2.setEn("dog");
        tag2.setTag(tagName2);

        ImageTagResponse tagResponse = new ImageTagResponse();
        ImageTagResult tagResult = new ImageTagResult();
        tagResult.setTags(Arrays.asList(tag1, tag2));
        tagResponse.setResult(tagResult);

        when(imagaaClient.getImageTags(anyString())).thenReturn(ResponseEntity.ok(tagResponse));
        when(imageRepository.addImage(anyString(), anyString())).thenReturn(1);
        String expectedJson = "{\"Make\":\"Camera\",\"Model\":\"Camera ABC 999\"}";
        when(imageMetadataService.extractMetadata(anyString())).thenReturn(expectedJson);

        // Act
        ImageResponse imageResponse = imagesService.addImageAndDetect(imageRequest);

        // Assert
        assertNotNull(imageResponse);
        assertEquals(1, imageResponse.getId());
        assertEquals("Test Image", imageResponse.getLabel());
        assertEquals(expectedJson, imageResponse.getImageMetadata());
        // We only return the tage over 50%, which is cat
        assertEquals(List.of("cat"), imageResponse.getDetectedObjects());

        verify(imageRepository, times(1)).addImage(anyString(), anyString());
    }

    @Test
    void addImageAndDetect_withObjectDetectionDisabled_shouldReturnImageResponse() {
        // Arrange
        ImageRequest imageRequest = new ImageRequest();
        imageRequest.setImageUrl("http://example.com/image.jpg");
        imageRequest.setObjectDetectionEnable(false);
        imageRequest.setLabel("Test Image");

        when(imageRepository.addImage(anyString(), anyString())).thenReturn(1);
        String expectedJson = "{\"Make\":\"Camera\",\"Model\":\"Camera ABC 999\"}";
        when(imageMetadataService.extractMetadata(anyString())).thenReturn(expectedJson);

        // Act
        ImageResponse imageResponse = imagesService.addImageAndDetect(imageRequest);

        // Assert
        assertNotNull(imageResponse);
        assertEquals(1, imageResponse.getId());
        assertEquals("Test Image", imageResponse.getLabel());
        assertEquals(expectedJson, imageResponse.getImageMetadata());
        assertNull(imageResponse.getDetectedObjects());

        verify(imageRepository, times(1)).addImage(anyString(), anyString());
        verify(imagaaClient, never()).getImageTags(anyString());
    }

    @Test
    void addImageAndDetect_withNullLabel_shouldSetDefaultLabel() {
        // Arrange
        ImageRequest imageRequest = new ImageRequest();
        imageRequest.setImageUrl("http://example.com/image.jpg");
        imageRequest.setObjectDetectionEnable(false);
        imageRequest.setLabel(null);

        when(imageRepository.addImage(eq(null), anyString())).thenReturn(1);
        String expectedJson = "{\"Make\":\"Camera\",\"Model\":\"Camera ABC 999\"}";
        when(imageMetadataService.extractMetadata(anyString())).thenReturn(expectedJson);

        // Act
        ImageResponse imageResponse = imagesService.addImageAndDetect(imageRequest);

        // Assert
        assertNotNull(imageResponse);
        assertEquals(1, imageResponse.getId());
        assertEquals("default", imageResponse.getLabel());
        assertEquals(expectedJson, imageResponse.getImageMetadata());
        assertNull(imageResponse.getDetectedObjects());

        verify(imageRepository, times(1)).addImage(eq(null), anyString());
    }

    // ====== Get All Images ======

    @Test
    void getAllImages_shouldReturnListOfImages() {
        // Arrange
        ImageEntity imageEntity1 = new ImageEntity();
        imageEntity1.setMetadata("metadata1");

        ImageEntity imageEntity2 = new ImageEntity();
        imageEntity2.setMetadata("metadata2");

        List<ImageEntity> imageEntityList = Arrays.asList(imageEntity1, imageEntity2);

        when(imageRepository.getAllImages()).thenReturn(imageEntityList);

        // Act
        List<Image> result = imagesService.getAllImages();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("metadata1", result.get(0).getMetaData());
        assertEquals("metadata2", result.get(1).getMetaData());

        verify(imageRepository, times(1)).getAllImages();
    }

    // ====== Get All Images by Objects ======

    @Test
    void getAllImagesByObjects_shouldReturnListOfImages() {
        // Arrange
        List<String> objectNames = Arrays.asList("cat", "dog");

        ImageEntity imageEntity1 = new ImageEntity();
        imageEntity1.setMetadata("metadata1");

        ImageEntity imageEntity2 = new ImageEntity();
        imageEntity2.setMetadata("metadata2");

        List<ImageEntity> imageEntityList = Arrays.asList(imageEntity1, imageEntity2);

        when(imageRepository.getAllImagesByObjects(objectNames)).thenReturn(imageEntityList);

        // Act
        List<Image> result = imagesService.getAllImagesByObjects(objectNames);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("metadata1", result.get(0).getMetaData());
        assertEquals("metadata2", result.get(1).getMetaData());

        verify(imageRepository, times(1)).getAllImagesByObjects(objectNames);
    }

    // ====== Get Image by image id ======

    @Test
    void getImageByImageId_shouldReturnImage() {
        // Arrange
        int imageId = 1;

        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setMetadata("metadata");

        when(imageRepository.getImageByImageId(imageId)).thenReturn(imageEntity);

        // Act
        Image result = imagesService.getImageByImageId(imageId);

        // Assert
        assertNotNull(result);
        assertEquals("metadata", result.getMetaData());

        verify(imageRepository, times(1)).getImageByImageId(imageId);
    }

    @Test
    void getImageByImageId_shouldReturnNull() {
        // Arrange
        int imageId = 1;

        when(imageRepository.getImageByImageId(imageId)).thenReturn(null);

        // Act
        Image result = imagesService.getImageByImageId(imageId);

        // Assert
        assertNull(result);

        verify(imageRepository, times(1)).getImageByImageId(imageId);
    }
}