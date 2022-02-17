package ru.kpfu.itis.nasibullin.medokspring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.itis.nasibullin.medokspring.services.product.ProductService;

@Controller
public class SearchController {
    @Autowired
    private ProductService productService;

    @GetMapping("/search")
    public String getSearchedProducts(Model model, @RequestParam String text) {
        if (text != null) {
            model.addAttribute("title", text);
            model.addAttribute("products", productService.findAllByPattern(text));
            return "productsList";
        } else {
            return "redirect:/welcome";
        }

    }
}
