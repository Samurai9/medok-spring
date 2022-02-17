package ru.kpfu.itis.nasibullin.medokspring.services.basket;

import ru.kpfu.itis.nasibullin.medokspring.dto.BasketAction;
import ru.kpfu.itis.nasibullin.medokspring.entities.Basket;
import ru.kpfu.itis.nasibullin.medokspring.entities.User;

public interface BasketService {
    Basket getBasketByUserId(int id);
    Basket addProductToBasket(User user, int productId);
    Basket addProductToBasket(int userId, int productId);
    Basket delete(User user, int productId);
    boolean handle(BasketAction basketAction);
    int countProductsInBasket(int basketId);
    double getBasketPrice(int basketId);
}
