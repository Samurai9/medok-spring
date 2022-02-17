package ru.kpfu.itis.nasibullin.medokspring.services.product;

import java.util.List;
import ru.kpfu.itis.nasibullin.medokspring.entities.Product;
import ru.kpfu.itis.nasibullin.medokspring.entities.ProductCategory;
import ru.kpfu.itis.nasibullin.medokspring.entities.Review;

public interface ProductService {
    //Create
    Product save(Product product);

    //Read
    List<Product> findAll();
    List<Product> findPopular();
    Product findById(int id);
    List<Product> findAllByPattern(String pattern);
    List<Product> findAllByCategory(ProductCategory productCategory);


    //Update
    Product update(Product product);
    Product updateByReview(int productId, Review review);

    //Delete
    void delete(int productId);
}
