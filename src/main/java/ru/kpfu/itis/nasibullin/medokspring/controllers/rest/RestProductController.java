package ru.kpfu.itis.nasibullin.medokspring.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.nasibullin.medokspring.entities.Product;
import ru.kpfu.itis.nasibullin.medokspring.entities.ProductCategory;
import ru.kpfu.itis.nasibullin.medokspring.services.product.ProductService;

import java.util.List;

@RestController
public class RestProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/rest/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id) {
        try {
            return ResponseEntity.ok(productService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/rest/product/liquid")
    public ResponseEntity<List<Product>> getAllLiquidProducts() {
        return ResponseEntity.ok(productService.findAllByCategory(ProductCategory.LIQUID));
    }

    @GetMapping("/rest/product/honeycomb")
    public ResponseEntity<List<Product>> getAllHoneycombProducts() {
        return ResponseEntity.ok(productService.findAllByCategory(ProductCategory.HONEYCOMB));
    }

    @PutMapping("/rest/product/")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.save(product));
    }

    @DeleteMapping("/rest/product/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable int id) {
        productService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/rest/product/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product product) {
        try {
            product.setProductId(id);
            return ResponseEntity.ok(productService.update(product));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
