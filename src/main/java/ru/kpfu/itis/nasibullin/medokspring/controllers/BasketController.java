package ru.kpfu.itis.nasibullin.medokspring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kpfu.itis.nasibullin.medokspring.dto.BasketAction;
import ru.kpfu.itis.nasibullin.medokspring.entities.User;
import ru.kpfu.itis.nasibullin.medokspring.services.basket.BasketService;
import ru.kpfu.itis.nasibullin.medokspring.services.product.ProductService;
import ru.kpfu.itis.nasibullin.medokspring.utils.AuthUtils;
import ru.kpfu.itis.nasibullin.medokspring.utils.BasketUtils;
import ru.kpfu.itis.nasibullin.medokspring.utils.UserUtils;

@Controller
public class BasketController {
    @Autowired
    private BasketService basketService;
    @Autowired
    private ProductService productService;

    @GetMapping("/basket")
    public String getBasketPage(Model model) {
        User user = AuthUtils.getUser();
        model.addAttribute("title", "Корзина");

        model.addAttribute("price", UserUtils.getPrice(user));
        model.addAttribute("basket", BasketUtils.getHashMap(user.getBasket()));
        model.addAttribute("productsCount", basketService.countProductsInBasket(user.getBasket().getId()));

        return "basket";
    }

    @PostMapping("/basket")
    public ResponseEntity handleBasket(Model model, BasketAction basketAction) {
        basketAction.setUser(AuthUtils.getUser());
        basketService.handle(basketAction);
        return ResponseEntity.ok().build();
    }

}
