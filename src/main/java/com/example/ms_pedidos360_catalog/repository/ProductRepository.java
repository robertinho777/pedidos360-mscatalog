package com.example.ms_pedidos360_catalog.repository;

import com.example.ms_pedidos360_catalog.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
