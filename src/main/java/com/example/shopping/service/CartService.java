package com.example.shopping.service;

import com.example.shopping.model.Cart;
import com.example.shopping.model.OrderHistory;
import com.example.shopping.model.Products;
import com.example.shopping.model.User;
import com.example.shopping.repository.CartRepository;
import com.example.shopping.repository.OrderHistoryRepository;
import com.example.shopping.repository.ProductsRepository;
import com.example.shopping.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Transactional
@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductsRepository productsRepository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    private OrderHistoryRepository orderHistoryRepository;

    /*@Autowired
    OrderHistoryRepository orderHistoryRepository;
*/

    public void addProduct(int user_id, int product_id) {

        Products products = productsRepository.findById(product_id);

        User users = userRepository.findByUserid(user_id);

        if(cartRepository.findByUsersAndProduct(users, products)!=null) {

            Cart cart = (Cart) cartRepository.findByUsersAndProduct(users, products);
            cart.setQuantity(cart.getQuantity()+1);
            cartRepository.save(cart);
        } else {
            Cart cart = new Cart(1,products, users );
            cartRepository.save(cart);
        }

        /*return cartRepository.findByUsersAndProducts(users, products);*/
    }


    public void removeProduct(int userId, int productId) {
        Products products = productsRepository.findById(productId);
        User users = userRepository.findByUserid(userId);
        cartRepository.deleteAllByUsersAndProduct(users, products);
        /*return "removed";*/
    }

    public List<Cart> showUserProducts(int userId) {
        User users = userRepository.findByUserid(userId);
        return cartRepository.findByUsers(users);
    }


    public void subtractProduct(int userId, int productId) {

        Products products = productsRepository.findById(productId);

        User users = userRepository.findByUserid(userId);

        if(cartRepository.findByUsersAndProduct(users, products)!=null) {

            Cart cart = (Cart) cartRepository.findByUsersAndProduct(users, products);
            if(cart.getQuantity()>=2) {
                cart.setQuantity(cart.getQuantity() - 1);
                cartRepository.save(cart);
            } else if(cart.getQuantity()==1) {
                removeProduct(userId, productId);
            }
        }
        /*return (Cart) cartRepository.findByUsersAndProducts(users, products);*/

    }

    public List<OrderHistory> checkout(Principal principal) {
        User users = userRepository.getByEmail(principal.getName());
        ArrayList<Cart> cartList = cartRepository.findAllByUsers(users);
        for(Cart cart : cartList) {
            OrderHistory orderHistory = new OrderHistory();
            orderHistory.setUserId((long) cart.getUsers().getUserid());
            orderHistory.setQuantity(cart.getQuantity());
            orderHistory.setPrice((double) cart.getProduct().getPrice());
            orderHistory.setName(cart.getProduct().getName());
            orderHistory.setImage(cart.getProduct().getImage());
            orderHistory.setProductId((long) cart.getProduct().getId());
            orderHistory.setDate(new Date());
            cartRepository.delete(cart);
            orderHistoryRepository.saveAndFlush(orderHistory);
        }
        return orderHistoryRepository.findAllByUserId((long) users.getUserid());
    }
}









