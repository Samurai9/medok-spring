package ru.kpfu.itis.nasibullin.medokspring.repositories.product;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.nasibullin.medokspring.entities.Product;
import ru.kpfu.itis.nasibullin.medokspring.entities.ProductCategory;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findProductsByRatingGreaterThan(double rating);
    List<Product> findProductsByNameContains(String pattern);
    List<Product> findAllByCategory(ProductCategory category);
}
