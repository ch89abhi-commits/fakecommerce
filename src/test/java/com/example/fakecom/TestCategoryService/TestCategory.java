package com.example.fakecom.TestCategoryService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.ArgumentCaptor;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.fakecom.DTOs.RequestDTOs.CategoryRequest;
import com.example.fakecom.DTOs.ResponseDTOs.CategoryResponse;
import com.example.fakecom.ObjectMappers.CategoryMapper;
import com.example.fakecom.repositories.CategoryRepository;
import com.example.fakecom.schema.CategorySchema;
import com.example.fakecom.services.CategoryServices.CategoryService;

@ExtendWith(MockitoExtension.class)
public class TestCategory {

    @Mock
    protected CategoryRepository categoryRepository;

    
    @Nested
    class IsolateServiceMapper{

        @Mock
        private CategoryMapper categoryMapper;
    
         
        private CategoryService categoryService;

        @BeforeEach() // manually calling the constructor for teh all args to wordk
        void setup(){
            categoryService=new CategoryService(categoryRepository,categoryMapper);
        }
    
        @Test
        void addingCategory_returnCategory(){
            CategorySchema beforeSave=CategorySchema.builder().name("test _category").build();
            CategorySchema afterSave= CategorySchema.builder().name("test _category").build();
            afterSave.setId(1L);
    
            CategoryRequest inputSchema= CategoryRequest.builder().name("test _category").build();
           
            CategoryResponse outputResponse= CategoryResponse.builder().name("test _category").build();
            outputResponse.setId(1L);
            
            
    
            when(categoryMapper.DTOtoSchema(any(CategoryRequest.class))).thenReturn(beforeSave);//first case;
    
            when(categoryRepository.save(any(CategorySchema.class))).thenReturn(afterSave);
    
            // when(categoryMapper.SchemaToDTO(any(CategorySchema.class))).thenReturn(outputSchema);
            when(categoryMapper.SchemaToResponse(any(CategorySchema.class))).thenReturn(outputResponse);

            
                // the above all are the arrangement as because i need to have the object that are to be passes
                // or the stubbing of the data to teh mocks

// now itiniate the actual call to the service layer;

            CategoryResponse result=categoryService.addingCategory(inputSchema);


            // checking tfor the result of the above call so we now getting aserts

            assertNotNull(result);
            assertEquals("test _category", result.getName());
            assertEquals(1L, result.getId());
            
    
            //conformation for the flow is correct or not
            
            verify(categoryMapper).DTOtoSchema(inputSchema);
            verify(categoryRepository).save(beforeSave);
            verify(categoryMapper).SchemaToResponse(afterSave);
    
    
    
    
        }
    
        

    }
    @Nested
    class IntegrateServiceMapper{

        // @Spy
        private CategoryMapper categoryMapper=Mappers.getMapper(CategoryMapper.class); //getting the implepbjet of the mapper ckass
    
        // @InjectMocks
        private CategoryService categoryService;

        @BeforeEach
        void setup() {
            // Create a Spy of the real mapper
            categoryMapper = Mockito.spy(categoryMapper);
            categoryService = new CategoryService(categoryRepository, categoryMapper);
        }
    
        @Test
        void addingCategory_returnCategory(){
             
            //arrrange; which is requred to be delivered statically
            CategoryRequest data=CategoryRequest.builder().name("test_category").build();
         
            CategorySchema beforeSave=CategorySchema.builder().name("test_category").build();
            CategorySchema afterSave=CategorySchema.builder().name("test_category").build();
            afterSave.setId(1L);
            
            
                        // instead of the below save the object the request response i can use the any(categoru schema.classa) 
                        // as theis objec tis convert to this type only by the mapper ckass 
            when(categoryRepository.save(any(CategorySchema.class))).thenReturn(afterSave); 


            // act;;
            CategoryResponse result=categoryService.addingCategory(data);
            
            //assert;;
            assertNotNull(result);
           assertInstanceOf(CategoryResponse.class, result);
            assertEquals("test_category", result.getName());
            assertEquals(1L, result.getId());


            // for checking the calling flow of the funtions that u have used here
            // verify(categoryMapper).DTOtoSchema(data);
            // verify(categoryRepository).save(beforeSave);
            // verify(categoryMapper).SchemaToDTO(afterSave);
            
            verify(categoryMapper).DTOtoSchema(any(CategoryRequest.class));

            verify(categoryRepository).save(any(CategorySchema.class));

            verify(categoryMapper).SchemaToResponse(any(CategorySchema.class));
            
    
    
    
        }
    
      

    }

    @Nested
    class ProductionTestCase{

        @Spy
        private CategoryMapper categoryMapper=Mappers.getMapper(CategoryMapper.class);
 
        private CategoryService categoryService;

        @BeforeEach
        public void ProductionTestCase(){
          categoryService =new CategoryService(categoryRepository,categoryMapper);
        }
 

        @Test
        void addingCategory_added_returnResponse(){

            // arrange step for teh mocks and the stubbing;
            CategoryRequest input =CategoryRequest.builder().name("test_category").build(); // this is to be inputted into the act call
            CategorySchema data=CategorySchema.builder().name("test_category").build();
            data.setId(1L);
            when(categoryRepository.save(any(CategorySchema.class))).thenReturn(data); // this is the mock this should be static

            // inorder verifier of the function flow

            InOrder inorder=inOrder(categoryMapper,categoryRepository);

            // for the data flow
            ArgumentCaptor<CategorySchema> schemaCaptor =ArgumentCaptor.forClass(CategorySchema.class);

            //act
            CategoryResponse result=categoryService.addingCategory(input);
            // assertion


            assertNotNull(result);
            assertInstanceOf(CategoryResponse.class, result);
            assertEquals("test_category", result.getName());
            assertEquals(1L, result.getId());

            // verify the order of the funciton call  that is used by the service layer 
            inorder.verify(categoryMapper).DTOtoSchema(input);

            inorder.verify(categoryRepository).save(schemaCaptor.capture());
            assertEquals("test_category", schemaCaptor.getValue().getName());
            assertEquals(null, schemaCaptor.getValue().getId());

            inorder.verify(categoryMapper).SchemaToResponse(schemaCaptor.capture());
            assertEquals("test_category", schemaCaptor.getValue().getName());   
            assertEquals(1L, schemaCaptor.getValue().getId());

            verifyNoMoreInteractions(categoryMapper,categoryRepository);
            
        }

        



    }




    
}
