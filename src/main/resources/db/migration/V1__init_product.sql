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
    -- category_id BIGINT NOT NULL,  added to the next commit
    Primary key(id)
    -- Foreign key(category_id) references Categories(id)

);

    