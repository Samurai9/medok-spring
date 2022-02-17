package ru.kpfu.itis.nasibullin.medokspring.dto;

import ru.kpfu.itis.nasibullin.medokspring.entities.User;

public class BasketAction {
    private String method;
    private int product_id;
    private User user;

    @Override
    public String toString() {
        return "BasketAction{" +
          "method='" + method + '\'' +
          ", product_id=" + product_id +
          ", user=" + user +
          '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public int getProductId() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }
}
