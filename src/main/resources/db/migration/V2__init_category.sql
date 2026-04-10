ALTER TABLE Products ADD category_id BIGINT NOT NULL; 

CREATE TABLE  IF NOT EXISTS Categories(
    id BIGINT NOT NULL AUTO_INCREMENT,
    created_At DATETIME NOT NULL,
    updated_At DATETIME ,
    deleted_At DATETIME ,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);
--  categories table created then now

ALTER TABLE Products ADD CONSTRAINT fk_prod_cat FOREIGN KEY (category_id) REFERENCES Categories(id);
-- add the name constraint is to futher drop the ofeirng ley constrait;
