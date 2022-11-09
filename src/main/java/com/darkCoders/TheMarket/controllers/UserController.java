package com.darkCoders.TheMarket.controllers;

import com.darkCoders.TheMarket.models.Cart;
import com.darkCoders.TheMarket.models.User;
import com.darkCoders.TheMarket.models.dtos.CartDTO;
import com.darkCoders.TheMarket.models.dtos.UserDTO;
import com.darkCoders.TheMarket.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping(value = "/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody final User user){
        User newUser = userService.addUser(user);
        return new ResponseEntity<>(UserDTO.from(newUser), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers(){
        List<User> users = userService.getUsers();
        List<UserDTO> userDTOS = users.stream().map(UserDTO::from).collect(Collectors.toList());
        return new ResponseEntity<>(userDTOS, HttpStatus.OK);
    }
    @GetMapping(value = "{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable final long id){
        User user = userService.getUser(id);
        return new ResponseEntity<>(UserDTO.from(user), HttpStatus.OK);
    }
    @PutMapping(value = "{id}")
    public ResponseEntity<UserDTO> updateUser(@RequestBody final User user,@PathVariable final long id){
        User userToUpdate = userService.updateUser(id, user);
        return new ResponseEntity<>(UserDTO.from(userToUpdate), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable final long id){
        User userToDelete = userService.deleteUser(id);
        return new ResponseEntity<>(UserDTO.from(userToDelete), HttpStatus.OK);
    }
    @PostMapping(value = "{productId}/cart/{cartId}/quantity/{quantity}")
    public ResponseEntity<CartDTO> addProductToCart(@PathVariable final long productId, @PathVariable final long cartId, @PathVariable final int quantity){
        Cart cart = userService.addProductToCart(productId, cartId, quantity);
        return new ResponseEntity<>(CartDTO.from(cart), HttpStatus.OK);
    }
    @DeleteMapping(value = "{productId}/cart/{cartId}")
    public ResponseEntity<CartDTO> removeProductFromCart(@PathVariable final long productId, @PathVariable final long cartId){
        Cart cart = userService.deleteProductFromCart(productId, cartId);
        return new ResponseEntity<>(CartDTO.from(cart), HttpStatus.OK);
    }
}
