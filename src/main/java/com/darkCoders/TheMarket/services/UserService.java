package com.darkCoders.TheMarket.services;

import com.darkCoders.TheMarket.models.Cart;
import com.darkCoders.TheMarket.models.Product;
import com.darkCoders.TheMarket.models.User;
import com.darkCoders.TheMarket.models.exceptions.UserNotFoundException;
import com.darkCoders.TheMarket.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final CartServices cartService;
    private final ProductService productService;
    @Autowired
    public UserService(UserRepository userRepository, CartServices cartService, ProductService productService) {
        this.userRepository = userRepository;
        this.cartService = cartService;
        this.productService = productService;
    }
    public User addUser(User newUser){
        Cart cart = new Cart();
        cartService.addCart(cart);
        newUser.setCart(cart);
        //cart.setUser(newUser);
        return  userRepository.save(newUser);
    }
    public List<User> getUsers(){
        return StreamSupport.stream(userRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }
    public User getUser(long id){
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }
    @Transactional
    public User updateUser(long id, User user){
        User userToUpdate = getUser(id);

        if (Objects.nonNull(user.getName()))
            userToUpdate.setName(user.getName());
        if (Objects.nonNull(user.getAddress()))
            userToUpdate.setAddress(user.getAddress());
        if (Objects.nonNull(user.getEmail()))
            userToUpdate.setEmail(user.getEmail());

        return userToUpdate;
    }
    public User deleteUser(long id){
        User userToDelete = getUser(id);
        userRepository.delete(userToDelete);
        return userToDelete;
    }
    @Transactional
    public Cart addProductToCart(long productId, long cartId, int quantity){
        Cart cart = cartService.getCart(cartId);
        Product product = productService.getProduct(productId);
        product.setQuantity(quantity);
        cart.addProduct(product);
        return cart;
    }
    @Transactional
    public Cart deleteProductFromCart(long productId, long cartId){
        Cart  cart = cartService.getCart(cartId);
        Product product = productService.getProduct(productId);
        cart.removeProduct(product);
        return  cart;
    }
    @Transactional
    public Cart increaseQuantity(long cartId, int quantity, int productId){
        Cart cart = cartService.getCart(cartId);
        Product product = productService.getProduct(productId);
        product.setQuantity(product.getQuantity()+quantity);
        return cart;
    }
}
