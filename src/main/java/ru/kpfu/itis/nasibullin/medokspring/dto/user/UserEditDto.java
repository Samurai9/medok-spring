package ru.kpfu.itis.nasibullin.medokspring.dto.user;

public class UserEditDto {
    private EditAction action;

    private String name;
    private String email;
    private String password;

    private String new_password;
    private String repeated_password;

    private String delete_agreement;

    public UserEditDto() {
    }


    public EditAction getAction() {
        return action;
    }

    public void setAction(EditAction action) {
        this.action = action;
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

    public String getNewPassword() {
        return new_password;
    }

    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }

    public String getRepeatedPassword() {
        return repeated_password;
    }

    public void setRepeated_password(String repeated_password) {
        this.repeated_password = repeated_password;
    }

    public String getAgreement() {
        return delete_agreement;
    }

    public void setDelete_agreement(String agreement) {
        this.delete_agreement = agreement;
    }

    @Override
    public String toString() {
        return "UserEditDto{" +
          "action=" + action +
          ", name='" + name + '\'' +
          ", email='" + email + '\'' +
          ", password='" + password + '\'' +
          ", new_password='" + new_password + '\'' +
          ", repeated_password='" + repeated_password + '\'' +
          ", agreement='" + delete_agreement + '\'' +
          '}';
    }
}
