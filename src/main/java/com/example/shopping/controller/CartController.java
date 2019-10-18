package com.example.shopping.controller;

import com.example.shopping.model.Cart;
import com.example.shopping.model.OrderHistory;
import com.example.shopping.model.Products;
import com.example.shopping.service.ProductService;
import com.example.shopping.service.CartService;
import com.example.shopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class CartController {

    @Autowired

    CartService cartService;
    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;

    @GetMapping("/addToCart/{productId}")
    public String addToCart(@PathVariable("productId") int productId, Principal principal) {
        cartService.addProduct(userService.getUserIds(principal), productId);
        return "\"Added Product To Cart\"";
    }

    @GetMapping("/removeOneFromCart/{productId}")
    public String removeOneFromCart(@PathVariable("productId") int productId, Principal principal) {
        cartService.subtractProduct(userService.getUserIds(principal), productId);
        return "\"Removed One Product\"";
    }

    @GetMapping("/removeFromCart/{productId}")
    public String removeFromCart(@PathVariable("productId") int productId, Principal principal) {
        cartService.removeProduct(userService.getUserIds(principal), productId);
        return "\"Product Removed\"";
    }

    @GetMapping("/showCart")
    public List<Cart> showCart(Principal principal) {
        return cartService.showUserProducts(userService.getUserIds(principal));
    }
    @GetMapping("/checkout")
    public List<OrderHistory> checkOutFromCart(Principal principal) {
        return cartService.checkout(principal);

    }

    @PostMapping("/editProduct")
    public Products editUsers(@RequestBody Products products)
    {
        return productService.changeProductDescriptions(products);
    }

}
