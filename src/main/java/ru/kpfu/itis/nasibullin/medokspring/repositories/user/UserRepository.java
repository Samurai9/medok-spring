package ru.kpfu.itis.nasibullin.medokspring.repositories.user;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.nasibullin.medokspring.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}
