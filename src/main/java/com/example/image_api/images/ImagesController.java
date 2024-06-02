package com.example.image_api.images;


import com.example.image_api.server.api.ImagesApi;
import com.example.image_api.server.model.Image;
import com.example.image_api.server.model.ImageRequest;
import com.example.image_api.server.model.ImageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ImagesController implements ImagesApi {

    private final ImagesService imagesService;

    public ImagesController(ImagesService imagesService) {
        this.imagesService = imagesService;
    }

    @Override
    public ResponseEntity<ImageResponse> addImageAndDetect(ImageRequest imageRequest) {
        return new ResponseEntity<>(imagesService.addImageAndDetect(imageRequest), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Image>> getAllImages() {
        return new ResponseEntity<>(imagesService.getAllImages(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Image>> getAllImagesByObjects(List<String> objects) {
        return new ResponseEntity<>(imagesService.getAllImagesByObjects(objects), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Image> getImageByImageId(Integer imageId) {
        Image result = imagesService.getImageByImageId(imageId);
        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }
}

