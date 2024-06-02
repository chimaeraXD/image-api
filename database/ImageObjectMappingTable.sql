CREATE TABLE ImageMapping (
                        image_mapping_id SMALLINT NOT NULL AUTO_INCREMENT,
                        image_id SMALLINT NOT NULL,
                        object_id SMALLINT,
                        insert_date date,
                        PRIMARY KEY (image_mapping_id),
                        CONSTRAINT Fk_image_id FOREIGN KEY (image_id)
                          REFERENCES Images(image_id))