package com.example.fakecom.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fakecom.schema.CategorySchema;
 

@Repository
public interface  CategoryRepository extends JpaRepository<CategorySchema, Long> {
    // enterrgig the lofic absed on the think that is yours


    public Optional<CategorySchema> findByName(String name); // considereing it is unique;;;
}
