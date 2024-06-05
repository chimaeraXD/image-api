package com.example.image_api.images;

import com.drew.imaging.ImageProcessingException;
import com.example.image_api.client.imagaa.model.ImageTag;
import com.example.image_api.client.imagaa.model.ImageTagResponse;
import com.example.image_api.imagaa.feign.ImagaaFeignClient;
import com.example.image_api.images.entity.ImageEntity;
import com.example.image_api.images.entity.ObjectEntity;
import com.example.image_api.server.model.Image;
import com.example.image_api.server.model.ImageRequest;
import com.example.image_api.server.model.ImageResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;
import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.net.URL;

@Service
public class ImagesService {
    private final ImagaaFeignClient imagaaClient;
    private final ImageRepository imageRepository;
    private final ImageMetadataService imageMetadataService;

    public ImagesService(ImagaaFeignClient imagaaClient, ImageRepository imageRepository,ImageMetadataService imageMetadataService) {
        this.imagaaClient = imagaaClient;
        this.imageRepository = imageRepository;
        this.imageMetadataService = imageMetadataService;
    }

    public ImageResponse addImageAndDetect(ImageRequest imageRequest) {
        ImageResponse imageResponse = new ImageResponse();
        List<String> objectList = null;

        if (imageRequest.getObjectDetectionEnable()) {
            ImageTagResponse response = imagaaClient.getImageTags(imageRequest.getImageUrl()).getBody();
            objectList = getDetectedTags((response.getResult().getTags()));
            imageResponse.setDetectedObjects(objectList);
        }

        String imageMetadata = imageMetadataService.extractMetadata(imageRequest.getImageUrl());
        int imageId = imageRepository.addImage(imageRequest.getLabel(), imageMetadata);
        imageResponse.setId(imageId);
        imageResponse.setImageMetadata(imageMetadata);

        if (objectList != null) {
            // add detected objects to database and create mapping with the image
            addObjects(objectList, imageId);
        }

        if (imageRequest.getLabel() == null || imageRequest.getLabel().isBlank()) {
            imageResponse.setLabel("default");
        } else {
            imageResponse.setLabel(imageRequest.getLabel());
        }

        return imageResponse;
    }

    private List<String> getDetectedTags(List<ImageTag> imageTagList) {
        // Only get the tags that is greater than confidence level 50%
        return imageTagList.stream()
                .filter(tag -> Double.parseDouble(tag.getConfidence()) >= 50)
                .map(tag -> tag.getTag().getEn())
                .collect(Collectors.toList());
    }

    private void addObjects(List<String> imageTagList, int imageId) {
        int objectId;
        ObjectEntity obj;
        for (String name : imageTagList) {
            obj = imageRepository.getObjectByName(name);
            if (obj == null) {
                objectId = imageRepository.addObject(name);
            } else {
                objectId = obj.getObjectId();
            }
            //Add the mapping for image as well, then we can retrieve the images based on objects
            imageRepository.addImageMapping(imageId, objectId);
        }
    }

    public List<Image> getAllImages() {
        List<ImageEntity> list = imageRepository.getAllImages();
        // Get an empty list if there is no image
        List<Image> result = new ArrayList<>();
        for (ImageEntity imageEntity : list) {
            Image image = new Image();
            image.metaData(imageEntity.getMetadata());
            image.imageId(imageEntity.getImageId());
            result.add(image);
        }
        return result;
    }

    public List<Image> getAllImagesByObjects(List<String> objectNameList) {
        List<ImageEntity> list = imageRepository.getAllImagesByObjects(objectNameList);
        // Get an empty list if there is no image associated with any of the object
        List<Image> result = new ArrayList<>();
        for (ImageEntity imageEntity : list) {
            Image image = new Image();
            image.metaData(imageEntity.getMetadata());
            image.imageId(imageEntity.getImageId());
            result.add(image);
        }
        return result;
    }

    public Image getImageByImageId(int imageId) {
        ImageEntity image = imageRepository.getImageByImageId(imageId);
        if (image == null) {
            return null;
        } else {
            Image result = new Image();
            result.metaData(image.getMetadata());
            result.imageId(image.getImageId());
            return result;
        }
    }
}
