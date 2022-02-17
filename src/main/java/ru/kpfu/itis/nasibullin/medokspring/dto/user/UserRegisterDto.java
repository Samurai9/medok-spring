package ru.kpfu.itis.nasibullin.medokspring.dto.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import ru.kpfu.itis.nasibullin.medokspring.entities.User;
import ru.kpfu.itis.nasibullin.medokspring.entities.UserRole;
import ru.kpfu.itis.nasibullin.medokspring.validation.password.PasswordsValidation;

@PasswordsValidation(
  password = "password",
  repeatedPassword = "repeat_password"
)
public class UserRegisterDto {
    @NotNull(message = "Вы ввели пустое имя")
    @Size(min = 2, max = 15, message = "Имя может содержать от 2 до 15 символов")
    private String name;
    @Email(message = "Вы ввели неверный почтовый адресс")
    private String email;
    @Size(min = 8, message = "Пароль слишком короткий")
    private String password;
    private String repeat_password;
    @NotNull(message = "Вы не согласились с правилами")
    private String licence_agreement;
    private String profilePicturePath;

    public UserRegisterDto() {
    }

    @Override
    public String toString() {
        return "UserRegisterDto{" +
          "name='" + name + '\'' +
          ", email='" + email + '\'' +
          ", password='" + password + '\'' +
          ", repeat_password='" + repeat_password + '\'' +
          ", licence_agreement='" + licence_agreement + '\'' +
          ", profilePicturePath='" + profilePicturePath + '\'' +
          '}';
    }

    public User toUser() {
        User user = new User();
        user.setEmail(email);

        user.setPassword(password);
        user.setName(name);
        user.setDiscount(50);
        user.setRole(UserRole.USER);
        user.setProfilePicturePath(profilePicturePath);
        return user;
    }

    public String getProfilePicturePath() {
        return profilePicturePath;
    }

    public void setProfilePicturePath(String profilePicturePath) {
        this.profilePicturePath = profilePicturePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeat_password() {
        return repeat_password;
    }

    public void setRepeat_password(String repeat_password) {
        this.repeat_password = repeat_password;
    }

    public String getLicence_agreement() {
        return licence_agreement;
    }

    public void setLicence_agreement(String licence_agreement) {
        this.licence_agreement = licence_agreement;
    }
}
