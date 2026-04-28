package com.example.fakecom.TestActualRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
 
import com.example.fakecom.Config.TestJpaConfig;
import com.example.fakecom.repositories.CategoryRepository;
import com.example.fakecom.schema.CategorySchema;

@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE) 
@Import(TestJpaConfig.class)

@Testcontainers // if the apllicaton yml file is -test then i need the active profiles annotaions
public class RepositoryTest {

    @Container
    @ServiceConnection
    static MySQLContainer<?> mysql=new MySQLContainer<>("mysql:8.0.36")
                                        .withCommand("--lower_case_table_names=1"); // forces to use lowerccase insenstivitys
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TestEntityManager testEntityManager;
    
    private Long dynamicId;

    @BeforeEach
    void setup(){
        categoryRepository.deleteAllInBatch();
       CategorySchema ct= CategorySchema.builder().name("test_category").build(); 

    dynamicId=(Long) testEntityManager.persistAndGetId(ct);
    testEntityManager.flush();
    testEntityManager.clear();

    }

    @Test
    void getCategory(){

        Optional<CategorySchema> data=categoryRepository.findById(dynamicId);
        
        // assertEquals(data.isPresent(), true);
        // assertNotNull(data.get());
        // assertEquals(data.get().getName(), "test_category");
        
        assertThat(data).isPresent();
        assertThat(data.get().getName()).isEqualTo("test_category");



    }



}
