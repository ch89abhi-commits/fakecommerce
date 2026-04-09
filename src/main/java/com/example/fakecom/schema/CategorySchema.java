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
@Table(name="Categories")
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=false)
public class CategorySchema extends  BaseEntity {

  
   @Column(nullable=false)
    private String name;

    
}
