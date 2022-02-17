package ru.kpfu.itis.nasibullin.medokspring.entities;

import java.util.Objects;
import java.util.Set;
import java.util.SortedSet;
import javax.persistence.*;

@Entity(name = "products")
public class Product {
    public static final String DEFAULT_PICTURE_PATH = "\\img\\ProductImages\\default-product-picture.png";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;
    private String name;
    private String description;
    private int remaining;
    private double size;
    private double price;
    private double rating;
    private String picture;

    @Enumerated(value = EnumType.STRING)
    private ProductCategory category;
    @OneToMany(cascade = CascadeType.ALL)
    @OrderBy("created_at")
    private SortedSet<Review> reviews;

    public Product() {
    }

    public Product(int productId, String name, String description, int remaining, double size, double price, double rating, String picture, ProductCategory category) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.remaining = remaining;
        this.size = size;
        this.price = price;
        this.rating = rating;
        if (picture == null){
            this.picture = DEFAULT_PICTURE_PATH;
        } else {
            this.picture = picture;
        }
        this.category = category;
    }



    public int getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getRemaining() {
        return remaining;
    }

    public double getSize() {
        return size;
    }

    public double getPrice() {
        return price;
    }

    public double getRating() {
        return rating;
    }

    public String getPicture() {
        return picture;
    }



    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }


    @Override
    public String toString() {
        return "Product{" +
          "productId=" + productId +
          ", name='" + name + '\'' +
          ", description='" + description + '\'' +
          ", remaining=" + remaining +
          ", size=" + size +
          ", price=" + price +
          ", rating=" + rating +
          ", picture='" + picture + '\'' +
          ", category='" + category + '\'' +
          ", reviews=" + reviews +
          '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productId == product.productId &&
          remaining == product.remaining &&
          Double.compare(product.size, size) == 0 &&
          Double.compare(product.price, price) == 0 &&
          Double.compare(product.rating, rating) == 0 &&
          Objects.equals(name, product.name) &&
          Objects.equals(description, product.description) &&
          Objects.equals(picture, product.picture) &&
          category == product.category &&
          Objects.equals(reviews, product.reviews);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, name, description, remaining, size, price, rating, picture, category);
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(SortedSet<Review> reviews) {
        this.reviews = reviews;
    }
}
