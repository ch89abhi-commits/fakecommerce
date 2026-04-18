CREATE TABLE IF NOT EXISTS orders (
    id BIGINT NOT NULL AUTO_INCREMENT,
    created_at DATETIME NOT NULL , 
    updated_at DATETIME ,
    deleted_at DATETIME ,
    status SMALLINT,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS Create_products (
    id BIGINT NOT NULL AUTO_INCREMENT,
    created_at DATETIME NOT NULL , 
    updated_at DATETIME ,
    deleted_at DATETIME ,
    order_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity INT NOT NULL,
    PRIMARY KEY (id),
   CONSTRAINT fk_order_status FOREIGN KEY  (order_id)  REFERENCES orders(id),
  CONSTRAINT fk_product_id  FOREIGN KEY  (product_id) REFERENCES Products(id) 

);









