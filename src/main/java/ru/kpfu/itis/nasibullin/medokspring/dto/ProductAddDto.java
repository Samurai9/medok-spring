package ru.kpfu.itis.nasibullin.medokspring.dto;

import java.util.TreeSet;
import ru.kpfu.itis.nasibullin.medokspring.entities.Product;
import ru.kpfu.itis.nasibullin.medokspring.entities.ProductCategory;

public class ProductAddDto {
    private String name;
    private String description;
    private ProductCategory category;
    private int remaining;
    private double size;
    private double price;

    public ProductAddDto() {
    }

    @Override
    public String toString() {
        return "ProductAddDto{" +
          "name='" + name + '\'' +
          ", description='" + description + '\'' +
          ", productCategory=" + category +
          ", remaining=" + remaining +
          ", size=" + size +
          ", price=" + price +
          '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public int getRemaining() {
        return remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Product toProduct() {
        Product product = new Product();

        product.setName(this.name);
        product.setDescription(this.description);
        product.setCategory(this.category);
        product.setPrice(this.price);
        product.setRating(0);
        product.setReviews(new TreeSet<>());
        product.setRemaining(this.remaining);
        product.setSize(this.size);
        product.setPicture(Product.DEFAULT_PICTURE_PATH);

        return product;
    }
}
