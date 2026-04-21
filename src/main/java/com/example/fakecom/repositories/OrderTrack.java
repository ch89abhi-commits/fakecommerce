package com.example.fakecom.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fakecom.schema.Order;

public interface OrderTrack extends JpaRepository<Order, Long> {
    
}
