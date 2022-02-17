package ru.kpfu.itis.nasibullin.medokspring.services.review;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.nasibullin.medokspring.entities.Review;
import ru.kpfu.itis.nasibullin.medokspring.repositories.review.ReviewRepository;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> findAllByProductId(int productId) {
        return reviewRepository.findAllByProduct_ProductIdOrderByCreatedAt(productId);
    }
}
