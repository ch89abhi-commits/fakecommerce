package com.example.fakecom.TestCategoryService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.fakecom.DTOs.RequestDTOs.CategoryRequest;
import com.example.fakecom.ObjectMappers.CategoryMapper;
import com.example.fakecom.repositories.CategoryRepository;
import com.example.fakecom.schema.CategorySchema;
import com.example.fakecom.services.CategoryServices.CategoryService;

@ExtendWith(MockitoExtension.class)
public class TestCategory {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryMapper categoryMapper;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    void addingCategory_returnCategory(){
        CategorySchema beforeSave=CategorySchema.builder().name("test _category").build();
        CategorySchema afterSave= CategorySchema.builder().name("test _category").build();
        afterSave.setId(1L);

        CategoryRequest inputSchema= CategoryRequest.builder().name("test _category").build();
        CategoryRequest outputSchema= CategoryRequest.builder().name("test _category").build();
        
        

        when(categoryMapper.DTOtoSchema(any(CategoryRequest.class))).thenReturn(beforeSave);//first case;

        when(categoryRepository.save(any(CategorySchema.class))).thenReturn(afterSave);

        when(categoryMapper.SchemaToDTO(any(CategorySchema.class))).thenReturn(outputSchema);


        CategoryRequest result=categoryService.addingCategory(inputSchema);
        assertNotNull(result);
        assertEquals("test _category", result.getName());
        // assertEquals(1L, result.wait(timeoutMillis, nanos));

        //conformastion for the flow is correct or not
        
        verify(categoryMapper).DTOtoSchema(inputSchema);
        verify(categoryRepository).save(beforeSave);
        verify(categoryMapper).SchemaToDTO(afterSave);




    }

    void addingCategory_callingMapper(){
        
    }
    
}
