package com.example.fakecom.schema;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@MappedSuperclass // it will show that it will not create any class but can be refrenced for the inhertiance
@EqualsAndHashCode(onlyExplicitlyIncluded=false) // this is for the hashing code that is used to put into the hash set
@ToString(onlyExplicitlyIncluded=false) // to hav e the to string methods of the logging purpose;
@EntityListeners(AuditingEntityListener.class) //this is not working as per the desired way
public class BaseEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @ToString.Include
    private Long id;

    @CreatedDate
    @Column(name="created_at",nullable=false,updatable=false)
    private LocalDateTime createdAt;

    @CreatedDate
    @Column(name="deleted_at")
    private LocalDateTime deleteAt;

    @LastModifiedDate
    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    
}
