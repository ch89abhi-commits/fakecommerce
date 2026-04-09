package com.example.fakecom.schema;

 

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="Products")
@ToString(callSuper=true)
@EqualsAndHashCode(onlyExplicitlyIncluded=false,callSuper=true)

// @SQLRestriction("deleted_at IS NULL")
public class ProductSchema extends  BaseEntity{

    @Column(nullable=false)
    @ToString.Include
    private String name;
    
    @Column(columnDefinition="TEXT")
    @ToString.Include
    private String description;
    
    @Column(nullable=false)
    @ToString.Include
    private Float price;  

    @ToString.Include
    private Float rating;

    private String image;// the url of the images that are stored in the other bucket like the s3 bucket in the amazon




}
