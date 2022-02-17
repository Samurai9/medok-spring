package ru.kpfu.itis.nasibullin.medokspring.entities;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import javax.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    private double discount;
    @Enumerated(value = EnumType.STRING)
    private UserRole role;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH})
    private Basket basket;

    private String profilePicturePath;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User() {
    }

    public User(int userId, String name, String email, String password, int discount) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public double getDiscount() {
        return discount;
    }

    public int getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }


    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

//    public Set<Review> getReviews() {
//        return reviews;
//    }
//
//    public void setReviews(Set<Review> reviews) {
//        this.reviews = reviews;
//    }

    public String getProfilePicturePath() {
        return profilePicturePath;
    }

    public void setProfilePicturePath(String profilePicturePath) {
        this.profilePicturePath = profilePicturePath;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.toString());
        return Collections.singleton(authority);
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "User{" +
          "userId=" + userId +
          ", name='" + name + '\'' +
          ", email='" + email + '\'' +
          ", password='" + password + '\'' +
          ", discount=" + discount +
          ", role=" + role +
          ", basket=" + basket +
          ", profilePicturePath='" + profilePicturePath + '\'' +
          '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId &&
          Double.compare(user.discount, discount) == 0 &&
          Objects.equals(name, user.name) &&
          Objects.equals(email, user.email) &&
          Objects.equals(password, user.password) &&
          role == user.role &&
          Objects.equals(profilePicturePath, user.profilePicturePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, name, email, password, discount, role, profilePicturePath);
    }
}
