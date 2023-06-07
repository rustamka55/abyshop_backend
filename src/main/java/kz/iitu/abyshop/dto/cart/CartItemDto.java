package kz.iitu.abyshop.dto.cart;

import kz.iitu.abyshop.model.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartItemDto {
    private Integer id;
    private Integer quantity;
    private Product product;
}
