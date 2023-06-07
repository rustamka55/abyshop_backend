package kz.iitu.abyshop.controller;

import kz.iitu.abyshop.dto.ProductDto;
import kz.iitu.abyshop.model.Category;
import kz.iitu.abyshop.model.Product;
import kz.iitu.abyshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestBody ProductDto productDto){
        return new ResponseEntity<>(productService.createProduct(productDto), HttpStatus.CREATED);
    }
    @GetMapping("/list")
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }
    @PostMapping("/edit/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable String productId, @RequestBody ProductDto productDto){
        return new ResponseEntity<>(productService.updateProduct(productDto, productId), HttpStatus.OK);
    }

    @PostMapping("/delete/{productId}")
    public ResponseEntity<List<ProductDto>> deleteProduct(@PathVariable Integer productId){
        productService.deleteProduct(productId);
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }
}
