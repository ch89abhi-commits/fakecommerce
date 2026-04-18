package com.example.fakecom.schema;

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
@ToString(onlyExplicitlyIncluded=true,callSuper=true)
@EqualsAndHashCode(onlyExplicitlyIncluded=true,callSuper=true)
@Entity

@Table(name="orders")

public class Order extends  BaseEntity {

    public OrderStatus status;

    
}
