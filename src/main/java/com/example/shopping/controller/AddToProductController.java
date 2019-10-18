package com.example.shopping.controller;
import com.example.shopping.model.Products;
import com.example.shopping.repository.AddToProductRepository;
import com.example.shopping.service.AddToProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/")

public class AddToProductController {
    @Autowired
    AddToProductService addToProductService;
    @PostMapping(path = "/enterproduct", consumes = "application/json", produces = "application/json")
    public Products addProduct(@RequestBody Products product)
    {

        return addToProductService.addProduct(product);
    }

}
