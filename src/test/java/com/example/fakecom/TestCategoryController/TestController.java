package com.example.fakecom.TestCategoryController;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.fakecom.DTOs.RequestDTOs.CategoryRequest;
import com.example.fakecom.DTOs.ResponseDTOs.CategoryResponse;
import com.example.fakecom.Exceptions.NoUserExistException;
import com.example.fakecom.controllers.CategoryControllers.CategoryController;
import com.example.fakecom.schema.CategorySchema;
import com.example.fakecom.services.CategoryServices.CategoryService;

import tools.jackson.databind.ObjectMapper;

@WebMvcTest(CategoryController.class)// setting up the controller sectionn of this category controller class;as
public class TestController {
    // i need the spring application context also i require the mocks of the service layer then i use the mockmvc
    
    @Autowired
    private MockMvc mockmvc;

    @MockitoBean
    private CategoryService categoryService;
    @Autowired
    private ObjectMapper objectMapper;

    
    @Test
    void getcategory_byId() throws Exception{


        // arrange steps
        CategoryRequest test_data= CategoryRequest.builder().name("electronics").build();
        when(categoryService.getCategory(any(Long.class))).thenReturn(test_data);

        // assert and the act step in teh same time to perfoem the requrest of the apoi
        // mockmvc.perform(MockMvcRequestBuilders.get("/api/v1/category/1").accept(MediaType.APPLICATION_JSON).contentType("json").andExpect(Status().isOk());
            // for making the request i use the mockmvcrequestbuilder and for the expect i use th mockmvcresultmatcher both the builder methos

        mockmvc.perform(MockMvcRequestBuilders.get("/api/v1/category/1")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        
    )
        .andExpect(MockMvcResultMatchers.status().isOk());
    

    }
    @Test
    void getcategory_byId_throwserror() throws Exception{


        // arrange steps
        CategoryRequest test_data= CategoryRequest.builder().name("electronics").build();
        when(categoryService.getCategory(any(Long.class))).thenThrow(new NoUserExistException("not exist in database"));

        // assertThrows(NoUserExistException.class, categoryService.getCategory(any()));

        // assert and the act step in teh same time to perfoem the requrest of the apoi
        // mockmvc.perform(MockMvcRequestBuilders.get("/api/v1/category/1").accept(MediaType.APPLICATION_JSON).contentType("json").andExpect(Status().isOk());
            // for making the request i use the mockmvcrequestbuilder and for the expect i use th mockmvcresultmatcher both the builder methos

        mockmvc.perform(MockMvcRequestBuilders.get("/api/v1/category/1")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        
    )
        .andExpect(MockMvcResultMatchers.status().is5xxServerError());
        // .andExpect(MockMvcResultMatchers.jsonPath("$.message").value ("not exist in database"));
    

    }
    @Test
    void getallcategory()throws Exception{
        List<CategorySchema> test=new ArrayList<>(); // need to initate the object of type List
        CategorySchema c=CategorySchema.builder().name("electronics").build();
        test.add(c);    
        test.add(CategorySchema.builder().name("textile").build());
        test.add(CategorySchema.builder().name("skincare").build());

        when(categoryService.gettingAllCategory()).thenReturn(test);


        /// starting the request usignteh request builder pattehr 
        mockmvc.perform(MockMvcRequestBuilders.get("/api/v1/category/all")
        .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
        .andDo(MockMvcResultHandlers.print())// just for checking the log for the response that we have recieverd
        .andExpect(MockMvcResultMatchers.status().is(202))
        .andExpect(MockMvcResultMatchers.jsonPath("$.data").isArray())
        .andExpect(MockMvcResultMatchers.jsonPath("$.data.length()").value(3))
        .andExpect(MockMvcResultMatchers.jsonPath("$.data[0]").exists())
        .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].name").value("electronics"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].name").value("textile"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.data[2].name").value("skincare"))
        ;
           

    }
    @Test
    void getallcategory_returnError()throws Exception{
        

        when(categoryService.gettingAllCategory() ).thenThrow(new NoUserExistException("not exist in database") );


        /// starting the request usignteh request builder pattehr 
        mockmvc.perform(MockMvcRequestBuilders.get("/api/v1/category/all")
        .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
        .andDo(MockMvcResultHandlers.print())// just for checking the log for the response that we have recieverd
        .andExpect(MockMvcResultMatchers.status().is(502))
        .andExpect(MockMvcResultMatchers.jsonPath("$.error").exists());

         
        
           

    }
    @Test
    void getallcategory_returnnull()throws Exception{
        List<CategorySchema> ct=new ArrayList<>();// defining the array list;

        when(categoryService.gettingAllCategory() ).thenReturn(ct);


        /// starting the request usignteh request builder pattehr 
        mockmvc.perform(MockMvcRequestBuilders.get("/api/v1/category/all")
        .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
        .andDo(MockMvcResultHandlers.print())// just for checking the log for the response that we have recieverd
        .andExpect(MockMvcResultMatchers.status().is(202))
        // .andExpect(MockMvcResultMatchers.jsonPath("$.error").exists()) // for the inside values exist or not hte string one;;
        .andExpect(MockMvcResultMatchers.jsonPath("$.data").isEmpty());

         

    }
    @Test
    void createCategory_returnSuccess() throws Exception{
        CategoryResponse data=CategoryResponse.builder().name("test_category").id(1L).build();
        //data to be sent to the request handler {name:"test_category"}
        CategoryRequest input =CategoryRequest.builder().name("test_category").build();


        when(categoryService.addingCategory(any(CategoryRequest.class))).thenReturn(data);

        mockmvc.perform(MockMvcRequestBuilders.post("/api/v1/category/add")
        .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
        // .content("{\"name\": \"test_category\"}")
        .content(objectMapper.writeValueAsString(input))
    ).andDo(MockMvcResultHandlers.print())

        .andExpect(MockMvcResultMatchers.status().is(202))
        .andExpect(MockMvcResultMatchers.jsonPath("$.success").value("true"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.data").exists())
        .andExpect(MockMvcResultMatchers.jsonPath("$.data.name").value("test_category"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.data.id").value(1L))
        ;



    }
    @Test
    void createCategory_returnfailure() throws Exception{
        CategoryRequest input =CategoryRequest.builder().name("test_category").build();
        when(categoryService.addingCategory(any(CategoryRequest.class))).thenThrow(new NoUserExistException("some expetion occure when the two same object is called"));

        mockmvc.perform(MockMvcRequestBuilders.post("/api/v1/category/add").accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(input))
    )
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.jsonPath("$.error").exists())
        ;
        


    }


}
