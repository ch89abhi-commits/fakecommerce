package com.example.fakecom.controllers.CategoryControllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.fakecom.DTOs.RequestDTOs.CategoryRequest;
import com.example.fakecom.Utility.ClientResponse;
import com.example.fakecom.controllers.CategoryControllers.V1_Controller.ReadContoller;
import com.example.fakecom.controllers.CategoryControllers.V1_Controller.WriteCategory;
import com.example.fakecom.schema.CategorySchema;
import com.example.fakecom.services.CategoryServices.CategoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@RequestMapping("/api/v1/category") // it takes ideally forom the class as mentioned in the spring boot documents
public class CategoryController implements ReadContoller,WriteCategory{

   public final CategoryService categoryService;



   @Override
   public CategoryRequest getCategory( Long id){

      return categoryService.getCategory(id);
    

   } 

   @Override
   public  ResponseEntity<ClientResponse<Object>> addingCategory(CategoryRequest data){
      return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                  ClientResponse.SuccessResponse(HttpStatus.ACCEPTED.value(), 
                                 categoryService.addingCategory(data)                  
               )
      );
   }

  @Override
   public ResponseEntity<ClientResponse<List<CategorySchema>>> getAll(){

     return  ResponseEntity.status(HttpStatus.ACCEPTED).body(
         ClientResponse.SuccessResponse(HttpStatus.ACCEPTED.value(), 
                        categoryService.gettingAllCategory()                  
      )
);

   }

      @Override
   public ResponseEntity<ClientResponse<Object>> deletingCategory(Long id){
      return ResponseEntity.status(HttpStatus.ACCEPTED).body(ClientResponse.SuccessResponse(HttpStatus.ACCEPTED.value(), 
               categoryService.categoryDelete(id)
            ));
   }

    
}
