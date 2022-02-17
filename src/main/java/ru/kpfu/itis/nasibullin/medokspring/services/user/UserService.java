package ru.kpfu.itis.nasibullin.medokspring.services.user;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kpfu.itis.nasibullin.medokspring.dto.user.UserEditDto;
import ru.kpfu.itis.nasibullin.medokspring.entities.User;

public interface UserService extends UserDetailsService {
    User findById(int id);
    User save(User user);
    void delete(User user);

    User authWithGithub(String code);

    User editProfile(User user, UserEditDto dto);
}
