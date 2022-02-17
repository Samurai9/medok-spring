package ru.kpfu.itis.nasibullin.medokspring.services.review;

import java.util.List;
import ru.kpfu.itis.nasibullin.medokspring.entities.Review;

public interface ReviewService {
    Review save(Review review);
    List<Review> findAllByProductId(int productId);
}
