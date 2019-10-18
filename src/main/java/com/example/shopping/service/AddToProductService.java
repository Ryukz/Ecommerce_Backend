package com.example.shopping.service;

import com.example.shopping.repository.AddToProductRepository;
import com.example.shopping.model.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AddToProductService {
    @Autowired
    AddToProductRepository addToProductRepository;
    public Products addProduct(Products product)
    {
        return addToProductRepository.save(product);

    }

}
