package com.example.shopping.repository;

import com.example.shopping.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddToProductRepository extends JpaRepository<Products,Integer>{

}
