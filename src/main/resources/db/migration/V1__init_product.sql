CREATE TABLE IF NOT EXISTS Products(

    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL ,
    image VARCHAR(100) ,
    description TEXT ,
    price Float NOT NULL ,
    rating FLOAT NOT NULL ,
    created_At DATETIME NOT NULL , 
    updated_At DATETIME ,
    deleted_At DATETIME ,
    Primary key(id)

);

    