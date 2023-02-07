package com.example.jpa.pagination.Repo;

import com.example.jpa.pagination.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
