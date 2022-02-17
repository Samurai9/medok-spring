package ru.kpfu.itis.nasibullin.medokspring.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;

@Entity
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    @ManyToMany(cascade = {
      CascadeType.DETACH,
      CascadeType.REFRESH,
      CascadeType.PERSIST
    })
    private List<Product> products;

    public Basket() {
        products = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Basket addProduct(Product product) {
        products.add(product);
        return this;
    }

    public Basket deleteProduct(int productId) {
        Product product = null;
        for (Product tempProduct: products) {
            if (tempProduct.getProductId() == productId) {
                product = tempProduct;
                break;
            }
        }
        if (product != null) {
            products.remove(product);
        }
        return this;
    }


    @Override
    public String toString() {
        return "Basket{" +
          "id=" + id +
          ", user=" + user.getUsername() +
          ", products=" + products +
          '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Basket basket = (Basket) o;
        return id == basket.id &&
          Objects.equals(user, basket.user) &&
          Objects.equals(products, basket.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, products);
    }
}
