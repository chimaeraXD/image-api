package com.example.image_api.images;

import com.example.image_api.images.entity.ImageEntity;
import com.example.image_api.images.entity.ObjectEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ImageRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final Logger LOG = LoggerFactory.getLogger(ImageRepository.class);

    public ImageRepository(NamedParameterJdbcTemplate jdbcTemplete) {
        this.jdbcTemplate = jdbcTemplete;
    }

    public static RowMapper<ImageEntity> getImageRowMapper() {
        return new RowMapper<ImageEntity>() {
            @Override
            public ImageEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
                ImageEntity imageEntity = new ImageEntity();
                imageEntity.setImageId(rs.getInt("image_id"));
                imageEntity.setLabel(rs.getString("label"));
                imageEntity.setMetadata(rs.getString("metadata"));
                return imageEntity;
            }
        };

    }

    public static RowMapper<ObjectEntity> getObjectRowMapper() {
        return new RowMapper<ObjectEntity>() {
            @Override
            public ObjectEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
                ObjectEntity objectEntity = new ObjectEntity();
                objectEntity.setObjectId(rs.getInt("object_id"));
                objectEntity.setName(rs.getString("name"));
                return objectEntity;
            }
        };

    }

    public int addImage(String label, String metadata) {
        try {
            GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

            String ADD_IMAGE = "insert into Images (label, metadata, insert_date) values (:label, :metadata, now())";
            jdbcTemplate.update(ADD_IMAGE,
                    new MapSqlParameterSource()
                            .addValue("label", label)
                            .addValue("metadata", metadata),
                    keyHolder, new String[]{"image_id"}
            );
            Number key = keyHolder.getKey();
            assert key != null;
            return key.intValue();
        } catch (Exception e) {
            LOG.error("Encounter issue while adding image", e);
            throw e;
        }
    }

    public List<ImageEntity> getAllImages() {
        try {
            String GET_ALL_IMAGE = "select image_id, label, metadata from Images";
            List<ImageEntity> list = jdbcTemplate.query(GET_ALL_IMAGE, getImageRowMapper());
            return list;
        } catch (Exception e) {
            LOG.error("Encounter issue while getting all images", e);
            throw e;
        }
    }

    public List<ImageEntity> getAllImagesByObjects(List<String> objectNameList) {
        try {
            String objects = objectNameList.stream().map(name -> "'" + name + "'").collect(Collectors.joining(","));
            String GET_ALL_IMAGE_BY_OBJECTS = "SELECT i.image_id, i.label, i.metadata " +
                    "FROM Images i " +
                    "JOIN ImageMapping im ON i.image_id = im.image_id " +
                    "JOIN Objects o ON im.object_id = o.object_id " +
                    "WHERE o.object_name IN (" + objects + ")";
            List<ImageEntity> list = jdbcTemplate.query(GET_ALL_IMAGE_BY_OBJECTS, getImageRowMapper());
            return list;
        } catch (Exception e) {
            LOG.error("Encounter issue while getting image from object", e);
            throw e;
        }
    }

    public int addObject(String objectTagName) {
        try {
            GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

            String ADD_OBJECT = "insert into Objects (object_name) values (:object_name)";
            jdbcTemplate.update(ADD_OBJECT,
                    new MapSqlParameterSource()
                            .addValue("object_name", objectTagName),
                    keyHolder, new String[]{"object_id"}
            );
            Number key = keyHolder.getKey();
            assert key != null;
            return key.intValue();
        } catch (Exception e) {
            LOG.error("Encounter issue while adding object", e);
            throw e;
        }
    }

    public ObjectEntity getObjectByName(String objectTagName) {
        try {
            String GET_OBJECT_BY_NAME = "select * from Objects where object_name = :object_name limit 1";
            List<ObjectEntity> item = jdbcTemplate.query(GET_OBJECT_BY_NAME,
                    new MapSqlParameterSource()
                            .addValue("object_name", objectTagName),
                    getObjectRowMapper());
            if (!item.isEmpty()) {
                return item.get(0);
            } else {
                return null;
            }
        } catch (Exception e) {
            LOG.error("Encounter issue while getting object by name", e);
            throw e;
        }
    }

    public int addImageMapping(int imageId, int objectId) {
        try {
            GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

            String ADD_IMAGE_MAPPING = "insert into ImageMapping (image_id, object_id, insert_date) values " +
                    "(:image_id, :object_id, now())";
            jdbcTemplate.update(ADD_IMAGE_MAPPING,
                    new MapSqlParameterSource()
                            .addValue("image_id", imageId)
                            .addValue("object_id", objectId),
                    keyHolder, new String[]{"image_mapping_id"}
            );
            Number key = keyHolder.getKey();
            assert key != null;
            return key.intValue();
        } catch (Exception e) {
            LOG.error("Encounter issue while adding image", e);
            throw e;
        }
    }

    public ImageEntity getImageByImageId(int imageId) {
        try {
            String GET_IMAGE_BY_IMAGE_ID = "select * from Images where image_id = :image_id limit 1";
            List<ImageEntity> item = jdbcTemplate.query(GET_IMAGE_BY_IMAGE_ID,
                    new MapSqlParameterSource()
                            .addValue("image_id", imageId),
                    getImageRowMapper());
            if (!item.isEmpty()) {
                return item.get(0);
            } else {
                return null;
            }
        } catch (Exception e) {
            LOG.error("Encounter issue while retrieving image", e);
            throw e;
        }
    }
}
