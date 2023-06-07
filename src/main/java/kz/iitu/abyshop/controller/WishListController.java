package kz.iitu.abyshop.controller;

import kz.iitu.abyshop.dto.ProductDto;
import kz.iitu.abyshop.model.Product;
import kz.iitu.abyshop.model.User;
import kz.iitu.abyshop.model.WishList;
import kz.iitu.abyshop.service.AuthenticationService;
import kz.iitu.abyshop.service.WishListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/wish-list")
@RequiredArgsConstructor
public class WishListController {
    private final WishListService wishListService;
    private final AuthenticationService authenticationService;

    @PostMapping("/add")
    public ResponseEntity<WishList> addToWishList(@RequestBody Product product, @RequestParam("token") String token){
        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);
        WishList wishList = new WishList();
        wishList.setUser(user);
        wishList.setProduct(product);
        wishList.setCreatedDate(new Date());
        System.out.println(wishList);
        return new ResponseEntity<>(wishListService.createWishList(wishList), HttpStatus.CREATED);
    }
    @GetMapping("/{token}")
    public ResponseEntity<List<ProductDto>> getWishList(@PathVariable String token){
        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);
        return new ResponseEntity<>(wishListService.getWishListForUser(user), HttpStatus.OK);
    }

    @PostMapping("/delete/{wishListId}")
    public ResponseEntity<List<ProductDto>> deleteWishList(@PathVariable Integer wishListId,
                                                           @RequestParam("token") String token){
        wishListService.deleteWishList(wishListId);
        return getWishList(token);
    }
}
