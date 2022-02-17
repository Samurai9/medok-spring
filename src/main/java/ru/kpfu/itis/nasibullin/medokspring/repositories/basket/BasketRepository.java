package ru.kpfu.itis.nasibullin.medokspring.repositories.basket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.kpfu.itis.nasibullin.medokspring.entities.Basket;

public interface BasketRepository extends JpaRepository<Basket, Integer> {
    Basket findBasketByUser_UserId(int id);

    @Query(value = "SELECT count(*) FROM basket_products WHERE basket_id = ?1",
    nativeQuery = true)
    int countProductInBasket(int basketId);

    @Query(value = "SELECT sum(products.price) FROM products WHERE product_id IN (SELECT basket_products.products_product_id FROM basket_products WHERE basket_id = ?1)",
    nativeQuery = true)
    double basketPrice(int basketId);
}
