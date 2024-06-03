CREATE TABLE Images (
    image_id SMALLINT NOT NULL AUTO_INCREMENT,
    label varchar(255) NOT NULL,
    metadata TEXT,
    insert_date date,
    CONSTRAINT Pk_Image PRIMARY KEY (image_id)
)