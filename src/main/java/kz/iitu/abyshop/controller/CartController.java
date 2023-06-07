package kz.iitu.abyshop.controller;

import kz.iitu.abyshop.dto.cart.CartDto;
import kz.iitu.abyshop.dto.cart.CartResponseDto;
import kz.iitu.abyshop.model.Cart;
import kz.iitu.abyshop.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    @PostMapping("/add")
    public ResponseEntity<Cart> addToCart(@RequestBody CartDto cartDto, @RequestParam("token") String token){
        return new ResponseEntity<>(cartService.addToCart(cartDto, token), HttpStatus.CREATED);
    }
    @GetMapping("/list")
    public ResponseEntity<CartResponseDto> getAllItemsFromCart(@RequestParam("token") String token){
        return new ResponseEntity<>(cartService.getAllItemsFromCart(token), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{cartItemId}")
    public ResponseEntity<Cart> deleteCartItem(@PathVariable String cartItemId, @RequestParam("token") String token){
        return new ResponseEntity<>(cartService.deleteCartItem(cartItemId, token), HttpStatus.NO_CONTENT);
    }
}
