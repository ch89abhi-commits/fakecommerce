package com.example.fakecom.repositories;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.fakecom.schema.CategorySchema;
import com.example.fakecom.schema.ProductSchema;

// consider the importatnt factor that teh sql is working based on the tables name ,felds etc,
// but the jpql is working based on the java entity

@Repository
public interface ProductRepository extends JpaRepository<ProductSchema, Long> ,CustomProductRepository {

@Query(value="SELECT * FROM Products ",nativeQuery=true) // native is for the sql queries;
 public List<ProductSchema> findAllProducts();



@Query(value="SELECT DISTINCT Products.category_id,Categories.name FROM Products INNER JOIN Categories ON Categories.id=Products.category_id",nativeQuery=true)                  // here i m wrirting  the sql queries
 public List<Map<String,Object>> findAllCategories();
    
@Query(value="SELECT * FROM Products",nativeQuery=true)
 public List<ProductSchema> findByCate(String category);

 public List<ProductSchema> findByCategory_id(Long id);
    
}
