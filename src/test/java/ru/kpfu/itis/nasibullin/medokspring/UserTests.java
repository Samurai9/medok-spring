package ru.kpfu.itis.nasibullin.medokspring;

import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.kpfu.itis.nasibullin.medokspring.dto.user.EditAction;
import ru.kpfu.itis.nasibullin.medokspring.dto.user.UserEditDto;
import ru.kpfu.itis.nasibullin.medokspring.entities.User;
import ru.kpfu.itis.nasibullin.medokspring.services.user.UserService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@AutoConfigureEmbeddedDatabase(beanName = "dataSource", refresh = AutoConfigureEmbeddedDatabase.RefreshMode.AFTER_CLASS)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserTests {
    @Autowired
    private UserService userService;

    @Test
    @Order(1)
    void testUserNotExist() {
        assertThatThrownBy(() -> userService.findById(1)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @Order(2)
    void testUserCreate() {
        User testUser = new User("Test","email@mail.ru", "123123123");
        User savedUser = userService.save(testUser);

        assertThat(testUser.getUserId()).isNotNull();
        assertThat(testUser.getUsername()).isEqualTo(savedUser.getUsername());
        assertThat(testUser.getEmail()).isEqualTo(savedUser.getEmail());
    }

    @Test
    @Order(3)
    void testUserGet() {
        assertThat(userService.findById(1)).isNotNull();
    }

    @Test
    @Order(4)
    void testUserEdit() {
        User testUser = userService.findById(1);

        String newEmail = "aaa@yandex.ru";
        String newName = "AAAAAAAAAAAA";

        testUser = userService.save(testUser);
        UserEditDto userEditDto = new UserEditDto();
        userEditDto.setEmail(newEmail);
        userEditDto.setName(newName);
        userEditDto.setAction(EditAction.CHANGE_INF);
        testUser = userService.editProfile(testUser, userEditDto);

        assertThat(testUser.getName()).isEqualTo(newName);
        assertThat(testUser.getEmail()).isEqualTo(newEmail);
    }

    @Test
    @Order(5)
    void testUserDelete() {
        User testUser = userService.findById(1);
        testUser = userService.save(testUser);
        userService.delete(testUser);
        User finalTestUser = testUser;
        assertThatThrownBy(() -> userService.findById(finalTestUser.getUserId())).isInstanceOf(IllegalArgumentException.class);
    }
}
