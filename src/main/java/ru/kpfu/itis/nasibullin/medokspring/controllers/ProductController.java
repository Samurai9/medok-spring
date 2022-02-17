package ru.kpfu.itis.nasibullin.medokspring.controllers;

import java.util.Date;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import ru.kpfu.itis.nasibullin.medokspring.dto.ProductAddDto;
import ru.kpfu.itis.nasibullin.medokspring.entities.Product;
import ru.kpfu.itis.nasibullin.medokspring.entities.ProductCategory;
import ru.kpfu.itis.nasibullin.medokspring.entities.Review;
import ru.kpfu.itis.nasibullin.medokspring.exceptions.IllegalProductIdException;
import ru.kpfu.itis.nasibullin.medokspring.exceptions.NotFoundException;
import ru.kpfu.itis.nasibullin.medokspring.services.product.ProductService;
import ru.kpfu.itis.nasibullin.medokspring.services.review.ReviewService;
import ru.kpfu.itis.nasibullin.medokspring.utils.AuthUtils;

@Controller
@RequestMapping(path = "/product/**")
public class ProductController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ProductService productService;
    @Autowired
    private ReviewService reviewService;

    @GetMapping("/liquid")
    public String getLiquidHoneyPage(Model model) {
        model.addAttribute("title", "Жидкий мёд");
        model.addAttribute("products", productService.findAllByCategory(ProductCategory.LIQUID));
        return "productsList";
    }


    @GetMapping("/honeycomb")
    public String getHoneycombHoneyPage(Model model) {
        model.addAttribute("title", "Сотовый мёд");
        model.addAttribute("products", productService.findAllByCategory(ProductCategory.HONEYCOMB));
        return "productsList";
    }

    @GetMapping("/{id}")
    public String getProductPage(Model model, @PathVariable Integer id) {
        try {
            Product product = productService.findById(id);
            model.addAttribute("title", product.getName());
            model.addAttribute("product", product);
            return "productPage";
        } catch (NumberFormatException | MethodArgumentTypeMismatchException e) {
            throw new IllegalProductIdException("Cannot convert " + id + " to product id");
        } catch (IllegalArgumentException e) {
            throw new NotFoundException("Cannot found product witt id: " + id);
        }
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping ("/{id}/review")
    public String addNewReview(Model model, @PathVariable int id, @RequestParam String text, @RequestParam int rating) {
        try {
            Review review = new Review();
            review.setProduct(productService.findById(id));
            review.setCreatedAt(new Date(System.currentTimeMillis()));
            review.setRating(rating);
            review.setText(text);
            review.setAuthor(AuthUtils.getUser());

            productService.updateByReview(id, review);
            return "redirect:/product/" + id;
        } catch (NumberFormatException e) {
            throw new IllegalProductIdException("Cannot convert " + id + " to product id");
        } catch (IllegalArgumentException e) {
            throw new NotFoundException("Cannot found product with id: " + id);
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/add")
    public String addProductPage(Model model) {
        model.addAttribute("title", "Добавление продукта");
        model.addAttribute("categories", ProductCategory.values());
        return "addProductPage";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/add")
    public String addProduct(Model model, @Valid ProductAddDto productAddDto, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            Product product = productAddDto.toProduct();
            productService.save(product);
            logger.info("Product " + product.toString() + " has been added");
            return "redirect:/product/add";
        }
        return "redirect:/product/add";
    }
}
