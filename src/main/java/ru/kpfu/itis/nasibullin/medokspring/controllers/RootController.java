package ru.kpfu.itis.nasibullin.medokspring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kpfu.itis.nasibullin.medokspring.services.product.ProductService;

@Controller
public class RootController {
    @Autowired
    private ProductService productService;

    @GetMapping("/welcome")
    public String getWelcomePage(Model model) {
        model.addAttribute("title", "Med.Ok");
        model.addAttribute("products", productService.findPopular());
        return "index";
    }

    @GetMapping("/")
    public String redirectToWelcome() {
        return "redirect:/welcome";
    }
}
