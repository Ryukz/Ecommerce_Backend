package com.example.shopping.controller;
import com.example.shopping.model.Products;
import com.example.shopping.repository.ProductsRepository;
import com.example.shopping.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
@RestController
@RequestMapping(value = "/show",method = {RequestMethod.GET,RequestMethod.DELETE,RequestMethod.POST})
public class
ProductController
{
    @Autowired
    ProductsRepository productsRepository;
    @Autowired
    ProductService productService;

    @GetMapping(path = "/all")
    public List<Products> getAll()
    {
        return productService.getProducts();
    }
    @GetMapping("/product/{id}")
    public Products getProduct(@PathVariable("id") int id)
    {
        return productService.getProductByID(id);
    }
    @GetMapping("/product-detail/{category}")
    public List<Products> getProduct(@PathVariable("category") String category)
    {
        List<Products> list =  productService.getProductByCategory(category);
        System.out.println(list);
        return productService.getProductByCategory(category);
    }
    @PostMapping(path = "/enter", consumes = "application/json", produces = "application/json")
    public Products addProduct(@RequestBody Products product)
    {

        return productService.addProduct(product);
    }
    @DeleteMapping("/product-del/{id}")
    public String deleteProduct(@PathVariable("id") int id)
    {
        return productService.deleteProduct(id);
    }
    @DeleteMapping("/del")
    public String deleteAll()
    {
        return productService.deleteAll();
    }
    @GetMapping("/product-detail/{id}")
    public Optional<Products> getProduct(@PathVariable("id") Long id) {
        return productService.getProduct(id);
    }
}
