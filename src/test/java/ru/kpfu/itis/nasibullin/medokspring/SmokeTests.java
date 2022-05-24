package ru.kpfu.itis.nasibullin.medokspring;

import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.kpfu.itis.nasibullin.medokspring.services.basket.BasketService;
import ru.kpfu.itis.nasibullin.medokspring.services.product.ProductService;
import ru.kpfu.itis.nasibullin.medokspring.services.review.ReviewService;
import ru.kpfu.itis.nasibullin.medokspring.services.user.UserService;

import static org.assertj.core.api.Assertions.assertThat;


@AutoConfigureEmbeddedDatabase(refresh = AutoConfigureEmbeddedDatabase.RefreshMode.AFTER_CLASS)
@SpringBootTest
public class SmokeTests {
    @Autowired
    private UserService userService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ProductService productService;

    @Autowired
    private BasketService basketService;


    @Test
    void userServiceTest() throws Exception {
        assertThat(userService).isNotNull();
    }

    @Test
    void reviewServiceTest() throws Exception {
        assertThat(reviewService).isNotNull();
    }

    @Test
    void basketServiceTest() throws Exception {
        assertThat(basketService).isNotNull();
    }

    @Test
    void productServiceTest() throws Exception {
        assertThat(productService).isNotNull();
    }
}
