package com.example.fakecom.ObjectMappers;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.fakecom.DTOs.RequestDTOs.CategoryRequest;
import com.example.fakecom.schema.CategorySchema;

@Mapper(componentModel="spring")

public interface CategoryMapper {


    @Mapping(source="name",target="name")
    CategorySchema DTOtoSchema (CategoryRequest data);
    
    @InheritInverseConfiguration(name="DTOtoSchema")
    CategoryRequest SchemaToDTO(CategorySchema data);

    List<CategorySchema> conversionList(List<CategoryRequest> data);

    @InheritInverseConfiguration(name="conversionList")
    List<CategoryRequest> listConversion(List<CategorySchema> data);

    
}
