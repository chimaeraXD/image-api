package com.example.image_api.images;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Service
public class ImageMetadataService {

    public String extractMetadata(String imageUrl) {
        Map<String, String> metadataMap = new HashMap<>();
        try (InputStream inputStream = new URL(imageUrl).openStream()) {
            Metadata metadata = ImageMetadataReader.readMetadata(inputStream);
            metadata.getDirectories().forEach(directory ->
                    directory.getTags().forEach(tag ->
                            metadataMap.put(tag.getTagName(), tag.getDescription())
                    )
            );
            // Convert metadata map to JSON string
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(metadataMap);
        } catch (ImageProcessingException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
