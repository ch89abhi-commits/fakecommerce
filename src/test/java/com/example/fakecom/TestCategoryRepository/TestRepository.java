package com.example.fakecom.TestCategoryRepository;

 
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;
import org.springframework.context.annotation.Import;

import com.example.fakecom.Config.TestJpaConfig;
import com.example.fakecom.repositories.CategoryRepository;
import com.example.fakecom.schema.CategorySchema;

@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.ANY)
@Import(TestJpaConfig.class)// importing for the jpa auditing cllases to get my timestamps
public class TestRepository {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TestEntityManager testEntityManager;
     
  private Long textileId;
  private Long electronicId;




    @BeforeEach //run before every funcitons
    void setup(){
            CategorySchema res1=CategorySchema.builder().name("electronics").build();
            CategorySchema res2=CategorySchema.builder().name("textile").build();
            // testEntityManager.persistAndFlush(res1);
            // testEntityManager.persistAndFlush(res2);
            // actu.add(res1);
            // actu.add(res2);
            textileId=(Long)testEntityManager.persistAndGetId(res2);
            electronicId=(Long)testEntityManager.persistAndGetId(res1);

            testEntityManager.flush();

            testEntityManager.clear();

    }

    @Test
    void findAllCategory(){
        //act
        List<CategorySchema> cateList =categoryRepository.findAll();
        assertEquals(2, cateList.size());
        // assertIterableEquals(cateList, actu); // check for the list or the order 
      
    
}

    @Test
    void findById_returnCategory(){
        Optional<CategorySchema> ct1=categoryRepository.findById(electronicId);
        

        assertEquals(ct1.isPresent(), true);
        assertNotNull(ct1.get()); // check using teh assert.equals usually for the values of the fildd
        // assertEquals(ct1.get(), CategorySchema.class);
        assertInstanceOf(CategorySchema.class,ct1.get()); // check for the inheritance
        assertEquals(ct1.get().getName(), "electronics");
        // assertEquals(ct1.get().getId(),  any(Long.class)); // any is the type that is used for the where clause only 
        assertInstanceOf(Long.class, ct1.get().getId());
        

        
    }

    @Test
    void findById_return_Error(){
        Optional<CategorySchema> ct1=categoryRepository.findById(3L);
        assertEquals(ct1.isPresent(), false);
        //  assertThrows(NoSuchElementException.class, ()->{ct1.get();});
         // as the no such exception class is forom the optional java

       
    }
    @Test
    void findbyId_then_delete(){
        CategorySchema ct= testEntityManager.find(CategorySchema.class, textileId);
        assertNotNull(ct,"category should not be null before delete");
        
       categoryRepository.delete(ct);

        testEntityManager.flush();// to provife the sql queris
        testEntityManager.clear();// check that it delete the cache;;

       CategorySchema data=testEntityManager.find(CategorySchema.class, textileId);
       assertNull(data);
    //    assertNotNull(data);
    //    assertInstanceOf(null, data);



    }

    @Test
    void findbyName_return_category(){
        Optional<CategorySchema> data=categoryRepository.findByName("electronics");
        
        assertNotNull(data);
        assertEquals(data.isPresent(), true);
        assertEquals(data.get().getName(), "electronics");
        // assertEquals(data.get().getId(), any(Long.class));
        assertInstanceOf(Long.class, data.get().getId());
        
    }


    @Test
    void findbyName_return_null(){
         Optional<CategorySchema> data=categoryRepository.findByName("skincare");
        assertEquals(data.isPresent(), false);
        //  assertThrows(NoSuchElementException.class, ()->data.get());
        // assertEquals(data.get().getId(), Long.class);

     }






























}