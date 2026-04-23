package com.example.fakecom.ObjectMappers;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.example.fakecom.DTOs.RequestDTOs.CategoryRequest;
import com.example.fakecom.DTOs.ResponseDTOs.CategoryResponse;
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

    @BeanMapping(ignoreByDefault=true,nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source="name",target="name")
    @Mapping(source="id",target="id")
    CategoryResponse SchemaToResponse(CategorySchema data);
    // CategoryResponse Response(CategorySchema data);


}
