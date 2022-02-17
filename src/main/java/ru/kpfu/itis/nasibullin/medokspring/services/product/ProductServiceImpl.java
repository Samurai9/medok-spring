package ru.kpfu.itis.nasibullin.medokspring.services.product;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.nasibullin.medokspring.entities.Product;
import ru.kpfu.itis.nasibullin.medokspring.entities.ProductCategory;
import ru.kpfu.itis.nasibullin.medokspring.entities.Review;
import ru.kpfu.itis.nasibullin.medokspring.repositories.product.ProductRepository;
import ru.kpfu.itis.nasibullin.medokspring.services.review.ReviewService;

@Service
public class ProductServiceImpl implements ProductService {
    private static final String PICTURE_PATH = "/img/ProductImages/";

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ReviewService reviewService;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findPopular() {
        return productRepository.findProductsByRatingGreaterThan(4.0);
    }

    @Override
    public Product findById(int id) {
        return productRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public List<Product> findAllByPattern(String pattern) {
        return productRepository.findProductsByNameContains(pattern);
    }

    @Override
    public List<Product> findAllByCategory(ProductCategory productCategory) {
        return productRepository.findAllByCategory(productCategory);
    }

    @Override
    public Product update(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateByReview(int productId, Review review) {
        Product product = findById(productId);
        product.setRating((double) (product.getReviews().size() * product.getRating() + review.getRating())/(product.getReviews().size()+1));
        product.getReviews().add(review);
        review.setProduct(product);
        reviewService.save(review);
        return productRepository.save(product);
    }

    @Override
    public Product save(Product product) {
        product = productRepository.save(product);
        product.setPicture(PICTURE_PATH + product.getProductId() + ".png");
        return productRepository.save(product);
    }

    @Override
    public void delete(int productId) {
        productRepository.deleteById(productId);
    }
}
