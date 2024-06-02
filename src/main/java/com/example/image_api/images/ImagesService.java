package com.example.image_api.images;

import com.example.image_api.client.imagaa.model.ImageTag;
import com.example.image_api.client.imagaa.model.ImageTagResponse;
import com.example.image_api.imagaa.feign.ImagaaFeignClient;
import com.example.image_api.images.entity.ImageEntity;
import com.example.image_api.images.entity.ObjectEntity;
import com.example.image_api.server.model.Image;
import com.example.image_api.server.model.ImageRequest;
import com.example.image_api.server.model.ImageResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImagesService {
    private final ImagaaFeignClient imagaaClient;
    private final ImageRepository imageRepository;

    public ImagesService(ImagaaFeignClient imagaaClient, ImageRepository imageRepository){
        this.imagaaClient = imagaaClient;
        this.imageRepository = imageRepository;
    }

    public ImageResponse addImageAndDetect(ImageRequest imageRequest){
        ImageResponse imageResponse = new ImageResponse();
        List<String> objectList = null;

        if (imageRequest.getObjectDetectionEnable()) {
            ImageTagResponse response = imagaaClient.getImageTags(imageRequest.getImageUrl()).getBody();
            objectList = getDetectedTags((response.getResult().getTags()));
            imageResponse.setDetectedObjects(objectList);
        }
        int imageId = imageRepository.addImage(imageRequest.getLabel(), imageRequest.getImageUrl());
        imageResponse.setId(imageId);

        if(objectList != null) {
            // add detected objects to database and create mapping with the image
            addObjects(objectList, imageId);
        }

        if(imageRequest.getLabel() == null || imageRequest.getLabel().isBlank()) {
            imageResponse.setLabel("default");
        } else {
            imageResponse.setLabel(imageRequest.getLabel());
        }
        // TODO: extract detailed metadata and save into database
        imageResponse.setImageMetadata(imageRequest.getImageUrl());
        return imageResponse;
    }

    private List<String> getDetectedTags(List<ImageTag> imageTagList) {
        // Only get the tags that is greater than confidence level 50%
        return imageTagList.stream()
                .filter(tag -> Double.parseDouble(tag.getConfidence()) >= 50)
                .map(tag -> tag.getTag().getEn())
                .collect(Collectors.toList());
    }

    private void addObjects (List<String> imageTagList, int imageId) {
        int objectId;
        ObjectEntity obj;
        for(String name: imageTagList) {
            obj = imageRepository.getObjectByName(name);
            if(obj == null) {
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
        List<Image> result = new ArrayList<>();
        for (ImageEntity imageEntity : list) {
            Image image = new Image();
            image.metaData(imageEntity.getMetadata());
            result.add(image);
        }
        return result;
    }

    public List<Image> getAllImagesByObjects(List<String> objectNameList) {
        List<ImageEntity> list = imageRepository.getAllImagesByObjects(objectNameList);
        List<Image> result = new ArrayList<>();
        for (ImageEntity imageEntity : list) {
            Image image = new Image();
            image.metaData(imageEntity.getMetadata());
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
           return result;
       }
    }
}
