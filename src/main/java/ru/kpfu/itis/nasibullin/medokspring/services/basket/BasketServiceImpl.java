package ru.kpfu.itis.nasibullin.medokspring.services.basket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.nasibullin.medokspring.dto.BasketAction;
import ru.kpfu.itis.nasibullin.medokspring.entities.Basket;
import ru.kpfu.itis.nasibullin.medokspring.entities.User;
import ru.kpfu.itis.nasibullin.medokspring.exceptions.IllegalBasketActionException;
import ru.kpfu.itis.nasibullin.medokspring.repositories.basket.BasketRepository;
import ru.kpfu.itis.nasibullin.medokspring.repositories.product.ProductRepository;
import ru.kpfu.itis.nasibullin.medokspring.repositories.user.UserRepository;

@Service
public class BasketServiceImpl implements BasketService {
    @Autowired
    private BasketRepository basketRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;


    @Override
    public Basket getBasketByUserId(int id) {
        return basketRepository.findBasketByUser_UserId(id);
    }

    @Override
    public Basket addProductToBasket(User user, int productId) {
        user.getBasket().addProduct(productRepository.findById(productId).orElseThrow(IllegalArgumentException::new));
        return basketRepository.save(user.getBasket());
    }

    @Override
    public Basket addProductToBasket(int userId, int productId) {
        User user = userRepository.findById(userId).orElseThrow(IllegalArgumentException::new);
        user.getBasket().addProduct(productRepository.findById(productId).orElseThrow(IllegalArgumentException::new));
        return basketRepository.save(user.getBasket());
    }

    @Override
    public Basket delete(User user, int productId) {
        return basketRepository.save(user.getBasket().deleteProduct(productId));
    }

    @Override
    public boolean handle(BasketAction basketAction) {
        switch (basketAction.getMethod()) {
            case "PUT":
                this.addProductToBasket(basketAction.getUser(), basketAction.getProductId());
                return true;
            case "DELETE":
                this.delete(basketAction.getUser(), basketAction.getProductId());
                return true;
            default:
                throw new IllegalBasketActionException("Cannot handle action: " + basketAction.getMethod());
        }
    }

    @Override
    public int countProductsInBasket(int basketId) {
        return basketRepository.countProductInBasket(basketId);
    }

    @Override
    public double getBasketPrice(int basketId) {
        return basketRepository.basketPrice(basketId);
    }
}
