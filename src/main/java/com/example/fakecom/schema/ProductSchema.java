package com.example.fakecom.schema;

 

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityResult;
import jakarta.persistence.FetchType;
import jakarta.persistence.FieldResult;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SqlResultSetMapping;
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
@EqualsAndHashCode(onlyExplicitlyIncluded=false,callSuper=true) // for considereing the equaltiy

// @SQLRestriction("deleted_at IS NULL")
@SqlResultSetMapping(  // use to mapp whwn their is the two same name of hte enttityr
    name = "entityMappingCategory",
    entities=@EntityResult(
        entityClass = ProductSchema.class,
        fields={
            @FieldResult(name="id",column="p_id"),
            @FieldResult(name="name",column="p_nmae"),
            @FieldResult(name="rating",column="p_rating"),
            @FieldResult(name="description",column="p_describe"),

        }
    )
)
@FilterDef(name="SeparateDeleteEnitites")
@Filter(name="SeparateDeleteEnitites" ,condition="deleted_at=null" )
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

    @EqualsAndHashCode.Exclude /// exclude for the circulart dependency dyring the finding teh equality of the two objects
    @ManyToOne(fetch=FetchType.LAZY) /// can be readed as many products belong to one category;
    @JoinColumn(name="category_id",referencedColumnName="id" , nullable=false) // in the table field name
    private CategorySchema category;  // this is in java world;


}
