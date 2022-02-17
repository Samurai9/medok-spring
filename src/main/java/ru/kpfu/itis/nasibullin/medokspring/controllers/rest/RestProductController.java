package ru.kpfu.itis.nasibullin.medokspring.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.nasibullin.medokspring.entities.Product;
import ru.kpfu.itis.nasibullin.medokspring.entities.ProductCategory;
import ru.kpfu.itis.nasibullin.medokspring.services.product.ProductService;

@RestController
public class RestProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/rest/product/{id}")
    public ResponseEntity getProduct(@PathVariable int id) {
        try {
            return ResponseEntity.ok(productService.findById(id).toString());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/rest/product/liquid")
    public ResponseEntity getAllLiquidProducts() {
        return ResponseEntity.ok(productService.findAllByCategory(ProductCategory.LIQUID));
    }

    @GetMapping("/rest/product/honeycomb")
    public ResponseEntity getAllHoneycombProducts() {
        return ResponseEntity.ok(productService.findAllByCategory(ProductCategory.HONEYCOMB));
    }

    @PutMapping("/rest/product/")
    public ResponseEntity addProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.save(product));
    }

    @DeleteMapping("/rest/product/{id}")
    public ResponseEntity deleteProduct(@PathVariable int id) {
        productService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/rest/product/{id}")
    public ResponseEntity updateProduct(@PathVariable int id, @RequestBody Product product) {
        try {
            product.setProductId(id);
            return ResponseEntity.ok(productService.update(product));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
