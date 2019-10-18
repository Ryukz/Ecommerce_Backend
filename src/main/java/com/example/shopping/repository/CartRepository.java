package com.example.shopping.repository;

import com.example.shopping.model.Cart;
import com.example.shopping.model.Products;
import com.example.shopping.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository

public interface CartRepository extends JpaRepository<Cart,Integer> {
 Object findByUsersAndProduct(User user, Products products);

    void deleteAllByUsersAndProduct(User users, Products products);

    List<Cart> findByUsers(User users);
    ArrayList<Cart> findAllByUsers(User users);
}
