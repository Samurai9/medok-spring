package ru.kpfu.itis.nasibullin.medokspring.repositories.review;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.nasibullin.medokspring.entities.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findAllByProduct_ProductIdOrderByCreatedAt(int productId);
}
