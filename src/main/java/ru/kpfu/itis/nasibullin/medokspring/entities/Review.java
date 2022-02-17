package ru.kpfu.itis.nasibullin.medokspring.entities;


import java.util.Date;
import java.util.Objects;
import javax.persistence.*;

@Entity
public class Review implements Comparable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String text;
    @ManyToOne (cascade = CascadeType.REMOVE)
    private User author;
    @ManyToOne (cascade = CascadeType.REMOVE)
    private Product product;
    private Date createdAt;
    private double rating;

    public Review() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public int compareTo(Object o) {
        Review review = (Review) o;
        return ((Review) o).getCreatedAt().compareTo(this.getCreatedAt());
    }

    @Override
    public String toString() {
        return "Review{" +
          "id=" + id +
          ", text='" + text + '\'' +
          ", author=" + author.getUsername() +
          ", product=" + product.getName() +
          ", createdAt=" + createdAt +
          ", rating=" + rating +
          '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return id == review.id &&
          Double.compare(review.rating, rating) == 0 &&
          Objects.equals(text, review.text) &&
          Objects.equals(createdAt, review.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, createdAt, rating);
    }
}
