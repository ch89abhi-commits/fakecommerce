package com.example.fakecom.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fakecom.schema.OrderProduct;

@Repository
public interface OrderRepository extends JpaRepository<OrderProduct, Long> {



    
}
