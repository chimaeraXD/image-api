package com.example.image_api.images;

import com.example.image_api.server.model.Image;
import com.example.image_api.server.model.ImageRequest;
import com.example.image_api.server.model.ImageResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ImagesControllerTest {

    @Mock
    private ImagesService imagesService;

    @InjectMocks
    private ImagesController imagesController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddImageAndDetect() {
        ImageRequest imageRequest = new ImageRequest();
        ImageResponse imageResponse = new ImageResponse();
        when(imagesService.addImageAndDetect(imageRequest)).thenReturn(imageResponse);

        ResponseEntity<ImageResponse> response = imagesController.addImageAndDetect(imageRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(imageResponse, response.getBody());
    }

    @Test
    void testGetAllImages() {
        List<Image> images = Arrays.asList(new Image(), new Image());
        when(imagesService.getAllImages()).thenReturn(images);

        ResponseEntity<List<Image>> response = imagesController.getAllImages();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(images, response.getBody());
    }

    @Test
    void testGetAllImagesByObjects() {
        List<String> objects = Arrays.asList("fish", "dogfood");
        List<Image> images = Arrays.asList(new Image(), new Image());
        when(imagesService.getAllImagesByObjects(objects)).thenReturn(images);

        ResponseEntity<List<Image>> response = imagesController.getAllImagesByObjects(objects);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(images, response.getBody());
    }

    @Test
    void testGetImageByImageId_found() {
        Integer imageId = 1;
        Image image = new Image();
        when(imagesService.getImageByImageId(imageId)).thenReturn(image);

        ResponseEntity<Image> response = imagesController.getImageByImageId(imageId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(image, response.getBody());
    }

    @Test
    void testGetImageByImageId_notFound() {
        Integer imageId = 1;
        when(imagesService.getImageByImageId(imageId)).thenReturn(null);

        ResponseEntity<Image> response = imagesController.getImageByImageId(imageId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
