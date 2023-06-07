package kz.iitu.abyshop.service;

import kz.iitu.abyshop.dto.ProductDto;
import kz.iitu.abyshop.model.User;
import kz.iitu.abyshop.model.WishList;
import kz.iitu.abyshop.repository.WishListRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WishListService {
    private final WishListRepository wishListRepository;
    private final ProductService productService;
    public WishList createWishList(WishList wishList) {
        return wishListRepository.save(wishList);
    }

    public Integer deleteWishList(Integer id){
        wishListRepository.deleteWishListById(id);
        return 1;
    }
    public List<ProductDto> getWishListForUser(User user) {
        final List<WishList> wishLists = wishListRepository.findAllByUserOrderByCreatedDateDesc(user);
        List<ProductDto> productDtos = new ArrayList<>();
        for(WishList wishList: wishLists){
            productDtos.add(productService.mapToDto(wishList.getProduct()));
        }
        return productDtos;
    }

}
