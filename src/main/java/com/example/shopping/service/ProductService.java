package com.example.shopping.service;

import com.example.shopping.repository.ProductsRepository;
import com.example.shopping.model.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class ProductService {

    @Autowired
    ProductsRepository productsRepository;

    public List<Products> getProducts() {
        return productsRepository.findAll();
    }

    public Products addProduct(Products product) {
        return productsRepository.save(product);

    }

    public Products getProductByID(int id) {
        return productsRepository.findById(id);
    }

    public List<Products> getProductByCategory(String category) {
        return productsRepository.findAllByCategory(category);
    }

    public String deleteProduct(int id) {
        productsRepository.deleteById((long) id);
        return "deleted";
    }

    public String deleteAll() {
        productsRepository.deleteAll();
        return "done";
    }
    public Optional<Products> getProduct(Long id) {
        return productsRepository.findById(id);
    }

    public Products changeProductDescriptions(Products products) {
        Products oldProduct = productsRepository.findById(products.getId());
        oldProduct.setId(products.getId());
        oldProduct.setName(products.getName());
        oldProduct.setImage(products.getImage());
        oldProduct.setPrice(products.getPrice());
        oldProduct.setCategory(products.getCategory());
        oldProduct.setDetails(products.getDetails());
        productsRepository.saveAndFlush(oldProduct);
        return oldProduct;
    }
}
