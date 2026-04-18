package com.example.fakecom.schema;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(onlyExplicitlyIncluded=true,callSuper=true)
@EqualsAndHashCode(onlyExplicitlyIncluded=true,callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Create_products")
public class OrderProduct extends BaseEntity{

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="order_id")
    public Order order; // this is only to track the order detatils onces the order is createed

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="product_id",nullable=false)
    public ProductSchema product; //for the creating order only for the one single product

    @Column(nullable=false)
    public Integer quantity;
    
}
