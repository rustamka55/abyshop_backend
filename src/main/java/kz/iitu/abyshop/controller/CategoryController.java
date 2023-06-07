package kz.iitu.abyshop.controller;

import kz.iitu.abyshop.model.Category;
import kz.iitu.abyshop.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<Category> createCategory(@RequestBody Category category){
        return new ResponseEntity<>(categoryService.createCategory(category), HttpStatus.CREATED);
    }
    @GetMapping("/list")
    public ResponseEntity<List<Category>> getAllCategories(){
        return new ResponseEntity<>(categoryService.findAllCategories(), HttpStatus.OK);
    }

    @PostMapping("/update/{categoryId}")
    public ResponseEntity<Category> updateCategory(@PathVariable String categoryId, @RequestBody Category category){
        return new ResponseEntity<>(categoryService.updateCategory(category, categoryId), HttpStatus.OK);
    }

    @PostMapping("/delete/{categoryId}")
    public ResponseEntity<List<Category>> deleteCategory(@PathVariable Integer categoryId){
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(categoryService.findAllCategories(), HttpStatus.OK);
    }


}
